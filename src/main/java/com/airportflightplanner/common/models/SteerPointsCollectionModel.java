/*
 * @(#)SteerPointsCollectionModel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FlightPlanCollectionProperties;
import com.airportflightplanner.common.api.flightplancollection.steerpoints.SteerPointsCollectionWriter;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsListModel;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsCollectionModel extends Model implements SteerPointsCollectionWriter {

    /**
     *
     */
    private static final long                        serialVersionUID     = -3596872287379176646L;
    /** */
    protected final SteerPointsListModel             steerPointsListModel = new SteerPointsListModel();
    /** */
    private final List<SteerPointsListModelListener> listeners            = new ArrayList<SteerPointsListModelListener>();
    /** */
    private FlightPlanReader                         currentFlightPlan;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void addSteerPoints(final List<SteerPointReader> value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : getListeners()) {
                for (final SteerPointReader steerPointModel : value) {
                    listener.addSteerPoint(steerPointModel);
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
    public final void removeSteerPoint(final SteerPointReader value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : getListeners()) {
                listener.removeSteerPoint(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final SteerPointReader getSteerPointByIndex(final int value) {
        return steerPointsListModel.getElementAt(value);
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
    public final SteerPointsListModel getSteerPointsListModel() {
        return steerPointsListModel;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void addSteerPointsListModelListener(final SteerPointsListModelListener listener) {
        getListeners().add(listener);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void removeSteerPointsListModelListener(final SteerPointsListModelListener listener) {
        getListeners().remove(listener);
    }

    /**
     * @return the listeners
     */
    private List<SteerPointsListModelListener> getListeners() {
        return listeners;
    }

}
