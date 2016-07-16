/*
 * @(#)AircraftsLiveriesMapper.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.models;

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
    private final Map<String, SortedSet<String>> liveriesMap = new ConcurrentHashMap<String, SortedSet<String>>();
    /** */
    private final String                   aircraftType;

    /**
     * @param newAircraftType
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
        if (getLiveriesMap().containsKey(company)) {
            final SortedSet<String> liveries = getLiveriesMap().get(company);
            liveries.add(airCraftType);
        } else {
            final SortedSet<String> liveries = new TreeSet<String>();
            liveries.add(airCraftType);
            getLiveriesMap().put(company, liveries);
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
        if (getLiveriesMap().containsKey(aircraftCie)) {
            result = Collections.unmodifiableSortedSet(getLiveriesMap().get(aircraftCie));
        }
        return result;
    }

    /**
     *
     * @return the list of the companies.
     */
    public final SortedSet<String> getCompagnies() {
        return Collections.unmodifiableSortedSet(new TreeSet<String>(getLiveriesMap().keySet()));
    }

    /**
     * @return the aircraftType
     */
    public String getAircraftType() {
        return aircraftType;
    }

    /**
     * @return the liveriesMap
     */
    private Map<String, SortedSet<String>> getLiveriesMap() {
        return liveriesMap;
    }
}
