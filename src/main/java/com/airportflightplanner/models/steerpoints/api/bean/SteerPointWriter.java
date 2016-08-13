/*
 * @(#)SteerPointWriter.java
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
public interface SteerPointWriter extends SteerPointReader {
    
    
    /**
     *
     * @param value
     */
    void setLatLong(LatLong value);

    /**
     *
     * @param value
     */
    void setVelocity(DecimalMeasure<Velocity> value);

    /**
     *
     * @param value
     */
    void setAltitude(Altitude value);

    /**
     *
     * @param value
     */
    void setName(String value);

    /**
     * @param value
     *            the altType to set
     */
    void setAltType(AltitudeType value);

    /**
     * @param value
     *            the formation to set
     */
    void setFormation(FormationType value);

    /**
     * @param value
     *            the heading to set
     */
    void setHeading(DecimalMeasure<Heading> value);

    /**
     * @param value
     */
    void setMaxBankingAngle(Measure<Integer, Angle> value);
}
