/* @(#)FlightPlanVisualisationReader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.api.flightplancollection.flightplan;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;

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
    public FligthPlanReader getFlightPlanByIndex(final int value);

    /**
     *
     * @return
     */
    public int getFlightPlanCollectionSize();

    /**
     *
     * @return
     */
    ListModel<FligthPlanReader> getListModel();

    /**
     *
     * @return
     */
    String getCurrentAirport();

}
