/*
 * @(#)FlightPlanCollectionReader.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.flightplan.collection;

import java.io.Serializable;

import javax.swing.ListModel;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;

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
    ListModel<FlightPlanReader> getFlightPlanListModel();
    
}
