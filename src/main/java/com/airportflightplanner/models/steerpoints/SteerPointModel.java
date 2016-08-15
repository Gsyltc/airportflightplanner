/*
 * @(#)SteerPointModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.domaintypes.BankingAngle;
import com.airportflightplanner.common.domaintypes.Heading;
import com.airportflightplanner.common.domaintypes.Speed;
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
    private Speed speed;
    /** */
    private Altitude altitude;
    /** */
    private AltitudeType altType;
    /** */
    private String name = "";
    /** */
    private Heading heading;
    /** */
    private FormationType formation;
    /** */
    private BankingAngle maxBankingAngle;

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
    public Speed getSpeed() {
        return speed;
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
    public Heading getHeading() {
        return new Heading(Double.valueOf(0.0), NonSI.DEGREE_ANGLE);
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
    public BankingAngle getMaxBankingAngle() {
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
    public void setSpeed(final Speed decimalMeasure) {
        final Speed oldValue = getSpeed();
        if (!decimalMeasure.equals(oldValue)) {
            speed = decimalMeasure;
            firePropertyChange(SteerPointProperties.VELOCITY, oldValue, speed);
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
    public void setHeading(final Heading value) {
        final Heading oldValue = getHeading();
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
    public void setMaxBankingAngle(final BankingAngle value) {
        final BankingAngle oldValue = getMaxBankingAngle();
        if (!value.equals(oldValue)) {
            maxBankingAngle = value;
            firePropertyChange(SteerPointProperties.MAX_BANKING_ANGLE, oldValue, maxBankingAngle);
        }
    }
}
