/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.steerpoints;

import javax.measure.quantity.Velocity;

import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

/**
 * @author Goubaud Sylvain
 *
 */
public interface SteerPointReader {
    /**
     *
     * @return
     */
    LatLong getLatLong();

    /**
     *
     * @return
     */
    Velocity getVelocity();

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
