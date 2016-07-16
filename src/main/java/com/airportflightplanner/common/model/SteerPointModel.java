/* @(#)FlightPlanVisualizationModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import javax.measure.DecimalMeasure;
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
    private static final long        serialVersionUID = 2802706994676346658L;
    /** */
    private LatLong                  latLong;
    /** */
    private DecimalMeasure<Velocity> velocity;
    /** */
    private Altitude                 altitude;
    /** */
    private String                   name             = "";

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
    public DecimalMeasure<Velocity> getVelocity() {
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
        final LatLong oldValue = latLong;
        if (!value.equals(oldValue)) {
            latLong = value;
            firePropertyChange(SteerPointProperties.LAT_LONG, oldValue, latLong);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final DecimalMeasure<Velocity> decimalMeasure) {
        final DecimalMeasure<Velocity> oldValue = velocity;
        if (!decimalMeasure.equals(oldValue)) {
            velocity = decimalMeasure;
            firePropertyChange(SteerPointProperties.VELOCITY, oldValue, velocity);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAltitude(final Altitude value) {
        final Altitude oldValue = altitude;
        if (!value.equals(oldValue)) {
            altitude = value;
            firePropertyChange(SteerPointProperties.VELOCITY, oldValue, altitude);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setName(final String value) {
        final String oldValue = name;
        if (!value.equals(oldValue)) {
            name = value;
            firePropertyChange(SteerPointProperties.NAME, oldValue, name);
        }
    }
}
