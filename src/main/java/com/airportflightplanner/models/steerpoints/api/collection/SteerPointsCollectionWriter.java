/*
 * @(#)SteerPointsCollectionWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints.api.collection;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

/***
 *
 * @author Goubaud Sylvain
 **/
public interface SteerPointsCollectionWriter extends SteerPointsCollectionReader {
    
    
    /**
     *
     * @param currentFlightPlan
     */
    void setCurrentFlightPlan(FlightPlanReader currentFlightPlan);
    
    /**
     * @param newSPListModel
     */
    void setSteerPointsListModel(LinkedListModel<SteerPointReader> newSPListModel);
}
