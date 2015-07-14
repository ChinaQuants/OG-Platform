/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableList;
import com.opengamma.sesame.config.ViewConfig;
import com.opengamma.sesame.marketdata.MarketDataEnvironment;
import com.opengamma.sesame.marketdata.scenarios.ScenarioDefinition;

/**
 * Encapsulates the arguments to
 * {@link ViewRunner#runScenarios(ViewConfig, CalculationArguments, MarketDataEnvironment, ScenarioDefinition, List)}
 * so it can be invoked via REST.
 */
@BeanDefinition
public final class RunScenariosArguments implements ImmutableBean {

  /** Configuration of the view defining the calculations. */
  @PropertyDefinition(validate = "notNull")
  private final ViewConfig _viewConfig;

  /** Arguments used when performing the calculations for the scenarios. */
  @PropertyDefinition(validate = "notNull")
  private final CalculationArguments _calculationArguments;

  /**
   * The base market data used to derive the data for each scenario, one set for each scenario. The market data
   * for each scenario is derived from the base market data by applying perturbations from the scenario definition.
   */
  @PropertyDefinition(validate = "notNull")
  private final MarketDataEnvironment _marketData;

  /** Defines how market data for each scenario is derived from the base market data. */
  @PropertyDefinition(validate = "notNull")
  private final ScenarioDefinition _scenarioDefinition;

  /** Items in the portfolio (normally trades) which are the inputs to the calculations. */
  @PropertyDefinition(validate = "notNull")
  private final List<Object> _portfolio;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code RunScenariosArguments}.
   * @return the meta-bean, not null
   */
  public static RunScenariosArguments.Meta meta() {
    return RunScenariosArguments.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(RunScenariosArguments.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static RunScenariosArguments.Builder builder() {
    return new RunScenariosArguments.Builder();
  }

  private RunScenariosArguments(
      ViewConfig viewConfig,
      CalculationArguments calculationArguments,
      MarketDataEnvironment marketData,
      ScenarioDefinition scenarioDefinition,
      List<Object> portfolio) {
    JodaBeanUtils.notNull(viewConfig, "viewConfig");
    JodaBeanUtils.notNull(calculationArguments, "calculationArguments");
    JodaBeanUtils.notNull(marketData, "marketData");
    JodaBeanUtils.notNull(scenarioDefinition, "scenarioDefinition");
    JodaBeanUtils.notNull(portfolio, "portfolio");
    this._viewConfig = viewConfig;
    this._calculationArguments = calculationArguments;
    this._marketData = marketData;
    this._scenarioDefinition = scenarioDefinition;
    this._portfolio = ImmutableList.copyOf(portfolio);
  }

  @Override
  public RunScenariosArguments.Meta metaBean() {
    return RunScenariosArguments.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets configuration of the view defining the calculations.
   * @return the value of the property, not null
   */
  public ViewConfig getViewConfig() {
    return _viewConfig;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets arguments used when performing the calculations for the scenarios.
   * @return the value of the property, not null
   */
  public CalculationArguments getCalculationArguments() {
    return _calculationArguments;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the base market data used to derive the data for each scenario, one set for each scenario. The market data
   * for each scenario is derived from the base market data by applying perturbations from the scenario definition.
   * @return the value of the property, not null
   */
  public MarketDataEnvironment getMarketData() {
    return _marketData;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets defines how market data for each scenario is derived from the base market data.
   * @return the value of the property, not null
   */
  public ScenarioDefinition getScenarioDefinition() {
    return _scenarioDefinition;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets items in the portfolio (normally trades) which are the inputs to the calculations.
   * @return the value of the property, not null
   */
  public List<Object> getPortfolio() {
    return _portfolio;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      RunScenariosArguments other = (RunScenariosArguments) obj;
      return JodaBeanUtils.equal(getViewConfig(), other.getViewConfig()) &&
          JodaBeanUtils.equal(getCalculationArguments(), other.getCalculationArguments()) &&
          JodaBeanUtils.equal(getMarketData(), other.getMarketData()) &&
          JodaBeanUtils.equal(getScenarioDefinition(), other.getScenarioDefinition()) &&
          JodaBeanUtils.equal(getPortfolio(), other.getPortfolio());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getViewConfig());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCalculationArguments());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMarketData());
    hash = hash * 31 + JodaBeanUtils.hashCode(getScenarioDefinition());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPortfolio());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("RunScenariosArguments{");
    buf.append("viewConfig").append('=').append(getViewConfig()).append(',').append(' ');
    buf.append("calculationArguments").append('=').append(getCalculationArguments()).append(',').append(' ');
    buf.append("marketData").append('=').append(getMarketData()).append(',').append(' ');
    buf.append("scenarioDefinition").append('=').append(getScenarioDefinition()).append(',').append(' ');
    buf.append("portfolio").append('=').append(JodaBeanUtils.toString(getPortfolio()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code RunScenariosArguments}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code viewConfig} property.
     */
    private final MetaProperty<ViewConfig> _viewConfig = DirectMetaProperty.ofImmutable(
        this, "viewConfig", RunScenariosArguments.class, ViewConfig.class);
    /**
     * The meta-property for the {@code calculationArguments} property.
     */
    private final MetaProperty<CalculationArguments> _calculationArguments = DirectMetaProperty.ofImmutable(
        this, "calculationArguments", RunScenariosArguments.class, CalculationArguments.class);
    /**
     * The meta-property for the {@code marketData} property.
     */
    private final MetaProperty<MarketDataEnvironment> _marketData = DirectMetaProperty.ofImmutable(
        this, "marketData", RunScenariosArguments.class, MarketDataEnvironment.class);
    /**
     * The meta-property for the {@code scenarioDefinition} property.
     */
    private final MetaProperty<ScenarioDefinition> _scenarioDefinition = DirectMetaProperty.ofImmutable(
        this, "scenarioDefinition", RunScenariosArguments.class, ScenarioDefinition.class);
    /**
     * The meta-property for the {@code portfolio} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<Object>> _portfolio = DirectMetaProperty.ofImmutable(
        this, "portfolio", RunScenariosArguments.class, (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "viewConfig",
        "calculationArguments",
        "marketData",
        "scenarioDefinition",
        "portfolio");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1970035271:  // viewConfig
          return _viewConfig;
        case -172471155:  // calculationArguments
          return _calculationArguments;
        case 1116764678:  // marketData
          return _marketData;
        case -690925309:  // scenarioDefinition
          return _scenarioDefinition;
        case 1121781064:  // portfolio
          return _portfolio;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public RunScenariosArguments.Builder builder() {
      return new RunScenariosArguments.Builder();
    }

    @Override
    public Class<? extends RunScenariosArguments> beanType() {
      return RunScenariosArguments.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code viewConfig} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ViewConfig> viewConfig() {
      return _viewConfig;
    }

    /**
     * The meta-property for the {@code calculationArguments} property.
     * @return the meta-property, not null
     */
    public MetaProperty<CalculationArguments> calculationArguments() {
      return _calculationArguments;
    }

    /**
     * The meta-property for the {@code marketData} property.
     * @return the meta-property, not null
     */
    public MetaProperty<MarketDataEnvironment> marketData() {
      return _marketData;
    }

    /**
     * The meta-property for the {@code scenarioDefinition} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ScenarioDefinition> scenarioDefinition() {
      return _scenarioDefinition;
    }

    /**
     * The meta-property for the {@code portfolio} property.
     * @return the meta-property, not null
     */
    public MetaProperty<List<Object>> portfolio() {
      return _portfolio;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1970035271:  // viewConfig
          return ((RunScenariosArguments) bean).getViewConfig();
        case -172471155:  // calculationArguments
          return ((RunScenariosArguments) bean).getCalculationArguments();
        case 1116764678:  // marketData
          return ((RunScenariosArguments) bean).getMarketData();
        case -690925309:  // scenarioDefinition
          return ((RunScenariosArguments) bean).getScenarioDefinition();
        case 1121781064:  // portfolio
          return ((RunScenariosArguments) bean).getPortfolio();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code RunScenariosArguments}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<RunScenariosArguments> {

    private ViewConfig _viewConfig;
    private CalculationArguments _calculationArguments;
    private MarketDataEnvironment _marketData;
    private ScenarioDefinition _scenarioDefinition;
    private List<Object> _portfolio = new ArrayList<Object>();

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(RunScenariosArguments beanToCopy) {
      this._viewConfig = beanToCopy.getViewConfig();
      this._calculationArguments = beanToCopy.getCalculationArguments();
      this._marketData = beanToCopy.getMarketData();
      this._scenarioDefinition = beanToCopy.getScenarioDefinition();
      this._portfolio = new ArrayList<Object>(beanToCopy.getPortfolio());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1970035271:  // viewConfig
          return _viewConfig;
        case -172471155:  // calculationArguments
          return _calculationArguments;
        case 1116764678:  // marketData
          return _marketData;
        case -690925309:  // scenarioDefinition
          return _scenarioDefinition;
        case 1121781064:  // portfolio
          return _portfolio;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 1970035271:  // viewConfig
          this._viewConfig = (ViewConfig) newValue;
          break;
        case -172471155:  // calculationArguments
          this._calculationArguments = (CalculationArguments) newValue;
          break;
        case 1116764678:  // marketData
          this._marketData = (MarketDataEnvironment) newValue;
          break;
        case -690925309:  // scenarioDefinition
          this._scenarioDefinition = (ScenarioDefinition) newValue;
          break;
        case 1121781064:  // portfolio
          this._portfolio = (List<Object>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public RunScenariosArguments build() {
      return new RunScenariosArguments(
          _viewConfig,
          _calculationArguments,
          _marketData,
          _scenarioDefinition,
          _portfolio);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code viewConfig} property in the builder.
     * @param viewConfig  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder viewConfig(ViewConfig viewConfig) {
      JodaBeanUtils.notNull(viewConfig, "viewConfig");
      this._viewConfig = viewConfig;
      return this;
    }

    /**
     * Sets the {@code calculationArguments} property in the builder.
     * @param calculationArguments  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder calculationArguments(CalculationArguments calculationArguments) {
      JodaBeanUtils.notNull(calculationArguments, "calculationArguments");
      this._calculationArguments = calculationArguments;
      return this;
    }

    /**
     * Sets the {@code marketData} property in the builder.
     * @param marketData  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder marketData(MarketDataEnvironment marketData) {
      JodaBeanUtils.notNull(marketData, "marketData");
      this._marketData = marketData;
      return this;
    }

    /**
     * Sets the {@code scenarioDefinition} property in the builder.
     * @param scenarioDefinition  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder scenarioDefinition(ScenarioDefinition scenarioDefinition) {
      JodaBeanUtils.notNull(scenarioDefinition, "scenarioDefinition");
      this._scenarioDefinition = scenarioDefinition;
      return this;
    }

    /**
     * Sets the {@code portfolio} property in the builder.
     * @param portfolio  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder portfolio(List<Object> portfolio) {
      JodaBeanUtils.notNull(portfolio, "portfolio");
      this._portfolio = portfolio;
      return this;
    }

    /**
     * Sets the {@code portfolio} property in the builder
     * from an array of objects.
     * @param portfolio  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder portfolio(Object... portfolio) {
      return portfolio(Arrays.asList(portfolio));
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("RunScenariosArguments.Builder{");
      buf.append("viewConfig").append('=').append(JodaBeanUtils.toString(_viewConfig)).append(',').append(' ');
      buf.append("calculationArguments").append('=').append(JodaBeanUtils.toString(_calculationArguments)).append(',').append(' ');
      buf.append("marketData").append('=').append(JodaBeanUtils.toString(_marketData)).append(',').append(' ');
      buf.append("scenarioDefinition").append('=').append(JodaBeanUtils.toString(_scenarioDefinition)).append(',').append(' ');
      buf.append("portfolio").append('=').append(JodaBeanUtils.toString(_portfolio));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
