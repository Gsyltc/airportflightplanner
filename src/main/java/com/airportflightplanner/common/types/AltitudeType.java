/*
 * @(#)AltitudeType.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
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

public enum AltitudeType {
    /** */
    UNDEFINED(-1),
    /** */
    ASL(0),
    /** */
    AGL(1);
    
    /**
     *
     */
    private static final String PREFIX = "AltitudeType.";
    /** */
    private int value;
    
    /**
     *
     * @param newValue
     */
    AltitudeType(final int newValue) {
        value = newValue;
    }
    
    /**
     *
     * @param typeIndex
     * @return
     */
    public static AltitudeType valueOf(final int typeIndex) {
        AltitudeType result = AltitudeType.UNDEFINED;
        for (final AltitudeType type : AltitudeType.values()) {
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
    public static int getIndex(final AltitudeType type) {
        return type.value;
        
    }
    
    /**
     *
     * @param value
     * @return
     */
    public static boolean isValid(final String value) {
        boolean result = false;
        for (final AltitudeType type : AltitudeType.values()) {
            if (type.name().equals(value)) {
                result = true;
            }
        }
        return result;
    }
}
