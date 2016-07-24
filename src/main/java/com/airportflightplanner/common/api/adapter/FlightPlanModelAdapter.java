/*
 * @(#)FlightPlanModelAdapter.java
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

import java.util.List;

import com.airportflightplanner.common.models.flightplans.FlightPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;

import fr.gsyltc.framework.adapters.api.DomainModelAdapter;

/**
 * Specific interface of a Flight plan Domain Model Adapter.
 *
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanModelAdapter extends DomainModelAdapter<FlightPlanModel> {
    
    
    /**
     * Construct a route for the flight plan.
     *
     * @param steerpoints
     *            list of steerpoints.
     */
    void addSteerpoints(List<String> steerpoints);
    
    /**
     * Update the flight plan.
     *
     * @param informationsType
     *            Balise type to know the information that must be updated.
     * @param line
     *            list read in the file.
     */
    void updateFlightPlan(FlightPlanInformationTypes informationsType, String line);
}
