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
 * @author Goubaud Sylvain
 *
 */
public class AircraftsLiveriesMapper {
    /** */
    private final Map<String, SortedSet<String>> liveriesMap = new HashMap<String, SortedSet<String>>();

    /**
     *
     * @param airCraftType
     *            aircraft type.
     */
    public final void addLivery(final String airCraftType) {
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
     *            aircraft companu.
     * @return list of liveries for the company.
     */
    public final SortedSet<String> getLiveriesByCpie(final String aircraftCie) {
        if (liveriesMap.containsKey(aircraftCie)) {
            return Collections.unmodifiableSortedSet(liveriesMap.get(aircraftCie));
        }
        return Collections.emptySortedSet();
    }

    /**
     *
     * @return list of companies.
     */
    public final SortedSet<String> getCompagnies() {
        final SortedSet<String> sortedSet = new TreeSet<>(liveriesMap.keySet());
        return Collections.unmodifiableSortedSet(sortedSet);
    }

}
