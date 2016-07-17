/*
 * @(#)AircraftsLiveriesMapper.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.api.adapter;

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
     *
     * @param aircraftCie
     *            aircraft companu.
     * @return list of liveries for the company.
     */
    SortedSet<String> getLiveriesByCpie(final String aircraftCie);

    /**
     *
     * @return the list of the companies.
     */
    SortedSet<String> getCompagnies();

    /**
     * @return the aircraftType
     */
    String getAircraftType();

}
