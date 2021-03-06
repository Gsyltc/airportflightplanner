/*
 * @(#)WaypointsEditorReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.routecreation.api.model;

import java.util.List;

import org.joda.time.Period;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */

public interface RouteEditorReader {
    
    
    /**
     * @return the distance
     */
    Distance getDistance();

    /**
     * @return the flightTime
     */
    Period getFlightTime();

    /**
     * @return the waypoints
     */
    List<SteerPointReader> getWaypoints();

}
