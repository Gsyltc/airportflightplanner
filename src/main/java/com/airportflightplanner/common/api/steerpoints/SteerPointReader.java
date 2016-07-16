/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.steerpoints;

import java.io.Serializable;

import javax.measure.DecimalMeasure;
import javax.measure.quantity.Velocity;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

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

}
