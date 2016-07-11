/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplancollection.steerpoints;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointsCollectionReader {

    /**
     *
     * @param value
     * @return
     */
    SteerPointReader getSteerPointByIndex(final int value);

    /**
     *
     * @return
     */
    int getSteerPointsCollectionSize();

    /**
     *
     * @return
     */
    ListModel<SteerPointReader> getListModel();

    /**
     *
     * @return
     */
    FligthPlanReader getCurrentFlightPlan();

}
