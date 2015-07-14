/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.source;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;

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

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.core.config.ConfigSource;
import com.opengamma.core.config.impl.DataConfigSourceResource;
import com.opengamma.core.config.impl.RemoteConfigSource;
import com.opengamma.master.config.ConfigMaster;
import com.opengamma.master.config.impl.EHCachingMasterConfigSource;
import com.opengamma.master.config.impl.MasterConfigSource;

/**
 * Component factory providing the {@code ConfigSource}.
 */
@BeanDefinition
public class ConfigSourceComponentFactory extends AbstractComponentFactory {

  /**
   * The classifier that the factory should publish under.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The flag determining whether the component should be published by REST (default true).
   */
  @PropertyDefinition
  private boolean _publishRest = true;
  /**
   * The underlying config master.
   */
  @PropertyDefinition(validate = "notNull")
  private ConfigMaster _configMaster;
  /**
   * The cache manager.
   */
  @PropertyDefinition
  private CacheManager _cacheManager;

  //-------------------------------------------------------------------------
  /**
   * Initializes the config source, setting up component information and REST.
   * Override using {@link #createConfigSource(ComponentRepository)}.
   * 
   * @param repo  the component repository, not null
   * @param configuration  the remaining configuration, not null
   */
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) {
    ConfigSource source = createConfigSource(repo);
    
    ComponentInfo info = new ComponentInfo(ConfigSource.class, getClassifier());
    info.addAttribute(ComponentInfoAttributes.LEVEL, 1);
    info.addAttribute(ComponentInfoAttributes.REMOTE_CLIENT_JAVA, RemoteConfigSource.class);
    repo.registerComponent(info, source);
    if (isPublishRest()) {
      repo.getRestComponents().publish(info, new DataConfigSourceResource(source));
    }
  }

  /**
   * Creates the config source without registering it.
   * 
   * @param repo  the component repository, only used to register secondary items like lifecycle, not null
   * @return the config source, not null
   */
  protected ConfigSource createConfigSource(ComponentRepository repo) {
    ConfigSource source = new MasterConfigSource(getConfigMaster());
    if (getCacheManager() != null) {
      source = new EHCachingMasterConfigSource(getConfigMaster(), getCacheManager());
    }
    return source;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ConfigSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ConfigSourceComponentFactory.Meta meta() {
    return ConfigSourceComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ConfigSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ConfigSourceComponentFactory.Meta metaBean() {
    return ConfigSourceComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier that the factory should publish under.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier that the factory should publish under.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the flag determining whether the component should be published by REST (default true).
   * @return the value of the property
   */
  public boolean isPublishRest() {
    return _publishRest;
  }

  /**
   * Sets the flag determining whether the component should be published by REST (default true).
   * @param publishRest  the new value of the property
   */
  public void setPublishRest(boolean publishRest) {
    this._publishRest = publishRest;
  }

  /**
   * Gets the the {@code publishRest} property.
   * @return the property, not null
   */
  public final Property<Boolean> publishRest() {
    return metaBean().publishRest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying config master.
   * @return the value of the property, not null
   */
  public ConfigMaster getConfigMaster() {
    return _configMaster;
  }

  /**
   * Sets the underlying config master.
   * @param configMaster  the new value of the property, not null
   */
  public void setConfigMaster(ConfigMaster configMaster) {
    JodaBeanUtils.notNull(configMaster, "configMaster");
    this._configMaster = configMaster;
  }

  /**
   * Gets the the {@code configMaster} property.
   * @return the property, not null
   */
  public final Property<ConfigMaster> configMaster() {
    return metaBean().configMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cache manager.
   * @return the value of the property
   */
  public CacheManager getCacheManager() {
    return _cacheManager;
  }

  /**
   * Sets the cache manager.
   * @param cacheManager  the new value of the property
   */
  public void setCacheManager(CacheManager cacheManager) {
    this._cacheManager = cacheManager;
  }

  /**
   * Gets the the {@code cacheManager} property.
   * @return the property, not null
   */
  public final Property<CacheManager> cacheManager() {
    return metaBean().cacheManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ConfigSourceComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ConfigSourceComponentFactory other = (ConfigSourceComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          (isPublishRest() == other.isPublishRest()) &&
          JodaBeanUtils.equal(getConfigMaster(), other.getConfigMaster()) &&
          JodaBeanUtils.equal(getCacheManager(), other.getCacheManager()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(isPublishRest());
    hash = hash * 31 + JodaBeanUtils.hashCode(getConfigMaster());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCacheManager());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("ConfigSourceComponentFactory{");
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
    buf.append("classifier").append('=').append(JodaBeanUtils.toString(getClassifier())).append(',').append(' ');
    buf.append("publishRest").append('=').append(JodaBeanUtils.toString(isPublishRest())).append(',').append(' ');
    buf.append("configMaster").append('=').append(JodaBeanUtils.toString(getConfigMaster())).append(',').append(' ');
    buf.append("cacheManager").append('=').append(JodaBeanUtils.toString(getCacheManager())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ConfigSourceComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", ConfigSourceComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code publishRest} property.
     */
    private final MetaProperty<Boolean> _publishRest = DirectMetaProperty.ofReadWrite(
        this, "publishRest", ConfigSourceComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code configMaster} property.
     */
    private final MetaProperty<ConfigMaster> _configMaster = DirectMetaProperty.ofReadWrite(
        this, "configMaster", ConfigSourceComponentFactory.class, ConfigMaster.class);
    /**
     * The meta-property for the {@code cacheManager} property.
     */
    private final MetaProperty<CacheManager> _cacheManager = DirectMetaProperty.ofReadWrite(
        this, "cacheManager", ConfigSourceComponentFactory.class, CacheManager.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "publishRest",
        "configMaster",
        "cacheManager");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -614707837:  // publishRest
          return _publishRest;
        case 10395716:  // configMaster
          return _configMaster;
        case -1452875317:  // cacheManager
          return _cacheManager;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ConfigSourceComponentFactory> builder() {
      return new DirectBeanBuilder<ConfigSourceComponentFactory>(new ConfigSourceComponentFactory());
    }

    @Override
    public Class<? extends ConfigSourceComponentFactory> beanType() {
      return ConfigSourceComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code publishRest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> publishRest() {
      return _publishRest;
    }

    /**
     * The meta-property for the {@code configMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ConfigMaster> configMaster() {
      return _configMaster;
    }

    /**
     * The meta-property for the {@code cacheManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CacheManager> cacheManager() {
      return _cacheManager;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((ConfigSourceComponentFactory) bean).getClassifier();
        case -614707837:  // publishRest
          return ((ConfigSourceComponentFactory) bean).isPublishRest();
        case 10395716:  // configMaster
          return ((ConfigSourceComponentFactory) bean).getConfigMaster();
        case -1452875317:  // cacheManager
          return ((ConfigSourceComponentFactory) bean).getCacheManager();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((ConfigSourceComponentFactory) bean).setClassifier((String) newValue);
          return;
        case -614707837:  // publishRest
          ((ConfigSourceComponentFactory) bean).setPublishRest((Boolean) newValue);
          return;
        case 10395716:  // configMaster
          ((ConfigSourceComponentFactory) bean).setConfigMaster((ConfigMaster) newValue);
          return;
        case -1452875317:  // cacheManager
          ((ConfigSourceComponentFactory) bean).setCacheManager((CacheManager) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ConfigSourceComponentFactory) bean)._classifier, "classifier");
      JodaBeanUtils.notNull(((ConfigSourceComponentFactory) bean)._configMaster, "configMaster");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
