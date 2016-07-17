/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.steerpoints.collection;

import java.util.List;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;

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
