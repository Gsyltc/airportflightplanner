/*
 * @(#)FlightPlanCollectionAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 27 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl.modeladapters;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.airportflightplanner.adapters.api.modeladapters.FlightPlanCollectionAdapter;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.models.flightplans.FlighPlanCollectionModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionProperties;

import fr.gsyltc.framework.adapters.AbstractModelAdapterImpl;

/**
 * The flight plan collection Domain Model Adapter.
 *
 * @author Goubaud Sylvain
 *
 */

public class FlightPlanCollectionAdapterImpl extends AbstractModelAdapterImpl<FlighPlanCollectionModel> //
        implements FlightPlanCollectionAdapter {
    
    
    /** . */
    private static final long serialVersionUID = 4408549118062180286L;
    /** */
    private final List<FlightPlanVisualizationListModelListener> listeners = new ArrayList<FlightPlanVisualizationListModelListener>();
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        getModel().addPropertyChangeListener(FlightPlanCollectionProperties.CURRENT_AIRPORT, new PropertyChangeListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                // TODO Auto-generated method stub

            }
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentAirport(final String value) {
        getModel().setCurrentAirport(value);
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
        }
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
        }
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void addListener(final FlightPlanVisualizationListModelListener listener) {
        getListeners().add(listener);
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void removeListener(final FlightPlanVisualizationListModelListener listener) {
        getListeners().remove(listener);
    }
    
    /**
     *
     * @return
     */
    private List<FlightPlanVisualizationListModelListener> getListeners() {
        return listeners;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void resetFlightPlans() {
        for (final FlightPlanVisualizationListModelListener listener : getListeners()) {
            listener.resetFlightPlans();
        }
    }
}
