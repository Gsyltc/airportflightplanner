/*
 * @(#)FlightPlanCollectionReader.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.common.api.flightplancollection.flightplan;

import java.io.Serializable;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanCollectionReader extends Serializable{

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
    ListModel<FlightPlanReader> getFlightPlanListModel();

    /**
     *
     * @return
     */
    String getCurrentAirport();

}
