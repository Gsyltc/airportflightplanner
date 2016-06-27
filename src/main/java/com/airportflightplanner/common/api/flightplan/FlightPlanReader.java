/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplan;

import java.util.Map;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanReader {
    /**
     *
     * @return
     */
    Map<Integer, SteerPointReader> getSteerPoints();

    /**
     *
     * @return
     */
    String getDepartureAirport();

    /**
     *
     * @return
     */
    String getArrivalAirport();

    /**
     *
     * @return
     */
    LocalTime getStartTime();

    /**
     *
     * @return
     */
    LocalTime getEndTime();

    /**
     *
     * @return
     */
    String getCallSign();

    /**
     *
     * @return
     */
    String getAircraftType();

    /**
     *
     * @return
     */
    String getAircraftCie();

    /**
     *
     * @return
     */
    Period getDuration();

    /**
     *
     * @return
     */
    ArrivalType getArrivalType();

    /**
     *
     * @return
     */
    DepartureType getDepartureType();

    /**
     *
     * @return
     */
    FlightType getFlightType();

    /**
     *
     * @return
     */
    Boolean isFlightToCompletion();

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

}
