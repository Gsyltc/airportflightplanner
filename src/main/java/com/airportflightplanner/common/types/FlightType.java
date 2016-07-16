/* @(#)FlightTypes.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum FlightType {
    /** */
    CIVILIAN(0),
    /** */
    MILITARY(1);
    /**
     *
     */
    private static final String PREFIX = "FlightType.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private FlightType(final int value) {
        this.value = value;
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
                result= type;
            }
        }
        return result;
    }

    /**
     * Get i18nString
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
