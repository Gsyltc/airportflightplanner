/*
 * @(#)FlightRouteReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 18 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.routes.api.bean;

import java.io.Serializable;

import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightRouteReader extends Serializable {
    
    
    /**
     *
     * @return
     */
    String getArrivalAirport();
    
    /**
     *
     * @return
     */
    String getDepartureAirport();
    
    /**
     *
     * @return
     */
    Period getDuration();
    
    /**
     *
     * @return
     */
    String getFileName();
    
    /**
     *
     * @return
     */
    String getName();
    
    /**
     *
     * @return
     */
    LocalTime getStartTime();
    
    /**
     *
     * @return
     */
    LinkedListModel<SteerPointReader> getSteerPoints();
    
    /**
     *
     * @return
     */
    Distance getDistance();
    
}
