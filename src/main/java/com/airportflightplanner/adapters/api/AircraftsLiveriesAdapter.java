/*
 * @(#)AircraftsLiveriesMapper.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api;

import java.util.SortedSet;

/**
 * @author Goubaud Sylvain
 *
 */
public interface AircraftsLiveriesAdapter {
    
    
    /**
     *
     * @param airCraftType
     *            aircraft type.
     */
    void addLivery(final String airCraftType);

    /**
     * @return the aircraftType
     */
    String getAircraftType();

    /**
     *
     * @return the list of the companies.
     */
    SortedSet<String> getCompagnies();

    /**
     *
     * @param aircraftCie
     *            aircraft companu.
     * @return list of liveries for the company.
     */
    SortedSet<String> getLiveriesByCpie(final String aircraftCie);

}
