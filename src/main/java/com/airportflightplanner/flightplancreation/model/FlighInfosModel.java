/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation.model;

import java.util.ArrayList;
import java.util.List;

import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosWriter;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FligthInfosProperties;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighInfosModel extends Model implements FlightInfosWriter {
  /**
   *
   */
  private static final long serialVersionUID = 9068391147414760708L;
  /** */
  private String            aircraftCie      = "";
  /** */
  private String            aircraftClass    = "";
  /** */
  private String            aircraftLivery   = "";
  /** */
  private List<String>      liveries         = new ArrayList<String>();
  /** */
  private List<String>      companies        = new ArrayList<String>();

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String getAircraftCie() {
    return aircraftCie;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String getAircraftClass() {
    return aircraftClass;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public String getAircraftLivery() {
    return aircraftLivery;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public List<String> getLiveries() {
    return liveries;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public List<String> getCompanies() {
    return companies;
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void setAircraftCie(final String value) {
    final String oldValue = getAircraftCie();
    if (null != value && !value.equals(oldValue)) {
      aircraftCie = value;
      firePropertyChange(FligthInfosProperties.AIRCRAFT_CIE, oldValue, aircraftCie);
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void setAircraftClass(final String value) {
    final String oldValue = getAircraftClass();
    if (null != value && !value.equals(oldValue)) {
      aircraftClass = value;
      firePropertyChange(FligthInfosProperties.AIRCRAFT_CLASS, oldValue, aircraftClass);
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void setAircraftLivery(final String value) {
    final String oldValue = getAircraftLivery();
    if (null != value && !value.equals(oldValue)) {
      aircraftLivery = value;
      firePropertyChange(FligthInfosProperties.AIRCRAFT_LIVERY, oldValue, aircraftLivery);
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void setLiveries(final List<String> value) {
    final List<String> oldValue = getLiveries();
    if (!value.equals(oldValue)) {
      liveries = value;
      firePropertyChange(FligthInfosProperties.LIVERIES, oldValue, liveries);
    }
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void setCompanies(final List<String> value) {
    final List<String> oldValue = getCompanies();
    if (!value.equals(oldValue)) {
      companies = value;
      firePropertyChange(FligthInfosProperties.COMPANIES, oldValue, companies);
    }
  }
}
