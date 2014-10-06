/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.server;

import java.util.Iterator;
import java.util.List;

import org.threeten.bp.Duration;

import com.opengamma.engine.marketdata.spec.MarketDataSpecification;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.VersionCorrection;
import com.opengamma.sesame.config.FunctionArguments;
import com.opengamma.sesame.engine.CycleArguments;
import com.opengamma.sesame.engine.Results;
import com.opengamma.sesame.engine.View;
import com.opengamma.sesame.marketdata.CycleMarketDataFactory;
import com.opengamma.sesame.marketdata.FieldName;
import com.opengamma.sesame.marketdata.MarketDataFactory;
import com.opengamma.sesame.marketdata.StrategyAwareMarketDataSource;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.result.Result;
import com.opengamma.util.tuple.Pair;
import com.opengamma.util.tuple.Pairs;

/**
 * Responsible for running a cycles of the engine.
 * <p>
 * This involves checking the source of market data and ensuring that
 * all required data is retrieved. To achieve this for data sources which
 * do not eagerly retrieve data (e.g. most live data providers), a cycle may
 * be run multiple times until all its market data requirements are fulfilled.
 */
public class CycleRunner {

  /**
   * A market data source used for setting up cycles.
   * It is not expected to be used to access market data but merely
   * provides a suitable initial value.
   */
  public static final StrategyAwareMarketDataSource INITIAL_MARKET_DATA_SOURCE = new InitialMarketDataSource();

  /**
   * The view to be executed, not null.
   */
  private final View _view;
  /**
   * The manager of the market data sources, not null.
   */
  private final MarketDataFactory _marketDataFactory;
  /**
   * The cycle options determining how the cycles of the view
   * should be executed, not null.
   */
  private final CycleOptions _cycleOptions;
  /**
   * The trades/securities to execute the cycles with, not null
   * but may be empty.
   */
  private final List<?> _inputs;
  /**
   * Handles the results produced by each cycle of the engine, not null.
   */
  private final CycleResultsHandler _handler;
  /**
   * Determines whether the execution of the cycles should be terminated, not null.
   * This is generally used when an infinite set of cycles has been requested
   * and we want to stop processing (e.g. a UI using streaming data, which the
   * user then decides they have finished with).
   */
  private final CycleTerminator _cycleTerminator;
  /**
   * Minimum time period between cycles when streaming, not null. If a series of
   * cycles are to be run and the first starts at time <code>t</code>, then the next cycle
   * will not be started until at least time <code>t + minimumTimeBetweenCycles</code>.
   * This prevents the server "spinning" for cases where the cycles execute quickly
   * as the underlying data has not changed since the last run.
   */
  private final Duration _minimumTimeBetweenCycles;

  /**
   * Creates the new cycle runner. Intended for views where throttling is
   * required such that there is a minimum period between each cycle.
   *
   * @param view  the view to be executed, not null
   * @param marketDataFactory  the factory for market data sources, not null
   * @param cycleOptions  the cycle options determining how the cycles of the
   * view should be executed, not null
   * @param inputs  the trades/securities to execute the cycles with,
   *   not null but may be empty
   * @param handler  handler for the results produced by each cycle of the
   *   engine, not null
   * @param cycleTerminator  determines whether the execution of the cycles
   *   should be terminated, not null
   * @param minimumTimeBetweenCycles  minimum duration between cycles, not null
   */
  public CycleRunner(View view,
                     MarketDataFactory marketDataFactory,
                     CycleOptions cycleOptions,
                     List<?> inputs,
                     CycleResultsHandler handler,
                     CycleTerminator cycleTerminator,
                     Duration minimumTimeBetweenCycles) {

    _view = ArgumentChecker.notNull(view, "view");
    _marketDataFactory = ArgumentChecker.notNull(marketDataFactory, "marketDataFactory");
    _cycleOptions = ArgumentChecker.notNull(cycleOptions, "cycleOptions");
    _inputs = ArgumentChecker.notNull(inputs, "inputs");
    _handler = ArgumentChecker.notNull(handler, "handler");
    _cycleTerminator = ArgumentChecker.notNull(cycleTerminator, "cycleTerminator");
    _minimumTimeBetweenCycles = ArgumentChecker.notNull(minimumTimeBetweenCycles, "minimumTimeBetweenCycles");
  }

  /**
   * Creates the new cycle runner. Intended for views where the results
   * for each cycle are wanted as fast as possible.
   *
   * @param view  the view to be executed, not null
   * @param marketDataFactory  the factory for market data sources, not null
   * @param cycleOptions  the cycle options determining how the cycles of the
   * view should be executed, not null
   * @param inputs  the trades/securities to execute the cycles with,
   *   not null but may be empty
   * @param handler  handler for the results produced by each cycle of the
   *   engine, not null
   * @param cycleTerminator  determines whether the execution of the cycles
   *   should be terminated, not null
   */
  public CycleRunner(View view,
                     MarketDataFactory marketDataFactory,
                     CycleOptions cycleOptions,
                     List<?> inputs,
                     CycleResultsHandler handler,
                     CycleTerminator cycleTerminator) {
    this(view, marketDataFactory, cycleOptions, inputs, handler, cycleTerminator, Duration.ZERO);
  }

  //-------------------------------------------------------------------------
  /**
   * Execute the view with each of the cycle options, checking if
   * early termination is required. We keep track of the market data
   * source used as this may be used to help setup the next cycle.
   */
  public void execute() {

    // We keep track of the market data being used so that when required we can
    // track the changes in market data between cycles
    CycleMarketDataFactory cycleMarketDataFactory =
        new DefaultCycleMarketDataFactory(_marketDataFactory, INITIAL_MARKET_DATA_SOURCE);

    // Iterate over the cycle options. As they may be infinite (e.g. streaming),
    // we need to check the terminator to see if external events mean we should stop.
    final Iterator<IndividualCycleOptions> cycleOptions = _cycleOptions.iterator();

    while (_cycleTerminator.shouldContinue() && cycleOptions.hasNext()) {

      long cycleStart = System.currentTimeMillis();

      Pair<Results, CycleMarketDataFactory> result =
          cycleUntilResultsAvailable(cycleOptions.next(), cycleMarketDataFactory);
      _handler.handleResults(result.getFirst());
      cycleMarketDataFactory = result.getSecond();

      if (!_minimumTimeBetweenCycles.isZero()) {

        long delay = cycleStart + _minimumTimeBetweenCycles.toMillis() - System.currentTimeMillis();
        if (delay > 0) {
          try {
            Thread.sleep(delay);
          } catch (InterruptedException e) {
            // Thread was interrupted, just ignore and try to keep going
          }
        }
      }
    }
  }

  private Pair<Results, CycleMarketDataFactory> cycleUntilResultsAvailable(
      IndividualCycleOptions cycleOptions, CycleMarketDataFactory previousFactory) {

    // We first run a cycle, then check it to see if any market data is
    // pending. Where we are using a non-lazy data source (i.e. not
    // live data), then we will not need to do anything more and can just
    // return the results we have

    CycleMarketDataFactory factory =
        createPrimedCycleMarketDataFactory(previousFactory, cycleOptions.getMarketDataSpec());

    Results result = executeCycle(cycleOptions, factory);

    while (result.isPendingMarketData()) {

      // If there is market data pending then we ask for a market data
      // source primed with the missing results and retry. It is possible
      // that the subsequent run then wants additional market market data
      // so we keep repeating until no data is pending.
      factory = factory.withPrimedMarketDataSource();
      result = executeCycle(cycleOptions, factory);
    }

    return Pairs.of(result, factory);
  }

  private CycleMarketDataFactory createPrimedCycleMarketDataFactory(CycleMarketDataFactory previousFactory,
                                                                    MarketDataSpecification marketDataSpecification) {

    // TODO - this cast suggests a design problem - get rid of it
    StrategyAwareMarketDataSource previousSource =
        (StrategyAwareMarketDataSource) previousFactory.getPrimaryMarketDataSource();
    if (previousSource.isCompatible(marketDataSpecification)) {
      return previousFactory.withPrimedMarketDataSource();
    } else {
      previousSource.dispose();
      // This is a new source, so it doesn't need to be primed (that
      // will happen automatically on the next cycle of appropriate)
      return previousFactory.withMarketDataSpecification(marketDataSpecification);
    }
  }

  private Results executeCycle(IndividualCycleOptions cycleOptions,
                               CycleMarketDataFactory cycleMarketDataFactory) {
    CycleArguments cycleArguments = createCycleArguments(cycleOptions, cycleMarketDataFactory);
    return _view.run(cycleArguments, _inputs);
  }

  private CycleArguments createCycleArguments(IndividualCycleOptions cycleOptions,
                                              CycleMarketDataFactory cycleMarketDataFactory) {

    // todo - we may want a method whereby we can get the delta of data that has changed since the previous cycle
    // todo - pass the delta in through the cycle arguments
    // todo - version correction should be coming from somewhere - cycle options?

    // todo - these need real values
    FunctionArguments functionArguments = FunctionArguments.EMPTY;
    VersionCorrection configVersionCorrection = VersionCorrection.LATEST;

    return new CycleArguments(cycleOptions.getValuationTime(),
                              configVersionCorrection,
                              cycleMarketDataFactory,
                              functionArguments,
                              cycleOptions.isCaptureInputs());
  }

  /**
   * A market data source used for setting up engine cycles. It is not
   * expected to be used to access market data but merely provides a
   * suitable initial value.
   */
  private static class InitialMarketDataSource implements StrategyAwareMarketDataSource {

    @Override
    public Result<?> get(ExternalIdBundle id, FieldName fieldName) {
      throw new UnsupportedOperationException("get not supported");
    }

    @Override
    public StrategyAwareMarketDataSource createPrimedSource() {
      throw new UnsupportedOperationException("should never be called");
    }

    @Override
    public boolean isCompatible(MarketDataSpecification specification) {
      // Always false - want a real source on the next cycle
      return false;
    }

    @Override
    public void dispose() {
    }
  }

}
