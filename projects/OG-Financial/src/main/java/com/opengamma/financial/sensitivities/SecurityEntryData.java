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
import org.threeten.bp.LocalDate;

import com.opengamma.id.ExternalId;
import com.opengamma.util.money.Currency;

/**
 * 
 */
@BeanDefinition
public class SecurityEntryData extends DirectBean {
  /** Name for the external security scheme */
  public static final String SECURITY_SCHEME = "EXTERNAL_SENSITIVITIES_SECURITY";
  @PropertyDefinition
  private ExternalId _id;
  @PropertyDefinition
  private Currency _currency;
  @PropertyDefinition
  private LocalDate _maturityDate;
  @PropertyDefinition
  private ExternalId _factorSetId;
  /** Name of the external security type */
  public static final String EXTERNAL_SENSITIVITIES_SECURITY_TYPE = "EXTERNAL_SENSITIVITIES_SECURITY";
  
  public SecurityEntryData() {
  }
  
  public SecurityEntryData(ExternalId id, Currency currency, LocalDate  maturityDate, ExternalId factorSetId) {
    setId(id);
    setCurrency(currency);
    setMaturityDate(maturityDate);
    setFactorSetId(factorSetId);
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SecurityEntryData}.
   * @return the meta-bean, not null
   */
  public static SecurityEntryData.Meta meta() {
    return SecurityEntryData.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SecurityEntryData.Meta.INSTANCE);
  }

  @Override
  public SecurityEntryData.Meta metaBean() {
    return SecurityEntryData.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the id.
   * @return the value of the property
   */
  public ExternalId getId() {
    return _id;
  }

  /**
   * Sets the id.
   * @param id  the new value of the property
   */
  public void setId(ExternalId id) {
    this._id = id;
  }

  /**
   * Gets the the {@code id} property.
   * @return the property, not null
   */
  public final Property<ExternalId> id() {
    return metaBean().id().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the currency.
   * @return the value of the property
   */
  public Currency getCurrency() {
    return _currency;
  }

  /**
   * Sets the currency.
   * @param currency  the new value of the property
   */
  public void setCurrency(Currency currency) {
    this._currency = currency;
  }

  /**
   * Gets the the {@code currency} property.
   * @return the property, not null
   */
  public final Property<Currency> currency() {
    return metaBean().currency().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturityDate.
   * @return the value of the property
   */
  public LocalDate getMaturityDate() {
    return _maturityDate;
  }

  /**
   * Sets the maturityDate.
   * @param maturityDate  the new value of the property
   */
  public void setMaturityDate(LocalDate maturityDate) {
    this._maturityDate = maturityDate;
  }

  /**
   * Gets the the {@code maturityDate} property.
   * @return the property, not null
   */
  public final Property<LocalDate> maturityDate() {
    return metaBean().maturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the factorSetId.
   * @return the value of the property
   */
  public ExternalId getFactorSetId() {
    return _factorSetId;
  }

  /**
   * Sets the factorSetId.
   * @param factorSetId  the new value of the property
   */
  public void setFactorSetId(ExternalId factorSetId) {
    this._factorSetId = factorSetId;
  }

  /**
   * Gets the the {@code factorSetId} property.
   * @return the property, not null
   */
  public final Property<ExternalId> factorSetId() {
    return metaBean().factorSetId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SecurityEntryData clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SecurityEntryData other = (SecurityEntryData) obj;
      return JodaBeanUtils.equal(getId(), other.getId()) &&
          JodaBeanUtils.equal(getCurrency(), other.getCurrency()) &&
          JodaBeanUtils.equal(getMaturityDate(), other.getMaturityDate()) &&
          JodaBeanUtils.equal(getFactorSetId(), other.getFactorSetId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaturityDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFactorSetId());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("SecurityEntryData{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("id").append('=').append(JodaBeanUtils.toString(getId())).append(',').append(' ');
    buf.append("currency").append('=').append(JodaBeanUtils.toString(getCurrency())).append(',').append(' ');
    buf.append("maturityDate").append('=').append(JodaBeanUtils.toString(getMaturityDate())).append(',').append(' ');
    buf.append("factorSetId").append('=').append(JodaBeanUtils.toString(getFactorSetId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SecurityEntryData}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<ExternalId> _id = DirectMetaProperty.ofReadWrite(
        this, "id", SecurityEntryData.class, ExternalId.class);
    /**
     * The meta-property for the {@code currency} property.
     */
    private final MetaProperty<Currency> _currency = DirectMetaProperty.ofReadWrite(
        this, "currency", SecurityEntryData.class, Currency.class);
    /**
     * The meta-property for the {@code maturityDate} property.
     */
    private final MetaProperty<LocalDate> _maturityDate = DirectMetaProperty.ofReadWrite(
        this, "maturityDate", SecurityEntryData.class, LocalDate.class);
    /**
     * The meta-property for the {@code factorSetId} property.
     */
    private final MetaProperty<ExternalId> _factorSetId = DirectMetaProperty.ofReadWrite(
        this, "factorSetId", SecurityEntryData.class, ExternalId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "currency",
        "maturityDate",
        "factorSetId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case 575402001:  // currency
          return _currency;
        case -414641441:  // maturityDate
          return _maturityDate;
        case 42976526:  // factorSetId
          return _factorSetId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SecurityEntryData> builder() {
      return new DirectBeanBuilder<SecurityEntryData>(new SecurityEntryData());
    }

    @Override
    public Class<? extends SecurityEntryData> beanType() {
      return SecurityEntryData.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code currency} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency() {
      return _currency;
    }

    /**
     * The meta-property for the {@code maturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<LocalDate> maturityDate() {
      return _maturityDate;
    }

    /**
     * The meta-property for the {@code factorSetId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalId> factorSetId() {
      return _factorSetId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((SecurityEntryData) bean).getId();
        case 575402001:  // currency
          return ((SecurityEntryData) bean).getCurrency();
        case -414641441:  // maturityDate
          return ((SecurityEntryData) bean).getMaturityDate();
        case 42976526:  // factorSetId
          return ((SecurityEntryData) bean).getFactorSetId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          ((SecurityEntryData) bean).setId((ExternalId) newValue);
          return;
        case 575402001:  // currency
          ((SecurityEntryData) bean).setCurrency((Currency) newValue);
          return;
        case -414641441:  // maturityDate
          ((SecurityEntryData) bean).setMaturityDate((LocalDate) newValue);
          return;
        case 42976526:  // factorSetId
          ((SecurityEntryData) bean).setFactorSetId((ExternalId) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
