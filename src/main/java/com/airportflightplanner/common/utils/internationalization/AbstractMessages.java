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
    /**
     *
     * @param key
     * @return
     */
    public static String format(final String key) {
        return Internationalizer.getI18String(key);
    }

}
