/* @(#)AircraftsLiveriesMapper.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.airportflightplanner.common.adapter.AircraftTypeAdapter;

/**
 * @author DCNS
 *
 */
public class AircraftsLiveriesMapper {
  /** */
  private final String                         aircraftClass = "";
  /** */
  private final Map<String, SortedSet<String>> liveriesMap   = new HashMap<String, SortedSet<String>>();

  /**
   *
   * @return
   */
  public String getAircraftClass() {
    return aircraftClass;
  }

  public void addLivery(final String airCraftType) {
    final String cpie = AircraftTypeAdapter.getAircraftCie(airCraftType);
    if (!liveriesMap.containsKey(cpie)) {
      final SortedSet<String> liveries = new TreeSet<String>();
      liveries.add(airCraftType);
      liveriesMap.put(cpie, liveries);
    } else {
      final SortedSet<String> liveries = liveriesMap.get(cpie);
      liveries.add(airCraftType);
    }
  }

  /**
   *
   * @param aircraftCie
   * @return
   */
  public SortedSet<String> getLiveriesByCpie(final String aircraftCie) {
    if (liveriesMap.containsKey(aircraftCie)) {
      return Collections.unmodifiableSortedSet(liveriesMap.get(aircraftCie));
    }
    return Collections.emptySortedSet();
  }

  /**
   *
   * @return
   */
  public SortedSet<String> getCompagnies() {
    final SortedSet<String> sortedSet = new TreeSet<>(liveriesMap.keySet());
    // final List<String> companies = new ArrayList<String>();
    // for (final String key : sortedSet) {
    // companies.add(key);
    // }
    return Collections.unmodifiableSortedSet(sortedSet);
  }

}
