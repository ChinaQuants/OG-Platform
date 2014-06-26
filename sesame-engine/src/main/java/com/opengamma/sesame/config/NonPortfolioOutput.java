/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.sesame.config;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.ArgumentChecker;

/**
 * Configuration object that defines a stand-alone output value.
 * <p>
 * Contains the output name and the configuration.
 */
@BeanDefinition
public class NonPortfolioOutput implements ImmutableBean {

  /**
   * The stand-alone output name.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _name;

  /**
   * The output configuration.
   */
  @PropertyDefinition(validate = "notNull")
  private final ViewOutput _output;

  @ImmutableConstructor
  public NonPortfolioOutput(String name, ViewOutput output) {
    _name = ArgumentChecker.notNull(name, "name");
    _output = ArgumentChecker.notNull(output, "output");
    ArgumentChecker.notNull(output.getOutputName(), "output.getOutputName()");
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code NonPortfolioOutput}.
   * @return the meta-bean, not null
   */
  public static NonPortfolioOutput.Meta meta() {
    return NonPortfolioOutput.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(NonPortfolioOutput.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static NonPortfolioOutput.Builder builder() {
    return new NonPortfolioOutput.Builder();
  }

  @Override
  public NonPortfolioOutput.Meta metaBean() {
    return NonPortfolioOutput.Meta.INSTANCE;
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
   * Gets the stand-alone output name.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the output configuration.
   * @return the value of the property, not null
   */
  public ViewOutput getOutput() {
    return _output;
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
      NonPortfolioOutput other = (NonPortfolioOutput) obj;
      return JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getOutput(), other.getOutput());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getOutput());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("NonPortfolioOutput{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("output").append('=').append(JodaBeanUtils.toString(getOutput())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code NonPortfolioOutput}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofImmutable(
        this, "name", NonPortfolioOutput.class, String.class);
    /**
     * The meta-property for the {@code output} property.
     */
    private final MetaProperty<ViewOutput> _output = DirectMetaProperty.ofImmutable(
        this, "output", NonPortfolioOutput.class, ViewOutput.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "name",
        "output");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case -1005512447:  // output
          return _output;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public NonPortfolioOutput.Builder builder() {
      return new NonPortfolioOutput.Builder();
    }

    @Override
    public Class<? extends NonPortfolioOutput> beanType() {
      return NonPortfolioOutput.class;
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
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code output} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ViewOutput> output() {
      return _output;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return ((NonPortfolioOutput) bean).getName();
        case -1005512447:  // output
          return ((NonPortfolioOutput) bean).getOutput();
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
   * The bean-builder for {@code NonPortfolioOutput}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<NonPortfolioOutput> {

    private String _name;
    private ViewOutput _output;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(NonPortfolioOutput beanToCopy) {
      this._name = beanToCopy.getName();
      this._output = beanToCopy.getOutput();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          return _name;
        case -1005512447:  // output
          return _output;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 3373707:  // name
          this._name = (String) newValue;
          break;
        case -1005512447:  // output
          this._output = (ViewOutput) newValue;
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
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public NonPortfolioOutput build() {
      return new NonPortfolioOutput(
          _name,
          _output);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the {@code name} property in the builder.
     * @param name  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder name(String name) {
      JodaBeanUtils.notNull(name, "name");
      this._name = name;
      return this;
    }

    /**
     * Sets the {@code output} property in the builder.
     * @param output  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder output(ViewOutput output) {
      JodaBeanUtils.notNull(output, "output");
      this._output = output;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("NonPortfolioOutput.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("name").append('=').append(JodaBeanUtils.toString(_name)).append(',').append(' ');
      buf.append("output").append('=').append(JodaBeanUtils.toString(_output)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
