/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */

package com.airportflightplanner.common.models.flightplans;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.api.flightplan.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.common.api.flightplan.collection.FlightPlanCollectionWriter;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationListModel;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighPlanCollectionModel extends Model implements FlightPlanCollectionWriter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -3596872287379176646L;
    /** */
    protected final FlightPlanVisualizationListModel flightPlanListModel = new FlightPlanVisualizationListModel();
    
    /** */
    private final List<FlightPlanVisualizationListModelListener> listeners = new ArrayList<FlightPlanVisualizationListModelListener>();
    /** */
    private String currentAirport = "";
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void addFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        getListeners().add(listener);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void addFlightPlan(final FlightPlanReader value) {
        if (null != value) {
            for (final FlightPlanVisualizationListModelListener listener : getListeners()) {
                listener.addFlightPlan(value);
            }
            commitChange();
        }
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final String getCurrentAirport() {
        return this.currentAirport;
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final FlightPlanReader getFlightPlanByIndex(final int value) {
        return this.flightPlanListModel.getElementAt(value);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final int getFlightPlanCollectionSize() {
        return this.flightPlanListModel.getSize();
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final FlightPlanVisualizationListModel getFlightPlanListModel() {
        return this.flightPlanListModel;
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void removeFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        getListeners().remove(listener);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void removeFlightPlan(final FlightPlanReader value) {
        if (null != value) {
            for (final FlightPlanVisualizationListModelListener listener : getListeners()) {
                listener.removeFlightPlan(value);
            }
            commitChange();
        }
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void setCurrentAirport(final String value) {
        final String oldValue = this.currentAirport;
        if (!value.equals(oldValue)) {
            this.currentAirport = value;
            firePropertyChange(FlightPlanCollectionProperties.CURRENT_AIRPORT, oldValue, this.currentAirport);
        }
    }
    
    /**
     *
     */
    private void commitChange() {
        if (SwingUtilities.isEventDispatchThread()) {
            firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, this.flightPlanListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 * {@inheritDoc}.
                 */
                @Override
                public void run() {
                    firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null,
                            FlighPlanCollectionModel.this.flightPlanListModel);
                }
            });
        }
    }
    
    /**
     *
     * @return
     */
    private List<FlightPlanVisualizationListModelListener> getListeners() {
        return this.listeners;
    }

}
