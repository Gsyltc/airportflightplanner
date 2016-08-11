/*
 * @(#)FlightPlanCollectionWriter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.flightplans.api.collection;

import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

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
     * @param currentAirport
     */
    void setCurrentAirport(String currentAirport);
}
