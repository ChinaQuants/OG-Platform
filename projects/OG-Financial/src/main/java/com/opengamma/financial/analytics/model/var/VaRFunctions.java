/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.var;

import java.util.List;

import com.opengamma.analytics.financial.schedule.ScheduleCalculatorFactory;
import com.opengamma.analytics.financial.schedule.TimeSeriesSamplingFunctionFactory;
import com.opengamma.analytics.math.statistics.descriptive.StatisticsCalculatorFactory;
import com.opengamma.engine.function.config.AbstractRepositoryConfigurationBean;
import com.opengamma.engine.function.config.FunctionConfiguration;
import com.opengamma.engine.function.config.RepositoryConfigurationSource;
import com.opengamma.financial.property.DefaultPropertyFunction.PriorityClass;
import com.opengamma.util.ArgumentChecker;

/**
 * Function repository configuration source for the functions contained in this package and sub-packages.
 */
public class VaRFunctions extends AbstractRepositoryConfigurationBean {

  /**
   * Default instance of a repository configuration source exposing the functions from this package.
   *
   * @return the configuration source exposing functions from this package
   */
  public static RepositoryConfigurationSource instance() {
    return new VaRFunctions().getObjectCreating();
  }

  public static RepositoryConfigurationSource defaults() {
    final Defaults factory = new Defaults();
    factory.afterPropertiesSet();
    return factory.getObject();
  }

  public static RepositoryConfigurationSource defaults(final String samplingPeriodName, final String scheduleName, final String samplingCalculatorName, final String meanCalculatorName,
      final String stdDevCalculatorName, final double confidenceLevel, final double horizon) {
    final Defaults factory = new Defaults();
    factory.setSamplingPeriodName(samplingCalculatorName);
    factory.setScheduleName(scheduleName);
    factory.setSamplingCalculatorName(samplingCalculatorName);
    factory.setMeanCalculatorName(meanCalculatorName);
    factory.setStdDevCalculatorName(stdDevCalculatorName);
    factory.setConfidenceLevel(confidenceLevel);
    factory.setHorizonName(horizon);
    factory.afterPropertiesSet();
    return factory.getObject();
  }

  /**
   * Function repository configuration source for the default functions contained in this package.
   */
  public static class Defaults extends AbstractRepositoryConfigurationBean {

    private String _samplingPeriodName = "P2Y";
    private String _scheduleName = ScheduleCalculatorFactory.DAILY;
    private String _samplingCalculatorName = TimeSeriesSamplingFunctionFactory.PREVIOUS_AND_FIRST_VALUE_PADDING;
    private String _meanCalculatorName = StatisticsCalculatorFactory.MEAN;
    private String _stdDevCalculatorName = StatisticsCalculatorFactory.SAMPLE_STANDARD_DEVIATION;
    private double _confidenceLevel = 0.99;
    private double _horizon = 1d;

    public String getSamplingPeriodName() {
      return _samplingPeriodName;
    }

    public void setSamplingPeriodName(final String samplingPeriodName) {
      _samplingPeriodName = samplingPeriodName;
    }

    public String getScheduleName() {
      return _scheduleName;
    }

    public void setScheduleName(final String scheduleName) {
      _scheduleName = scheduleName;
    }

    public String getSamplingCalculatorName() {
      return _samplingCalculatorName;
    }

    public void setSamplingCalculatorName(final String samplingCalculatorName) {
      _samplingCalculatorName = samplingCalculatorName;
    }

    public String getMeanCalculatorName() {
      return _meanCalculatorName;
    }

    public void setMeanCalculatorName(final String meanCalculatorName) {
      _meanCalculatorName = meanCalculatorName;
    }

    public String getStdDevCalculatorName() {
      return _stdDevCalculatorName;
    }

    public void setStdDevCalculatorName(final String stdDevCalculatorName) {
      _stdDevCalculatorName = stdDevCalculatorName;
    }

    public double getConfidenceLevel() {
      return _confidenceLevel;
    }

    public void setConfidenceLevel(final double confidenceLevel) {
      _confidenceLevel = confidenceLevel;
    }

    public double getHorizon() {
      return _horizon;
    }

    public void setHorizonName(final double horizon) {
      _horizon = horizon;
    }

    @Override
    public void afterPropertiesSet() {
      ArgumentChecker.notNull(getSamplingPeriodName(), "samplingPeriodName");
      ArgumentChecker.notNull(getScheduleName(), "scheduleName");
      ArgumentChecker.notNull(getSamplingCalculatorName(), "samplingCalculatorName");
      ArgumentChecker.notNull(getMeanCalculatorName(), "meanCalculatorName");
      ArgumentChecker.notNull(getStdDevCalculatorName(), "stdDevCalculatorName");
      super.afterPropertiesSet();
    }

    @Override
    protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
      //functions.add(functionConfiguration(OptionPositionParametricVaRFunction.class, DEFAULT_CONFIG_NAME));
      //functions.add(functionConfiguration(OptionPortfolioParametricVaRFunction.class, DEFAULT_CONFIG_NAME, startDate, defaultReturnCalculatorName,
      //  defaultScheduleName, defaultSamplingCalculatorName, "0.99", "1", ValueRequirementNames.VALUE_DELTA));
      //functions.add(functionConfiguration(PositionValueGreekSensitivityPnLFunction.class, DEFAULT_CONFIG_NAME, startDate, defaultReturnCalculatorName,
      //  defaultScheduleName, defaultSamplingCalculatorName, ValueRequirementNames.VALUE_DELTA));
      functions.add(functionConfiguration(NormalHistoricalVaRDefaultPropertiesFunction.class, getSamplingPeriodName(), getScheduleName(), getSamplingCalculatorName(), getMeanCalculatorName(),
          getStdDevCalculatorName(), Double.toString(getConfidenceLevel()), Double.toString(getHorizon()), PriorityClass.NORMAL.name()));
    }

  }

  @Override
  protected void addAllConfigurations(final List<FunctionConfiguration> functions) {
    functions.add(functionConfiguration(EmpiricalHistoricalConditionalVaRFunction.class));
    functions.add(functionConfiguration(EmpiricalHistoricalVaRFunction.class));
    functions.add(functionConfiguration(NormalHistoricalVaRFunction.class));
  }

}
