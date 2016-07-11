/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FlightPlanCollectionWriter;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FligthPlanCollectionProperties;
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
    private static final long                                    serialVersionUID    = -3596872287379176646L;
    /** */
    protected final FlightPlanVisualizationListModel             flightPlanListModel = new FlightPlanVisualizationListModel();
    /** */
    private final List<FlightPlanVisualizationListModelListener> listeners           = new ArrayList<FlightPlanVisualizationListModelListener>();
    /** */
    private String                                               currentAirport      = "";

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addFlightPlan(final FligthPlanReader value) {
        if (null != value) {
            for (FlightPlanVisualizationListModelListener flightPlanListModelListener : listeners) {
                flightPlanListModelListener.addFlightPlan(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removeFlightPlan(final FligthPlanReader value) {
        if (null != value) {
            for (FlightPlanVisualizationListModelListener flightPlanListModelListener : listeners) {
                flightPlanListModelListener.removeFlightPlan(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FligthPlanReader getFlightPlanByIndex(final int value) {
        return flightPlanListModel.getElementAt(value);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getFlightPlanCollectionSize() {
        return flightPlanListModel.getSize();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setCurrentAirport(final String currentAirport) {
        String oldValue = this.currentAirport;
        if (!currentAirport.equals(oldValue)) {
            this.currentAirport = currentAirport;
            firePropertyChange(FligthPlanCollectionProperties.CURRENT_AIRPORT, oldValue, this.currentAirport);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getCurrentAirport() {
        return currentAirport;
    }

    /**
     *
     */
    private void commitChange() {
        if (SwingUtilities.isEventDispatchThread()) {
            firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, flightPlanListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                /**
                 *
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, flightPlanListModel);
                }
            });
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FlightPlanVisualizationListModel getListModel() {
        return flightPlanListModel;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        this.listeners.add(listener);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removeFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        this.listeners.remove(listener);
    }

}
