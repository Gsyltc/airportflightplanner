/* @(#)FlightPlanTableColumn.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum SteerPointsTableColumn {
    LATITUDE(0), LONGITUDE(1), VELOCITY(2), ALTITUDE(3);

    private static final String PREFIX = "SteerPointsTableColumn.";
    /** */
    private int                 value;

    /**
     *
     * @param value
     */
    private SteerPointsTableColumn(final int value) {
        this.value = value;
    }

    /**
     *
     * @param columnNumber
     * @return
     */
    public static SteerPointsTableColumn valueOf(final int columnNumber) {
        for (SteerPointsTableColumn iterable_element : SteerPointsTableColumn.values()) {
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

    /**
     *
     * @param column
     * @return
     */
    public static int getColumnNumber(final SteerPointsTableColumn column) {
        return column.value;

    }
}
