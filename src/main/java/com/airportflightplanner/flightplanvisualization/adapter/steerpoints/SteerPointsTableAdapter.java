/* @(#)FlightPlanTableModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import java.text.MessageFormat;

import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.airportflightplanner.common.api.flightplancollection.flightplan.FlightPlanCollectionReader;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.common.types.GeographicFormatter;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsListModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsTableAdapter extends AbstractTableAdapter<FlightPlanCollectionReader> {
    /**
     *
     */
    private static final long                     serialVersionUID = -1614722326210452309L;
    /** */
    private static final SteerPointsTableColumn[] COLUMN_NAME      = new SteerPointsTableColumn[] {   //
            SteerPointsTableColumn.LATITUDE,                                                          //
            SteerPointsTableColumn.LONGITUDE,                                                         //
            SteerPointsTableColumn.VELOCITY,                                                          //
            SteerPointsTableColumn.ALTITUDE };                                                        //

    /**
     *
     * @param listModel
     *            List model for the steerpoint.
     */
    public SteerPointsTableAdapter(final SteerPointsListModel listModel) {
        setListModel(listModel);

        listModel.addListDataListener(new ListDataListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalRemoved(final ListDataEvent e) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalAdded(final ListDataEvent e) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void contentsChanged(final ListDataEvent e) {
                fireTableDataChanged();

            }
        });
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(final int row, final int column) {
        final SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(column);
        final SteerPointReader steerpoint = (SteerPointReader) getListModel().getElementAt(row);
        switch (fpColumn) {
        case LATITUDE:
            return GeographicUtils.getFormattedLatitude(steerpoint.getLatLong());

        case LONGITUDE:
            return GeographicUtils.getFormattedLongitude(steerpoint.getLatLong());

        case ALTITUDE:
            return MessageFormat.format(GeographicFormatter.ALTITUDE_FOOT, new Object[] { steerpoint.getAltitude().longValue(NonSI.FOOT) });

        case VELOCITY:
            return MessageFormat.format(GeographicFormatter.VELOCITY_KNOT, new Object[] { steerpoint.getVelocity().getValue() });

        default:
            break;
        }
        throw new IllegalArgumentException();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return COLUMN_NAME.length;
    }

    /**
     *
     * @param columnIndex
     * @return
     */
    @Override
    public String getColumnName(final int columnIndex) {
        return SteerPointsTableColumn.getName(columnIndex);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        final SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(columnIndex);
        switch (fpColumn) {
        case LATITUDE:
            return String.class;

        case LONGITUDE:
            return String.class;

        case ALTITUDE:
            return String.class;

        case VELOCITY:
            return Velocity.class;

        default:
            break;
        }
        throw new IllegalArgumentException();
    }
}
