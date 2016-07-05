/* @(#)GeographicFormatter.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.types;

import com.airportflightplanner.common.utils.internationalization.AbstractMessages;

/**
 * @author DCNS
 *
 */
public class GeographicFormatter extends AbstractMessages {
    /** */
    private static final String PREFIX        = GeographicFormatter.class.getSimpleName() + ".";
    /**
     *
     */
    public static final String  LATITUDE_DMS  = format(PREFIX + "LATITUDE_DMS");
    /**
     *
     */
    public static final String  LONGITUDE_DMS = format(PREFIX + "LONGITUDE_DMS");
    /**
     *
     */
    public static final String  VELOCITY_KNOT = format(PREFIX + "VELOCITY_KNOT");
    /**
     *
     */
    public static final String  ALTITUDE_FOOT = format(PREFIX + "ALTITUDE_FOOT");
}
