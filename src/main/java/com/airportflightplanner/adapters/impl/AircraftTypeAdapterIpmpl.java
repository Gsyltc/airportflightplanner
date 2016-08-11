/*
 * @(#)AircraftTypeAdapterIpmpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 27 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.airportflightplanner.adapters.api.AircraftTypeAdapter;
import com.airportflightplanner.adapters.api.AircraftsLiveriesAdapter;
import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;

import fr.gsyltc.framework.adapters.AbstractAdapterImpl;

/**
 * @author Goubaud Sylvain
 *
 */
public final class AircraftTypeAdapterIpmpl extends AbstractAdapterImpl implements AircraftTypeAdapter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 1059528093535615678L;
    /** */
    private static final Map<String, AircraftsLiveriesAdapter> AIRCRAFT_CLASS_CIE = //
            new ConcurrentHashMap<String, AircraftsLiveriesAdapter>();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addLivery(final String airCraftType) {
        AircraftsLiveriesAdapter liveriesAdapter = AIRCRAFT_CLASS_CIE.get(AircraftDecoder.getAircraftClass(airCraftType));
        if (null == liveriesAdapter) {
            liveriesAdapter = new AircraftsLiveriesAdapterImpl(AircraftDecoder.getAircraftClass(airCraftType));
            AIRCRAFT_CLASS_CIE.put(AircraftDecoder.getAircraftClass(airCraftType), liveriesAdapter);

        }

        liveriesAdapter.addLivery(airCraftType);

    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public SortedSet<String> getAircraftClasses() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(AIRCRAFT_CLASS_CIE.keySet()));
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public List<String> getAircraftCompaniesByClass(final String aircraftClass) {
        final List<String> result = new ArrayList<String>();
        final AircraftsLiveriesAdapter liveriesAdapter = AIRCRAFT_CLASS_CIE.get(aircraftClass);
        if (null != liveriesAdapter) {
            result.addAll(liveriesAdapter.getCompagnies());
        }
        return result;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public List<String> getAircraftCompaniesByType(final String aircraftType) {
        return getAircraftCompaniesByClass(AircraftDecoder.getAircraftClass(aircraftType));
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public List<String> getAircraftLiveriesByClassCpie(final String classCpie) {
        final List<String> result = new ArrayList<String>();
        final AircraftsLiveriesAdapter liveriesAdapter = AIRCRAFT_CLASS_CIE.get(AircraftDecoder.getAircraftClass(classCpie));
        if (null != liveriesAdapter) {
            final String company = AircraftDecoder.getAircraftCie(classCpie);
            final SortedSet<String> liveries = liveriesAdapter.getLiveriesByCpie(company);
            result.addAll(liveries);
        }
        return result;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        // Nothing to do
    }
}
