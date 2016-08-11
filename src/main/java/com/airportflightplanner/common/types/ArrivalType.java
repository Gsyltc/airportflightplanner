/*
 * @(#)ArrivalType.java
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
    private int value;
    
    /**
     * Protected constructor.
     *
     * @param newValue
     */
    ArrivalType(final int newValue) {
        this.value = newValue;
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
    public static int getIndex(final ArrivalType type) {
        return type.value;
        
    }
}
