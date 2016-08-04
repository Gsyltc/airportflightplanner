/*
 * @(#)FlightPlanVisualizationMessages.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 2 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.messages;

import fr.gsyltc.framework.utils.internationalizer.AbstractMessages;

/**
 *
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationMessages extends AbstractMessages {
    
    
    /** */
    private static final String PREFIX = FlightPlanVisualizationMessages.class.getSimpleName() + ".";

    /** */
    public static final String AIRPORT = format(PREFIX + "AIRPORT" + LABEL);
    /** */
    public static final String TIME = format(PREFIX + "TIME" + LABEL);
    /** */
    public static final String CONFIRM_DIALOG_TEXT = format(PREFIX + "CONFIRM_DIALOG" + TEXT);
    /** */
    public static final String CONFIRM_DIALOG_TITLE = format(PREFIX + "CONFIRM_DIALOG" + TITLE);
}
