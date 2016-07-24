/*
 * @(#)FlightPlanVisualisationTableColumn.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.flightplanvisualization.adapter.flightplan;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum FlightPlanVisualisationTableColumn {
    /** */
    START_AIRPORT(0),
    /** */
    DEPARTURE_TIME(1),
    /** */
    ARRIVAL_TIME(2),
    /** */
    AIRCRAFT_TYPE(3),
    /** */
    COMPANY(4),
    /** */
    DEST_AIRPORT(5),
    /** */
    DURATION(6);
    /** */
    private static final String PREFIX = "FlightPlanVisualisationTableColumn.";
    /** */
    private int                 value;

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
        FlightPlanVisualisationTableColumn result = null;
        for (final FlightPlanVisualisationTableColumn column : FlightPlanVisualisationTableColumn.values()) {
            if (column.ordinal() == columnNumber) {
                result = column;
            }
        }
        return result;
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

    /**
     *
     * @param column
     * @return
     */
    public static int getColumnNumber(final FlightPlanVisualisationTableColumn column) {
        return column.value;

    }
}
