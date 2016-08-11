/*
 * @(#)FormationType.java
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

public enum FormationType {
    /** */
    UNDEFINED(-1),
    /** */
    ECHELON(0),
    /** */
    FINGERTIP(1),
    /** */
    BATTLE_SPREAD(2),
    /** */
    FLUID_2(3),
    /** */
    FLUID_4(4),
    /** */
    TRAIL(5),
    /** */
    ROUTE(6),
    /** */
    BOX(7);
    
    /** */
    private static final String PREFIX = "FormationType.";
    /** */
    private int value;
    
    /**
     * Protected constructor.
     *
     * @param newValue
     */
    FormationType(final int newValue) {
        value = newValue;
    }
    
    /**
     *
     * @param typeIndex
     * @return
     */
    public static FormationType valueOf(final int typeIndex) {
        FormationType result = null;
        for (final FormationType type : FormationType.values()) {
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
    public static int getIndex(final FormationType type) {
        return type.value;
        
    }
}
