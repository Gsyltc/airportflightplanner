/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplancollection.FlightPlanCollectionWriter;
import com.airportflightplanner.common.api.flightplancollection.FligthPlanCollectionProperties;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.flightplanvisualization.presenter.FlightPlanVisualizationListModel;
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
    private final Map<Integer, FlightPlanReader>                 flightPlansSet      = new ConcurrentHashMap<Integer, FlightPlanReader>();
    /** */
    private final FlightPlanVisualizationListModel               flightPlanListModel = new FlightPlanVisualizationListModel();
    /** */
    private final List<FlightPlanVisualizationListModelListener> listeners           = new ArrayList<FlightPlanVisualizationListModelListener>();
    /** */
    private String                                               currentAirport      = "";

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addFlightPlan(final FlightPlanReader value) {
        if (null != value) {
            if (!flightPlansSet.containsValue(value)) {
                flightPlansSet.put(flightPlansSet.size(), value);
                for (FlightPlanVisualizationListModelListener flightPlanListModelListener : listeners) {
                    flightPlanListModelListener.addFlightPlan(value);
                }
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removeFlightPlan(final FlightPlanReader value) {
        if (null != value) {
            for (Entry<Integer, FlightPlanReader> entry : flightPlansSet.entrySet()) {
                if (entry.getValue().equals(value)) {
                    flightPlansSet.remove(value);
                    for (FlightPlanVisualizationListModelListener flightPlanListModelListener : listeners) {
                        flightPlanListModelListener.removeFlightPlan(value);
                    }
                }
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FlightPlanReader getFlightPlanByIndex(final int value) {
        return flightPlansSet.get(value);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getFlightPlanCollectionSize() {
        return flightPlansSet.size();
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
            firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, flightPlansSet);
        } else {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, flightPlansSet);
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
