/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.master.holiday.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.threeten.bp.LocalDate;

import com.google.common.collect.Maps;
import com.opengamma.core.holiday.Holiday;
import com.opengamma.core.holiday.HolidaySource;
import com.opengamma.core.holiday.HolidayType;
import com.opengamma.id.ExternalId;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ObjectId;
import com.opengamma.id.UniqueId;
import com.opengamma.id.VersionCorrection;
import com.opengamma.util.money.Currency;
import com.opengamma.util.tuple.Pair;
import com.opengamma.util.tuple.Pairs;

/**
 * Simple implementation of a container of holidays per calendar relying on a map. The putAll method can be used to
 * add calendars and their corresponding holidays to the store. The other methods are part of the {@link HolidaySource}
 * interface and allow to check whether a date is a holiday for a calendar or region.
 * 
 * The methods to get a collection of holidays for a currency or to check with a date is a holiday for a currency 
 * (rather than a calendar) are not implemented.
 */
public class SimpleInMemoryHolidayStore implements HolidaySource {

  private Map<Pair<UniqueId, HolidayType>, Holiday> _calendarHolidays = Maps.newHashMap();
 
  /**
   * @param calendarHolidays a map from a calendar or region id to a holiday that will be added to the store
   */
  public void putAll(Map<UniqueId, Holiday> calendarHolidays) {
    for (Entry<UniqueId, Holiday> entry : calendarHolidays.entrySet()) {
      Holiday holiday = entry.getValue();
      
      _calendarHolidays.put(Pairs.of(entry.getKey(), holiday.getType()), holiday);
    }
  }
  
  @Override
  public Holiday get(UniqueId uniqueId) {
    return _calendarHolidays.get(uniqueId);
  }

  @Override
  public Holiday get(ObjectId objectId, VersionCorrection versionCorrection) {
    return _calendarHolidays.get(UniqueId.of(objectId, versionCorrection.getVersionAsOfString()));
  }

  @Override
  public Map<UniqueId, Holiday> get(Collection<UniqueId> uniqueIds) {
    Map<UniqueId, Holiday> holidays = Maps.newHashMap();
    
    for (UniqueId id : uniqueIds) {
      holidays.put(id, _calendarHolidays.get(id));
    }
    return holidays;
  }

  @Override
  public Map<ObjectId, Holiday> get(Collection<ObjectId> objectIds, VersionCorrection versionCorrection) {
    Map<ObjectId, Holiday> holidays = Maps.newHashMap();
    
    for (ObjectId obj : objectIds) {
      holidays.put(obj, _calendarHolidays.get(UniqueId.of(obj, versionCorrection.getVersionAsOfString())));
    }
    return holidays;
  }

  @Override
  public Set<Holiday> get(HolidayType holidayType, ExternalIdBundle regionOrExchangeIds) {
    Set<Holiday> holidays = new HashSet<>();
    
    for (ExternalId id : regionOrExchangeIds.getExternalIds()) {
      Pair<ExternalId, HolidayType> key = Pairs.of(id, holidayType);
      
      if (_calendarHolidays.containsKey(key)) {
        holidays.add(_calendarHolidays.get(key));
      }
    }
    return holidays;
  }

  @Override
  public Collection<Holiday> get(Currency currency) {
    throw new UnsupportedOperationException("Cannot check if date is a holiday without a calendar or region id");
  }

  @Override
  public boolean isHoliday(LocalDate dateToCheck, Currency currency) {
    throw new UnsupportedOperationException("Cannot check if date is a holiday without a calendar or region id");
  }

  @Override
  public boolean isHoliday(LocalDate dateToCheck, HolidayType holidayType, ExternalIdBundle regionOrExchangeIds) {
    if (isWeekend(dateToCheck)) {
      return true;
    }
    
    for (ExternalId id : regionOrExchangeIds.getExternalIds()) {
      if (isHoliday(dateToCheck, holidayType, id)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isHoliday(LocalDate dateToCheck, HolidayType holidayType, ExternalId regionOrExchangeId) {
    return isWeekend(dateToCheck) || isHoliday(dateToCheck, Pairs.of(regionOrExchangeId, holidayType));
  }
  
  private boolean isHoliday(LocalDate dateToCheck, Pair<ExternalId, HolidayType> key) {
    if (_calendarHolidays.containsKey(key)) {
      return _calendarHolidays.get(key).getHolidayDates().contains(dateToCheck);
    } else {
      return false;
    }
  }
  
  /**
   * Checks if the date is at the weekend, defined as a Saturday or Sunday.
   * 
   * @param date the date to check, not null
   * @return true if it is a weekend
   */
  private boolean isWeekend(LocalDate date) {
    // avoids calling date.getDayOfWeek() twice
    return date.getDayOfWeek().getValue() >= 6;
  }

}
