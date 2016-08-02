/*
 * @(#)AbstractMessages.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 2 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.internationalization;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class AbstractMessages {
    
    
    /** */
    private static final String PREFIX = AbstractMessages.class.getSimpleName() + ".";
    /** */
    public static final String LABEL = ".label";
    /** */
    public static final String TITLE = ".title";
    /** */
    public static final String TEXT = ".text";
    /** */
    public static final String UNKNOWN = format(PREFIX + "UNKNOWN");

    /**
     *
     * @param key
     * @return
     */
    public static String format(final String key) {
        return Internationalizer.getI18String(key);
    }
}
