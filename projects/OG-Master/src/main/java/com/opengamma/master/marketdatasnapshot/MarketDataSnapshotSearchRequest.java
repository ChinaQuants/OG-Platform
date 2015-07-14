/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.master.marketdatasnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.master.AbstractDocument;
import com.opengamma.master.AbstractSearchRequest;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.PublicSPI;
import com.opengamma.util.RegexUtils;

/**
 * Request for searching for market data snapshots.
 * <p>
 * Documents will be returned that match the search criteria.
 * This class provides the ability to page the results and to search
 * as at a specific version and correction instant.
 */
@PublicSPI
@BeanDefinition
public class MarketDataSnapshotSearchRequest extends AbstractSearchRequest implements Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The set of marketDataSnapshot object identifiers, null to not limit by marketDataSnapshot object identifiers.
   * Note that an empty set will return no marketDataSnapshots.
   */
  @PropertyDefinition(set = "manual")
  private List<ObjectId> _snapshotIds;

  /**
   * The market data snapshot name, wildcards allowed, null to not match on name.
   */
  @PropertyDefinition
  private String _name;

  /**
   * The market data snapshot type, null to not match on type.
   */
  @PropertyDefinition
  private Class<? extends NamedSnapshot> _type;

  /**
   * The sort order to use.
   */
  @PropertyDefinition(validate = "notNull")
  private MarketDataSnapshotSearchSortOrder _sortOrder = MarketDataSnapshotSearchSortOrder.OBJECT_ID_ASC;

  /**
   * Whether to include the snapshot data in the search results.
   * Set to true to include the data, or false to omit it. Defaults to true.
   * Note that a master may ignore this value and always return the full data.
   */
  @PropertyDefinition
  private boolean _includeData = true;

  /**
   * Creates an instance.
   */
  public MarketDataSnapshotSearchRequest() {
  }

  //-------------------------------------------------------------------------
  /**
   * Adds a single marketDataSnapshot object identifier to the set.
   * 
   * @param marketDataSnapshotId  the marketDataSnapshot object identifier to add, not null
   */
  public void addMarketDataSnapshotId(ObjectIdentifiable marketDataSnapshotId) {
    ArgumentChecker.notNull(marketDataSnapshotId, "marketDataSnapshotId");
    if (_snapshotIds == null) {
      _snapshotIds = new ArrayList<>();
    }
    _snapshotIds.add(marketDataSnapshotId.getObjectId());
  }

  /**
   * Sets the set of marketDataSnapshot object identifiers, null to not limit by marketDataSnapshot object identifiers.
   * Note that an empty set will return no marketDataSnapshots.
   * 
   * @param marketDataSnapshotIds  the new marketDataSnapshot identifiers, null clears the marketDataSnapshot id search
   */
  public void setSnapshotIds(Iterable<? extends ObjectIdentifiable> marketDataSnapshotIds) {
    if (marketDataSnapshotIds == null) {
      _snapshotIds = null;
    } else {
      _snapshotIds = new ArrayList<>();
      for (ObjectIdentifiable marketDataSnapshotId : marketDataSnapshotIds) {
        _snapshotIds.add(marketDataSnapshotId.getObjectId());
      }
    }
  }

  //-------------------------------------------------------------------------
  @Override
  public boolean matches(final AbstractDocument obj) {
    if (obj instanceof MarketDataSnapshotDocument == false) {
      return false;
    }
    final MarketDataSnapshotDocument document = (MarketDataSnapshotDocument) obj;
    final NamedSnapshot marketDataSnapshot = document.getNamedSnapshot();
    if (getSnapshotIds() != null && getSnapshotIds().contains(document.getObjectId()) == false) {
      return false;
    }
    if (getName() != null && RegexUtils.wildcardMatch(getName(), marketDataSnapshot.getName()) == false) {
      return false;
    }
    if (getType() != null && !getType().isAssignableFrom(marketDataSnapshot.getClass())) {
      return false;
    }
    return true;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code MarketDataSnapshotSearchRequest}.
   * @return the meta-bean, not null
   */
  public static MarketDataSnapshotSearchRequest.Meta meta() {
    return MarketDataSnapshotSearchRequest.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(MarketDataSnapshotSearchRequest.Meta.INSTANCE);
  }

  @Override
  public MarketDataSnapshotSearchRequest.Meta metaBean() {
    return MarketDataSnapshotSearchRequest.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the set of marketDataSnapshot object identifiers, null to not limit by marketDataSnapshot object identifiers.
   * Note that an empty set will return no marketDataSnapshots.
   * @return the value of the property
   */
  public List<ObjectId> getSnapshotIds() {
    return _snapshotIds;
  }

  /**
   * Gets the the {@code snapshotIds} property.
   * Note that an empty set will return no marketDataSnapshots.
   * @return the property, not null
   */
  public final Property<List<ObjectId>> snapshotIds() {
    return metaBean().snapshotIds().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the market data snapshot name, wildcards allowed, null to not match on name.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the market data snapshot name, wildcards allowed, null to not match on name.
   * @param name  the new value of the property
   */
  public void setName(String name) {
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
  /**
   * Gets the market data snapshot type, null to not match on type.
   * @return the value of the property
   */
  public Class<? extends NamedSnapshot> getType() {
    return _type;
  }

  /**
   * Sets the market data snapshot type, null to not match on type.
   * @param type  the new value of the property
   */
  public void setType(Class<? extends NamedSnapshot> type) {
    this._type = type;
  }

  /**
   * Gets the the {@code type} property.
   * @return the property, not null
   */
  public final Property<Class<? extends NamedSnapshot>> type() {
    return metaBean().type().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the sort order to use.
   * @return the value of the property, not null
   */
  public MarketDataSnapshotSearchSortOrder getSortOrder() {
    return _sortOrder;
  }

  /**
   * Sets the sort order to use.
   * @param sortOrder  the new value of the property, not null
   */
  public void setSortOrder(MarketDataSnapshotSearchSortOrder sortOrder) {
    JodaBeanUtils.notNull(sortOrder, "sortOrder");
    this._sortOrder = sortOrder;
  }

  /**
   * Gets the the {@code sortOrder} property.
   * @return the property, not null
   */
  public final Property<MarketDataSnapshotSearchSortOrder> sortOrder() {
    return metaBean().sortOrder().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to include the snapshot data in the search results.
   * Set to true to include the data, or false to omit it. Defaults to true.
   * Note that a master may ignore this value and always return the full data.
   * @return the value of the property
   */
  public boolean isIncludeData() {
    return _includeData;
  }

  /**
   * Sets whether to include the snapshot data in the search results.
   * Set to true to include the data, or false to omit it. Defaults to true.
   * Note that a master may ignore this value and always return the full data.
   * @param includeData  the new value of the property
   */
  public void setIncludeData(boolean includeData) {
    this._includeData = includeData;
  }

  /**
   * Gets the the {@code includeData} property.
   * Set to true to include the data, or false to omit it. Defaults to true.
   * Note that a master may ignore this value and always return the full data.
   * @return the property, not null
   */
  public final Property<Boolean> includeData() {
    return metaBean().includeData().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public MarketDataSnapshotSearchRequest clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      MarketDataSnapshotSearchRequest other = (MarketDataSnapshotSearchRequest) obj;
      return JodaBeanUtils.equal(getSnapshotIds(), other.getSnapshotIds()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getType(), other.getType()) &&
          JodaBeanUtils.equal(getSortOrder(), other.getSortOrder()) &&
          (isIncludeData() == other.isIncludeData()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getSnapshotIds());
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getType());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSortOrder());
    hash = hash * 31 + JodaBeanUtils.hashCode(isIncludeData());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("MarketDataSnapshotSearchRequest{");
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
    buf.append("snapshotIds").append('=').append(JodaBeanUtils.toString(getSnapshotIds())).append(',').append(' ');
    buf.append("name").append('=').append(JodaBeanUtils.toString(getName())).append(',').append(' ');
    buf.append("type").append('=').append(JodaBeanUtils.toString(getType())).append(',').append(' ');
    buf.append("sortOrder").append('=').append(JodaBeanUtils.toString(getSortOrder())).append(',').append(' ');
    buf.append("includeData").append('=').append(JodaBeanUtils.toString(isIncludeData())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code MarketDataSnapshotSearchRequest}.
   */
  public static class Meta extends AbstractSearchRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code snapshotIds} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<ObjectId>> _snapshotIds = DirectMetaProperty.ofReadWrite(
        this, "snapshotIds", MarketDataSnapshotSearchRequest.class, (Class) List.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", MarketDataSnapshotSearchRequest.class, String.class);
    /**
     * The meta-property for the {@code type} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<? extends NamedSnapshot>> _type = DirectMetaProperty.ofReadWrite(
        this, "type", MarketDataSnapshotSearchRequest.class, (Class) Class.class);
    /**
     * The meta-property for the {@code sortOrder} property.
     */
    private final MetaProperty<MarketDataSnapshotSearchSortOrder> _sortOrder = DirectMetaProperty.ofReadWrite(
        this, "sortOrder", MarketDataSnapshotSearchRequest.class, MarketDataSnapshotSearchSortOrder.class);
    /**
     * The meta-property for the {@code includeData} property.
     */
    private final MetaProperty<Boolean> _includeData = DirectMetaProperty.ofReadWrite(
        this, "includeData", MarketDataSnapshotSearchRequest.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "snapshotIds",
        "name",
        "type",
        "sortOrder",
        "includeData");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -168607148:  // snapshotIds
          return _snapshotIds;
        case 3373707:  // name
          return _name;
        case 3575610:  // type
          return _type;
        case -26774448:  // sortOrder
          return _sortOrder;
        case 274670706:  // includeData
          return _includeData;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends MarketDataSnapshotSearchRequest> builder() {
      return new DirectBeanBuilder<MarketDataSnapshotSearchRequest>(new MarketDataSnapshotSearchRequest());
    }

    @Override
    public Class<? extends MarketDataSnapshotSearchRequest> beanType() {
      return MarketDataSnapshotSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code snapshotIds} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<ObjectId>> snapshotIds() {
      return _snapshotIds;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code type} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Class<? extends NamedSnapshot>> type() {
      return _type;
    }

    /**
     * The meta-property for the {@code sortOrder} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MarketDataSnapshotSearchSortOrder> sortOrder() {
      return _sortOrder;
    }

    /**
     * The meta-property for the {@code includeData} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> includeData() {
      return _includeData;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -168607148:  // snapshotIds
          return ((MarketDataSnapshotSearchRequest) bean).getSnapshotIds();
        case 3373707:  // name
          return ((MarketDataSnapshotSearchRequest) bean).getName();
        case 3575610:  // type
          return ((MarketDataSnapshotSearchRequest) bean).getType();
        case -26774448:  // sortOrder
          return ((MarketDataSnapshotSearchRequest) bean).getSortOrder();
        case 274670706:  // includeData
          return ((MarketDataSnapshotSearchRequest) bean).isIncludeData();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -168607148:  // snapshotIds
          ((MarketDataSnapshotSearchRequest) bean).setSnapshotIds((List<ObjectId>) newValue);
          return;
        case 3373707:  // name
          ((MarketDataSnapshotSearchRequest) bean).setName((String) newValue);
          return;
        case 3575610:  // type
          ((MarketDataSnapshotSearchRequest) bean).setType((Class<? extends NamedSnapshot>) newValue);
          return;
        case -26774448:  // sortOrder
          ((MarketDataSnapshotSearchRequest) bean).setSortOrder((MarketDataSnapshotSearchSortOrder) newValue);
          return;
        case 274670706:  // includeData
          ((MarketDataSnapshotSearchRequest) bean).setIncludeData((Boolean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((MarketDataSnapshotSearchRequest) bean)._sortOrder, "sortOrder");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
