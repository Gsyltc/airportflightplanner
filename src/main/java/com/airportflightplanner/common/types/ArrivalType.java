/* @(#)ArrivalType.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum ArrivalType {
    STRAIGHT_IN_APPROCH(0), OVERHEAD_BRAKE(1), LOW_APPROCH(2);

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
        for (ArrivalType type : ArrivalType.values()) {
            if (type.ordinal() == typeIndex) {
                return type;
            }
        }
        return null;
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
