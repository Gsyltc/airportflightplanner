/* @(#)FlightPlanVisualizationModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import javax.measure.quantity.Velocity;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.api.steerpoints.SteerPointProperties;
import com.airportflightplanner.common.api.steerpoints.SteerPointWriter;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointModel extends Model implements SteerPointWriter {

    /**
     *
     */
    private static final long serialVersionUID = 2802706994676346658L;
    /** */
    LatLong                   latLong;
    /** */
    private Velocity          velocity;
    /** */
    private Altitude          altitude;
    /** */
    private String            name             = "";

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LatLong getLatLong() {
        return latLong;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Altitude getAltitude() {
        return altitude;
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
    public void setLatLong(final LatLong value) {
        LatLong oldValue = this.latLong;
        if (!value.equals(oldValue)) {
            this.latLong = value;
            firePropertyChange(SteerPointProperties.LAT_LONG, oldValue, latLong);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final Velocity value) {
        Velocity oldValue = this.velocity;
        if (!value.equals(oldValue)) {
            this.velocity = value;
            firePropertyChange(SteerPointProperties.VELOCITY, oldValue, velocity);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAltitude(final Altitude value) {
        Altitude oldValue = this.altitude;
        if (!value.equals(oldValue)) {
            this.altitude = value;
            firePropertyChange(SteerPointProperties.VELOCITY, oldValue, altitude);
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
            firePropertyChange(SteerPointProperties.NAME, oldValue, name);
        }
    }
}
