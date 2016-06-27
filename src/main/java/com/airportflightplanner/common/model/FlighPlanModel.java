/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.measure.unit.SI;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.flightplan.FlightPlanWriter;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighPlanModel extends Model implements FlightPlanWriter { 
    /**
     *
     */
    private static final long              serialVersionUID     = 9068391147414760708L;
    /** */
    private Map<Integer, SteerPointReader> steerPoints          = new ConcurrentHashMap<Integer, SteerPointReader>();
    /** */
    private String                         departureAirport;
    /** */
    private String                         arrivalAirport;
    /** */
    private LocalTime                      startTime;
    /** */
    private LocalTime                      endTime;
    /** */
    private String                         callSign;
    /** */
    private String                         aircraftType;
    /** */
    private String                         aircraftCie;
    /** */
    private Period                       duration;
    /** */
    private ArrivalType                    arrivalType          = ArrivalType.STRAIGHT_IN_APPROCH;
    /** */
    private DepartureType                  departureType        = DepartureType.NORMAL;
    /** */
    private FlightType                     flightType           = FlightType.CIVILIAN;
    /** */
    private Boolean                        isFlightToCompletion = false;
    /** */
    private Altitude                       landingLightAltitude = Altitude.valueOf(0.0, SI.METER);
    /** */
    private String                         name;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Map<Integer, SteerPointReader> getSteerPoints() {
        return steerPoints;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getDepartureAirport() {
        return departureAirport;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getCallSign() {
        return callSign;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftType() {
        return aircraftType;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftCie() {
        return aircraftCie;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Period getDuration() {
        return duration;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public ArrivalType getArrivalType() {
        return arrivalType;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public DepartureType getDepartureType() {
        return departureType;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FlightType getFlightType() {
        return flightType;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Boolean isFlightToCompletion() {
        return isFlightToCompletion;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Altitude getLandingLightAltitude() {
        return landingLightAltitude;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSteerPoints(final Map<Integer, SteerPointReader> value) {
        Map<Integer, SteerPointReader> oldValue = this.steerPoints;
        if (!value.equals(oldValue)) {
            this.steerPoints = value;
            firePropertyChange(FligthPlanProperties.STEERPOINTS_MAP, oldValue, steerPoints);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDepartureAirport(final String value) {
        String oldValue = this.departureAirport;
        if (!value.equals(oldValue)) {
            this.departureAirport = value;
            firePropertyChange(FligthPlanProperties.DEPARTURE_AIRPORT, oldValue, departureAirport);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setArrivalAirport(final String value) {
        String oldValue = this.arrivalAirport;
        if (!value.equals(oldValue)) {
            this.arrivalAirport = value;
            firePropertyChange(FligthPlanProperties.ARRIVAL_AIRPORT, oldValue, arrivalAirport);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setStartTime(final LocalTime value) {
        LocalTime oldValue = this.startTime;
        if ((null != value) && !value.equals(oldValue)) {
            this.startTime = value;
            firePropertyChange(FligthPlanProperties.START_TIME, oldValue, startTime);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setEndTime(final LocalTime value) {
        LocalTime oldValue = this.endTime;
        if ((null != value) && !value.equals(oldValue)) {
            this.endTime = value;
            firePropertyChange(FligthPlanProperties.END_TIME, oldValue, endTime);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setCallSign(final String value) {
        String oldValue = this.callSign;
        if (!value.equals(oldValue)) {
            this.callSign = value;
            firePropertyChange(FligthPlanProperties.CALLSIGN, oldValue, callSign);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftType(final String value) {
        String oldValue = this.aircraftType;
        if (!value.equals(oldValue)) {
            this.aircraftType = value;
            firePropertyChange(FligthPlanProperties.AIRCRAFT_TYPE, oldValue, aircraftType);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftCie(final String value) {
        String oldValue = this.aircraftCie;
        if (!value.equals(oldValue)) {
            this.aircraftCie = value;
            firePropertyChange(FligthPlanProperties.AIRCRAFT_CIE, oldValue, aircraftCie);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDuration(final Period value) {
        Period oldValue = this.duration;
        if ((null != value) && !value.equals(oldValue)) {
            this.duration = value;
            firePropertyChange(FligthPlanProperties.DURATION, oldValue, duration);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setArrivalType(final ArrivalType value) {
        ArrivalType oldValue = this.arrivalType;
        if (!value.equals(oldValue)) {
            this.arrivalType = value;
            firePropertyChange(FligthPlanProperties.AIRCRAFT_TYPE, oldValue, arrivalType);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDepartureType(final DepartureType value) {
        DepartureType oldValue = this.departureType;
        if (!value.equals(oldValue)) {
            this.departureType = value;
            firePropertyChange(FligthPlanProperties.DEPARTURE_TYPE, oldValue, departureType);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFlighType(final FlightType value) {
        FlightType oldValue = this.flightType;
        if (!value.equals(oldValue)) {
            this.flightType = value;
            firePropertyChange(FligthPlanProperties.FLIGHT_TYPE, oldValue, flightType);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFlightToCompletion(final Boolean value) {
        Boolean oldValue = this.isFlightToCompletion;
        if (!value.equals(oldValue)) {
            this.isFlightToCompletion = value;
            firePropertyChange(FligthPlanProperties.FLIGHT_TO_COMPLETION, oldValue, isFlightToCompletion);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setLandingLightAltitude(final Altitude value) {
        Altitude oldValue = this.landingLightAltitude;
        if (!value.equals(oldValue)) {
            this.landingLightAltitude = value;
            firePropertyChange(FligthPlanProperties.LANDING_LIGHT_ALTITUDE, oldValue, landingLightAltitude);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setName(final String value) {
        String oldValue = this.name;
        if (!value.equals(oldValue)) {
            this.name = value;
            firePropertyChange(FligthPlanProperties.NAME, oldValue, name);
        }
    }

}
