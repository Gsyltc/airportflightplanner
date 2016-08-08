/* @(#)FlightPlanTableModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import java.text.MessageFormat;

import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.airportflightplanner.common.api.flightplan.collection.FlightPlanCollectionReader;
import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
import com.airportflightplanner.common.processors.GeographicProcessor;
import com.airportflightplanner.common.types.GeographicFormatter;
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
    public SteerPointsTableAdapter(final ListModel<SteerPointReader> listModel) {
        super(listModel);
        listModel.addListDataListener(new ListDataListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalRemoved(final ListDataEvent event) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalAdded(final ListDataEvent event) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void contentsChanged(final ListDataEvent event) {
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
        Object result = null;
        final SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(column);
        final SteerPointReader steerpoint = (SteerPointReader) getListModel().getElementAt(row);
        switch (fpColumn) {
        case LATITUDE:
            result = GeographicProcessor.getFormattedLatitude(steerpoint.getLatLong());
            break;

        case LONGITUDE:
            result = GeographicProcessor.getFormattedLongitude(steerpoint.getLatLong());
            break;

        case ALTITUDE:
            result = MessageFormat.format(GeographicFormatter.ALTITUDE_FOOT, new Object[] { steerpoint.getAltitude().longValue(NonSI.FOOT) });
            break;

        case VELOCITY:
            result = MessageFormat.format(GeographicFormatter.VELOCITY_KNOT, new Object[] { steerpoint.getVelocity().getValue() });
            break;

        default:
            break;
        }
        if (null == result) {
            throw new IllegalArgumentException();
        }
        return result;
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
        Class<?> result = null;
        final SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(columnIndex);
        switch (fpColumn) {
        case LATITUDE:
            result = String.class;
            break;

        case LONGITUDE:
            result = String.class;
            break;

        case ALTITUDE:
            result = String.class;
            break;

        case VELOCITY:
            result = Velocity.class;
            break;

        default:
            break;
        }
        if (null == result) {
            throw new IllegalArgumentException();
        }

        return result;
    }
}
