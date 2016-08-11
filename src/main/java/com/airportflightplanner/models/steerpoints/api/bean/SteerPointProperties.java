/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
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
    
    /**
     * Protected constructor.
     */
    private SteerPointProperties() {
        //
    }
}
