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
public enum MapType {
    ROADMAP(0), SATTELITE(1), HYBRID(2), TERRAIN(3);

    private static final String PREFIX = "arrivaltype.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private MapType(final int value) {
        this.value = value;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static MapType valueOf(final int typeIndex) {
        for (MapType type : MapType.values()) {
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
    public static int getIndex(final MapType type) {
        return type.value;

    }
}
