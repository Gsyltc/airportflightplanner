/*
 * @(#)FlightPlanCollectionWriter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.api.flightplan.collection;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanCollectionWriter extends FlightPlanCollectionReader {
    /**
     *
     * @param value
     */
    void addFlightPlan(FlightPlanReader value);

    /**
     *
     * @param value
     */
    void removeFlightPlan(FlightPlanReader value);

    /**
     *
     * @param listener
     */
    void addFligfhtPlanModelListener(FlightPlanVisualizationListModelListener listener);

    /**
     *
     * @param listener
     */
    void removeFligfhtPlanModelListener(FlightPlanVisualizationListModelListener listener);

    /**
     *
     * @param currentAirport
     */
    void setCurrentAirport(String currentAirport);
}
