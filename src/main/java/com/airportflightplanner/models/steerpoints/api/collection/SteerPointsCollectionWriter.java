/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.models.steerpoints.api.collection;

import java.util.List;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointsCollectionWriter extends SteerPointsCollectionReader {
    /**
     *
     * @param value
     */
    void addSteerPoints(List<SteerPointReader> value);

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
