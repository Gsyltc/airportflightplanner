/*
 * @(#)FlightPlanVisualizationListModelListener.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.api;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanVisualizationListModelListener {
    
    
    /**
     *
     * @param flightPlan
     */
    void onFlightPlanAdded(FlightPlanReader flightPlan);
    
    /**
     *
     * @param flightPlan
     */
    void onFlightPlanRemoved(FlightPlanReader flightPlan);
    
    /**
     *
     */
    void onFlightPlansReset();
}
