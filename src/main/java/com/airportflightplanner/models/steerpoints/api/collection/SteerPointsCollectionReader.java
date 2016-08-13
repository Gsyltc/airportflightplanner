/*
 * @(#)SteerPointsCollectionReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints.api.collection;

import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

/***
 *
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
    LinkedListModel<SteerPointReader> getSteerPointsListModel();

    /**
     *
     * @return
     */
    FlightPlanReader getCurrentFlightPlan();

    /**
     *
     * @param listener
     */
    void addListener(SteerPointsListModelListener listener);

    /**
     *
     * @param listener
     */
    void removeListener(SteerPointsListModelListener listener);

}
