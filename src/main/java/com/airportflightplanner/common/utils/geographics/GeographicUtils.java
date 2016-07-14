/* @(#)GeographicProcessor.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
// */
package com.airportflightplanner.common.utils.geographics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
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

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.common.model.SteerPointModel;
import com.airportflightplanner.common.types.GeographicFormatter;
import com.google.maps.model.EncodedPolyline;

/**
 * @author Goubaud Sylvain
 *
 */
public class GeographicUtils {

    /**
     *
     * @param steerpointsString
     * @return
     */
    public static List<SteerPointReader> getSteerPoints(final List<String> steerpointsString) {
        final List<SteerPointReader> steerPointList = new ArrayList<SteerPointReader>();
        for (final String lines : steerpointsString) {
            final Pattern pattern = Pattern.compile(" +");
            // séparation en sous-chaînes
            final String[] items = pattern.split(lines, 10);
            final SteerPointModel steerPoint = new SteerPointModel();

            steerPoint.setLatLong(LatLong.valueOf(Double.valueOf(items[0]), Double.valueOf(items[1]), NonSI.DEGREE_ANGLE));
            steerPoint.setVelocity(new DecimalMeasure<Velocity>(new BigDecimal(items[4]), NonSI.KNOT));
            steerPoint.setAltitude(Altitude.valueOf(Double.valueOf(items[2]), NonSI.FOOT));

            steerPointList.add(steerPoint);
        }
        return steerPointList;
    }

    /**
     *
     * @param latLong
     * @return
     */
    public static String getFormattedLatitude(final LatLong latLong) {
        return decimalToDMS(latLong.latitudeValue(NonSI.DEGREE_ANGLE), true);
    }

    /**
     *
     * @param steerpointsString
     * @return
     */
    public static EncodedPolyline getEncodePolyline(final List<String> steerpointsString) {
        final List<SteerPointReader> steerPoints = getSteerPoints(steerpointsString);
        final StringBuilder stringBuilder = new StringBuilder();
        for (final SteerPointReader steerPointReader : steerPoints) {
            stringBuilder.append("|").append(steerPointReader.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE))//
            .append(",")//
            .append(steerPointReader.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE));
        }
        final EncodedPolyline polyline = new EncodedPolyline(stringBuilder.toString());
        return polyline;
    }

    /**
     *
     * @param latLong
     * @return
     */
    public static String getFormattedLongitude(final LatLong latLong) {
        return decimalToDMS(latLong.longitudeValue(NonSI.DEGREE_ANGLE), false);
    }

    /**
     *
     * @param steerpointsString
     * @return
     */
    public static long getFlightTime(final List<String> steerpointsString) {
        return calculate(getSteerPoints(steerpointsString));
    }

    /**
     *
     * @param steerPointList
     * @return
     */
    private static long calculate(final List<SteerPointReader> steerPointList) {
        double result = 0.0;
        GlobalPosition lastCoord = null;
        for (final SteerPointReader latLongSpeed : steerPointList) {

            final GlobalPosition coord = new GlobalPosition(latLongSpeed.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE), //
                    latLongSpeed.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE), latLongSpeed.getAltitude().doubleValue(NonSI.FOOT));

            if (null != lastCoord) {
                result += new GeodeticCalculator().calculateGeodeticMeasurement(//
                        Ellipsoid.WGS84, lastCoord, coord).getPointToPointDistance() / //
                        latLongSpeed.getVelocity().doubleValue(SI.METERS_PER_SECOND) * 1000;
            }

            lastCoord = coord;
        }

        return (long) result;
    }

    /**
     * @param coord
     * @param isLAtitude
     * @return
     */
    private static String decimalToDMS(final double coord, final boolean isLAtitude) {
        double value = coord;
        // Degree
        double mod = value % 1;
        final int degree = (int) value;
        String direction = "";
        DecimalFormat formatDegree = null;
        if (isLAtitude) {
            formatDegree = new DecimalFormat("##");
            formatDegree.setMinimumIntegerDigits(2);
            if (degree > 0) {
                direction = "N";

            } else {
                direction = "S";
            }
        } else {
            formatDegree = new DecimalFormat("###");
            formatDegree.setMinimumIntegerDigits(3);
            if (degree > 0) {
                direction = "E";
            } else {
                direction = "W";
            }
        }
        formatDegree.setRoundingMode(RoundingMode.CEILING);

        // Minutes
        value = mod * 60;
        mod = coord % 1;
        int intMinutes = (int) value;
        if (intMinutes < 0) {
            intMinutes *= -1;
        }
        final DecimalFormat formatMinutes = new DecimalFormat("##");
        formatMinutes.setRoundingMode(RoundingMode.CEILING);

        final double second = mod * 60;
        final DecimalFormat formatSecond = new DecimalFormat("##.####");
        formatSecond.setRoundingMode(RoundingMode.CEILING);

        return MessageFormat.format(GeographicFormatter.LATITUDE_DMS, new Object[] { formatDegree.format(degree), //
                formatMinutes.format(intMinutes), //
                formatSecond.format(second), direction });

    }

    /**
     * Conversion DMS to decimal.
     *
     * Input: latitude or longitude in the DMS format ( example: W 79° 58'
     * 55.903") Return: latitude or longitude in decimal format
     * hemisphereOUmeridien => {W,E,S,N}
     *
     * @param hemOUmeridien
     * @param degres
     * @param minutes
     * @param secondes
     * @return
     *
     */
    public double dMSToDecimal(final String hemOUmeridien, final double degres, final double minutes, final double secondes) {
        double latOrLon = 0;
        double signe = 1.0;

        if (hemOUmeridien.equals("W") || hemOUmeridien.equals("S")) {
            signe = -1.0;
        }
        latOrLon = signe * (Math.floor(degres) + Math.floor(minutes) / 60.0 + secondes / 3600.0);

        return latOrLon;
    }
}
