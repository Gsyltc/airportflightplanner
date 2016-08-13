/*
 * @(#)SteerPointsCollectionWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints.api.collection;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/***
 *
 * @author Goubaud Sylvain
 **/
public interface SteerPointsCollectionWriter extends SteerPointsCollectionReader {
    
    
    /**
     *
     * @param value
     */
    void addSteerPoint(SteerPointReader value);

    /**
     *
     * @param value
     */
    void removeSteerPoint(SteerPointReader value);

    /**
     *
     * @param currentFlightPlan
     */
    void setCurrentFlightPlan(FlightPlanReader currentFlightPlan);
}
