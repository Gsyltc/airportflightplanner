/* @(#)DepartureType.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum DepartureType {
    NORMAL(0), VERTICAL(1);

    private static final String PREFIX = "DepartureType.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private DepartureType(final int value) {
        this.value = value;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static DepartureType valueOf(final int typeIndex) {
        for (DepartureType type : DepartureType.values()) {
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
    public static int getIndex(final DepartureType type) {
        return type.value;

    }
}
