/*
 * @(#)WaypointModificationMessages.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 8 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.waypointmodifications.messages;

import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;

import fr.gsyltc.framework.utils.internationalizer.AbstractMessages;

/**
 * @author Goubaud Sylvain
 *
 */
public class WaypointModificationMessages extends AbstractMessages {
    
    
    /** */
    private static final String PREFIX = WaypointModificationMessages.class.getSimpleName() + ".";

    /** */
    public static final String WAYPOINT_LIST_TITLE = format(PREFIX + "WAYPOINT_LIST" + TITLE);
    /** */
    public static final String TIME_LABEL = FlightPlanCreationPanelMessages.TIME_LABEL;
    /** */
    public static final String DISTANCE_LABEL = format(PREFIX + "DISTANCE" + LABEL);
    /** */
    public static final String FLIGHT_INFO_TITLE = format(PREFIX + "FLIGHT_INFO" + TITLE);
}
