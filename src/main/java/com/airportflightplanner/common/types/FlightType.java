/*
 * @(#)FlightType.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 9 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.types;

import fr.gsyltc.framework.utils.internationalizer.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum FlightType {
    /** */
    UNDEFINED(-1),
    /** */
    CIVILIAN(0),
    /** */
    MILITARY(1);

    /**
     *
     */
    private static final String PREFIX = "FlightType.";
    /** */
    private int value;

    /**
     *
     * @param newValue
     */
    FlightType(final int newValue) {
        value = newValue;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static FlightType valueOf(final int typeIndex) {
        FlightType result = null;
        for (final FlightType type : FlightType.values()) {
            if (type.ordinal() == typeIndex) {
                result = type;
            }
        }
        return result;
    }

    /**
     * Get i18nString.
     *
     * @param typeIndex
     * @return
     */
    public static String getI18NName(final int typeIndex) {
        return Internationalizer.getI18String(PREFIX + valueOf(typeIndex).name());
    }

    /**
     *
     * @param type
     * @return
     */
    public static int getIndex(final FlightType type) {
        return type.value;

    }
}
