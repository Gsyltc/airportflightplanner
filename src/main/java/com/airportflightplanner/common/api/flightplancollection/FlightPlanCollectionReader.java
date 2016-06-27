/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplancollection;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanCollectionReader {

    /**
     *
     * @param value
     * @return
     */
    public FlightPlanReader getFlightPlanByIndex(final int value);

    /**
     *
     * @return
     */
    public int getFlightPlanCollectionSize();

    /**
     *
     * @return
     */
    ListModel<FlightPlanReader> getListModel();

    /**
     *
     * @return
     */
    String getCurrentAirport();

}
