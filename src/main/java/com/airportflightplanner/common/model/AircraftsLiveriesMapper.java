/* @(#)AircraftsLiveriesMapper.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.model;

import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftsLiveriesMapper {
    /** */
    private transient final Map<String, SortedSet<String>> liveriesMap = new ConcurrentHashMap<String, SortedSet<String>>();
    /** */
    private final String                                   aircraftType;

    /**
     * @param newAircraftCie
     *            The companie.
     */
    public AircraftsLiveriesMapper(final String newAircraftType) {
        aircraftType = newAircraftType;
    }

    /**
     *
     * @param airCraftType
     *            aircraft type.
     */
    public final void addLivery(final String airCraftType) {
        final String company = AircraftDecoder.getAircraftCie(airCraftType);
        if (liveriesMap.containsKey(company)) {
            final SortedSet<String> liveries = liveriesMap.get(company);
            liveries.add(airCraftType);
        } else {
            final SortedSet<String> liveries = new TreeSet<String>();
            liveries.add(airCraftType);
            liveriesMap.put(company, liveries);
        }
    }

    /**
     *
     * @param aircraftCie
     *            aircraft companu.
     * @return list of liveries for the company.
     */
    public final SortedSet<String> getLiveriesByCpie(final String aircraftCie) {
        SortedSet<String> result = Collections.emptySortedSet();
        if (liveriesMap.containsKey(aircraftCie)) {
            result = Collections.unmodifiableSortedSet(liveriesMap.get(aircraftCie));
        }
        return result;
    }

    /*
     *
     * @return list of companies.
     */
    public final SortedSet<String> getCompagnies() {
        return Collections.unmodifiableSortedSet(new TreeSet<String>(liveriesMap.keySet()));
    }
}
