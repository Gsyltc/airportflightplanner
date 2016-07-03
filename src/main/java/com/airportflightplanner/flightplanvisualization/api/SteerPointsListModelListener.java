/* @(#)FlightPlanListModelListener.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.api;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointsListModelListener {

    /**
     *
     * @param steerPointReader
     */
    void addSteerPoint(SteerPointReader steerPointReader);

    /**
     *
     * @param steerPointReader
     */
    void removeSteerPoint(SteerPointReader steerPointReader);
}
