/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.measure.unit.SI;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.flightplan.FlightPlanWriter;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
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
    private List<String> steerPoints          = new CopyOnWriteArrayList<String>();
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
    private Period                         duration;
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
    /** */
    private Set<StartDays>                 startDays            = new HashSet<StartDays>();
    /** */
    private String                         alternateAirport;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<String> getSteerPoints() {
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
    public Set<StartDays> getStartDays() {
        return startDays;
    }


    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAlternateAirport() {
        return alternateAirport;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSteerPoints(final List<String> value) {
        List<String> oldValue = getSteerPoints();
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
        String oldValue = getDepartureAirport();
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
        String oldValue = getArrivalAirport();
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
        LocalTime oldValue = getStartTime();
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
        LocalTime oldValue = getEndTime();
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
        String oldValue = getCallSign();
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
        String oldValue = getAircraftType();
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
        String oldValue = getAircraftCie();
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
        Period oldValue = getDuration();
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
        ArrivalType oldValue = getArrivalType();
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
        DepartureType oldValue = getDepartureType();
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
        FlightType oldValue = getFlightType();
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
        Boolean oldValue = isFlightToCompletion();
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
        Altitude oldValue = getLandingLightAltitude();
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
        String oldValue = getName();
        if (!value.equals(oldValue)) {
            this.name = value;
            firePropertyChange(FligthPlanProperties.NAME, oldValue, name);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setStartDays(final Set<StartDays> value) {
        Set<StartDays> oldValue = getStartDays();
        if (!value.equals(oldValue)) {
            this.startDays = value;
            firePropertyChange(FligthPlanProperties.START_DAYS, oldValue, startDays);
        }

    }
    /**
    *
    * {@inheritDoc}
    */
   @Override
    public void setAlternateAirport(final String value) {
        String oldValue = getAlternateAirport();
        if (!value.equals(oldValue)) {
            this.alternateAirport = value;
            firePropertyChange(FligthPlanProperties.ALTERNATE_AIRPORT, oldValue, alternateAirport);
        }
    }

}
