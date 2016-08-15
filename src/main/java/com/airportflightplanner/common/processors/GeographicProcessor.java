/*
 * @(#)GeographicProcessor.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.processors;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.List;

import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.types.GeographicFormatter;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public class GeographicProcessor {
    
    
    /** */
    private static final String SOUTH = "S";
    /** */
    private static final String WEST = "W";
    /** */
    private static final double THOUSAND = 1000;
    /** */
    private static final int MINUTE_IN_SECOND = 60;
    /** */
    private static final double TIME_FACTOR = 3600.0;
    
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
     * @param steerPoints
     *            list of the steer points
     * @return the duration.
     */
    public static long getFlightTime(final List<SteerPointReader> steerPoints) {
        SteerPointReader origin = null;
        long result = 0L;
        for (final SteerPointReader destination : steerPoints) {
            if (null != origin && null != destination) {
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
        final double velocity = origin.getSpeed().doubleValue(SI.METERS_PER_SECOND);
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
}
