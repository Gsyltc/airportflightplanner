/*
 * @(#)FlightPlanCollectionWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.models.flightplans.api.collection;

/**
 * @author Goubaud Sylvain
 *
 */
public interface FlightPlanCollectionWriter extends FlightPlanCollectionReader {
    
    
    /**
     *
     * @param currentAirport
     */
    void setCurrentAirport(String currentAirport);
}
