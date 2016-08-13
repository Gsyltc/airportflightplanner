/*
 * @(#)SteerPointReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints.api.bean;

import java.io.Serializable;

import javax.measure.DecimalMeasure;
import javax.measure.Measure;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Velocity;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.domaintypes.Heading;
import com.airportflightplanner.common.types.AltitudeType;
import com.airportflightplanner.common.types.FormationType;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointReader extends Serializable {
    
    
    /**
     *
     * @return
     */
    LatLong getLatLong();

    /**
     *
     * @return
     */
    DecimalMeasure<Velocity> getVelocity();

    /**
     *
     * @return
     */
    Altitude getAltitude();

    /**
     *
     * @return
     */
    String getName();

    /**
     * @return the altType
     */
    AltitudeType getAltType();

    /**
     * @return the formation
     */
    FormationType getFormation();

    /**
     * @return the heading
     */
    DecimalMeasure<Heading> getHeading();

    /**
     * @return
     */
    Measure<Integer, Angle> getMaxBankingAngle();

}
