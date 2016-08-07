/*
 * @(#)StartDays.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
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
public enum StartDays {
    /** */
    MONDAY(0),
    /** */
    TUESDAY(1),
    /** */
    WEDNESDAY(2),
    /** */
    THRUSDAY(3),
    /** */
    FRIDAY(4),
    /** */
    SATURDAY(5),
    /** */
    SUNDAY(6);

    /** Prefix for internationalization. */
    private static final String PREFIX = "StartDays.";
    /** the index. */
    private int value;

    /**
     * Constructor.
     *
     * @param newVvalue
     *            the index.
     *
     */
    StartDays(final int newVvalue) {
        value = newVvalue;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static StartDays valueOf(final int typeIndex) {
        StartDays result = null;
        for (final StartDays day : StartDays.values()) {
            if (day.ordinal() == typeIndex) {
                result = day;
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
        return valueOf(typeIndex).toString();
    }

    /**
     * Get the index.
     *
     * @param type
     *            the type.
     * @return the index.
     */
    public static int getIndex(final StartDays type) {
        return type.value;

    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return Internationalizer.getI18String(PREFIX + valueOf(value).name());
    }
}
