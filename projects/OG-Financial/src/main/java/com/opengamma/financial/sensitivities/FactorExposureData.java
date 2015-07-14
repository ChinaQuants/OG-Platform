/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.sensitivities;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.id.ExternalId;

/**
 * Class in which to store externally provided sensitivities meta data (actual data stored in associated time series) 
 */
@BeanDefinition
public class FactorExposureData extends DirectBean {
  /**
   * The identifier scheme for external sensitivities factor set securities
   */
  public static final String FACTOR_SET_SCHEME = "EXTERNAL_SENSITIVITIES_FACTOR_SET";
  /**
   * The identifier scheme for individual exposures
   */
  public static final String EXPOSURE_SCHEME = "EXTERNAL_SENSITIVITIES_EXPOSURE";
  /**
   * The identifier for common risk factors (not per-specific exposure).
   */
  public static final String FACTOR_SCHEME = "EXTERNAL_SENSITIVITIES_FACTOR";
  
  @PropertyDefinition
  private long _factorSetId;
  @PropertyDefinition
  private FactorType _factorType;
  @PropertyDefinition
  private String _factorName;
  @PropertyDefinition
  private String _node;
  /**
   * Security type for GSI risk factor sensitivities pseudo-instrument.
   */
  public static final String EXTERNAL_SENSITIVITIES_RISK_FACTORS_SECURITY_TYPE = "EXTERNAL_SENSITIVITY_RISK_FACTORS";
  
  public FactorExposureData() {
  }
  
  public FactorExposureData(long factorSetId, FactorType factorType, String factorName, String node) {
    setFactorSetId(factorSetId);
    setFactorType(factorType);
    setFactorName(factorName);
    setNode(node);
  }
  
  public ExternalId getFactorSetExternalId() {
    return ExternalId.of(FACTOR_SET_SCHEME, Long.toString(getFactorSetId()));
  }
  
  public ExternalId getExposureExternalId() {
    StringBuilder sb = new StringBuilder();
    sb.append(Long.toString(getFactorSetId()));
    sb.append(".");
    sb.append(getFactorName());
    return ExternalId.of(EXPOSURE_SCHEME, sb.toString());
  }
  
  public ExternalId getFactorExternalId() {
    StringBuilder sb = new StringBuilder();
    sb.append(getFactorName());
    return ExternalId.of(FACTOR_SCHEME, sb.toString());
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FactorExposureData}.
   * @return the meta-bean, not null
   */
  public static FactorExposureData.Meta meta() {
    return FactorExposureData.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FactorExposureData.Meta.INSTANCE);
  }

  @Override
  public FactorExposureData.Meta metaBean() {
    return FactorExposureData.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the factorSetId.
   * @return the value of the property
   */
  public long getFactorSetId() {
    return _factorSetId;
  }

  /**
   * Sets the factorSetId.
   * @param factorSetId  the new value of the property
   */
  public void setFactorSetId(long factorSetId) {
    this._factorSetId = factorSetId;
  }

  /**
   * Gets the the {@code factorSetId} property.
   * @return the property, not null
   */
  public final Property<Long> factorSetId() {
    return metaBean().factorSetId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the factorType.
   * @return the value of the property
   */
  public FactorType getFactorType() {
    return _factorType;
  }

  /**
   * Sets the factorType.
   * @param factorType  the new value of the property
   */
  public void setFactorType(FactorType factorType) {
    this._factorType = factorType;
  }

  /**
   * Gets the the {@code factorType} property.
   * @return the property, not null
   */
  public final Property<FactorType> factorType() {
    return metaBean().factorType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the factorName.
   * @return the value of the property
   */
  public String getFactorName() {
    return _factorName;
  }

  /**
   * Sets the factorName.
   * @param factorName  the new value of the property
   */
  public void setFactorName(String factorName) {
    this._factorName = factorName;
  }

  /**
   * Gets the the {@code factorName} property.
   * @return the property, not null
   */
  public final Property<String> factorName() {
    return metaBean().factorName().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the node.
   * @return the value of the property
   */
  public String getNode() {
    return _node;
  }

  /**
   * Sets the node.
   * @param node  the new value of the property
   */
  public void setNode(String node) {
    this._node = node;
  }

  /**
   * Gets the the {@code node} property.
   * @return the property, not null
   */
  public final Property<String> node() {
    return metaBean().node().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FactorExposureData clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FactorExposureData other = (FactorExposureData) obj;
      return (getFactorSetId() == other.getFactorSetId()) &&
          JodaBeanUtils.equal(getFactorType(), other.getFactorType()) &&
          JodaBeanUtils.equal(getFactorName(), other.getFactorName()) &&
          JodaBeanUtils.equal(getNode(), other.getNode());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getFactorSetId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFactorType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFactorName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getNode());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("FactorExposureData{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("factorSetId").append('=').append(JodaBeanUtils.toString(getFactorSetId())).append(',').append(' ');
    buf.append("factorType").append('=').append(JodaBeanUtils.toString(getFactorType())).append(',').append(' ');
    buf.append("factorName").append('=').append(JodaBeanUtils.toString(getFactorName())).append(',').append(' ');
    buf.append("node").append('=').append(JodaBeanUtils.toString(getNode())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FactorExposureData}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code factorSetId} property.
     */
    private final MetaProperty<Long> _factorSetId = DirectMetaProperty.ofReadWrite(
        this, "factorSetId", FactorExposureData.class, Long.TYPE);
    /**
     * The meta-property for the {@code factorType} property.
     */
    private final MetaProperty<FactorType> _factorType = DirectMetaProperty.ofReadWrite(
        this, "factorType", FactorExposureData.class, FactorType.class);
    /**
     * The meta-property for the {@code factorName} property.
     */
    private final MetaProperty<String> _factorName = DirectMetaProperty.ofReadWrite(
        this, "factorName", FactorExposureData.class, String.class);
    /**
     * The meta-property for the {@code node} property.
     */
    private final MetaProperty<String> _node = DirectMetaProperty.ofReadWrite(
        this, "node", FactorExposureData.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "factorSetId",
        "factorType",
        "factorName",
        "node");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 42976526:  // factorSetId
          return _factorSetId;
        case 1802550569:  // factorType
          return _factorType;
        case 1802348666:  // factorName
          return _factorName;
        case 3386882:  // node
          return _node;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FactorExposureData> builder() {
      return new DirectBeanBuilder<FactorExposureData>(new FactorExposureData());
    }

    @Override
    public Class<? extends FactorExposureData> beanType() {
      return FactorExposureData.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code factorSetId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> factorSetId() {
      return _factorSetId;
    }

    /**
     * The meta-property for the {@code factorType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<FactorType> factorType() {
      return _factorType;
    }

    /**
     * The meta-property for the {@code factorName} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> factorName() {
      return _factorName;
    }

    /**
     * The meta-property for the {@code node} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> node() {
      return _node;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 42976526:  // factorSetId
          return ((FactorExposureData) bean).getFactorSetId();
        case 1802550569:  // factorType
          return ((FactorExposureData) bean).getFactorType();
        case 1802348666:  // factorName
          return ((FactorExposureData) bean).getFactorName();
        case 3386882:  // node
          return ((FactorExposureData) bean).getNode();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 42976526:  // factorSetId
          ((FactorExposureData) bean).setFactorSetId((Long) newValue);
          return;
        case 1802550569:  // factorType
          ((FactorExposureData) bean).setFactorType((FactorType) newValue);
          return;
        case 1802348666:  // factorName
          ((FactorExposureData) bean).setFactorName((String) newValue);
          return;
        case 3386882:  // node
          ((FactorExposureData) bean).setNode((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
