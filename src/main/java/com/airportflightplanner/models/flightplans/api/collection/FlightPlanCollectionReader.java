/*
 * @(#)FlightPlanCollectionReader.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.flightplans.api.collection;

import java.io.Serializable;
import java.util.List;

import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationListModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanCollectionReader extends Serializable {
    
    
    /**
     *
     * @return
     */
    String getCurrentAirport();

    /**
     *
     * @param value
     * @return
     */
    FlightPlanReader getFlightPlanByIndex(final int value);

    /**
     *
     * @return
     */
    int getFlightPlanCollectionSize();

    /**
     *
     * @return
     */
    List<FlightPlanReader> getFlightPlans();

    /**
     * @return
     */
    FlightPlanVisualizationListModel getFlightPlanListModel();

}
