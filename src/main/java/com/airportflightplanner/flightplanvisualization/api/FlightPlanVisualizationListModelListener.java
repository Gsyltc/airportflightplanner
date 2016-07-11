/* @(#)FlightPlanListModelListener.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.api;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanVisualizationListModelListener {

    /**
     *
     * @param flightPlan
     */
    void addFlightPlan(FligthPlanReader flightPlan);

    /**
     *
     * @param flightPlan
     */
    void removeFlightPlan(FligthPlanReader flightPlan);
}
