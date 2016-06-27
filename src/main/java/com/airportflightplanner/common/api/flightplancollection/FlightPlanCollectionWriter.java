/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplancollection;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
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
