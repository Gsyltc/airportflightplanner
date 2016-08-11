/*
 * @(#)FlightPlanCollectionAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 11 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api.modeladapters;

import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.models.flightplans.FlighPlanCollectionModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

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

    /**
     *
     * @param listener
     */
    void addListener(FlightPlanVisualizationListModelListener listener);

    /**
     *
     * @param listener
     */
    void removeListener(FlightPlanVisualizationListModelListener listener);

    /**
     * @param value
     */
    void addFlightPlan(FlightPlanReader value);

    /**
     * @param value
     */
    void removeFlightPlan(FlightPlanReader value);

    /**
     *
     */
    void resetFlightPlans();

}
