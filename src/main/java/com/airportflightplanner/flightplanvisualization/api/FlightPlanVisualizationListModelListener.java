/* @(#)FlightPlanListModelListener.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.api;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;

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
}
