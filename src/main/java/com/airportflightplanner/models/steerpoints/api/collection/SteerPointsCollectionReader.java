/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.models.steerpoints.api.collection;

import javax.swing.ListModel;

import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

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
    ListModel<SteerPointReader> getSteerPointsListModel();

    /**
     *
     * @return
     */
    FlightPlanReader getCurrentFlightPlan();

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

}
