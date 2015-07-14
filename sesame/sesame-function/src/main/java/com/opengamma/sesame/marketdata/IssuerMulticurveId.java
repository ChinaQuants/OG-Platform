/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.marketdata;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
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

import com.opengamma.core.link.ConfigLink;
import com.opengamma.financial.analytics.curve.CurveConstructionConfiguration;
import com.opengamma.sesame.IssuerProviderBundle;
import com.opengamma.util.ArgumentChecker;

/**
 * Identifier of a bundle of curves.
 */
@BeanDefinition(builderScope = "private")
public final class IssuerMulticurveId implements MarketDataId<IssuerProviderBundle>, ImmutableBean {

  /** The name of the bundle. */
  @PropertyDefinition(validate = "notEmpty")
  private final String _name;

  /** Link to the configuration for building the bundle. */
  @PropertyDefinition(validate = "notNull", get = "private")
  private final ConfigLink<CurveConstructionConfiguration> _curveConfigLink;

  private IssuerMulticurveId(String name) {
    this(name, ConfigLink.resolvable(name, CurveConstructionConfiguration.class));
  }

  private IssuerMulticurveId(CurveConstructionConfiguration config) {
    this(ArgumentChecker.notNull(config, "config").getName(), ConfigLink.resolved(config));
  }

  @Override
  public Class<IssuerProviderBundle> getMarketDataType() {
    return IssuerProviderBundle.class;
  }

  /**
   * Creates an ID for the curve bundle with the specified name.
   *
   * @param name the curve bundle name
   * @return an ID for the curve bundle
   */
  public static IssuerMulticurveId of(String name) {
    return new IssuerMulticurveId(name);
  }

  /**
   * Creates an ID for the curve bundle built from the specified configuration
   *
   * @param config configuration for building the curve bundle
   * @return an ID for the curve bundle
   */
  public static IssuerMulticurveId of(CurveConstructionConfiguration config) {
    return new IssuerMulticurveId(config);
  }

  /**
   * Resolves and returns the configuration for the curve.
   * <p>
   * Resolution needs to be done lazily as an ID can be created outside the engine when pre-populating a
   * {@code MarketDataEnvironment}. If resolution were done eagerly it would fail.
   *
   * @return the configuration for the curve
   */
  CurveConstructionConfiguration getConfig() {
    return _curveConfigLink.resolve();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code IssuerMulticurveId}.
   * @return the meta-bean, not null
   */
  public static IssuerMulticurveId.Meta meta() {
    return IssuerMulticurveId.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(IssuerMulticurveId.Meta.INSTANCE);
  }

  private IssuerMulticurveId(
      String name,
      ConfigLink<CurveConstructionConfiguration> curveConfigLink) {
    JodaBeanUtils.notEmpty(name, "name");
    JodaBeanUtils.notNull(curveConfigLink, "curveConfigLink");
    this._name = name;
    this._curveConfigLink = curveConfigLink;
  }

  @Override
  public IssuerMulticurveId.Meta metaBean() {
    return IssuerMulticurveId.Meta.INSTANCE;
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
   * Gets the name of the bundle.
   * @return the value of the property, not empty
   */
  public String getName() {
    return _name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets link to the configuration for building the bundle.
   * @return the value of the property, not null
   */
  private ConfigLink<CurveConstructionConfiguration> getCurveConfigLink() {
    return _curveConfigLink;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      IssuerMulticurveId other = (IssuerMulticurveId) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getCurveConfigLink(), other.getCurveConfigLink());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurveConfigLink());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("IssuerMulticurveId{");
    buf.append("name").append('=').append(getName()).append(',').append(' ');
    buf.append("curveConfigLink").append('=').append(JodaBeanUtils.toString(getCurveConfigLink()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code IssuerMulticurveId}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofImmutable(
        this, "name", IssuerMulticurveId.class, String.class);
    /**
     * The meta-property for the {@code curveConfigLink} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ConfigLink<CurveConstructionConfiguration>> _curveConfigLink = DirectMetaProperty.ofImmutable(
        this, "curveConfigLink", IssuerMulticurveId.class, (Class) ConfigLink.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "curveConfigLink");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case 587920555:  // curveConfigLink
          return _curveConfigLink;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends IssuerMulticurveId> builder() {
      return new IssuerMulticurveId.Builder();
    }

    @Override
    public Class<? extends IssuerMulticurveId> beanType() {
      return IssuerMulticurveId.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code curveConfigLink} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ConfigLink<CurveConstructionConfiguration>> curveConfigLink() {
      return _curveConfigLink;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((IssuerMulticurveId) bean).getName();
        case 587920555:  // curveConfigLink
          return ((IssuerMulticurveId) bean).getCurveConfigLink();
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
   * The bean-builder for {@code IssuerMulticurveId}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<IssuerMulticurveId> {

    private String _name;
    private ConfigLink<CurveConstructionConfiguration> _curveConfigLink;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case 587920555:  // curveConfigLink
          return _curveConfigLink;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          this._name = (String) newValue;
          break;
        case 587920555:  // curveConfigLink
          this._curveConfigLink = (ConfigLink<CurveConstructionConfiguration>) newValue;
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
    public IssuerMulticurveId build() {
      return new IssuerMulticurveId(
          _name,
          _curveConfigLink);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("IssuerMulticurveId.Builder{");
      buf.append("name").append('=').append(JodaBeanUtils.toString(_name)).append(',').append(' ');
      buf.append("curveConfigLink").append('=').append(JodaBeanUtils.toString(_curveConfigLink));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
