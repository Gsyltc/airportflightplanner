/* @(#)FlightPlanListModelListener.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
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
    void addFlightPlan(FlightPlanReader flightPlan);

    /**
     *
     * @param flightPlan
     */
    void removeFlightPlan(FlightPlanReader flightPlan);

    /**
     *
     */
    void resetFlightPlans();
}
