/*
 * @(#)SteerPointsTableColumn.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public enum SteerPointsTableColumn {
    /** */
    LATITUDE(0),
    /** */
    LONGITUDE(1),
    /** */
    VELOCITY(2),
    /** */
    ALTITUDE(3);
    
    /** */
    private static final String PREFIX = "SteerPointsTableColumn.";
    
    /**
     *
     * @param column
     * @return
     */
    public static int getColumnNumber(final SteerPointsTableColumn column) {
        return column.value;

    }

    /**
     * Get i18nString.
     *
     * @param columnIndex
     * @return
     */
    public static String getName(final int columnIndex) {
        return Internationalizer.getI18String(PREFIX + valueOf(columnIndex).name());
    }

    /**
     *
     * @param columnNumber
     * @return
     */
    public static SteerPointsTableColumn valueOf(final int columnNumber) {
        SteerPointsTableColumn result = null;
        for (final SteerPointsTableColumn column : SteerPointsTableColumn.values()) {
            if (column.ordinal() == columnNumber) {
                result = column;
            }
        }
        return result;
    }

    /** */
    private int value;

    /**
     *
     * @param value
     */
    SteerPointsTableColumn(final int value) {
        this.value = value;
    }
}
