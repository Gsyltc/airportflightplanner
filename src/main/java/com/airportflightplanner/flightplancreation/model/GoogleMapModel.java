/* @(#)GoogleMapModel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.model;

import java.util.ArrayList;
import java.util.List;

import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapModelProperties;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapWriter;
import com.google.maps.model.EncodedPolyline;
import com.jgoodies.binding.beans.Model;

/**
 * @author DCNS
 *
 */
public class GoogleMapModel extends Model implements GoogleMapWriter {
    /**
     *
     */
    private static final long      serialVersionUID = 2127934258581088787L;
    /** */
    private static final int       FIRST_STERRPOINT = 0;
    /** */
    private EncodedPolyline        polyline;
    /** */
    private List<SteerPointReader> specificSteerPoint;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setEncodedPolyline(final EncodedPolyline polyline) {
        EncodedPolyline oldValue = this.polyline;
        if ((null != polyline) && !polyline.equals(this.polyline)) {
            this.polyline = polyline;
            firePropertyChange(GoogleMapModelProperties.POLYLINE, oldValue, this.polyline);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setMarkers(final List<SteerPointReader> specificSteerPoint) {
        List<SteerPointReader> oldValue = this.specificSteerPoint;
        if ((null != specificSteerPoint) && !specificSteerPoint.equals(this.specificSteerPoint)) {
            this.specificSteerPoint = specificSteerPoint;
            firePropertyChange(GoogleMapModelProperties.STEERPOINTS, oldValue, this.specificSteerPoint);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public EncodedPolyline getEncodePolyline() {
        return polyline;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LatLong getStartMarker() {
        return specificSteerPoint.get(FIRST_STERRPOINT).getLatLong();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LatLong getEndMarker() {
        return specificSteerPoint.get(specificSteerPoint.size() - 1).getLatLong();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<SteerPointReader> getNamedMarker() {
        List<SteerPointReader> namedMarkers = new ArrayList<SteerPointReader>();
        for (SteerPointReader steerPointReader : specificSteerPoint) {
            if (!steerPointReader.getName().isEmpty()) {
                namedMarkers.add(steerPointReader);
            }
        }
        return namedMarkers;
    }

}
