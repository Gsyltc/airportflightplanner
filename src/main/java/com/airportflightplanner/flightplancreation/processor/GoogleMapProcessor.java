/* @(#)GoogleMapAdapterImpl.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.processor;

import javax.measure.unit.NonSI;

import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.flightplancreation.api.model.GoogleMapReader;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;

/**
 * @author DCNS
 *
 */
public class GoogleMapProcessor {
    /** */
    private static final String MARKER_PREFIX = "&markers=color:";
    /** */
    private static final String START_MARKER  = MARKER_PREFIX + "blue%7Clabel:" + FlightPlanCreationPanelMessages.MARKER_START + "%7C";
    /** */
    private static final String END_MARKER    = MARKER_PREFIX + "red%7Clabel:" + FlightPlanCreationPanelMessages.MARKER_END + "%7C";

    /**
     *
     * @param mapReader
     * @return
     */
    public static String getEncodedRoad(final GoogleMapReader mapReader) {
        StringBuilder sb = new StringBuilder();
        sb.append(mapReader.getEncodePolyline().getEncodedPath())//
                .append(START_MARKER + getFormattedCoordinates(mapReader.getStartMarker()))//
                .append(END_MARKER + getFormattedCoordinates(mapReader.getEndMarker()));
        return sb.toString();
    }

    /**
     *
     * @param latLong
     * @return
     */
    private static String getFormattedCoordinates(final LatLong latLong) {
        return latLong.latitudeValue(NonSI.DEGREE_ANGLE) + "," + latLong.longitudeValue(NonSI.DEGREE_ANGLE);
    }
}
