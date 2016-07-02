/* @(#)MainPanelMessages.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.messages;

import com.airportflightplanner.common.utils.internationalization.AbstractMessages;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationMessages extends AbstractMessages {
    /** */
    private static final String PREFIX  = FlightPlanVisualizationMessages.class.getSimpleName() + ".";

    /** */
    public static final String  AIRPORT = format(PREFIX + "AIRPORT" + LABEL);
    /** */
    public static final String  TIME    = format(PREFIX + "TIME" + LABEL);
}
