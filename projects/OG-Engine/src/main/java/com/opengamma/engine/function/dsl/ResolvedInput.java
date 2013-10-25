/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.engine.function.dsl;

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

/**
 * The resolved input.
 */
@BeanDefinition
public class ResolvedInput extends DirectBean {

  @PropertyDefinition(validate = "notNull")
  private Map<String, Object> _properties;

  @PropertyDefinition(validate = "notNull")
  private String _name;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedInput}.
   * @return the meta-bean, not null
   */
  public static ResolvedInput.Meta meta() {
    return ResolvedInput.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedInput.Meta.INSTANCE);
  }

  @Override
  public ResolvedInput.Meta metaBean() {
    return ResolvedInput.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the properties.
   * @return the value of the property, not null
   */
  public Map<String, Object> getProperties() {
    return _properties;
  }

  /**
   * Sets the properties.
   * @param properties  the new value of the property, not null
   */
  public void setProperties(Map<String, Object> properties) {
    JodaBeanUtils.notNull(properties, "properties");
    this._properties = properties;
  }

  /**
   * Gets the the {@code properties} property.
   * @return the property, not null
   */
  public final Property<Map<String, Object>> properties() {
    return metaBean().properties().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name.
   * @param name  the new value of the property, not null
   */
  public void setName(String name) {
    JodaBeanUtils.notNull(name, "name");
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ResolvedInput clone() {
    BeanBuilder<? extends ResolvedInput> builder = metaBean().builder();
    for (MetaProperty<?> mp : metaBean().metaPropertyIterable()) {
      if (mp.style().isBuildable()) {
        Object value = mp.get(this);
        if (value instanceof Bean) {
          value = ((Bean) value).clone();
        }
        builder.set(mp.name(), value);
      }
    }
    return builder.build();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ResolvedInput other = (ResolvedInput) obj;
      return JodaBeanUtils.equal(getProperties(), other.getProperties()) &&
          JodaBeanUtils.equal(getName(), other.getName());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getProperties());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("ResolvedInput{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("properties").append('=').append(JodaBeanUtils.toString(getProperties())).append(',').append(' ');
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedInput}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code properties} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<String, Object>> _properties = DirectMetaProperty.ofReadWrite(
        this, "properties", ResolvedInput.class, (Class) Map.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", ResolvedInput.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "properties",
        "name");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -926053069:  // properties
          return _properties;
        case 3373707:  // name
          return _name;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ResolvedInput> builder() {
      return new DirectBeanBuilder<ResolvedInput>(new ResolvedInput());
    }

    @Override
    public Class<? extends ResolvedInput> beanType() {
      return ResolvedInput.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code properties} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<String, Object>> properties() {
      return _properties;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -926053069:  // properties
          return ((ResolvedInput) bean).getProperties();
        case 3373707:  // name
          return ((ResolvedInput) bean).getName();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -926053069:  // properties
          ((ResolvedInput) bean).setProperties((Map<String, Object>) newValue);
          return;
        case 3373707:  // name
          ((ResolvedInput) bean).setName((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ResolvedInput) bean)._properties, "properties");
      JodaBeanUtils.notNull(((ResolvedInput) bean)._name, "name");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
