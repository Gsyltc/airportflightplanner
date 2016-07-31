/*
 * @(#)StartDays.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 31 juil. 2016.
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
public enum StartDays {
    /** */
    MONDAY(1),
    /** */
    TUESDAY(2),
    /** */
    WEDNESDAY(3),
    /** */
    THRUSDAY(4),
    /** */
    FRIDAY(5),
    /** */
    SATURDAY(6),
    /** */
    SUNDAY(0);
    
    /** */
    private static final String PREFIX = "StartDays.";
    /** */
    private int value;
    
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
        StartDays result = null;
        for (final StartDays day : StartDays.values()) {
            if (day.ordinal() == typeIndex) {
                result = day;
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
