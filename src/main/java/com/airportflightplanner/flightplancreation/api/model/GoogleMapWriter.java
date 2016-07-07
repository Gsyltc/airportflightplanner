/* @(#)GoogleMapWriter.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.api.model;

import java.util.List;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.google.maps.model.EncodedPolyline;

/**
 * @author DCNS
 *
 */
public interface GoogleMapWriter extends GoogleMapReader {
    /**
     *
     * @param polyline
     */
    void setEncodedPolyline(EncodedPolyline polyline);

    /**
     *
     * @param specificSteerPoint
     */
    void setMarkers(List<SteerPointReader> specificSteerPoint);

}
