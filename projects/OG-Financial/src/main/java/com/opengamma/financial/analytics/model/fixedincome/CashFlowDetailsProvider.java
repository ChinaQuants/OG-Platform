/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.model.fixedincome;

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
import org.threeten.bp.ZonedDateTime;

import com.opengamma.analytics.financial.instrument.swap.SwapDefinition;
import com.opengamma.analytics.financial.provider.description.interestrate.MulticurveProviderInterface;
import com.opengamma.financial.security.irs.FixedInterestRateSwapLeg;
import com.opengamma.financial.security.irs.InterestRateSwapSecurity;
import com.opengamma.financial.security.irs.PayReceiveType;
import com.opengamma.util.ArgumentChecker;

/**
 * Container to provide the ValuationTime, MulticurveProviderInterface and Swap InstrumentDefinition
 */
@BeanDefinition
public class CashFlowDetailsProvider implements ImmutableBean {

  /**
   * The MulticurveProviderInterface bundle
   */
  @PropertyDefinition(validate = "notNull")
  private final MulticurveProviderInterface _multicurveProviderInterface;
  /**
   * The valuation time
   */
  @PropertyDefinition(validate = "notNull")
  private final ZonedDateTime _zonedDateTime;
  /**
   * Boolean, whether the leg is fixed or floating
   */
  @PropertyDefinition(validate = "notNull")
  private final boolean _fixed;
  /**
   * The swap definition
   */
  @PropertyDefinition(validate = "notNull")
  private final SwapDefinition _definition;
  /**
   * The PayReceiveType, whether the leg is pay or receive
   */
  @PropertyDefinition(validate = "notNull")
  private final PayReceiveType _type;

  /**
   * Creates an instance
   *
   * @param multicurveProviderInterface the MulticurveProviderInterface
   * @param zonedDateTime the ZonedDateTime
   * @param definition the SwapDefinition containing the payment definitions
   * @param security the InterestRateSwapSecurity
   * @param type the PayReceiveType, either pay or receive
   */
  public CashFlowDetailsProvider(MulticurveProviderInterface multicurveProviderInterface,
                                 ZonedDateTime zonedDateTime,
                                 SwapDefinition definition,
                                 InterestRateSwapSecurity security,
                                 PayReceiveType type) {
    ArgumentChecker.notNull(multicurveProviderInterface, "multicurveProviderInterface");
    ArgumentChecker.notNull(zonedDateTime, "zonedDateTime");
    ArgumentChecker.notNull(definition, "definition");
    ArgumentChecker.notNull(type, "type");
    _multicurveProviderInterface = multicurveProviderInterface;
    _zonedDateTime = zonedDateTime;
    _definition = definition;
    _type = type;

    if (type == PayReceiveType.PAY) {
      _fixed = security.getPayLeg() instanceof FixedInterestRateSwapLeg;
    } else {
      _fixed = security.getReceiveLeg() instanceof FixedInterestRateSwapLeg;
    }

  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CashFlowDetailsProvider}.
   * @return the meta-bean, not null
   */
  public static CashFlowDetailsProvider.Meta meta() {
    return CashFlowDetailsProvider.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CashFlowDetailsProvider.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static CashFlowDetailsProvider.Builder builder() {
    return new CashFlowDetailsProvider.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected CashFlowDetailsProvider(CashFlowDetailsProvider.Builder builder) {
    JodaBeanUtils.notNull(builder._multicurveProviderInterface, "multicurveProviderInterface");
    JodaBeanUtils.notNull(builder._zonedDateTime, "zonedDateTime");
    JodaBeanUtils.notNull(builder._fixed, "fixed");
    JodaBeanUtils.notNull(builder._definition, "definition");
    JodaBeanUtils.notNull(builder._type, "type");
    this._multicurveProviderInterface = builder._multicurveProviderInterface;
    this._zonedDateTime = builder._zonedDateTime;
    this._fixed = builder._fixed;
    this._definition = builder._definition;
    this._type = builder._type;
  }

  @Override
  public CashFlowDetailsProvider.Meta metaBean() {
    return CashFlowDetailsProvider.Meta.INSTANCE;
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
   * Gets the MulticurveProviderInterface bundle
   * @return the value of the property, not null
   */
  public MulticurveProviderInterface getMulticurveProviderInterface() {
    return _multicurveProviderInterface;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the valuation time
   * @return the value of the property, not null
   */
  public ZonedDateTime getZonedDateTime() {
    return _zonedDateTime;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets boolean, whether the leg is fixed or floating
   * @return the value of the property, not null
   */
  public boolean isFixed() {
    return _fixed;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the swap definition
   * @return the value of the property, not null
   */
  public SwapDefinition getDefinition() {
    return _definition;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the PayReceiveType, whether the leg is pay or receive
   * @return the value of the property, not null
   */
  public PayReceiveType getType() {
    return _type;
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
      CashFlowDetailsProvider other = (CashFlowDetailsProvider) obj;
      return JodaBeanUtils.equal(getMulticurveProviderInterface(), other.getMulticurveProviderInterface()) &&
          JodaBeanUtils.equal(getZonedDateTime(), other.getZonedDateTime()) &&
          (isFixed() == other.isFixed()) &&
          JodaBeanUtils.equal(getDefinition(), other.getDefinition()) &&
          JodaBeanUtils.equal(getType(), other.getType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getMulticurveProviderInterface());
    hash = hash * 31 + JodaBeanUtils.hashCode(getZonedDateTime());
    hash = hash * 31 + JodaBeanUtils.hashCode(isFixed());
    hash = hash * 31 + JodaBeanUtils.hashCode(getDefinition());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("CashFlowDetailsProvider{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("multicurveProviderInterface").append('=').append(JodaBeanUtils.toString(getMulticurveProviderInterface())).append(',').append(' ');
    buf.append("zonedDateTime").append('=').append(JodaBeanUtils.toString(getZonedDateTime())).append(',').append(' ');
    buf.append("fixed").append('=').append(JodaBeanUtils.toString(isFixed())).append(',').append(' ');
    buf.append("definition").append('=').append(JodaBeanUtils.toString(getDefinition())).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CashFlowDetailsProvider}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code multicurveProviderInterface} property.
     */
    private final MetaProperty<MulticurveProviderInterface> _multicurveProviderInterface = DirectMetaProperty.ofImmutable(
        this, "multicurveProviderInterface", CashFlowDetailsProvider.class, MulticurveProviderInterface.class);
    /**
     * The meta-property for the {@code zonedDateTime} property.
     */
    private final MetaProperty<ZonedDateTime> _zonedDateTime = DirectMetaProperty.ofImmutable(
        this, "zonedDateTime", CashFlowDetailsProvider.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code fixed} property.
     */
    private final MetaProperty<Boolean> _fixed = DirectMetaProperty.ofImmutable(
        this, "fixed", CashFlowDetailsProvider.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code definition} property.
     */
    private final MetaProperty<SwapDefinition> _definition = DirectMetaProperty.ofImmutable(
        this, "definition", CashFlowDetailsProvider.class, SwapDefinition.class);
    /**
     * The meta-property for the {@code type} property.
     */
    private final MetaProperty<PayReceiveType> _type = DirectMetaProperty.ofImmutable(
        this, "type", CashFlowDetailsProvider.class, PayReceiveType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "multicurveProviderInterface",
        "zonedDateTime",
        "fixed",
        "definition",
        "type");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1762839150:  // multicurveProviderInterface
          return _multicurveProviderInterface;
        case -1255723533:  // zonedDateTime
          return _zonedDateTime;
        case 97445748:  // fixed
          return _fixed;
        case -1014418093:  // definition
          return _definition;
        case 3575610:  // type
          return _type;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public CashFlowDetailsProvider.Builder builder() {
      return new CashFlowDetailsProvider.Builder();
    }

    @Override
    public Class<? extends CashFlowDetailsProvider> beanType() {
      return CashFlowDetailsProvider.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code multicurveProviderInterface} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MulticurveProviderInterface> multicurveProviderInterface() {
      return _multicurveProviderInterface;
    }

    /**
     * The meta-property for the {@code zonedDateTime} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ZonedDateTime> zonedDateTime() {
      return _zonedDateTime;
    }

    /**
     * The meta-property for the {@code fixed} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> fixed() {
      return _fixed;
    }

    /**
     * The meta-property for the {@code definition} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SwapDefinition> definition() {
      return _definition;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PayReceiveType> type() {
      return _type;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1762839150:  // multicurveProviderInterface
          return ((CashFlowDetailsProvider) bean).getMulticurveProviderInterface();
        case -1255723533:  // zonedDateTime
          return ((CashFlowDetailsProvider) bean).getZonedDateTime();
        case 97445748:  // fixed
          return ((CashFlowDetailsProvider) bean).isFixed();
        case -1014418093:  // definition
          return ((CashFlowDetailsProvider) bean).getDefinition();
        case 3575610:  // type
          return ((CashFlowDetailsProvider) bean).getType();
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
   * The bean-builder for {@code CashFlowDetailsProvider}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<CashFlowDetailsProvider> {

    private MulticurveProviderInterface _multicurveProviderInterface;
    private ZonedDateTime _zonedDateTime;
    private boolean _fixed;
    private SwapDefinition _definition;
    private PayReceiveType _type;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(CashFlowDetailsProvider beanToCopy) {
      this._multicurveProviderInterface = beanToCopy.getMulticurveProviderInterface();
      this._zonedDateTime = beanToCopy.getZonedDateTime();
      this._fixed = beanToCopy.isFixed();
      this._definition = beanToCopy.getDefinition();
      this._type = beanToCopy.getType();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1762839150:  // multicurveProviderInterface
          return _multicurveProviderInterface;
        case -1255723533:  // zonedDateTime
          return _zonedDateTime;
        case 97445748:  // fixed
          return _fixed;
        case -1014418093:  // definition
          return _definition;
        case 3575610:  // type
          return _type;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1762839150:  // multicurveProviderInterface
          this._multicurveProviderInterface = (MulticurveProviderInterface) newValue;
          break;
        case -1255723533:  // zonedDateTime
          this._zonedDateTime = (ZonedDateTime) newValue;
          break;
        case 97445748:  // fixed
          this._fixed = (Boolean) newValue;
          break;
        case -1014418093:  // definition
          this._definition = (SwapDefinition) newValue;
          break;
        case 3575610:  // type
          this._type = (PayReceiveType) newValue;
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
    public CashFlowDetailsProvider build() {
      return new CashFlowDetailsProvider(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code multicurveProviderInterface} property in the builder.
     * @param multicurveProviderInterface  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder multicurveProviderInterface(MulticurveProviderInterface multicurveProviderInterface) {
      JodaBeanUtils.notNull(multicurveProviderInterface, "multicurveProviderInterface");
      this._multicurveProviderInterface = multicurveProviderInterface;
      return this;
    }

    /**
     * Sets the {@code zonedDateTime} property in the builder.
     * @param zonedDateTime  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder zonedDateTime(ZonedDateTime zonedDateTime) {
      JodaBeanUtils.notNull(zonedDateTime, "zonedDateTime");
      this._zonedDateTime = zonedDateTime;
      return this;
    }

    /**
     * Sets the {@code fixed} property in the builder.
     * @param fixed  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder fixed(boolean fixed) {
      JodaBeanUtils.notNull(fixed, "fixed");
      this._fixed = fixed;
      return this;
    }

    /**
     * Sets the {@code definition} property in the builder.
     * @param definition  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder definition(SwapDefinition definition) {
      JodaBeanUtils.notNull(definition, "definition");
      this._definition = definition;
      return this;
    }

    /**
     * Sets the {@code type} property in the builder.
     * @param type  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder type(PayReceiveType type) {
      JodaBeanUtils.notNull(type, "type");
      this._type = type;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("CashFlowDetailsProvider.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("multicurveProviderInterface").append('=').append(JodaBeanUtils.toString(_multicurveProviderInterface)).append(',').append(' ');
      buf.append("zonedDateTime").append('=').append(JodaBeanUtils.toString(_zonedDateTime)).append(',').append(' ');
      buf.append("fixed").append('=').append(JodaBeanUtils.toString(_fixed)).append(',').append(' ');
      buf.append("definition").append('=').append(JodaBeanUtils.toString(_definition)).append(',').append(' ');
      buf.append("type").append('=').append(JodaBeanUtils.toString(_type)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
