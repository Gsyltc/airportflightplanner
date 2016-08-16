/*
 * @(#)SteerPointModelAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api.modeladapters;

import java.util.List;

import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

import fr.gsyltc.framework.adapters.api.CommonAdapter;

/**
 * @author Goubaud Sylvain
 *
 */

public interface SteerPointModelAdapter extends CommonAdapter {
    
    
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

    /**
     * @param value
     */
    void addSteerPoint(SteerPointReader value);

    /**
     * @param value
     */
    void removeSteerPoint(SteerPointReader value);

    /**
     *
     */
    void reset();

    /**
     * @param list
     */
    void addSteerPoints(List<SteerPointReader> list);
}
