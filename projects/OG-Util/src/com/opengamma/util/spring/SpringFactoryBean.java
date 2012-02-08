/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.spring;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Spring factory bean that builds on Joda-Beans.
 * <p>
 * This provides a singleton factory bean, where the configuration can be set using Joda-Beans.
 * 
 * @param <T> the type
 */
@BeanDefinition
public abstract class SpringFactoryBean<T> extends DirectBean implements FactoryBean<T>, InitializingBean {

  /**
   * The created type.
   */
  private final Class<T> _type;
  /**
   * The created object.
   */
  private T _object;

  /**
   * Creates an instance.
   * 
   * @param type  the type of the factory, not null
   */
  public SpringFactoryBean(Class<T> type) {
    _type = type;
  }

  //-------------------------------------------------------------------------
  @Override
  public final void afterPropertiesSet() throws Exception {
    _object = createObject();
  }

  @Override
  public final T getObject() {
    return _object;
  }

  /**
   * Gets the object, creating if necessary.
   * 
   * @return the object
   */
  public final T getObjectCreating() {
    T object = getObject();
    if (object == null) {
      object = createObject();
    }
    return object;
  }

  @Override
  public final Class<?> getObjectType() {
    return _type;
  }

  @Override
  public final boolean isSingleton() {
    return true;
  }

  /**
   * Override to create the singleton.
   * 
   * @return the singleton, not null
   */
  protected abstract T createObject();

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SpringFactoryBean}.
   * @param <R>  the bean's generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R> SpringFactoryBean.Meta<R> meta() {
    return SpringFactoryBean.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SpringFactoryBean.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public SpringFactoryBean.Meta<T> metaBean() {
    return SpringFactoryBean.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SpringFactoryBean}.
   */
  public static class Meta<T> extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends SpringFactoryBean<T>> builder() {
      throw new UnsupportedOperationException("SpringFactoryBean is an abstract class");
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends SpringFactoryBean<T>> beanType() {
      return (Class) SpringFactoryBean.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
