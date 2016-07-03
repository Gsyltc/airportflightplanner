/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FligthPlanCollectionProperties;
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
    public void addSteerPoints(final List<SteerPointReader> value) {
        if (null != value) {
            for (SteerPointsListModelListener steerpointsListModelListener : listeners) {
                for (SteerPointReader steerPointModel : value) {
                    steerpointsListModelListener.addSteerPoint(steerPointModel);
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
    public void removeSteerPoint(final SteerPointReader value) {
        if (null != value) {
            for (SteerPointsListModelListener steerpointsListModelListener : listeners) {
                steerpointsListModelListener.removeSteerPoint(value);
            }
            commitChange();
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public SteerPointReader getSteerPointByIndex(final int value) {
        return steerPointsListModel.getElementAt(value);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getSteerPointsCollectionSize() {
        return steerPointsListModel.getSize();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setCurrentFlightPlan(final FlightPlanReader currentFlightPlan) {
        FlightPlanReader oldValue = this.currentFlightPlan;
        if (!currentFlightPlan.equals(oldValue)) {
            this.currentFlightPlan = currentFlightPlan;
            firePropertyChange(FligthPlanCollectionProperties.CURRENT_AIRPORT, oldValue, this.currentFlightPlan);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FlightPlanReader getCurrentFlightPlan() {
        return currentFlightPlan;
    }

    /**
     *
     */
    private void commitChange() {
        if (SwingUtilities.isEventDispatchThread()) {
            firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, steerPointsListModel);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                /**
                 *
                 * {@inheritDoc}
                 */
                @Override
                public void run() {
                    firePropertyChange(FligthPlanCollectionProperties.FLIGHT_PLANS, null, steerPointsListModel);
                }
            });
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public SteerPointsListModel getListModel() {
        return steerPointsListModel;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addSteerPointsListModelListener(final  SteerPointsListModelListener listener) {
        this.listeners.add(listener);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void removeSteerPointsListModelListener(final  SteerPointsListModelListener listener) {
        this.listeners.remove(listener);
    }

}
