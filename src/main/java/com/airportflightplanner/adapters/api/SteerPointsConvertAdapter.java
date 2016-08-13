/*
 * @(#)SteerPointsConvertAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api;

import java.util.List;

import com.airportflightplanner.models.steerpoints.SteerPointModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

import fr.gsyltc.framework.adapters.api.CommonAdapter;

/**
 * @author Goubaud Sylvain
 *
 */

public interface SteerPointsConvertAdapter extends CommonAdapter {
    
    
    /**
     *
     * @param value
     * @return
     */
    List<SteerPointModel> convertSteerPoints(List<String> value);
    
    /**
     *
     * @param value
     * @return
     */
    SteerPointModel convertSteerPoint(String value);
    
    /**
     * @param steerPoint
     * @return
     */
    String convertSteerPointToString(SteerPointReader steerPoint);
}
