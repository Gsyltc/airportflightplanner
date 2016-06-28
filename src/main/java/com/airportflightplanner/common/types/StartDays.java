/* @(#)StartDays.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author DCNS
 *
 */
public enum StartDays {
    MONDAY(0), TUESDAY(1), WEDNESDAY(2), THRUSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6);

    private static final String PREFIX = "StartDays.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private StartDays(final int value) {
        this.value = value;
    }

    /**
     *
     * @param typeIndex
     * @return
     */
    public static StartDays valueOf(final int typeIndex) {
        for (StartDays day : StartDays.values()) {
            if (day.ordinal() == typeIndex) {
                return day;
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
        return valueOf(typeIndex).toString();
    }

    /**
     *
     * @param type
     * @return
     */
    public static int getIndex(final StartDays type) {
        return type.value;

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return Internationalizer.getI18String(PREFIX + valueOf(value).name());
    }
}