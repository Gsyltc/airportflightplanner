/* @(#)FlightPlanTableColumn.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum FlightPlanVisualisationTableColumn {
    START_AIRPORT(0), DEPARTURE_TIME(1), ARRIVAL_TIME(2), AIRCRAFT_TYPE(3), COMPANY(4), DEST_AIRPORT(5), DURATION(6);

    private static final String PREFIX = "FlightPlanVisualisationTableColumn.";
    /** */
    public int                  value;

    /**
     *
     * @param value
     */
    private FlightPlanVisualisationTableColumn(final int value) {
        this.value = value;
    }

    /**
     *
     * @param columnNumber
     * @return
     */
    public static FlightPlanVisualisationTableColumn valueOf(final int columnNumber) {
        for (FlightPlanVisualisationTableColumn iterable_element : FlightPlanVisualisationTableColumn.values()) {
            if (iterable_element.ordinal() == columnNumber) {
                return iterable_element;
            }
        }
        return null;
    }

    /**
     * Get i18nString
     *
     * @param columnIndex
     * @return
     */
    public static String getName(final int columnIndex) {
        return Internationalizer.getI18String(PREFIX + valueOf(columnIndex).name());
    }
}
