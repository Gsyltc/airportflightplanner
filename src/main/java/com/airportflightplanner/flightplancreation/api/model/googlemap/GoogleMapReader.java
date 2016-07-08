/* @(#)GoogleMapReader.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.api.model.googlemap;

import java.util.List;

import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.google.maps.model.EncodedPolyline;

/**
 * @author DCNS
 *
 */
public interface GoogleMapReader {

    /**
     *
     * @return
     */
    EncodedPolyline getEncodePolyline();

    /**
     *
     * @return
     */
    LatLong getStartMarker();

    /**
     *
     * @return
     */
    LatLong getEndMarker();

    /**
     *
     * @return
     */
    List<SteerPointReader> getNamedMarker();
}
