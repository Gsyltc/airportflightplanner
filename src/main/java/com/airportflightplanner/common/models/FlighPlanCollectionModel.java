/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.models;

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
    protected transient final FlightPlanVisualizationListModel             flightPlanListModel = new FlightPlanVisualizationListModel();
    /** */
    private transient final List<FlightPlanVisualizationListModelListener> listeners           = new ArrayList<FlightPlanVisualizationListModelListener>();
    /** */
    private String                                               currentAirport      = "";

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void addFlightPlan(final FligthPlanReader value) {
        if (null != value) {
            for (final FlightPlanVisualizationListModelListener listener : listeners) {
                listener.addFlightPlan(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void removeFlightPlan(final FligthPlanReader value) {
        if (null != value) {
            for (final FlightPlanVisualizationListModelListener listener : listeners) {
                listener.removeFlightPlan(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  FligthPlanReader getFlightPlanByIndex(final int value) {
        return flightPlanListModel.getElementAt(value);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  int getFlightPlanCollectionSize() {
        return flightPlanListModel.getSize();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public  final void setCurrentAirport(final String value) {
        final String oldValue = currentAirport;
        if (!value.equals(oldValue)) {
            currentAirport = value;
            firePropertyChange(FligthPlanCollectionProperties.CURRENT_AIRPORT, oldValue, currentAirport);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public  final String getCurrentAirport() {
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
    public  final FlightPlanVisualizationListModel getListModel() {
        return flightPlanListModel;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public  final void addFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        listeners.add(listener);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public  final void removeFligfhtPlanModelListener(final FlightPlanVisualizationListModelListener listener) {
        listeners.remove(listener);
    }

}
