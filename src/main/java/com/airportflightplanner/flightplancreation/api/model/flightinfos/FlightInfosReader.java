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
public interface FlightInfosReader {

  /**
   *
   * @return
   */
  String getAircraftClass();

  /**
   *
   * @return
   */
  String getAircraftCie();

  /**
   *
   * @return
   */
  String getAircraftLivery();

  /**
   *
   * @return
   */
  List<String> getLiveries();

  /**
   *
   * @return
   */
  List<String> getCompanies();
}
