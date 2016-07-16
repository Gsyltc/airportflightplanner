/*
 * @(#)AircraftDecoder.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

/*
 * @(#)AircraftDecoder.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.aircraft;

/**
 * @author Goubaud Sylvain
 *
 */

/**
 * @author Goubaud Sylvain
 *
 */
public final class AircraftDecoder {
    /**
     * Protected constructor.
     */
    private AircraftDecoder() {
        //
    }

    /**
     *
     * @param airCraftType
     * @return
     */
    public static String getAircraftClass(final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");

        final String[] splitAirCraft = splitTmp[0].split("_");
        return splitAirCraft[0];

    }

    /**
     *
     * @param airCraftType
     * @return
     */
    public static String getAircraftCie(final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");
        String result = "";

        final String[] splitAirCraft = splitTmp[0].split("_");

        final boolean hasCpie = splitAirCraft.length > 1;
        if (hasCpie) {
            result = splitAirCraft[1];
        }
        return result;
    }

    /**
     *
     * @param airCraftClass
     * @param airCraftType
     * @return
     */
    public static String getAircraftLivery(final String airCraftClass, final String airCraftType) {
        final String[] splitTmp = airCraftType.split(" +");

        return splitTmp[0].replace(airCraftClass + "_", "");
    }
}
