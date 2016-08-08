/*
 * @(#)GeographicUtils.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.processors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
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

import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
import com.airportflightplanner.common.models.steerpoints.SteerPointModel;
import com.airportflightplanner.common.types.GeographicFormatter;

/**
 * @author Goubaud Sylvain
 *
 */
public class GeographicProcessor {
    
    
    /** */
    private static final int LATITUDE_INDEX = 0;
    /** */
    private static final int LONGITUDE_INDEX = 1;
    /** */
    private static final int ALTITUDE_INDEX = 2;
    /** */
    private static final int VELOCITY_INDEX = 4;
    /** */
    private static final String SOUTH = "S";
    /** */
    private static final String WEST = "W";
    /** */
    private static final double THOUSAND = 1000;
    /** */
    private static final int MINUTE_IN_SECOND = 60;
    /** */
    private static final int MIN_LENGTH = 6;
    /** */
    private static final int LATITUDE_KEY = 0;
    /** */
    private static final int LONGITUDE_KEY = 1;
    /** */
    private static final int ALTITUDE_KEY = 2;
    /** */
    private static final int SPEED_KEY = 3;
    /** */
    private static final int MAX_BANKING_ANGLE_KEY = 5;
    /** */
    private static final int WAYPOINT_NAME_KEY = 9;
    /** */
    private static final double TIME_FACTOR = 3600.0;

    /**
     *
     * @param steerpointsString
     *            List of steerpoints.
     * @return List of steerpoints.
     */
    public static List<SteerPointReader> getSteerPoints(final List<String> steerpointsString) {
        final List<SteerPointReader> steerPointList = new ArrayList<SteerPointReader>();
        for (final String lines : steerpointsString) {
            final SteerPointModel steerPoint = new SteerPointModel();
            final Pattern pattern = Pattern.compile(" +");
            // séparation en sous-chaînes
            final String[] items = pattern.split(lines, 10);
            steerPoint.setLatLong(LatLong.valueOf(Double.valueOf(items[LATITUDE_INDEX]), //
                    Double.valueOf(items[LONGITUDE_INDEX]), NonSI.DEGREE_ANGLE));
            steerPoint.setVelocity(new DecimalMeasure<Velocity>(new BigDecimal(items[VELOCITY_INDEX]), NonSI.KNOT));
            steerPoint.setAltitude(Altitude.valueOf(Double.valueOf(items[ALTITUDE_INDEX]), NonSI.FOOT));

            steerPointList.add(steerPoint);
        }
        return steerPointList;
    }

    /**
     *
     * @param latLong
     *            the LatLong.
     * @return the formatted latitude.
     */
    public static String getFormattedLatitude(final LatLong latLong) {
        return decimalToDMS(latLong.latitudeValue(NonSI.DEGREE_ANGLE), true);
    }

    /**
     *
     * @param latLong
     *            the LatLong.
     * @return the formatted longitude.
     */
    public static String getFormattedLongitude(final LatLong latLong) {
        return decimalToDMS(latLong.longitudeValue(NonSI.DEGREE_ANGLE), false);
    }

    /**
     * Get the duration of the flight.
     *
     * @param steerpointsString
     *            list of the steerpoint
     * @return the duration.
     */
    public static long getFlightTime(final List<String> steerpointsString) {
        SteerPointReader origin = null;
        long result = 0L;
        for (final SteerPointReader destination : getSteerPoints(steerpointsString)) {
            if (null != origin) {
                result += getTimeBetweenWaypoint(origin, destination);
                origin = destination;
            }
            origin = destination;
        }
        return result;
    }

    /**
     * Get the time between two steerpoint.
     *
     * @param origin
     *            the origin steerpoint
     * @param destination
     *            the destination steerpoint
     * @return the duration.
     */
    public static long getTimeBetweenWaypoint(final SteerPointReader origin, final SteerPointReader destination) {
        final double velocity = origin.getVelocity().doubleValue(SI.METERS_PER_SECOND);
        final long result = (long) (calculateDistanceBeetwenPoint(origin, destination) / velocity * THOUSAND);

        return result;
    }

    /**
     * Calculate distance between 2 points (Geodesic - WG84).
     *
     * @param origin
     *            the origin lat long
     * @param destination
     *            the destination lat long
     * @return the calculated time.
     */
    public static double calculateDistanceBeetwenPoint(final SteerPointReader origin, final SteerPointReader destination) {
        final GlobalPosition startWaypoint = new GlobalPosition(origin.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE), //
                origin.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE), origin.getAltitude().doubleValue(NonSI.FOOT));

        final GlobalPosition endWaypoint = new GlobalPosition(destination.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE), //
                destination.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE), destination.getAltitude().doubleValue(NonSI.FOOT));

        final double result = new GeodeticCalculator().calculateGeodeticMeasurement(Ellipsoid.WGS84, startWaypoint, endWaypoint)
                .getPointToPointDistance();
        return result;
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
        value = mod * MINUTE_IN_SECOND;
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
     */
    public double dMSToDecimal(final String hemOUmeridien, final double degres, final double minutes, final double secondes) {
        double latOrLon = 0;
        double signe = 1.0;

        if (WEST.equals(hemOUmeridien) || SOUTH.equals(hemOUmeridien)) {
            signe = -1.0;
        }
        latOrLon = signe * (Math.floor(degres) + Math.floor(minutes) / MINUTE_IN_SECOND + secondes / TIME_FACTOR);

        return latOrLon;
    }

    /**
     *
     * @param steerPointDatas
     * @return
     */
    public static boolean validateSteerpoints(final String steerPointDatas) {
        boolean result = true;
        final String[] datas = steerPointDatas.split(" +");
        // Check number of param
        if (datas.length >= MIN_LENGTH) {
            result &= isPositionValid(datas[LATITUDE_KEY]);
            result &= isPositionValid(datas[LONGITUDE_KEY]);
            result &= isAltitudeValid(datas[ALTITUDE_KEY]);
            result &= isSpeedValid(datas[SPEED_KEY]);
            result &= isMaxBankAngleValid(datas[MAX_BANKING_ANGLE_KEY]);
            result &= null != datas[WAYPOINT_NAME_KEY];
        }

        return result;
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isPositionValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,3}+\\.?\\d{0,10}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isAltitudeValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,5}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isSpeedValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,3}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private static boolean isMaxBankAngleValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,2}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
