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

    //    /**
    //     * Get the aircraft class.
    //     *
    //     * @param airCraftType
    //     *            Aircraft type (like CLASS_CPIE_LIV).
    //     * @return the aircraft class.
    //     */
    //    String getAircraftClass(final String airCraftType);
    //
    //    /**
    //     *
    //     * @param airCraftType
    //     *            Aircraft type (like CLASS_CPIE_LIV).
    //     * @return the aircraft company.
    //     */
    //    String getAircraftCie(final String airCraftType);
    //
    //    /**
    //     *
    //     * @param airCraftClass
    //     *            Aircraft class.
    //     * @param airCraftType
    //     *            Air craft company.
    //     * @return aircraft livery.
    //     */
    //    String getAircraftLivery(final String airCraftClass, final String airCraftType);

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
