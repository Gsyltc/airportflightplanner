/*
 * @(#)FlightRouteProperties.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 18 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.routes.api.bean;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public final class FlightRouteProperties {
    
    
    /** */
    public static final String STEERPOINTS_LIST = FlightPlanProperties.STEERPOINTS_LIST;
    /** */
    public static final String DEPARTURE_AIRPORT = FlightPlanProperties.DEPARTURE_AIRPORT;
    /** */
    public static final String ARRIVAL_AIRPORT = FlightPlanProperties.ARRIVAL_AIRPORT;
    /** */
    public static final String NAME = FlightPlanProperties.NAME;
    /** */
    public static final String FILENAME = FlightPlanProperties.FILENAME;
    /** */
    public static final String DURATION = FlightPlanProperties.DURATION;
    /** */
    public static final String DISTANCE = "distance";

    /**
     * Protected constructor.
     */
    private FlightRouteProperties() {
        //
    }

}
