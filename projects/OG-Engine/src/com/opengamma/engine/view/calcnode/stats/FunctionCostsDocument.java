/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.engine.view.calcnode.stats;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.time.Instant;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.BasicMetaBean;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.util.ArgumentChecker;

/**
 * Lightweight storage for function costs.
 */
@BeanDefinition
public class FunctionCostsDocument extends DirectBean {

  /**
   * The configuration name.
   */
  @PropertyDefinition
  private String _configurationName;
  /**
   * The function id.
   */
  @PropertyDefinition
  private String _functionId;
  /**
   * The instant the these costs were stored.
   */
  @PropertyDefinition
  private Instant _version;
  /**
   * The cost estimate for the time the function takes.
   */
  @PropertyDefinition
  private double _invocationCost;
  /**
   * The cost estimate for the data input size.
   */
  @PropertyDefinition
  private double _dataInputCost;
  /**
   * The cost estimate for the data output size.
   */
  @PropertyDefinition
  private double _dataOutputCost;

  /**
   * Creates an instance.
   */
  public FunctionCostsDocument() {
  }

  /**
   * Creates an instance.
   * 
   * @param configurationName  the configuration name, not null
   * @param functionId  the function id, not null
   */
  public FunctionCostsDocument(final String configurationName, final String functionId) {
    ArgumentChecker.notNull(configurationName, "configurationName");
    ArgumentChecker.notNull(functionId, "functionId");
    setConfigurationName(configurationName);
    setFunctionId(functionId);
  }

  /**
   * Creates a clone of this document.
   * 
   * @return the clone, not null
   */
  public FunctionCostsDocument clone() {
    FunctionCostsDocument cloned = new FunctionCostsDocument();
    cloned.setConfigurationName(getConfigurationName());
    cloned.setFunctionId(getFunctionId());
    cloned.setVersion(getVersion());
    cloned.setInvocationCost(getInvocationCost());
    cloned.setDataInputCost(getDataInputCost());
    cloned.setDataOutputCost(getDataOutputCost());
    return cloned;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FunctionCostsDocument}.
   * @return the meta-bean, not null
   */
  public static FunctionCostsDocument.Meta meta() {
    return FunctionCostsDocument.Meta.INSTANCE;
  }

  @Override
  public FunctionCostsDocument.Meta metaBean() {
    return FunctionCostsDocument.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case 302577825:  // configurationName
        return getConfigurationName();
      case -62789869:  // functionId
        return getFunctionId();
      case 351608024:  // version
        return getVersion();
      case 1773393021:  // invocationCost
        return getInvocationCost();
      case -98847187:  // dataInputCost
        return getDataInputCost();
      case -1404333128:  // dataOutputCost
        return getDataOutputCost();
    }
    return super.propertyGet(propertyName);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case 302577825:  // configurationName
        setConfigurationName((String) newValue);
        return;
      case -62789869:  // functionId
        setFunctionId((String) newValue);
        return;
      case 351608024:  // version
        setVersion((Instant) newValue);
        return;
      case 1773393021:  // invocationCost
        setInvocationCost((Double) newValue);
        return;
      case -98847187:  // dataInputCost
        setDataInputCost((Double) newValue);
        return;
      case -1404333128:  // dataOutputCost
        setDataOutputCost((Double) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the configuration name.
   * @return the value of the property
   */
  public String getConfigurationName() {
    return _configurationName;
  }

  /**
   * Sets the configuration name.
   * @param configurationName  the new value of the property
   */
  public void setConfigurationName(String configurationName) {
    this._configurationName = configurationName;
  }

  /**
   * Gets the the {@code configurationName} property.
   * @return the property, not null
   */
  public final Property<String> configurationName() {
    return metaBean().configurationName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the function id.
   * @return the value of the property
   */
  public String getFunctionId() {
    return _functionId;
  }

  /**
   * Sets the function id.
   * @param functionId  the new value of the property
   */
  public void setFunctionId(String functionId) {
    this._functionId = functionId;
  }

  /**
   * Gets the the {@code functionId} property.
   * @return the property, not null
   */
  public final Property<String> functionId() {
    return metaBean().functionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the instant the these costs were stored.
   * @return the value of the property
   */
  public Instant getVersion() {
    return _version;
  }

  /**
   * Sets the instant the these costs were stored.
   * @param version  the new value of the property
   */
  public void setVersion(Instant version) {
    this._version = version;
  }

  /**
   * Gets the the {@code version} property.
   * @return the property, not null
   */
  public final Property<Instant> version() {
    return metaBean().version().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cost estimate for the time the function takes.
   * @return the value of the property
   */
  public double getInvocationCost() {
    return _invocationCost;
  }

  /**
   * Sets the cost estimate for the time the function takes.
   * @param invocationCost  the new value of the property
   */
  public void setInvocationCost(double invocationCost) {
    this._invocationCost = invocationCost;
  }

  /**
   * Gets the the {@code invocationCost} property.
   * @return the property, not null
   */
  public final Property<Double> invocationCost() {
    return metaBean().invocationCost().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cost estimate for the data input size.
   * @return the value of the property
   */
  public double getDataInputCost() {
    return _dataInputCost;
  }

  /**
   * Sets the cost estimate for the data input size.
   * @param dataInputCost  the new value of the property
   */
  public void setDataInputCost(double dataInputCost) {
    this._dataInputCost = dataInputCost;
  }

  /**
   * Gets the the {@code dataInputCost} property.
   * @return the property, not null
   */
  public final Property<Double> dataInputCost() {
    return metaBean().dataInputCost().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cost estimate for the data output size.
   * @return the value of the property
   */
  public double getDataOutputCost() {
    return _dataOutputCost;
  }

  /**
   * Sets the cost estimate for the data output size.
   * @param dataOutputCost  the new value of the property
   */
  public void setDataOutputCost(double dataOutputCost) {
    this._dataOutputCost = dataOutputCost;
  }

  /**
   * Gets the the {@code dataOutputCost} property.
   * @return the property, not null
   */
  public final Property<Double> dataOutputCost() {
    return metaBean().dataOutputCost().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FunctionCostsDocument}.
   */
  public static class Meta extends BasicMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code configurationName} property.
     */
    private final MetaProperty<String> _configurationName = DirectMetaProperty.ofReadWrite(this, "configurationName", String.class);
    /**
     * The meta-property for the {@code functionId} property.
     */
    private final MetaProperty<String> _functionId = DirectMetaProperty.ofReadWrite(this, "functionId", String.class);
    /**
     * The meta-property for the {@code version} property.
     */
    private final MetaProperty<Instant> _version = DirectMetaProperty.ofReadWrite(this, "version", Instant.class);
    /**
     * The meta-property for the {@code invocationCost} property.
     */
    private final MetaProperty<Double> _invocationCost = DirectMetaProperty.ofReadWrite(this, "invocationCost", Double.TYPE);
    /**
     * The meta-property for the {@code dataInputCost} property.
     */
    private final MetaProperty<Double> _dataInputCost = DirectMetaProperty.ofReadWrite(this, "dataInputCost", Double.TYPE);
    /**
     * The meta-property for the {@code dataOutputCost} property.
     */
    private final MetaProperty<Double> _dataOutputCost = DirectMetaProperty.ofReadWrite(this, "dataOutputCost", Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap();
      temp.put("configurationName", _configurationName);
      temp.put("functionId", _functionId);
      temp.put("version", _version);
      temp.put("invocationCost", _invocationCost);
      temp.put("dataInputCost", _dataInputCost);
      temp.put("dataOutputCost", _dataOutputCost);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public FunctionCostsDocument createBean() {
      return new FunctionCostsDocument();
    }

    @Override
    public Class<? extends FunctionCostsDocument> beanType() {
      return FunctionCostsDocument.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code configurationName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> configurationName() {
      return _configurationName;
    }

    /**
     * The meta-property for the {@code functionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> functionId() {
      return _functionId;
    }

    /**
     * The meta-property for the {@code version} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Instant> version() {
      return _version;
    }

    /**
     * The meta-property for the {@code invocationCost} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> invocationCost() {
      return _invocationCost;
    }

    /**
     * The meta-property for the {@code dataInputCost} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> dataInputCost() {
      return _dataInputCost;
    }

    /**
     * The meta-property for the {@code dataOutputCost} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Double> dataOutputCost() {
      return _dataOutputCost;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
