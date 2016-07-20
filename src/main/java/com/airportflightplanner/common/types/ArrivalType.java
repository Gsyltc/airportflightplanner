/*
 * @(#)ArrivalType.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum ArrivalType {
    /** */
    UNDEFINED(0),
    /** */
    STRAIGHT_IN_APPROCH(1),
    /** */
    OVERHEAD_BRAKE(2),
    /** */
    LOW_APPROCH(3);
    /** */
    private static final String PREFIX = "ArrivalType.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private ArrivalType(final int value) {
        this.value = value;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static ArrivalType valueOf(final int typeIndex) {
        ArrivalType result = null;
        for (final ArrivalType type : ArrivalType.values()) {
            if (type.ordinal() == typeIndex) {
                result = type;
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
    public static int getIndex(final ArrivalType type) {
        return type.value;

    }
}
