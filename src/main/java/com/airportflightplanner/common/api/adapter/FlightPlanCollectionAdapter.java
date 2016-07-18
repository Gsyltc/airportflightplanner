/*
 * @(#)FlightPlanCollectionAdapter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter;

import com.airportflightplanner.common.api.adapter.common.CommonAdapter;
import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;

/**
 * @author Goubaud Sylvain
 *
 */

public interface FlightPlanCollectionAdapter extends CommonAdapter<FlighPlanCollectionModel> {
    /**
     *
     * @param value
     */
    void setCurrentAirport(String value);
}
