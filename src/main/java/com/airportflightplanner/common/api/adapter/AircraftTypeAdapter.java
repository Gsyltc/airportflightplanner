/*
 * @(#)AircraftTypeAdapter
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter;

import java.util.List;
import java.util.SortedSet;

import com.airportflightplanner.common.api.adapter.common.CommonAdapter;

/**
 * @author Goubaud Sylvain
 *
 */
public interface AircraftTypeAdapter extends CommonAdapter{

    /**
     * Get the liveries.
     *
     * @param classCpie
     *            Company.
     * @return List of liveries for the company.
     */
    List<String> getAircraftLiveriesByClassCpie(final String classCpie);

    /**
     * Add a new livery for the type.
     *
     * @param airCraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     */
    void addLivery(final String airCraftType);

    /**
     * Get All the aircraft class founded in directory.
     *
     * @return //
     */
    SortedSet<String> getAircraftClasses();

    /**
     * Get the list of aircraft company by aircraft type.
     *
     * @param aircraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     * @return List of aircraft company.
     */
    List<String> getAircraftCompaniesByType(final String aircraftType);

    /**
     * Get all the company for the class.
     *
     * @param aircraftClass
     *            Aircraft class.
     * @return List of the companies.
     */
    List<String> getAircraftCompaniesByClass(final String aircraftClass);

}
