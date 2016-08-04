/*
 * @(#)GeographicFormatter.java
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

import fr.gsyltc.framework.utils.internationalizer.AbstractMessages;

/**
 * @author DCNS
 *
 */
public class GeographicFormatter extends AbstractMessages {
    
    
    /** */
    private static final String PREFIX = GeographicFormatter.class.getSimpleName() + ".";
    /**
     *
     */
    public static final String LATITUDE_DMS = format(PREFIX + "LATITUDE_DMS");
    /**
     *
     */
    public static final String LONGITUDE_DMS = format(PREFIX + "LONGITUDE_DMS");
    /**
     *
     */
    public static final String VELOCITY_KNOT = format(PREFIX + "VELOCITY_KNOT");
    /**
     *
     */
    public static final String ALTITUDE_FOOT = format(PREFIX + "ALTITUDE_FOOT");
}
