/*
 * @(#)FlightPlanModelAdapter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.api.adapter;

import java.util.List;

import com.airportflightplanner.common.model.FligthPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanModelAdapter extends CommonAdapter{

    /**
     * Construct a route for the flight plan.
     *
     * @param newFlightPlan
     *            a flight plan.
     * @param steerpoints
     *            list of steerpoints.
     */
    void addSteerpoints(FligthPlanModel newFlightPlan, List<String> steerpoints);

    /**
     * Update the flight plan.
     *
     * @param newFlightPlan
     *            a flight plan.
     * @param informationsType
     *            Balise type to know the information that must be updated.
     * @param line
     *            list read in the file.
     */
    void updateFlightPlan(FligthPlanModel newFlightPlan, FlightPlanInformationTypes informationsType, String line);
}
