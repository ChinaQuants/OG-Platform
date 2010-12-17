/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.master.position;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.beans.BeanDefinition;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;

import com.opengamma.id.IdentifierBundle;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.master.AbstractSearchRequest;

/**
 * Request for searching for positions.
 * <p>
 * Documents will be returned that match the search criteria.
 * This class provides the ability to page the results and to search
 * as at a specific version and correction instant.
 * See {@link PositionHistoryRequest} for more details on how history works.
 */
@BeanDefinition
public class PositionSearchRequest extends AbstractSearchRequest {

  /**
   * The list of position object identifiers, empty to not limit by position object identifiers.
   */
  @PropertyDefinition
  private final List<UniqueIdentifier> _positionIds = new ArrayList<UniqueIdentifier>();
  /**
   * The list of trade object identifiers, empty to not limit by trade object identifiers.
   * Each returned position will contain at least one of these trades.
   */
  @PropertyDefinition
  private final List<UniqueIdentifier> _tradeIds = new ArrayList<UniqueIdentifier>();
  /**
   * The minimum quantity, inclusive, null for no minimum.
   */
  @PropertyDefinition
  private BigDecimal _minQuantity;
  /**
   * The maximum quantity, exclusive, null for no maximum.
   */
  @PropertyDefinition
  private BigDecimal _maxQuantity;
  /**
   * The security key to match, null to not match on security key.
   */
  @PropertyDefinition
  private IdentifierBundle _securityKey;

  /**
   * Creates an instance.
   */
  public PositionSearchRequest() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code PositionSearchRequest}.
   * @return the meta-bean, not null
   */
  public static PositionSearchRequest.Meta meta() {
    return PositionSearchRequest.Meta.INSTANCE;
  }

  @Override
  public PositionSearchRequest.Meta metaBean() {
    return PositionSearchRequest.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName) {
    switch (propertyName.hashCode()) {
      case -137459505:  // positionIds
        return getPositionIds();
      case 1271202484:  // tradeIds
        return getTradeIds();
      case 69860605:  // minQuantity
        return getMinQuantity();
      case 747293199:  // maxQuantity
        return getMaxQuantity();
      case 1550083839:  // securityKey
        return getSecurityKey();
    }
    return super.propertyGet(propertyName);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue) {
    switch (propertyName.hashCode()) {
      case -137459505:  // positionIds
        setPositionIds((List<UniqueIdentifier>) newValue);
        return;
      case 1271202484:  // tradeIds
        setTradeIds((List<UniqueIdentifier>) newValue);
        return;
      case 69860605:  // minQuantity
        setMinQuantity((BigDecimal) newValue);
        return;
      case 747293199:  // maxQuantity
        setMaxQuantity((BigDecimal) newValue);
        return;
      case 1550083839:  // securityKey
        setSecurityKey((IdentifierBundle) newValue);
        return;
    }
    super.propertySet(propertyName, newValue);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the list of position object identifiers, empty to not limit by position object identifiers.
   * @return the value of the property
   */
  public List<UniqueIdentifier> getPositionIds() {
    return _positionIds;
  }

  /**
   * Sets the list of position object identifiers, empty to not limit by position object identifiers.
   * @param positionIds  the new value of the property
   */
  public void setPositionIds(List<UniqueIdentifier> positionIds) {
    this._positionIds.clear();
    this._positionIds.addAll(positionIds);
  }

  /**
   * Gets the the {@code positionIds} property.
   * @return the property, not null
   */
  public final Property<List<UniqueIdentifier>> positionIds() {
    return metaBean().positionIds().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the list of trade object identifiers, empty to not limit by trade object identifiers.
   * Each returned position will contain at least one of these trades.
   * @return the value of the property
   */
  public List<UniqueIdentifier> getTradeIds() {
    return _tradeIds;
  }

  /**
   * Sets the list of trade object identifiers, empty to not limit by trade object identifiers.
   * Each returned position will contain at least one of these trades.
   * @param tradeIds  the new value of the property
   */
  public void setTradeIds(List<UniqueIdentifier> tradeIds) {
    this._tradeIds.clear();
    this._tradeIds.addAll(tradeIds);
  }

  /**
   * Gets the the {@code tradeIds} property.
   * Each returned position will contain at least one of these trades.
   * @return the property, not null
   */
  public final Property<List<UniqueIdentifier>> tradeIds() {
    return metaBean().tradeIds().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the minimum quantity, inclusive, null for no minimum.
   * @return the value of the property
   */
  public BigDecimal getMinQuantity() {
    return _minQuantity;
  }

  /**
   * Sets the minimum quantity, inclusive, null for no minimum.
   * @param minQuantity  the new value of the property
   */
  public void setMinQuantity(BigDecimal minQuantity) {
    this._minQuantity = minQuantity;
  }

  /**
   * Gets the the {@code minQuantity} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> minQuantity() {
    return metaBean().minQuantity().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the maximum quantity, exclusive, null for no maximum.
   * @return the value of the property
   */
  public BigDecimal getMaxQuantity() {
    return _maxQuantity;
  }

  /**
   * Sets the maximum quantity, exclusive, null for no maximum.
   * @param maxQuantity  the new value of the property
   */
  public void setMaxQuantity(BigDecimal maxQuantity) {
    this._maxQuantity = maxQuantity;
  }

  /**
   * Gets the the {@code maxQuantity} property.
   * @return the property, not null
   */
  public final Property<BigDecimal> maxQuantity() {
    return metaBean().maxQuantity().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the security key to match, null to not match on security key.
   * @return the value of the property
   */
  public IdentifierBundle getSecurityKey() {
    return _securityKey;
  }

  /**
   * Sets the security key to match, null to not match on security key.
   * @param securityKey  the new value of the property
   */
  public void setSecurityKey(IdentifierBundle securityKey) {
    this._securityKey = securityKey;
  }

  /**
   * Gets the the {@code securityKey} property.
   * @return the property, not null
   */
  public final Property<IdentifierBundle> securityKey() {
    return metaBean().securityKey().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code PositionSearchRequest}.
   */
  public static class Meta extends AbstractSearchRequest.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code positionIds} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<UniqueIdentifier>> _positionIds = DirectMetaProperty.ofReadWrite(this, "positionIds", (Class) List.class);
    /**
     * The meta-property for the {@code tradeIds} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<UniqueIdentifier>> _tradeIds = DirectMetaProperty.ofReadWrite(this, "tradeIds", (Class) List.class);
    /**
     * The meta-property for the {@code minQuantity} property.
     */
    private final MetaProperty<BigDecimal> _minQuantity = DirectMetaProperty.ofReadWrite(this, "minQuantity", BigDecimal.class);
    /**
     * The meta-property for the {@code maxQuantity} property.
     */
    private final MetaProperty<BigDecimal> _maxQuantity = DirectMetaProperty.ofReadWrite(this, "maxQuantity", BigDecimal.class);
    /**
     * The meta-property for the {@code securityKey} property.
     */
    private final MetaProperty<IdentifierBundle> _securityKey = DirectMetaProperty.ofReadWrite(this, "securityKey", IdentifierBundle.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<Object>> _map;

    @SuppressWarnings({"unchecked", "rawtypes" })
    protected Meta() {
      LinkedHashMap temp = new LinkedHashMap(super.metaPropertyMap());
      temp.put("positionIds", _positionIds);
      temp.put("tradeIds", _tradeIds);
      temp.put("minQuantity", _minQuantity);
      temp.put("maxQuantity", _maxQuantity);
      temp.put("securityKey", _securityKey);
      _map = Collections.unmodifiableMap(temp);
    }

    @Override
    public PositionSearchRequest createBean() {
      return new PositionSearchRequest();
    }

    @Override
    public Class<? extends PositionSearchRequest> beanType() {
      return PositionSearchRequest.class;
    }

    @Override
    public Map<String, MetaProperty<Object>> metaPropertyMap() {
      return _map;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code positionIds} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<UniqueIdentifier>> positionIds() {
      return _positionIds;
    }

    /**
     * The meta-property for the {@code tradeIds} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<UniqueIdentifier>> tradeIds() {
      return _tradeIds;
    }

    /**
     * The meta-property for the {@code minQuantity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> minQuantity() {
      return _minQuantity;
    }

    /**
     * The meta-property for the {@code maxQuantity} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<BigDecimal> maxQuantity() {
      return _maxQuantity;
    }

    /**
     * The meta-property for the {@code securityKey} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<IdentifierBundle> securityKey() {
      return _securityKey;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
