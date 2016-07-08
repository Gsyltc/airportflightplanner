/* @(#)AircraftTypeModel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.adapter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.airportflightplanner.common.model.AircraftsLiveriesMapper;

/**
 * @author DCNS
 *
 */
public class AircraftTypeAdapter {
  /** */
  private static final Map<String, AircraftsLiveriesMapper> AIRCRAFT_CLASS_CIE = new HashMap<String, AircraftsLiveriesMapper>();

  /**
   *
   * @param airCraftType
   * @return
   */
  public static List<String> getAircraftLiveriesByType(final String airCraftType) {
    final AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(getAircraftClass(airCraftType));
    if (null != mapper) {
      return mapper.getLiveriesByCpie(getAircraftCie(airCraftType));
    }
    return Collections.emptyList();
  }
  //
  // /**
  // *
  // * @param airCraftType
  // * @return
  // */
  // public static List<String> getAircraftLiveriesByCompagnie(final String airCraftCompanie) {
  // final AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(getAircraftClass(airCraftType));
  // if (null != mapper) {
  // return mapper.getLiveriesByCpie(getAircraftCie(airCraftType));
  // }
  // return Collections.emptyList();
  // }

  /**
   *
   * @param airCraftType
   */
  public static void addLivery(final String airCraftType) {
    AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(getAircraftClass(airCraftType));
    if (null == mapper) {
      mapper = new AircraftsLiveriesMapper();
      AIRCRAFT_CLASS_CIE.put(getAircraftClass(airCraftType), mapper);

    }
    mapper.addLivery(airCraftType);

  }

  /**
   *
   * @return //
   */
  public static SortedSet<String> getAircraftClass() {
    return Collections.unmodifiableSortedSet(new TreeSet<>(AIRCRAFT_CLASS_CIE.keySet()));
  }

  /**
   *
   * @param airCraftType
   * @return
   */
  public static String getAircraftClass(final String airCraftType) {
    final String[] splitTmp = airCraftType.split(" +");

    final String[] splitAirCraft = splitTmp[0].split("_");
    return splitAirCraft[0];

  }

  /**
   *
   * @param airCraftType
   * @return
   */
  public static String getAircraftCie(final String airCraftType) {
    final String[] splitTmp = airCraftType.split(" +");

    final String[] splitAirCraft = splitTmp[0].split("_");
    if (splitAirCraft.length > 1) {
      return splitAirCraft[1];
    }
    return "";
  }

  /**
   *
   * @param airCraftClass
   * @param airCraftType
   * @return
   */
  public static String getAircraftLivery(final String airCraftClass, final String airCraftType) {
    final String[] splitTmp = airCraftType.split(" +");

    return splitTmp[0].replace(airCraftClass + "_", "");
  }

  /**
   *
   * @param aircraftType
   * @return
   */
  public static List<String> getAircraftCompaniesByType(final String aircraftType) {
    return getAircraftCompaniesByClass(getAircraftClass(aircraftType));
  }

  /**
   *
   * @param aircraftClass
   * @return
   */
  public static List<String> getAircraftCompaniesByClass(final String aircraftClass) {
    final AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(aircraftClass);
    if (null != mapper) {
      return mapper.getCompagnies();
    }
    return Collections.emptyList();
  }

}
