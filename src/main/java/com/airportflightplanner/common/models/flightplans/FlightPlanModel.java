/*
 * @(#)FlightPlanModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.models.flightplans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.measure.unit.SI;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanWriter;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
// @formatter:off
public class FlightPlanModel extends Model implements FlightPlanWriter { // NOPMD by sylva on 31/07/16 16:13
// @formatter:on
    
    /**
     *
     */
    private static final long serialVersionUID = 9068391147414760708L;
    /** */
    private String aircraftCie = "";
    /** */
    private String aircraftLivery = "";
    /** */
    private String aircraftClass = "";
    /** */
    private String alternateAirport = "";
    /** */
    private String arrivalAirport = "";
    /** */
    private ArrivalType arrivalType = ArrivalType.UNDEFINED;
    /** */
    private String callSign = "";
    /** */
    private String departureAirport = "";
    /** */
    private DepartureType departureType = DepartureType.UNDEFINED;
    /** */
    private Period duration;
    /** */
    private LocalTime endTime;
    /** */
    private FlightType flightType = FlightType.UNDEFINED;
    /** */
    private Boolean isFightToCompletion = false; // NOPMD by sylva on 31/07/16
                                                 // 16:12
    /** */
    private Altitude landingLightAltitude = Altitude.valueOf(0.0, SI.METER);
    /** */
    private String name;
    /** */
    private Set<StartDays> startDays = new HashSet<StartDays>();
    /** */
    private LocalTime startTime;
    /** */
    private List<String> steerPoints = new CopyOnWriteArrayList<String>();
    /** */
    private String fileName = "";
    
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
    public String getAircraftClass() {
        return aircraftClass;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftLivery() {
        return aircraftLivery;
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
    public String getArrivalAirport() {
        return arrivalAirport;
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
    public String getCallSign() {
        return callSign;
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
    public DepartureType getDepartureType() {
        return departureType;
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
    public LocalTime getEndTime() {
        return endTime;
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
    public LocalTime getStartTime() {
        return startTime;
    }
    
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
    public Boolean getFlightToCompletion() {
        return isFightToCompletion;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getFileName() {
        return fileName;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftCie(final String value) {
        final String oldValue = getAircraftCie();
        if (!value.equals(oldValue)) {
            aircraftCie = value;
            firePropertyChange(FlightPlanProperties.AIRCRAFT_CIE, oldValue, aircraftCie);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftLivery(final String value) {
        final String oldValue = getAircraftLivery();
        if (!value.equals(oldValue)) {
            aircraftLivery = value;
            firePropertyChange(FlightPlanProperties.AIRCRAFT_LIVERY, oldValue, aircraftLivery);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftClass(final String value) {
        final String oldValue = getAircraftClass();
        if (!value.equals(oldValue)) {
            aircraftClass = value;
            firePropertyChange(FlightPlanProperties.AIRCRAFT_CLASS, oldValue, aircraftClass);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAlternateAirport(final String value) {
        final String oldValue = getAlternateAirport();
        if (!value.equals(oldValue)) {
            alternateAirport = value;
            firePropertyChange(FlightPlanProperties.ALTERNATE_AIRPORT, oldValue, alternateAirport);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setArrivalAirport(final String value) {
        final String oldValue = getArrivalAirport();
        if (!value.equals(oldValue)) {
            arrivalAirport = value;
            firePropertyChange(FlightPlanProperties.ARRIVAL_AIRPORT, oldValue, arrivalAirport);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setArrivalType(final ArrivalType value) {
        final ArrivalType oldValue = getArrivalType();
        if (null != value && !value.equals(oldValue)) {
            arrivalType = value;
            firePropertyChange(FlightPlanProperties.ARRIVAL_TYPE, oldValue, arrivalType);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setCallSign(final String value) {
        final String oldValue = getCallSign();
        if (!value.equals(oldValue)) {
            callSign = value;
            firePropertyChange(FlightPlanProperties.CALLSIGN, oldValue, callSign);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDepartureAirport(final String value) {
        final String oldValue = getDepartureAirport();
        if (!value.equals(oldValue)) {
            departureAirport = value;
            firePropertyChange(FlightPlanProperties.DEPARTURE_AIRPORT, oldValue, departureAirport);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDepartureType(final DepartureType value) {
        final DepartureType oldValue = getDepartureType();
        if (null != value && !value.equals(oldValue)) {
            departureType = value;
            firePropertyChange(FlightPlanProperties.DEPARTURE_TYPE, oldValue, departureType);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setDuration(final Period value) {
        final Period oldValue = getDuration();
        if (null != value && !value.equals(oldValue)) {
            duration = value;
            firePropertyChange(FlightPlanProperties.DURATION, oldValue, duration);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setEndTime(final LocalTime value) {
        final LocalTime oldValue = getEndTime();
        if (null != value && !value.equals(oldValue)) {
            endTime = value;
            firePropertyChange(FlightPlanProperties.END_TIME, oldValue, endTime);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFlightToCompletion(final Boolean value) {
        final Boolean oldValue = getFlightToCompletion();
        if (null != value && !value.equals(oldValue)) {
            isFightToCompletion = value;
            firePropertyChange(FlightPlanProperties.FLIGHT_TO_COMPLETION, oldValue, isFightToCompletion);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFlightType(final FlightType value) {
        final FlightType oldValue = getFlightType();
        if (null != value && !value.equals(oldValue)) {
            flightType = value;
            firePropertyChange(FlightPlanProperties.FLIGHT_TYPE, oldValue, flightType);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setLandingLightAltitude(final Altitude value) {
        final Altitude oldValue = getLandingLightAltitude();
        if (null != value && !value.equals(oldValue)) {
            landingLightAltitude = value;
            firePropertyChange(FlightPlanProperties.LANDING_LIGHT_ALTITUDE, oldValue, landingLightAltitude);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setName(final String value) {
        final String oldValue = getName();
        if (!value.equals(oldValue)) {
            name = value;
            firePropertyChange(FlightPlanProperties.NAME, oldValue, name);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setStartDays(final Set<StartDays> value) {
        final Set<StartDays> oldValue = getStartDays();
        if (null != value && !value.equals(oldValue)) {
            startDays = value;
            firePropertyChange(FlightPlanProperties.START_DAYS, oldValue, startDays);
        }
        
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setStartTime(final LocalTime value) {
        final LocalTime oldValue = getStartTime();
        if (null != value && !value.equals(oldValue)) {
            startTime = value;
            firePropertyChange(FlightPlanProperties.START_TIME, oldValue, startTime);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSteerPoints(final List<String> value) {
        final List<String> oldValue = getSteerPoints();
        if (null != value && !value.equals(oldValue)) {
            steerPoints = value;
            firePropertyChange(FlightPlanProperties.STEERPOINTS_LIST, oldValue, steerPoints);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setFileName(final String value) {
        final String oldValue = getFileName();
        if (!value.equals(oldValue)) {
            fileName = value;
            firePropertyChange(FlightPlanProperties.FILENAME, oldValue, fileName);
        }
    }
}
