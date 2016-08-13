/*
 * @(#)GoogleMapProcessor.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.processors;

import java.util.List;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapReader;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.google.maps.model.EncodedPolyline;

/**
 * @author DCNS
 *
 */
public final class GoogleMapProcessor {
    
    
    /** */
    private static final String MARKER_PREFIX = "&markers=color:";
    /** */
    private static final String START_MARKER = MARKER_PREFIX + "red%7Clabel:" + FlightPlanCreationPanelMessages.MARKER_START
            + "%7C";
    /** */
    private static final String END_MARKER = MARKER_PREFIX + "green%7Clabel:" + FlightPlanCreationPanelMessages.MARKER_END + "%7C";
    /** */
    private static final String ROUTE_SEPARATOR = "|";
    
    /**
     * Protected constructor.
     */
    private GoogleMapProcessor() {
        //
    }
    
    /**
     *
     * @param mapReader
     * @return
     */
    public static String getEncodedRoad(final GoogleMapReader mapReader) {
        final StringBuilder sbuilder = new StringBuilder();
        sbuilder.append(mapReader.getEncodedPolyline().getEncodedPath())//
                .append(START_MARKER + getFormattedCoordinates(mapReader.getStartMarker()))//
                .append(END_MARKER + getFormattedCoordinates(mapReader.getEndMarker()));
        return sbuilder.toString();
    }
    
    /**
     *
     * @param latLong
     * @return
     */
    private static String getFormattedCoordinates(final LatLong latLong) {
        return latLong.latitudeValue(NonSI.DEGREE_ANGLE) + "," + latLong.longitudeValue(NonSI.DEGREE_ANGLE);
    }
    
    /**
     *
     * @param steerPoints
     *            List of steer points.
     * @return Encoded Polyline.
     */
    public static EncodedPolyline getEncodePolyline(final List<SteerPointReader> steerPoints) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final SteerPointReader steerPointReader : steerPoints) {
            stringBuilder.append(ROUTE_SEPARATOR).append(steerPointReader.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE))//
                    .append(',')//
                    .append(steerPointReader.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE));
        }
        return new EncodedPolyline(stringBuilder.toString());
    }
}
