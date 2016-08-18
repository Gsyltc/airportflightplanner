/*
 * @(#)WaypointsEditorModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.routecreation.models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.joda.time.Period;

import com.airportflightplanner.common.domaintypes.Distance;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.airportflightplanner.routecreation.api.model.RouteEditorProperties;
import com.airportflightplanner.routecreation.api.model.RouteEditorWriter;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */

public class RouteEditorModel extends Model implements RouteEditorWriter {
    
    
    /** the distance of the flight. */
    Distance distance;
    /** the duration of the flight. */
    Period flightTime;
    /** the list of waypoints. */
    List<SteerPointReader> waypoints = new CopyOnWriteArrayList<SteerPointReader>();

    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public Distance getDistance() {
        return distance;
    }

    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public Period getFlightTime() {
        return flightTime;
    }

    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public List<SteerPointReader> getWaypoints() {
        return waypoints;
    }

    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void setDistance(final Distance value) {
        final Distance oldValue = getDistance();
        if (!distance.getValue().equals(value.getValue())) {
            distance = value;
            firePropertyChange(RouteEditorProperties.DISTANCE, oldValue, distance);
        }
    }

    @Override
    public void setFlightTime(final Period value) {
        final Period oldValue = getFlightTime();
        if (!flightTime.equals(value)) {
            flightTime = value;
            firePropertyChange(RouteEditorProperties.FLIGHT_TIME, oldValue, flightTime);
        }
    }

    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void setWaypoints(final List<SteerPointReader> value) {
        final List<SteerPointReader> oldValue = getWaypoints();
        if (!waypoints.equals(value)) {
            waypoints = value;
            firePropertyChange(RouteEditorProperties.WAYPOINTS, oldValue, waypoints);
        }
    }

}
