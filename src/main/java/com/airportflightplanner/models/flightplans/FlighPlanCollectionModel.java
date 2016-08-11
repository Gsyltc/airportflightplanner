/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */

package com.airportflightplanner.models.flightplans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionWriter;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.LinkedListModel;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighPlanCollectionModel extends Model implements FlightPlanCollectionWriter, FlightPlanVisualizationListModelListener {

    /** */
    protected final LinkedListModel<FlightPlanReader>            flightPlanListModel = new LinkedListModel<>();
    /**
     *
     */
    private static final long                                    serialVersionUID    = -3596872287379176646L;
    /** */
    private String                                               currentAirport      = "";
    /** */
    private final List<FlightPlanVisualizationListModelListener> listeners           = new ArrayList<FlightPlanVisualizationListModelListener>();

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final String getCurrentAirport() {
        return currentAirport;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addFlightPlan(final FlightPlanReader value) {
        if (!flightPlanListModel.contains(value)) {
            flightPlanListModel.add(value);
        }
        for (FlightPlanVisualizationListModelListener listener : listeners) {
            listener.addFlightPlan(value);
        }
        commitChange();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeFlightPlan(final FlightPlanReader value) {
        if (flightPlanListModel.contains(value)) {
            flightPlanListModel.remove(value);

        }
        for (FlightPlanVisualizationListModelListener listener : listeners) {
            listener.removeFlightPlan(value);
        }

        commitChange();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final FlightPlanReader getFlightPlanByIndex(final int value) {
        return flightPlanListModel.get(value);
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final int getFlightPlanCollectionSize() {
        return flightPlanListModel.size();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final LinkedListModel<FlightPlanReader> getFlightPlanListModel() {
        return flightPlanListModel;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void setCurrentAirport(final String value) {
        final String oldValue = currentAirport;
        if (!value.equals(oldValue)) {
            currentAirport = value;
            firePropertyChange(FlightPlanCollectionProperties.CURRENT_AIRPORT, oldValue, currentAirport);
        }
    }

    /**
     *
     */
    private void commitChange() {
        if (SwingUtilities.isEventDispatchThread()) {
            firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, flightPlanListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {

                /**
                 *
                 * {@inheritDoc}.
                 */
                @Override
                public void run() {
                    firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, flightPlanListModel);
                }
            });
        }
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public List<FlightPlanReader> getFlightPlans() {
        return Collections.unmodifiableList(flightPlanListModel);
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void resetFlightPlans() {
        flightPlanListModel.clear();
        commitChange();
    }

    /**
     *
     */
    @Override
    public void addListener(final FlightPlanVisualizationListModelListener listener) {
        listeners.add(listener);
    }

    /**
     *
     * @param listener
     */
    @Override
    public void removeListener(final FlightPlanVisualizationListModelListener listener) {
        listeners.remove(listener);
    }
}
