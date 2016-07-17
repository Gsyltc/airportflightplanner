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
    private static final long         serialVersionUID = 2127934258581088787L;
    /** */
    private static final int          FIRST_STERRPOINT = 0;
    /** */
    private transient EncodedPolyline encodedPolyline;
    /** */
    private List<SteerPointReader>    specificSteerPoint;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setEncodedPolyline(final EncodedPolyline polyline) {
        final EncodedPolyline oldValue = encodedPolyline;
        if (null != polyline && !polyline.equals(encodedPolyline)) {
            encodedPolyline = polyline;
            firePropertyChange(GoogleMapModelProperties.POLYLINE, oldValue, encodedPolyline);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setMarkers(final List<SteerPointReader> specificSteerPoint) {
        final List<SteerPointReader> oldValue = getSpecificSteerPoint();
        if (null != specificSteerPoint && !specificSteerPoint.equals(this.specificSteerPoint)) {
            setSpecificSteerPoint(specificSteerPoint);
            firePropertyChange(GoogleMapModelProperties.STEERPOINTS, oldValue, this.specificSteerPoint);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public EncodedPolyline getEncodedPolyline() {
        return encodedPolyline;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LatLong getStartMarker() {
        return getSpecificSteerPoint().get(FIRST_STERRPOINT).getLatLong();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public LatLong getEndMarker() {
        return getSpecificSteerPoint().get(specificSteerPoint.size() - 1).getLatLong();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<SteerPointReader> getNamedMarker() {
        final List<SteerPointReader> namedMarkers = new ArrayList<SteerPointReader>();
        for (final SteerPointReader steerPointReader : getSpecificSteerPoint()) {
            if (!steerPointReader.getName().isEmpty()) {
                namedMarkers.add(steerPointReader);
            }
        }
        return namedMarkers;
    }

    /**
     * @return the specificSteerPoint
     */
    private List<SteerPointReader> getSpecificSteerPoint() {
        return specificSteerPoint;
    }

    /**
     * @param specificSteerPoint
     *            the specificSteerPoint to set
     */
    private void setSpecificSteerPoint(final List<SteerPointReader> specificSteerPoint) {
        this.specificSteerPoint = specificSteerPoint;
    }
}
