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

package com.airportflightplanner.common.adapter.impl;

import com.airportflightplanner.common.api.adapter.FlightPlanCollectionAdapter;
import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;

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

    /**
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        // Nothing to do
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentAirport(final String value) {
        getModel().setCurrentAirport(value);
    }
}
