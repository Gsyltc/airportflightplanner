/*
 * @(#)SteerPointsCollectionModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
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
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.airportflightplanner.models.steerpoints.api.collection.SteerPointsCollectionWriter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsCollectionModel extends Model implements SteerPointsCollectionWriter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -3596872287379176646L;
    /** */
    protected final LinkedListModel<SteerPointReader> steerPointsListModel = new LinkedListModel<SteerPointReader>();
    /** */
    private final List<SteerPointsListModelListener> listeners = new ArrayList<SteerPointsListModelListener>();
    /** */
    private FlightPlanReader currentFlightPlan;
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void addSteerPoint(final SteerPointReader value) {
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
                        listener.addSteerPoint(value);
                    }
                }
            });
        } else {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.addSteerPoint(value);
            }
        }
        
        commitChange();
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void removeSteerPoint(final SteerPointReader value) {
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
                        listener.removeSteerPoint(value);
                    }
                }
            });
        } else {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.removeSteerPoint(value);
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
     *
     * {@inheritDoc}
     */
    @Override
    public final void setCurrentFlightPlan(final FlightPlanReader newCurrentFlightPlan) {
        final FlightPlanReader oldValue = currentFlightPlan;
        if (!newCurrentFlightPlan.equals(oldValue)) {
            currentFlightPlan = newCurrentFlightPlan;
            firePropertyChange(FlightPlanCollectionProperties.CURRENT_AIRPORT, oldValue, currentFlightPlan);
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
            firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, steerPointsListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, steerPointsListModel);
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
    
}
