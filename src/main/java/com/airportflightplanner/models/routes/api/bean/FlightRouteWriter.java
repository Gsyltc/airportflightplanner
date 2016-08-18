/*
 * @(#)FlightRouteWriter.java
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

import org.joda.time.Period;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * Flight plan writer.
 *
 * @author Goubaud Sylvain
 *
 */
public interface FlightRouteWriter extends FlightRouteReader {
    
    
    /**
     * The destination of the flight.
     *
     * @param value
     *            the arrival airport.
     */
    void setArrivalAirport(String value);

    /**
     * The departure airport.
     *
     * @param value
     *            the airport.
     */
    void setDepartureAirport(String value);

    /**
     * Duration of the flight.
     *
     * @param value
     *            the duration.
     */
    void setDuration(Period value);

    /**
     * the file name of the flight plan.
     *
     * @param value
     *            the file name.
     */
    void setFileName(String value);
    
    /**
     * Name of the flight plan.
     *
     * @param value
     *            the name.
     */
    void setName(String value);

    /**
     * List of the steer points.
     *
     * @param value
     *            the steer points.
     */
    void setSteerPoints(LinkedListModel<SteerPointReader> value);
    
    /**
     * Distance of the flight plan.
     *
     * @param value
     *            the name.
     */
    void setDistance(Distance value);
}
