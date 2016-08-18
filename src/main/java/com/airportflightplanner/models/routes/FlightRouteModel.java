/*
 * @(#)FlightRouteModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 18 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.routes;

import java.util.HashSet;
import java.util.Set;

import javax.measure.unit.SI;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.models.routes.api.bean.FlightRouteProperties;
import com.airportflightplanner.models.routes.api.bean.FlightRouteWriter;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
// @formatter:off
public class FlightRouteModel extends Model implements FlightRouteWriter { // NOPMD by sylva on 31/07/16 16:13
// @formatter:on
    
    /**
     *
     */
    private static final long serialVersionUID = 9068391147414760708L;
    /** */
    private final String aircraftCie = "";
    /** */
    private final String aircraftLivery = "";
    /** */
    private final String aircraftClass = "";
    /** */
    private final String alternateAirport = "";
    /** */
    private String arrivalAirport = "";
    /** */
    private final ArrivalType arrivalType = ArrivalType.UNDEFINED;
    /** */
    private final String callSign = "";
    /** */
    private String departureAirport = "";
    /** */
    private final DepartureType departureType = DepartureType.UNDEFINED;
    /** */
    private Period duration;
    /** */
    private LocalTime endTime;
    /** */
    private final FlightType flightType = FlightType.UNDEFINED;
    /** */
    private final Boolean isFightToCompletion = false; // NOPMD by sylva on
                                                       // 31/07/16
    // 16:12
    /** */
    private final Altitude landingLightAltitude = Altitude.valueOf(0.0, SI.METER);
    /** */
    private String name;
    /** */
    private final Set<StartDays> startDays = new HashSet<StartDays>();
    /** */
    private LocalTime startTime;
    /** */
    private LinkedListModel<SteerPointReader> steerPoints;
    /** */
    private String fileName = "";
    private Distance distance;
    
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
    public String getDepartureAirport() {
        return departureAirport;
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
    public String getName() {
        return name;
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
    public LinkedListModel<SteerPointReader> getSteerPoints() {
        return steerPoints;
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
     * {@inheritDoc}.
     */
    @Override
    public Distance getDistance() {
        return distance;
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
            firePropertyChange(FlightRouteProperties.ARRIVAL_AIRPORT, oldValue, arrivalAirport);
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
            firePropertyChange(FlightRouteProperties.DEPARTURE_AIRPORT, oldValue, departureAirport);
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
            firePropertyChange(FlightRouteProperties.DURATION, oldValue, duration);
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
            firePropertyChange(FlightRouteProperties.NAME, oldValue, name);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setSteerPoints(final LinkedListModel<SteerPointReader> value) {
        final LinkedListModel<SteerPointReader> oldValue = getSteerPoints();
        if (null != value && !value.equals(oldValue)) {
            steerPoints = value;
            firePropertyChange(FlightRouteProperties.STEERPOINTS_LIST, oldValue, steerPoints);
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
            firePropertyChange(FlightRouteProperties.FILENAME, oldValue, fileName);
        }
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void setDistance(final Distance value) {
        final Distance oldValue = getDistance();
        if (!value.equals(oldValue)) {
            distance = value;
            firePropertyChange(FlightRouteProperties.DISTANCE, oldValue, distance);
        }
    }
}
