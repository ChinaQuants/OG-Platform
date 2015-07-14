/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.convention;

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

import com.opengamma.core.convention.ConventionType;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.util.ArgumentChecker;

/**
 * Convention for equities.
 */
@BeanDefinition
public class EquityConvention extends FinancialConvention {

  /**
   * Type of the convention.
   */
  public static final ConventionType TYPE = ConventionType.of("Equity");

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The ex-dividend period.
   */
  @PropertyDefinition
  private int _exDividendPeriod;

  /**
   * Creates an instance.
   */
  protected EquityConvention() {
  }

  /**
   * Creates an instance.
   * 
   * @param name  the convention name, not null
   * @param externalIdBundle  the external identifiers for this convention, not null
   * @param exDividendPeriod  the ex-dividend period
   */
  public EquityConvention(final String name, final ExternalIdBundle externalIdBundle, final int exDividendPeriod) {
    super(name, externalIdBundle);
    setExDividendPeriod(exDividendPeriod);
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the type identifying this convention.
   * 
   * @return the {@link #TYPE} constant, not null
   */
  @Override
  public ConventionType getConventionType() {
    return TYPE;
  }

  /**
   * Accepts a visitor to manage traversal of the hierarchy.
   *
   * @param <T>  the result type of the visitor
   * @param visitor  the visitor, not null
   * @return the result
   */
  @Override
  public <T> T accept(final FinancialConventionVisitor<T> visitor) {
    ArgumentChecker.notNull(visitor, "visitor");
    return visitor.visitEquityConvention(this);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code EquityConvention}.
   * @return the meta-bean, not null
   */
  public static EquityConvention.Meta meta() {
    return EquityConvention.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(EquityConvention.Meta.INSTANCE);
  }

  @Override
  public EquityConvention.Meta metaBean() {
    return EquityConvention.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the ex-dividend period.
   * @return the value of the property
   */
  public int getExDividendPeriod() {
    return _exDividendPeriod;
  }

  /**
   * Sets the ex-dividend period.
   * @param exDividendPeriod  the new value of the property
   */
  public void setExDividendPeriod(int exDividendPeriod) {
    this._exDividendPeriod = exDividendPeriod;
  }

  /**
   * Gets the the {@code exDividendPeriod} property.
   * @return the property, not null
   */
  public final Property<Integer> exDividendPeriod() {
    return metaBean().exDividendPeriod().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public EquityConvention clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      EquityConvention other = (EquityConvention) obj;
      return (getExDividendPeriod() == other.getExDividendPeriod()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getExDividendPeriod());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("EquityConvention{");
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
    buf.append("exDividendPeriod").append('=').append(JodaBeanUtils.toString(getExDividendPeriod())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code EquityConvention}.
   */
  public static class Meta extends FinancialConvention.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code exDividendPeriod} property.
     */
    private final MetaProperty<Integer> _exDividendPeriod = DirectMetaProperty.ofReadWrite(
        this, "exDividendPeriod", EquityConvention.class, Integer.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "exDividendPeriod");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1415964195:  // exDividendPeriod
          return _exDividendPeriod;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends EquityConvention> builder() {
      return new DirectBeanBuilder<EquityConvention>(new EquityConvention());
    }

    @Override
    public Class<? extends EquityConvention> beanType() {
      return EquityConvention.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code exDividendPeriod} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Integer> exDividendPeriod() {
      return _exDividendPeriod;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1415964195:  // exDividendPeriod
          return ((EquityConvention) bean).getExDividendPeriod();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 1415964195:  // exDividendPeriod
          ((EquityConvention) bean).setExDividendPeriod((Integer) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
