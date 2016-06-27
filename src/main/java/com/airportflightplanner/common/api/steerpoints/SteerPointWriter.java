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
    void setVelocity(Velocity value);

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
}
