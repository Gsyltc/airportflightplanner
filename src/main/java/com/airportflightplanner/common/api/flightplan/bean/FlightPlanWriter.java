/*
 * @(#)FlightPlanWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.flightplan.bean;

import java.util.List;
import java.util.Set;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;

/**
 * Flight plan writer.
 *
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanWriter extends FlightPlanReader {
    
    
    /**
     * Company of the aircraft.
     *
     * @param value
     *            the company to set.
     */
    void setAircraftCie(String value);
    
    /**
     * Livery of the aircraft.
     *
     * @param value
     *            the type to set.
     */
    void setAircraftLivery(String value);
    
    /**
     * Class of the aircraft.
     *
     * @param value
     *            the class to set.
     */
    void setAircraftClass(String value);
    
    /**
     * Alternate airport of the aircraft.
     *
     * @param value
     *            the alternate airport.
     */
    void setAlternateAirport(String value);
    
    /**
     * The destination of the flight.
     *
     * @param value
     *            the arrival airport.
     */
    void setArrivalAirport(String value);
    
    /**
     * the arrival type.
     *
     * @param value
     *            the type.
     */
    void setArrivalType(ArrivalType value);
    
    /**
     * The call sign of the flight.
     *
     * @param value
     *            the call sign.
     */
    void setCallSign(String value);
    
    /**
     * The departure airport.
     *
     * @param value
     *            the airport.
     */
    void setDepartureAirport(String value);
    
    /**
     * The departure type.
     *
     * @param value
     *            the type.
     */
    void setDepartureType(DepartureType value);
    
    /**
     * Duration of the flight.
     *
     * @param value
     *            the duration.
     */
    void setDuration(Period value);
    
    /**
     * The arrival time.
     *
     * @param value
     *            the time.
     */
    void setEndTime(LocalTime value);
    
    /**
     * the file name of the flight plan.
     *
     * @param value
     *            the file name.
     */
    void setFileName(String value);
    
    /**
     * The type of completion.
     *
     * @param value
     *            the type.
     */
    void setFlightToCompletion(Boolean value);
    
    /**
     * the flight type (Civilian, / Military).
     *
     * @param value
     *            the type.
     */
    void setFlightType(FlightType value);
    
    /**
     * The altitude to switch the landing light on.
     *
     * @param value
     *            the altitude.
     */
    void setLandingLightAltitude(Altitude value);
    
    /**
     * Name of the flight plan.
     *
     * @param value
     *            the name.
     */
    void setName(String value);
    
    /**
     * Sets of the start days (When the flight is available).
     *
     * @param value
     *            the days.
     */
    void setStartDays(Set<StartDays> value);
    
    /**
     * Departure time.
     *
     * @param value
     *            the time.
     */
    void setStartTime(LocalTime value);
    
    /**
     * List of the steer points.
     *
     * @param value
     *            the steer points.
     */
    void setSteerPoints(List<String> value);
}
