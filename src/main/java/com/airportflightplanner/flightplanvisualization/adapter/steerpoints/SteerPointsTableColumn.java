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
        SteerPointsTableColumn result = null;
        for (final SteerPointsTableColumn iterable_element : SteerPointsTableColumn.values()) {
            if (iterable_element.ordinal() == columnNumber) {
                result= iterable_element;
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
    public static int getColumnNumber(final SteerPointsTableColumn column) {
        return column.value;

    }
}
