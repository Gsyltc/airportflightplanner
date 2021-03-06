/*
 * @(#)FlightPlanReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.flightplans.api.bean;

import java.io.Serializable;
import java.util.Set;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanReader extends Serializable {
    
    
    /**
     *
     * @return
     */
    String getAircraftCie();
    
    /**
     *
     * @return
     */
    String getAircraftLivery();
    
    /**
     *
     * @return
     */
    String getAircraftClass();
    
    /**
     *
     * @return
     */
    String getAlternateAirport();
    
    /**
     *
     * @return
     */
    String getArrivalAirport();
    
    /**
     *
     * @return
     */
    ArrivalType getArrivalType();
    
    /**
     *
     * @return
     */
    String getCallSign();
    
    /**
     *
     * @return
     */
    String getDepartureAirport();
    
    /**
     *
     * @return
     */
    DepartureType getDepartureType();
    
    /**
     *
     * @return
     */
    Period getDuration();
    
    /**
     *
     * @return
     */
    LocalTime getEndTime();
    
    /**
     *
     * @return
     */
    String getFileName();
    
    /**
     *
     * @return
     */
    Boolean getFlightToCompletion();
    
    /**
     *
     * @return
     */
    FlightType getFlightType();
    
    /**
     *
     * @return
     */
    Altitude getLandingLightAltitude();
    
    /**
     *
     * @return
     */
    String getName();
    
    /**
     *
     * @return
     */
    Set<StartDays> getStartDays();
    
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
    
}
