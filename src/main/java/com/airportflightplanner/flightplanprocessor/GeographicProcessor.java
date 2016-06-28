/* @(#)GeographicProcessor.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
// */
package com.airportflightplanner.flightplanprocessor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.measure.DecimalMeasure;
import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

/**
 * @author Goubaud Sylvain
 *
 */
public class GeographicProcessor {

    private final List<LatLongSpeedAltitude> latLongSpeedAltitudeValues = new ArrayList<LatLongSpeedAltitude>();

    /**
     *
     * @param steerpoints
     * @return
     */
    public long getFlightTime(final List<String> steerpoints) {
        for (String lines : steerpoints) {
            Pattern p = Pattern.compile(" +");
            // séparation en sous-chaînes
            String[] items = p.split(lines, 10);
            LatLongSpeedAltitude latLongSpeed = new LatLongSpeedAltitude(items[0], items[1], items[4], items[2]);
            latLongSpeedAltitudeValues.add(latLongSpeed);
        }
        return calculate();
    }

    /**
     *
     * @return
     */
    private long calculate() {
        double result = 0.0;
        GlobalPosition lastCoord = null;
        for (LatLongSpeedAltitude latLongSpeed : latLongSpeedAltitudeValues) {

            GlobalPosition coord = new GlobalPosition(latLongSpeed.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE), //
                    latLongSpeed.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE), latLongSpeed.getAltitude().doubleValue(NonSI.FOOT));

            if (null != lastCoord) {
                result += ((new GeodeticCalculator().calculateGeodeticMeasurement(//
                        Ellipsoid.WGS84, lastCoord, coord).getPointToPointDistance()) / //
                        latLongSpeed.getVelocity().doubleValue(SI.METERS_PER_SECOND)) * 1000;
            }

            lastCoord = coord;
        }

        return (long) result;
    }

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected class LatLongSpeedAltitude {
        /** */
        private final LatLong                  latLong;
        /** */
        private final DecimalMeasure<Velocity> velocity;

        /** */
        private final Altitude                 altitude;

        /**
         *
         * @param latitude
         * @param longitude
         * @param speed
         * @param altitude
         */
        public LatLongSpeedAltitude(final String latitude, final String longitude, final String speed, final String altitude) {
            this.latLong = LatLong.valueOf(Double.valueOf(latitude), Double.valueOf(longitude), NonSI.DEGREE_ANGLE);
            this.velocity = new DecimalMeasure<Velocity>(new BigDecimal(speed), NonSI.KNOT);
            this.altitude = Altitude.valueOf(Double.valueOf(altitude), NonSI.FOOT);
        }

        /**
         *
         * @return
         */
        public LatLong getLatLong() {
            return latLong;
        }

        /**
         *
         * @return
         */
        public DecimalMeasure<Velocity> getVelocity() {
            return velocity;
        }

        /**
         *
         * @return
         */
        public Altitude getAltitude() {
            return altitude;
        }
    }
}
