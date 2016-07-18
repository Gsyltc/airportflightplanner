/*
 * @(#)FlightPlanCollectionAdapterImpl.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.adapter.impl;

import com.airportflightplanner.common.api.adapter.FlightPlanCollectionAdapter;
import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;

/**
 * @author Goubaud Sylvain
 *
 */

public class FlightPlanCollectionAdapterImpl implements FlightPlanCollectionAdapter {
    /** */
    private FlighPlanCollectionModel model;
    /** */
    private String                   adapterName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentAirport(final String value) {
        model.setCurrentAirport(value);
    }

    /**
     * @param flighPlanCollectionModel
     *            the flighPlanCollectionModel to set
     */
    @Override
    public void setModel(final FlighPlanCollectionModel flighPlanCollectionModel) {
        model = flighPlanCollectionModel;
    }

    /**
     * @return the flighPlanCollectionModel
     */
    @Override
    public FlighPlanCollectionModel getModel() {
        return model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdapterName() {
        return adapterName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdapterName(final String name) {
        adapterName = name;
    }
}
