/*
 * @(#)SteerPointProperties.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints.api.bean;

/**
 * @author Goubaud Sylvain
 *
 */
public final class SteerPointProperties {
    
    
    /** */
    public static final String LAT_LONG = "latlong";
    /** */
    public static final String ALTITUDE = "altitude";
    /** */
    public static final String VELOCITY = "velocity";
    /** */
    public static final String NAME = "name";
    /** the altitude type property. */
    public static final String ALT_TYPE = "altType";
    /** the heading property. */
    public static final String HEADING = "heading";
    /** the formation type property. */
    public static final String FORMATION_TYPE = "formation";
    /** */
    public static final String MAX_BANKING_ANGLE = "maxBankingAngle";
    
    /**
     * Protected constructor.
     */
    private SteerPointProperties() {
        //
    }
}
