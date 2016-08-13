/*
 * @(#)AircraftsLiveriesAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl;

import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.api.AircraftsLiveriesAdapter;
import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;

import fr.gsyltc.framework.adapters.AbstractAdapterImpl;

/**
 * @author Goubaud Sylvain
 *
 */
public class AircraftsLiveriesAdapterImpl extends AbstractAdapterImpl implements AircraftsLiveriesAdapter {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(AircraftsLiveriesAdapterImpl.class);
    
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
     *            The company.
     */
    public AircraftsLiveriesAdapterImpl(final String newAircraftType) {
        super();
        aircraftType = newAircraftType;
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
        return aircraftType;
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
        return liveriesMap;
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Init Adapter");
        }
    }
}
