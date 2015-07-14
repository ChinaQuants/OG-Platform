/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.masterdb.security.hibernate.swap;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.financial.security.swap.SwapSecurity;
import com.opengamma.masterdb.security.hibernate.SecurityBean;
import com.opengamma.masterdb.security.hibernate.TenorBean;
import com.opengamma.masterdb.security.hibernate.ZonedDateTimeBean;

/**
 * A Hibernate bean representation of {@link SwapSecurity}.
 */
@BeanDefinition
public class SwapSecurityBean extends SecurityBean {

  @PropertyDefinition
  private SwapType _swapType;
  @PropertyDefinition
  private ZonedDateTimeBean _tradeDate;
  @PropertyDefinition
  private ZonedDateTimeBean _effectiveDate;
  @PropertyDefinition
  private ZonedDateTimeBean _maturityDate;
  @PropertyDefinition
  private ZonedDateTimeBean _forwardStartDate;
  @PropertyDefinition
  private String _counterparty;
  @PropertyDefinition
  private boolean _exchangeInitialNotional;
  @PropertyDefinition
  private boolean _exchangeFinalNotional;
  @PropertyDefinition
  private TenorBean _maturityTenor;
  @PropertyDefinition
  private SwapLegBean _payLeg;
  @PropertyDefinition
  private SwapLegBean _receiveLeg;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SwapSecurityBean}.
   * @return the meta-bean, not null
   */
  public static SwapSecurityBean.Meta meta() {
    return SwapSecurityBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(SwapSecurityBean.Meta.INSTANCE);
  }

  @Override
  public SwapSecurityBean.Meta metaBean() {
    return SwapSecurityBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the swapType.
   * @return the value of the property
   */
  public SwapType getSwapType() {
    return _swapType;
  }

  /**
   * Sets the swapType.
   * @param swapType  the new value of the property
   */
  public void setSwapType(SwapType swapType) {
    this._swapType = swapType;
  }

  /**
   * Gets the the {@code swapType} property.
   * @return the property, not null
   */
  public final Property<SwapType> swapType() {
    return metaBean().swapType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the tradeDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getTradeDate() {
    return _tradeDate;
  }

  /**
   * Sets the tradeDate.
   * @param tradeDate  the new value of the property
   */
  public void setTradeDate(ZonedDateTimeBean tradeDate) {
    this._tradeDate = tradeDate;
  }

  /**
   * Gets the the {@code tradeDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> tradeDate() {
    return metaBean().tradeDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the effectiveDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getEffectiveDate() {
    return _effectiveDate;
  }

  /**
   * Sets the effectiveDate.
   * @param effectiveDate  the new value of the property
   */
  public void setEffectiveDate(ZonedDateTimeBean effectiveDate) {
    this._effectiveDate = effectiveDate;
  }

  /**
   * Gets the the {@code effectiveDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> effectiveDate() {
    return metaBean().effectiveDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturityDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getMaturityDate() {
    return _maturityDate;
  }

  /**
   * Sets the maturityDate.
   * @param maturityDate  the new value of the property
   */
  public void setMaturityDate(ZonedDateTimeBean maturityDate) {
    this._maturityDate = maturityDate;
  }

  /**
   * Gets the the {@code maturityDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> maturityDate() {
    return metaBean().maturityDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the forwardStartDate.
   * @return the value of the property
   */
  public ZonedDateTimeBean getForwardStartDate() {
    return _forwardStartDate;
  }

  /**
   * Sets the forwardStartDate.
   * @param forwardStartDate  the new value of the property
   */
  public void setForwardStartDate(ZonedDateTimeBean forwardStartDate) {
    this._forwardStartDate = forwardStartDate;
  }

  /**
   * Gets the the {@code forwardStartDate} property.
   * @return the property, not null
   */
  public final Property<ZonedDateTimeBean> forwardStartDate() {
    return metaBean().forwardStartDate().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the counterparty.
   * @return the value of the property
   */
  public String getCounterparty() {
    return _counterparty;
  }

  /**
   * Sets the counterparty.
   * @param counterparty  the new value of the property
   */
  public void setCounterparty(String counterparty) {
    this._counterparty = counterparty;
  }

  /**
   * Gets the the {@code counterparty} property.
   * @return the property, not null
   */
  public final Property<String> counterparty() {
    return metaBean().counterparty().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchangeInitialNotional.
   * @return the value of the property
   */
  public boolean isExchangeInitialNotional() {
    return _exchangeInitialNotional;
  }

  /**
   * Sets the exchangeInitialNotional.
   * @param exchangeInitialNotional  the new value of the property
   */
  public void setExchangeInitialNotional(boolean exchangeInitialNotional) {
    this._exchangeInitialNotional = exchangeInitialNotional;
  }

  /**
   * Gets the the {@code exchangeInitialNotional} property.
   * @return the property, not null
   */
  public final Property<Boolean> exchangeInitialNotional() {
    return metaBean().exchangeInitialNotional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exchangeFinalNotional.
   * @return the value of the property
   */
  public boolean isExchangeFinalNotional() {
    return _exchangeFinalNotional;
  }

  /**
   * Sets the exchangeFinalNotional.
   * @param exchangeFinalNotional  the new value of the property
   */
  public void setExchangeFinalNotional(boolean exchangeFinalNotional) {
    this._exchangeFinalNotional = exchangeFinalNotional;
  }

  /**
   * Gets the the {@code exchangeFinalNotional} property.
   * @return the property, not null
   */
  public final Property<Boolean> exchangeFinalNotional() {
    return metaBean().exchangeFinalNotional().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maturityTenor.
   * @return the value of the property
   */
  public TenorBean getMaturityTenor() {
    return _maturityTenor;
  }

  /**
   * Sets the maturityTenor.
   * @param maturityTenor  the new value of the property
   */
  public void setMaturityTenor(TenorBean maturityTenor) {
    this._maturityTenor = maturityTenor;
  }

  /**
   * Gets the the {@code maturityTenor} property.
   * @return the property, not null
   */
  public final Property<TenorBean> maturityTenor() {
    return metaBean().maturityTenor().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the payLeg.
   * @return the value of the property
   */
  public SwapLegBean getPayLeg() {
    return _payLeg;
  }

  /**
   * Sets the payLeg.
   * @param payLeg  the new value of the property
   */
  public void setPayLeg(SwapLegBean payLeg) {
    this._payLeg = payLeg;
  }

  /**
   * Gets the the {@code payLeg} property.
   * @return the property, not null
   */
  public final Property<SwapLegBean> payLeg() {
    return metaBean().payLeg().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the receiveLeg.
   * @return the value of the property
   */
  public SwapLegBean getReceiveLeg() {
    return _receiveLeg;
  }

  /**
   * Sets the receiveLeg.
   * @param receiveLeg  the new value of the property
   */
  public void setReceiveLeg(SwapLegBean receiveLeg) {
    this._receiveLeg = receiveLeg;
  }

  /**
   * Gets the the {@code receiveLeg} property.
   * @return the property, not null
   */
  public final Property<SwapLegBean> receiveLeg() {
    return metaBean().receiveLeg().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public SwapSecurityBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SwapSecurityBean other = (SwapSecurityBean) obj;
      return JodaBeanUtils.equal(getSwapType(), other.getSwapType()) &&
          JodaBeanUtils.equal(getTradeDate(), other.getTradeDate()) &&
          JodaBeanUtils.equal(getEffectiveDate(), other.getEffectiveDate()) &&
          JodaBeanUtils.equal(getMaturityDate(), other.getMaturityDate()) &&
          JodaBeanUtils.equal(getForwardStartDate(), other.getForwardStartDate()) &&
          JodaBeanUtils.equal(getCounterparty(), other.getCounterparty()) &&
          (isExchangeInitialNotional() == other.isExchangeInitialNotional()) &&
          (isExchangeFinalNotional() == other.isExchangeFinalNotional()) &&
          JodaBeanUtils.equal(getMaturityTenor(), other.getMaturityTenor()) &&
          JodaBeanUtils.equal(getPayLeg(), other.getPayLeg()) &&
          JodaBeanUtils.equal(getReceiveLeg(), other.getReceiveLeg()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getSwapType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getTradeDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getEffectiveDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaturityDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getForwardStartDate());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCounterparty());
    hash = hash * 31 + JodaBeanUtils.hashCode(isExchangeInitialNotional());
    hash = hash * 31 + JodaBeanUtils.hashCode(isExchangeFinalNotional());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMaturityTenor());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPayLeg());
    hash = hash * 31 + JodaBeanUtils.hashCode(getReceiveLeg());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(384);
    buf.append("SwapSecurityBean{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
    buf.append("swapType").append('=').append(JodaBeanUtils.toString(getSwapType())).append(',').append(' ');
    buf.append("tradeDate").append('=').append(JodaBeanUtils.toString(getTradeDate())).append(',').append(' ');
    buf.append("effectiveDate").append('=').append(JodaBeanUtils.toString(getEffectiveDate())).append(',').append(' ');
    buf.append("maturityDate").append('=').append(JodaBeanUtils.toString(getMaturityDate())).append(',').append(' ');
    buf.append("forwardStartDate").append('=').append(JodaBeanUtils.toString(getForwardStartDate())).append(',').append(' ');
    buf.append("counterparty").append('=').append(JodaBeanUtils.toString(getCounterparty())).append(',').append(' ');
    buf.append("exchangeInitialNotional").append('=').append(JodaBeanUtils.toString(isExchangeInitialNotional())).append(',').append(' ');
    buf.append("exchangeFinalNotional").append('=').append(JodaBeanUtils.toString(isExchangeFinalNotional())).append(',').append(' ');
    buf.append("maturityTenor").append('=').append(JodaBeanUtils.toString(getMaturityTenor())).append(',').append(' ');
    buf.append("payLeg").append('=').append(JodaBeanUtils.toString(getPayLeg())).append(',').append(' ');
    buf.append("receiveLeg").append('=').append(JodaBeanUtils.toString(getReceiveLeg())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SwapSecurityBean}.
   */
  public static class Meta extends SecurityBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code swapType} property.
     */
    private final MetaProperty<SwapType> _swapType = DirectMetaProperty.ofReadWrite(
        this, "swapType", SwapSecurityBean.class, SwapType.class);
    /**
     * The meta-property for the {@code tradeDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _tradeDate = DirectMetaProperty.ofReadWrite(
        this, "tradeDate", SwapSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code effectiveDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _effectiveDate = DirectMetaProperty.ofReadWrite(
        this, "effectiveDate", SwapSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code maturityDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _maturityDate = DirectMetaProperty.ofReadWrite(
        this, "maturityDate", SwapSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code forwardStartDate} property.
     */
    private final MetaProperty<ZonedDateTimeBean> _forwardStartDate = DirectMetaProperty.ofReadWrite(
        this, "forwardStartDate", SwapSecurityBean.class, ZonedDateTimeBean.class);
    /**
     * The meta-property for the {@code counterparty} property.
     */
    private final MetaProperty<String> _counterparty = DirectMetaProperty.ofReadWrite(
        this, "counterparty", SwapSecurityBean.class, String.class);
    /**
     * The meta-property for the {@code exchangeInitialNotional} property.
     */
    private final MetaProperty<Boolean> _exchangeInitialNotional = DirectMetaProperty.ofReadWrite(
        this, "exchangeInitialNotional", SwapSecurityBean.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code exchangeFinalNotional} property.
     */
    private final MetaProperty<Boolean> _exchangeFinalNotional = DirectMetaProperty.ofReadWrite(
        this, "exchangeFinalNotional", SwapSecurityBean.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code maturityTenor} property.
     */
    private final MetaProperty<TenorBean> _maturityTenor = DirectMetaProperty.ofReadWrite(
        this, "maturityTenor", SwapSecurityBean.class, TenorBean.class);
    /**
     * The meta-property for the {@code payLeg} property.
     */
    private final MetaProperty<SwapLegBean> _payLeg = DirectMetaProperty.ofReadWrite(
        this, "payLeg", SwapSecurityBean.class, SwapLegBean.class);
    /**
     * The meta-property for the {@code receiveLeg} property.
     */
    private final MetaProperty<SwapLegBean> _receiveLeg = DirectMetaProperty.ofReadWrite(
        this, "receiveLeg", SwapSecurityBean.class, SwapLegBean.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "swapType",
        "tradeDate",
        "effectiveDate",
        "maturityDate",
        "forwardStartDate",
        "counterparty",
        "exchangeInitialNotional",
        "exchangeFinalNotional",
        "maturityTenor",
        "payLeg",
        "receiveLeg");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -318434451:  // swapType
          return _swapType;
        case 752419634:  // tradeDate
          return _tradeDate;
        case -930389515:  // effectiveDate
          return _effectiveDate;
        case -414641441:  // maturityDate
          return _maturityDate;
        case -414907925:  // forwardStartDate
          return _forwardStartDate;
        case -1651301782:  // counterparty
          return _counterparty;
        case -1304307199:  // exchangeInitialNotional
          return _exchangeInitialNotional;
        case -1976228493:  // exchangeFinalNotional
          return _exchangeFinalNotional;
        case 45907375:  // maturityTenor
          return _maturityTenor;
        case -995239866:  // payLeg
          return _payLeg;
        case 209233963:  // receiveLeg
          return _receiveLeg;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SwapSecurityBean> builder() {
      return new DirectBeanBuilder<SwapSecurityBean>(new SwapSecurityBean());
    }

    @Override
    public Class<? extends SwapSecurityBean> beanType() {
      return SwapSecurityBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code swapType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SwapType> swapType() {
      return _swapType;
    }

    /**
     * The meta-property for the {@code tradeDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> tradeDate() {
      return _tradeDate;
    }

    /**
     * The meta-property for the {@code effectiveDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> effectiveDate() {
      return _effectiveDate;
    }

    /**
     * The meta-property for the {@code maturityDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> maturityDate() {
      return _maturityDate;
    }

    /**
     * The meta-property for the {@code forwardStartDate} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTimeBean> forwardStartDate() {
      return _forwardStartDate;
    }

    /**
     * The meta-property for the {@code counterparty} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> counterparty() {
      return _counterparty;
    }

    /**
     * The meta-property for the {@code exchangeInitialNotional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> exchangeInitialNotional() {
      return _exchangeInitialNotional;
    }

    /**
     * The meta-property for the {@code exchangeFinalNotional} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> exchangeFinalNotional() {
      return _exchangeFinalNotional;
    }

    /**
     * The meta-property for the {@code maturityTenor} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<TenorBean> maturityTenor() {
      return _maturityTenor;
    }

    /**
     * The meta-property for the {@code payLeg} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SwapLegBean> payLeg() {
      return _payLeg;
    }

    /**
     * The meta-property for the {@code receiveLeg} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SwapLegBean> receiveLeg() {
      return _receiveLeg;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -318434451:  // swapType
          return ((SwapSecurityBean) bean).getSwapType();
        case 752419634:  // tradeDate
          return ((SwapSecurityBean) bean).getTradeDate();
        case -930389515:  // effectiveDate
          return ((SwapSecurityBean) bean).getEffectiveDate();
        case -414641441:  // maturityDate
          return ((SwapSecurityBean) bean).getMaturityDate();
        case -414907925:  // forwardStartDate
          return ((SwapSecurityBean) bean).getForwardStartDate();
        case -1651301782:  // counterparty
          return ((SwapSecurityBean) bean).getCounterparty();
        case -1304307199:  // exchangeInitialNotional
          return ((SwapSecurityBean) bean).isExchangeInitialNotional();
        case -1976228493:  // exchangeFinalNotional
          return ((SwapSecurityBean) bean).isExchangeFinalNotional();
        case 45907375:  // maturityTenor
          return ((SwapSecurityBean) bean).getMaturityTenor();
        case -995239866:  // payLeg
          return ((SwapSecurityBean) bean).getPayLeg();
        case 209233963:  // receiveLeg
          return ((SwapSecurityBean) bean).getReceiveLeg();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -318434451:  // swapType
          ((SwapSecurityBean) bean).setSwapType((SwapType) newValue);
          return;
        case 752419634:  // tradeDate
          ((SwapSecurityBean) bean).setTradeDate((ZonedDateTimeBean) newValue);
          return;
        case -930389515:  // effectiveDate
          ((SwapSecurityBean) bean).setEffectiveDate((ZonedDateTimeBean) newValue);
          return;
        case -414641441:  // maturityDate
          ((SwapSecurityBean) bean).setMaturityDate((ZonedDateTimeBean) newValue);
          return;
        case -414907925:  // forwardStartDate
          ((SwapSecurityBean) bean).setForwardStartDate((ZonedDateTimeBean) newValue);
          return;
        case -1651301782:  // counterparty
          ((SwapSecurityBean) bean).setCounterparty((String) newValue);
          return;
        case -1304307199:  // exchangeInitialNotional
          ((SwapSecurityBean) bean).setExchangeInitialNotional((Boolean) newValue);
          return;
        case -1976228493:  // exchangeFinalNotional
          ((SwapSecurityBean) bean).setExchangeFinalNotional((Boolean) newValue);
          return;
        case 45907375:  // maturityTenor
          ((SwapSecurityBean) bean).setMaturityTenor((TenorBean) newValue);
          return;
        case -995239866:  // payLeg
          ((SwapSecurityBean) bean).setPayLeg((SwapLegBean) newValue);
          return;
        case 209233963:  // receiveLeg
          ((SwapSecurityBean) bean).setReceiveLeg((SwapLegBean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
