/* @(#)FlighPlanModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.models;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
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
    protected transient final SteerPointsListModel             steerPointsListModel = new SteerPointsListModel();
    /** */
    private transient final List<SteerPointsListModelListener> listeners            = new ArrayList<SteerPointsListModelListener>();
    /** */
    private FligthPlanReader                         currentFlightPlan;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void addSteerPoints(final List<SteerPointReader> value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : listeners) {
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
    public final  void removeSteerPoint(final SteerPointReader value) {
        if (null != value) {
            for (final SteerPointsListModelListener listener : listeners) {
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
    public final  SteerPointReader getSteerPointByIndex(final int value) {
        return steerPointsListModel.getElementAt(value);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  int getSteerPointsCollectionSize() {
        return steerPointsListModel.getSize();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void setCurrentFlightPlan(final FligthPlanReader newCurrentFlightPlan) {
        final FligthPlanReader oldValue = currentFlightPlan;
        if (!newCurrentFlightPlan.equals(oldValue)) {
            currentFlightPlan = newCurrentFlightPlan;
            firePropertyChange(FligthPlanCollectionProperties.CURRENT_AIRPORT, oldValue, currentFlightPlan);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  FligthPlanReader getCurrentFlightPlan() {
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
    public  final SteerPointsListModel getListModel() {
        return steerPointsListModel;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void addSteerPointsListModelListener(final  SteerPointsListModelListener listener) {
        listeners.add(listener);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final  void removeSteerPointsListModelListener(final  SteerPointsListModelListener listener) {
        listeners.remove(listener);
    }

}
