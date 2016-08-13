/*
 * @(#)SteerPointModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Velocity;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.domaintypes.Heading;
import com.airportflightplanner.common.types.AltitudeType;
import com.airportflightplanner.common.types.FormationType;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointProperties;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointWriter;
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
    private LatLong latLong;
    /** */
    private DecimalMeasure<Velocity> velocity;
    /** */
    private Altitude altitude;
    /** */
    private AltitudeType altType;
    /** */
    private String name = "";
    /** */
    private DecimalMeasure<Heading> heading;
    /** */
    private FormationType formation;
    /** */
    private Measure<Integer, Angle> maxBankingAngle;
    
    /** */
    
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
    public AltitudeType getAltType() {
        return altType;
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public DecimalMeasure<Heading> getHeading() {
        return heading;
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public FormationType getFormation() {
        return formation;
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public Measure<Integer, Angle> getMaxBankingAngle() {
        return maxBankingAngle;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setLatLong(final LatLong value) {
        final LatLong oldValue = getLatLong();
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
        final DecimalMeasure<Velocity> oldValue = getVelocity();
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
        final Altitude oldValue = getAltitude();
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
        final String oldValue = getName();
        if (!value.equals(oldValue)) {
            name = value;
            firePropertyChange(SteerPointProperties.NAME, oldValue, name);
        }
    }
    
    /**
     *
     * @param value
     */
    @Override
    public void setAltType(final AltitudeType value) {
        final AltitudeType oldValue = getAltType();
        if (!value.equals(oldValue)) {
            altType = value;
            firePropertyChange(SteerPointProperties.ALT_TYPE, oldValue, altType);
        }
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void setHeading(final DecimalMeasure<Heading> value) {
        final DecimalMeasure<Heading> oldValue = getHeading();
        if (!value.equals(oldValue)) {
            heading = value;
            firePropertyChange(SteerPointProperties.HEADING, oldValue, heading);
        }
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void setFormation(final FormationType value) {
        final FormationType oldValue = getFormation();
        if (!value.equals(oldValue)) {
            formation = value;
            firePropertyChange(SteerPointProperties.FORMATION_TYPE, oldValue, formation);
        }
    }
    
    /**
     *
     *
     * {@inheritDoc}.
     */
    @Override
    public void setMaxBankingAngle(final Measure<Integer, Angle> value) {
        final Measure<Integer, Angle> oldValue = getMaxBankingAngle();
        if (!value.equals(oldValue)) {
            maxBankingAngle = value;
            firePropertyChange(SteerPointProperties.MAX_BANKING_ANGLE, oldValue, maxBankingAngle);
        }
    }
}
