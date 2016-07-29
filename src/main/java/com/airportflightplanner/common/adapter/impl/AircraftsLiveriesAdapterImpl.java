/*
 * @(#)AircraftsLiveriesAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 29 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.adapter.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.airportflightplanner.common.api.adapter.AircraftsLiveriesAdapter;
import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftsLiveriesAdapterImpl implements AircraftsLiveriesAdapter, Serializable {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 5942133142250388619L;
    /** */
    private final Map<String, SortedSet<String>> liveriesMap = new ConcurrentHashMap<String, SortedSet<String>>();
    /** */
    private final String aircraftType;
    
    /**
     * @param newAircraftType
     *            The companie.
     */
    public AircraftsLiveriesAdapterImpl(final String newAircraftType) {
        this.aircraftType = newAircraftType;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
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
     * {@inheritDoc}
     */
    @Override
    public String getAircraftType() {
        return this.aircraftType;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final SortedSet<String> getCompagnies() {
        return Collections.unmodifiableSortedSet(new TreeSet<String>(getLiveriesMap().keySet()));
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final SortedSet<String> getLiveriesByCpie(final String aircraftCie) {
        SortedSet<String> result = Collections.emptySortedSet();
        if (getLiveriesMap().containsKey(aircraftCie)) {
            result = Collections.unmodifiableSortedSet(getLiveriesMap().get(aircraftCie));
        }
        return result;
    }
    
    /**
     * @return the liveriesMap
     */
    private Map<String, SortedSet<String>> getLiveriesMap() {
        return this.liveriesMap;
    }
}
