/*
 * @(#)WaypointsEditorWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 9 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.waypointmodifications.api.model;

import java.util.List;

import javax.measure.DecimalMeasure;

import org.joda.time.Period;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */

public interface WaypointsEditorWriter extends WaypointsEditorReader {
    
    
    /**
     * @param value
     *            the distance to set
     */
    void setDistance(DecimalMeasure<Distance> value);

    /**
     * @param value
     *            the flightTime to set
     */
    void setFlightTime(Period value);
    
    /**
     *
     * @param value
     */
    void setWaypoints(List<SteerPointReader> value);

}
