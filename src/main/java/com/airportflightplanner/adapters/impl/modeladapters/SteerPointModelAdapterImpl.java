/*
 * @(#)SteerPointModelAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl.modeladapters;

import java.util.ArrayList;
import java.util.List;

import com.airportflightplanner.adapters.api.modeladapters.SteerPointModelAdapter;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

import fr.gsyltc.framework.adapters.AbstractModelAdapterImpl;

/**
 * @author Goubaud Sylvain
 *
 */

public class SteerPointModelAdapterImpl extends AbstractModelAdapterImpl<SteerPointsCollectionModel> //
        implements SteerPointModelAdapter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -4401948080326943883L;
    
    /** */
    private final List<SteerPointsListModelListener> listeners = new ArrayList<SteerPointsListModelListener>();
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addListener(final SteerPointsListModelListener listener) {
        listeners.add(listener);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeListener(final SteerPointsListModelListener listener) {
        listeners.remove(listener);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addSteerPoint(final SteerPointReader value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.onSteerPointAdded(value);
            }
        }
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addSteerPoints(final List<SteerPointReader> list) {
        reset();
        for (final SteerPointReader steerPointReader : list) {
            addSteerPoint(steerPointReader);
        }
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeSteerPoint(final SteerPointReader value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.onSteerPointRemoved(value);
            }
        }
    }
    
    /**
     * @return the listeners
     */
    private List<SteerPointsListModelListener> getListeners() {
        return listeners;
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        // Nothing to do
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void reset() {
        for (final SteerPointsListModelListener listener : getListeners()) {
            listener.onListReset();
        }
    }
}
