/*
 * @(#)SteerPointsListModelListener.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.api;

import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointsListModelListener {
    
    
    /**
     *
     * @param steerPointReader
     */
    void onSteerPointAdded(SteerPointReader steerPointReader);

    /**
     *
     * @param steerPointReader
     */
    void onSteerPointRemoved(SteerPointReader steerPointReader);

    /**
     *
     */
    void onListReset();
}
