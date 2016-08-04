/*
 * @(#)UnitTypes.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

/*
 * @(#)UnitTypes.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 4 août 2016.
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

/**
 * @author Goubaud Sylvain
 *
 */
public enum UnitTypes {
    /** Meter unit. */
    METER,
    /** Feet Unit. */
    FEET;
    
    /** */
    private static final String PREFIX = UnitTypes.class.getSimpleName() + ".";
    
    /**
     *
     * @param useLong
     * @return
     */
    public String getI18String(final boolean useLong) {
        String result = "";
        if (useLong) {
            result = Internationalizer.getI18String(PREFIX + this.name() + ".long");
        } else {
            result = Internationalizer.getI18String(PREFIX + this.name() + ".short");
        }
        return result;
    }
}
