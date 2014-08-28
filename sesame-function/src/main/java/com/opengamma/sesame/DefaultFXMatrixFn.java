/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.forex.method.FXMatrix;
import com.opengamma.core.config.ConfigSource;
import com.opengamma.core.config.impl.ConfigItem;
import com.opengamma.core.convention.ConventionSource;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.analytics.curve.AbstractCurveDefinition;
import com.opengamma.financial.analytics.curve.CurveConstructionConfiguration;
import com.opengamma.financial.analytics.curve.CurveDefinition;
import com.opengamma.financial.analytics.curve.CurveGroupConfiguration;
import com.opengamma.financial.analytics.curve.CurveNodeCurrencyVisitor;
import com.opengamma.financial.analytics.curve.CurveTypeConfiguration;
import com.opengamma.financial.analytics.ircurve.strips.CurveNode;
import com.opengamma.financial.currency.CurrencyPair;
import com.opengamma.id.VersionCorrection;
import com.opengamma.sesame.component.CurrencyPairSet;
import com.opengamma.sesame.marketdata.MarketDataFn;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.money.Currency;
import com.opengamma.util.result.FailureStatus;
import com.opengamma.util.result.Result;
import com.opengamma.util.result.ResultStatus;
import com.opengamma.util.result.SuccessStatus;

/**
 * Function implementation that provides a FX matrix.
 */
public class DefaultFXMatrixFn implements FXMatrixFn {

  /**
   * The convention source.
   */
  private final ConventionSource _conventionSource;
  /**
   * The security source.
   */
  private final SecuritySource _securitySource;
  /**
   * The market data function.
   */
  private final MarketDataFn _marketDataFn;
  /**
   * The config source.
   */
  private final ConfigSource _configSource;

  public DefaultFXMatrixFn(ConfigSource configSource,
                           ConventionSource conventionSource,
                           SecuritySource securitySource,
                           MarketDataFn marketDataFn) {
    _configSource = ArgumentChecker.notNull(configSource, "configSource");
    _conventionSource = ArgumentChecker.notNull(conventionSource, "conventionSource");
    _securitySource = ArgumentChecker.notNull(securitySource, "securitySource");
    _marketDataFn = ArgumentChecker.notNull(marketDataFn, "marketDataProviderFunction");
  }

  //-------------------------------------------------------------------------
  private Set<Currency> extractCurrencies(CurveConstructionConfiguration configuration,
                                          CurveNodeCurrencyVisitor curveNodeCurrencyVisitor) {

    final Set<Currency> currencies = new TreeSet<>();

    for (final CurveGroupConfiguration group : configuration.getCurveGroups()) {

      for (final Map.Entry<String, List<? extends CurveTypeConfiguration>> entry : group.getTypesForCurves().entrySet()) {

        final String curveName = entry.getKey();
        final AbstractCurveDefinition curveDefinition = findCurveDefinition(curveName);

        if (curveDefinition == null) {
          throw new OpenGammaRuntimeException("Could not get curve definition called " + curveName);
        }
        if (curveDefinition instanceof CurveDefinition) {
          for (final CurveNode node : ((CurveDefinition) curveDefinition).getNodes()) {
            currencies.addAll(node.accept(curveNodeCurrencyVisitor));
          }
        } else {
          return Collections.emptySet();
        }
      }
    }
    final List<String> exogenousConfigurations = configuration.getExogenousConfigurations();

    if (exogenousConfigurations != null) {
      for (final String name : exogenousConfigurations) {

        final CurveConstructionConfiguration exogenousConfiguration =
            _configSource.getLatestByName(CurveConstructionConfiguration.class, name);
        currencies.addAll(extractCurrencies(exogenousConfiguration, curveNodeCurrencyVisitor));
      }
    }
    return currencies;
  }

  private AbstractCurveDefinition findCurveDefinition(String curveName) {

    final Collection<ConfigItem<Object>> items =
        _configSource.get(Object.class, curveName, VersionCorrection.LATEST);

    for (ConfigItem<Object> item : items) {
      final Object value = item.getValue();
      if (value instanceof AbstractCurveDefinition) {
        return (AbstractCurveDefinition) value;
      }
    }
    return null;
  }

  @Override
  public Result<FXMatrix> getFXMatrix(Environment env, CurveConstructionConfiguration configuration) {
    // todo - should this actually be another function or set of functions
    final Set<Currency> currencies = extractCurrencies(configuration, new CurveNodeCurrencyVisitor(_conventionSource, _securitySource));
    return buildResult(env, currencies, true);
  }

  @Override
  public Result<FXMatrix> getFXMatrix(Environment env, Set<Currency> currencies) {
    return buildResult(env, currencies, true);
  }

  @Override
  public Result<Map<Currency, FXMatrix>> getAvailableFxRates(Environment env, CurrencyPairSet currencyPairs) {
    final Map<Currency, FXMatrix> fxMatrices = new HashMap<Currency, FXMatrix>();
    for (CurrencyPair currencyPair : currencyPairs.getCurrencyPairs()) {
      if (!fxMatrices.containsKey(currencyPair.getBase())) {
        fxMatrices.put(currencyPair.getBase(), new FXMatrix(currencyPair.getBase()));
      }
      addFxRate(env, currencyPair, fxMatrices.get(currencyPair.getBase()));
    }
    return Result.success(fxMatrices);
  }

  private Result<FXMatrix> buildResult(Environment env, Set<Currency> currencies, boolean failOnMissingConversion) {
    final FXMatrix matrix = new FXMatrix();
    Currency refCurr = null;

    for (Currency currency : currencies) {
      // Use the first currency in the set as the reference currency in the matrix
      if (refCurr == null) {
        refCurr = currency;
      } else {
        //note - currency matrix will ensure the spotRate returned is interpreted correctly,
        //depending on the order base and counter are specified in.
        CurrencyPair currencyPair = CurrencyPair.of(refCurr, currency);
        
        ResultStatus resultStatus = addFxRate(env, currencyPair, matrix);
        if (resultStatus != SuccessStatus.SUCCESS && failOnMissingConversion) {
          return Result.failure((FailureStatus) resultStatus, 
              new OpenGammaRuntimeException("Missing fx rate for " + currencyPair.toString()));
        }
      }
    }
    
    if (currencies.size() >= 2 && matrix.getCurrencies().isEmpty()) {
      return Result.failure(FailureStatus.MISSING_DATA, new OpenGammaRuntimeException("Could not find any fx rates"));
    } else {  
      return Result.success(matrix);
    }
  }

  private ResultStatus addFxRate(Environment env, CurrencyPair currencyPair, FXMatrix outputMatrix) {
    Result<Double> marketDataResult = _marketDataFn.getFxRate(env, currencyPair);

    if (marketDataResult.isSuccess()) {
      outputMatrix.addCurrency(currencyPair.getCounter(), currencyPair.getBase(), marketDataResult.getValue());
      return SuccessStatus.SUCCESS;
    } else {
      return FailureStatus.MISSING_DATA;
    }
  }

}
