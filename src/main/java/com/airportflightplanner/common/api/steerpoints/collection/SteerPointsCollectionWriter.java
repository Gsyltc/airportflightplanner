/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.steerpoints.collection;

import java.util.List;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;

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
     * @param listener
     */
    void addSteerPointsListModelListener(SteerPointsListModelListener listener);

    /**
     *
     * @param listener
     */
    void removeSteerPointsListModelListener(SteerPointsListModelListener listener);

    /**
     *
     * @param currentFlightPlan
     */
    void setCurrentFlightPlan(FlightPlanReader currentFlightPlan);
}
