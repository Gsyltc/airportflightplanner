/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.steerpoints.collection;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;

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
