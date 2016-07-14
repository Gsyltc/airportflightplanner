/* @(#)AircraftTypeModel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */

package com.airportflightplanner.common.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.airportflightplanner.common.model.AircraftsLiveriesMapper;

/**
 * @author DCNS
 *
 */
public final class AircraftTypeAdapter {
    /** */
    private static final Map<String, AircraftsLiveriesMapper> AIRCRAFT_CLASS_CIE = new HashMap<String, AircraftsLiveriesMapper>();

    /**
     * Private constructor for util class.
     */
    private AircraftTypeAdapter() {

    }

    /**
     * Get the liveries.
     *
     * @param classCpie
     *            Company.
     * @return List of liveries for the company.
     */
    public static List<String> getAircraftLiveriesByClassCpie(final String classCpie) {
        final AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(getAircraftClass(classCpie));
        if (null != mapper) {
            return new ArrayList<String>(mapper.getLiveriesByCpie(getAircraftCie(classCpie)));
        }
        return Collections.emptyList();
    }

    /**
     * Add a new livery for the type.
     *
     * @param airCraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     */
    public static void addLivery(final String airCraftType) {
        AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(getAircraftClass(airCraftType));
        if (null == mapper) {
            mapper = new AircraftsLiveriesMapper();
            AIRCRAFT_CLASS_CIE.put(getAircraftClass(airCraftType), mapper);

        }
        mapper.addLivery(airCraftType);

    }

    /**
     * Get All the aircraft class founded in directory.
     *
     * @return //
     */
    public static SortedSet<String> getAircraftClass() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(AIRCRAFT_CLASS_CIE.keySet()));
    }

    /**
     * Get the aircraft class.
     *
     * @param airCraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     * @return the aircraft class.
     */
    public static String getAircraftClass(final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");

        final String[] splitAirCraft = splitTmp[0].split("_");
        return splitAirCraft[0];

    }

    /**
     *
     * @param airCraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     * @return the aircraft company.
     */
    public static String getAircraftCie(final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");

        final String[] splitAirCraft = splitTmp[0].split("_");
        if (splitAirCraft.length > 1) {
            return splitAirCraft[1];
        }
        return "";
    }

    /**
     *
     * @param airCraftClass
     *            Aircraft class.
     * @param airCraftType
     *            Air craft company.
     * @return aircraft livery.
     */
    public static String getAircraftLivery(final String airCraftClass, final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");

        return splitTmp[0].replace(airCraftClass + "_", "");
    }

    /**
     * Get the list of aircraft company by aircraft type.
     *
     * @param aircraftType
     *            Aircraft type (like CLASS_CPIE_LIV).
     * @return List of aircraft company.
     */
    public static List<String> getAircraftCompaniesByType(final String aircraftType) {
        return getAircraftCompaniesByClass(getAircraftClass(aircraftType));
    }

    /**
     * Get all the company for the class.
     *
     * @param aircraftClass
     *            Aircraft class.
     * @return List of the companies.
     */
    public static List<String> getAircraftCompaniesByClass(final String aircraftClass) {
        final AircraftsLiveriesMapper mapper = AIRCRAFT_CLASS_CIE.get(aircraftClass);
        if (null != mapper) {
            return new ArrayList<String>(mapper.getCompagnies());
        }
        return Collections.emptyList();
    }

}
