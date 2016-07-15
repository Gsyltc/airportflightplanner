/* @(#)AbstractMessages.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.utils.internationalization;

/**
 * @author Goubaud Sylvain
 *
 */
public abstract class AbstractMessages {
    /** */
    private static final String PREFIX  = AbstractMessages.class.getSimpleName() + ".";
    /** */
    public static final String  LABEL   = ".label";
    /** */
    public static final String  UNKNOWN = format(PREFIX + "UNKNOWN");

    /**
     *
     * @param key
     * @return
     */
    public static String format(final String key) {
        return Internationalizer.getI18String(key);
    }
}
