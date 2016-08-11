/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */

package com.airportflightplanner.models.flightplans;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.SwingUtilities;

import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationListModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionWriter;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighPlanCollectionModel extends Model implements FlightPlanCollectionWriter,
        FlightPlanVisualizationListModelListener {
    
    
    /** */
    protected final FlightPlanVisualizationListModel flightPlanListModel = new FlightPlanVisualizationListModel();
    /**
     *
     */
    private static final long serialVersionUID = -3596872287379176646L;
    /** flight plans list. */
    private final List<FlightPlanReader> flightPlans = new CopyOnWriteArrayList<FlightPlanReader>();
    /** */
    private String currentAirport = "";

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
        if (!flightPlans.contains(value)) {
            flightPlans.add(value);
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeFlightPlan(final FlightPlanReader value) {
        if (flightPlans.contains(value)) {
            flightPlans.remove(value);
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final FlightPlanReader getFlightPlanByIndex(final int value) {
        return flightPlans.get(value);
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final int getFlightPlanCollectionSize() {
        return flightPlans.size();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final FlightPlanVisualizationListModel getFlightPlanListModel() {
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
            firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, flightPlans);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                
                
                /**
                 *
                 * {@inheritDoc}.
                 */
                @Override
                public void run() {
                    firePropertyChange(FlightPlanCollectionProperties.FLIGHT_PLANS, null, flightPlans);
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
        return Collections.unmodifiableList(flightPlans);
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void resetFlightPlans() {
        flightPlans.clear();
        commitChange();
    }
}
