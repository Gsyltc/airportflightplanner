/*
 * @(#)SteerPointsCollectionModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.steerpoints;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionProperties;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionWriter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsCollectionModel extends Model implements SteerPointsCollectionWriter, SteerPointsListModelListener {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -3596872287379176646L;
    /** */
    protected LinkedListModel<SteerPointReader> steerPointsListModel = new LinkedListModel<SteerPointReader>();
    /** */
    private final List<SteerPointsListModelListener> listeners = new ArrayList<SteerPointsListModelListener>();
    /** */
    private FlightPlanReader currentFlightPlan;
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void onSteerPointAdded(final SteerPointReader value) {
        if (!steerPointsListModel.contains(value)) {
            steerPointsListModel.add(value);
        }
        
        if (SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 *
                 * {@inheritDoc}.
                 */
                @Override
                public void run() {
                    for (final SteerPointsListModelListener listener : getListeners()) {
                        listener.onSteerPointAdded(value);
                    }
                }
            });
        } else {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.onSteerPointAdded(value);
            }
        }
        
        commitChange();
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void onSteerPointRemoved(final SteerPointReader value) {
        if (!steerPointsListModel.contains(value)) {
            steerPointsListModel.add(value);
        }
        
        if (SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 *
                 * {@inheritDoc}.
                 */
                @Override
                public void run() {
                    for (final SteerPointsListModelListener listener : getListeners()) {
                        listener.onSteerPointRemoved(value);
                    }
                }
            });
        } else {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.onSteerPointRemoved(value);
            }
        }
        
        commitChange();
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final SteerPointReader getSteerPointByIndex(final int value) {
        return steerPointsListModel.get(value);
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int getSteerPointsCollectionSize() {
        return steerPointsListModel.getSize();
    }
    
    /**
     * @param newSPListModel
     *            the steerPointsListModel to set
     */
    @Override
    public void setSteerPointsListModel(final LinkedListModel<SteerPointReader> newSPListModel) {
        final LinkedListModel<SteerPointReader> oldValue = getSteerPointsListModel();
        if (!steerPointsListModel.equals(oldValue)) {
            steerPointsListModel = newSPListModel;
            firePropertyChange(SteerPointsCollectionProperties.STEERPOINTS_MAP, oldValue, steerPointsListModel);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void setCurrentFlightPlan(final FlightPlanReader newCurrentFlightPlan) {
        final FlightPlanReader oldValue = currentFlightPlan;
        if (!newCurrentFlightPlan.equals(oldValue)) {
            currentFlightPlan = newCurrentFlightPlan;
            firePropertyChange(SteerPointsCollectionProperties.CURRENT_FP, oldValue, currentFlightPlan);
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final FlightPlanReader getCurrentFlightPlan() {
        return currentFlightPlan;
    }
    
    /**
     *
     */
    private void commitChange() {
        if (SwingUtilities.isEventDispatchThread()) {
            firePropertyChange(SteerPointsCollectionProperties.STEERPOINTS_MAP, null, steerPointsListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    firePropertyChange(SteerPointsCollectionProperties.STEERPOINTS_MAP, null, steerPointsListModel);
                }
            });
        }
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final LinkedListModel<SteerPointReader> getSteerPointsListModel() {
        return steerPointsListModel;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void addListener(final SteerPointsListModelListener listener) {
        getListeners().add(listener);
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void removeListener(final SteerPointsListModelListener listener) {
        getListeners().remove(listener);
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
    public void onListReset() {
        steerPointsListModel.clear();
    }

}
