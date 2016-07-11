/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplancollection.flightplan;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
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
    void addFlightPlan(FligthPlanReader value);

    /**
     *
     * @param value
     */
    void removeFlightPlan(FligthPlanReader value);

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
