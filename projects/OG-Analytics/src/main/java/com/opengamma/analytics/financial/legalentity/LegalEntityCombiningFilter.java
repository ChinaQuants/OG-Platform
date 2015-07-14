/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.analytics.financial.legalentity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.types.ParameterizedTypeImpl;
import com.opengamma.util.types.VariantType;

/**
 * Combines a number of {@link LegalEntityFilter}s.
 */
@BeanDefinition
public class LegalEntityCombiningFilter implements LegalEntityFilter<LegalEntity>, Bean {

  /** Serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * The filters to use
   */
  @PropertyDefinition(validate = "notNull")
  private Set<LegalEntityFilter<LegalEntity>> _filtersToUse;

  /**
   * For the builder.
   */
  public LegalEntityCombiningFilter() {
    setFiltersToUse(Collections.<LegalEntityFilter<LegalEntity>>emptySet());
  }

  /**
   * @param filtersToUse The filters, not null
   */
  public LegalEntityCombiningFilter(final Set<LegalEntityFilter<LegalEntity>> filtersToUse) {
    setFiltersToUse(filtersToUse);
  }

  @Override
  public Object getFilteredData(final LegalEntity legalEntity) {
    ArgumentChecker.notNull(legalEntity, "legal entity");
    final Set<Object> selections = new HashSet<>();
    for (final LegalEntityFilter<LegalEntity> filter : _filtersToUse) {
      final Object filteredData = filter.getFilteredData(legalEntity);
      if (filteredData instanceof Set<?>) {
        selections.addAll((Set<?>) filteredData);
      } else {
        selections.add(filteredData);
      }
    }
    return selections;
  }

  private Type applyUsedFilterDataType(Type before, final Type filteredType) {
    if (filteredType instanceof VariantType) {
      for (Type type : ((VariantType) filteredType).getLogicalTypes()) {
        before = applyUsedFilterDataType(before, type);
      }
    } else if (filteredType instanceof ParameterizedType) {
      final ParameterizedType parameterizedType = (ParameterizedType) filteredType;
      final Type rawType = parameterizedType.getRawType();
      if ((rawType instanceof Class) && Set.class.isAssignableFrom((Class<?>) rawType)) {
        final Type setMember = parameterizedType.getActualTypeArguments()[0];
        if (setMember != Void.class) {
          before = VariantType.either(before, setMember);
        }
      } else {
        before = VariantType.either(before, filteredType);
      }
    } else if (filteredType instanceof Class) {
      before = VariantType.either(before, filteredType);
    } else {
      throw new UnsupportedOperationException("filtered type = " + filteredType);
    }
    return before;
  }

  @Override
  public Type getFilteredDataType() {
    if (_filtersToUse.isEmpty()) {
      return ParameterizedTypeImpl.of(Set.class, Void.class);
    } else {
      Type setMember = null;
      for (LegalEntityFilter<LegalEntity> filter : _filtersToUse) {
        setMember = applyUsedFilterDataType(setMember, filter.getFilteredDataType());
      }
      return ParameterizedTypeImpl.of(Set.class, setMember);
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code LegalEntityCombiningFilter}.
   * @return the meta-bean, not null
   */
  public static LegalEntityCombiningFilter.Meta meta() {
    return LegalEntityCombiningFilter.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(LegalEntityCombiningFilter.Meta.INSTANCE);
  }

  @Override
  public LegalEntityCombiningFilter.Meta metaBean() {
    return LegalEntityCombiningFilter.Meta.INSTANCE;
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
   * Gets the filters to use
   * @return the value of the property, not null
   */
  public Set<LegalEntityFilter<LegalEntity>> getFiltersToUse() {
    return _filtersToUse;
  }

  /**
   * Sets the filters to use
   * @param filtersToUse  the new value of the property, not null
   */
  public void setFiltersToUse(Set<LegalEntityFilter<LegalEntity>> filtersToUse) {
    JodaBeanUtils.notNull(filtersToUse, "filtersToUse");
    this._filtersToUse = filtersToUse;
  }

  /**
   * Gets the the {@code filtersToUse} property.
   * @return the property, not null
   */
  public final Property<Set<LegalEntityFilter<LegalEntity>>> filtersToUse() {
    return metaBean().filtersToUse().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public LegalEntityCombiningFilter clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      LegalEntityCombiningFilter other = (LegalEntityCombiningFilter) obj;
      return JodaBeanUtils.equal(getFiltersToUse(), other.getFiltersToUse());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getFiltersToUse());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("LegalEntityCombiningFilter{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("filtersToUse").append('=').append(JodaBeanUtils.toString(getFiltersToUse())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code LegalEntityCombiningFilter}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code filtersToUse} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Set<LegalEntityFilter<LegalEntity>>> _filtersToUse = DirectMetaProperty.ofReadWrite(
        this, "filtersToUse", LegalEntityCombiningFilter.class, (Class) Set.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "filtersToUse");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1274996271:  // filtersToUse
          return _filtersToUse;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends LegalEntityCombiningFilter> builder() {
      return new DirectBeanBuilder<LegalEntityCombiningFilter>(new LegalEntityCombiningFilter());
    }

    @Override
    public Class<? extends LegalEntityCombiningFilter> beanType() {
      return LegalEntityCombiningFilter.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code filtersToUse} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Set<LegalEntityFilter<LegalEntity>>> filtersToUse() {
      return _filtersToUse;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1274996271:  // filtersToUse
          return ((LegalEntityCombiningFilter) bean).getFiltersToUse();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1274996271:  // filtersToUse
          ((LegalEntityCombiningFilter) bean).setFiltersToUse((Set<LegalEntityFilter<LegalEntity>>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((LegalEntityCombiningFilter) bean)._filtersToUse, "filtersToUse");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
