/*
 * @(#)FlightPlanModelAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api.modeladapters;

import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.models.flightplans.FlightPlanModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.common.collect.LinkedListModel;

import fr.gsyltc.framework.adapters.api.DomainModelAdapter;

/**
 * Specific interface of a Flight plan Domain Model Adapter.
 *
 * @author Goubaud Sylvain
 */
public interface FlightPlanModelAdapter extends DomainModelAdapter<FlightPlanModel> {
    
    
    /**
     * Construct a route for the flight plan.
     *
     * @param steerpoints
     *            list of steer points.
     */
    void addSteerpoints(LinkedListModel<SteerPointReader> steerpoints);
    
    /**
     * Update the flight plan.
     *
     * @param informationsType
     *            Balise type to know the information that must be updated.
     * @param line
     *            list read in the file.
     */
    void updateFlightPlan(FlightPlanInformationTypes informationsType, String line);
    
    /**
     * Is the current Flight plan has been modified.
     *
     * @return true or false.
     */
    boolean isModificationToCommit();
    
    /**
     * Set if the flight plan has been modified.
     *
     * @param value
     *            the value to set
     */
    void setModificationtoCommit(boolean value);
}
