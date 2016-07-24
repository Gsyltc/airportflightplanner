/*
 * @(#)FlightPlanCollectionAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 27 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter;

import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;

import fr.gsyltc.framework.adapters.api.DomainModelAdapter;

/**
 * @author Goubaud Sylvain
 *
 */

public interface FlightPlanCollectionAdapter extends DomainModelAdapter<FlighPlanCollectionModel> {
    
    
    /**
     *
     * @param value
     */
    void setCurrentAirport(String value);
}
