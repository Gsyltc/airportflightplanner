/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation.api.model.flightinfos;

import java.util.List;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightInfosWriter extends FlightInfosReader {
  /**
   * @param value
   *
   */
  void setAircraftClass(String value);

  /**
   * @param value
   */
  void setAircraftCie(String value);

  /**
   *
   * @param value
   */
  void setAircraftLivery(String value);

  /**
   *
   * @param value
   */
  void setLiveries(List<String> value);

  /**
   *
   * @param value
   */
  void setCompanies(List<String> value);
}
