/* @(#)FlightPlanModelAdapter.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.api.adapter;

import java.util.List;
import java.util.Set;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanModelAdapter {
    /**
     * @param dataType
     * @param data
     *
     */
    void update(FlightPlanInformationTypes dataType, String data);

    /**
     *
     * @param newFlightPlan
     * @param steerpoints
     */
    void addSteerpoints(FlighPlanModel newFlightPlan, List<String> steerpoints);

    /**
     *
     * @param newFlightPlan
     * @param informationsType
     * @param line
     */
    void updateFlightPlan(FlighPlanModel newFlightPlan, FlightPlanInformationTypes informationsType, String line);

    /**
     *
     * @param newFlightPlan
     * @param startDays
     */
    void addStartDays(FlighPlanModel newFlightPlan, Set<String> startDays);

    /**
     *
     * @return
     */
    List<FlightPlanReader> getFlighPlans();

}
