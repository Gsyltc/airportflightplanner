/*
 * @(#)TopicName.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 1 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.slotsignal;

/**
 * @author Goubaud Sylvain
 *
 */
public final class TopicName {
    
    
    /** */
    public static final String UPDATE_AIRPORT_TOPIC = "UPDATE_AIRPORT_TOPIC";
    /** */
    public static final String FP_TABLE_SELECTED_TOPIC = "FLIGHTPLAN_TABLE_SELECTED";
    /** */
    public static final String WRITE_FLIGHT_PLAN_TOPIC = "WRITE_FLIGHT_PLAN";
    /** */
    public static final String GOOGLE_PARAMETERS_TOPIC = "GOOGLE_PARAMETERS";
    /*** */
    public static final String VALIDATION_TOPIC = "VALIDATION";
    /*** */
    public static final String CANCELLATION_TOPIC = "CANCELLATION";
    /** */
    public static final String FP_MODIFIED_TOPIC = "FP_MODIFIED";
    
    /**
     * Protected constructor.
     */
    private TopicName() {
        //
    }
}
