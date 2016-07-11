/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplan;

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
 * @author Goubaud Sylvain
 *
 */
public interface FligthPlanWriter extends FligthPlanReader {
    /**
     *
     * @param value
     */
    void setSteerPoints(List<String> value);

    /**
     *
     * @param value
     */
    void setDepartureAirport(String value);

    /**
     *
     * @param value
     */
    void setArrivalAirport(String value);

    /**
     *
     * @param value
     */
    void setStartTime(LocalTime value);

    /**
     *
     * @param value
     */
    void setEndTime(LocalTime value);

    /**
     *
     * @param value
     */
    void setCallSign(String value);

    /**
     *
     * @param value
     */
    void setAircraftType(String value);

    /**
     *
     * @param value
     */
    void setAircraftCie(String value);

    /**
     *
     * @param value
     */
    void setDuration(Period value);

    /**
     *
     * @param value
     */
    void setArrivalType(ArrivalType value);

    /**
     *
     * @param value
     */
    void setDepartureType(DepartureType value);

    /**
     *
     * @param value
     */
    void setFlighType(FlightType value);

    /**
     *
     * @param value
     */
    void setFlightToCompletion(Boolean value);

    /**
     *
     * @param value
     */
    void setLandingLightAltitude(Altitude value);

    /**
     *
     * @param value
     */
    void setName(String value);

    /**
     *
     * @param value
     */
    void setStartDays(Set<StartDays> value);

    /**
     *
     * @param value
     */
    void setAlternateAirport(String value);
    /**
     *
     * @param value
     */
    void setFileName(String value);
}
