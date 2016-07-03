/* @(#)FlightPlanTableModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter.steerpoints;

import javax.measure.quantity.Velocity;
import javax.measure.unit.NonSI;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.airportflightplanner.common.api.flightplancollection.flightplan.FlightPlanCollectionReader;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
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
    private final static SteerPointsTableColumn[] COLUMN_NAME      = new SteerPointsTableColumn[] {   //
            SteerPointsTableColumn.LATITUDE,                                                          //
            SteerPointsTableColumn.LONGITUDE,                                                         //
            SteerPointsTableColumn.VELOCITY,                                                          //
            SteerPointsTableColumn.ALTITUDE };                                                        //

    /**
     *
     * @param listModel
     */
    public SteerPointsTableAdapter(final SteerPointsListModel listModel) {
        setListModel(listModel);

        listModel.addListDataListener(new ListDataListener() {
            /**
             *
             * @param e
             */
            @Override
            public void intervalRemoved(final ListDataEvent e) {
                //
            }

            /**
             *
             * @param e
             */
            @Override
            public void intervalAdded(final ListDataEvent e) {
                //
            }

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
        SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(column);
        SteerPointReader steerpoint = (SteerPointReader) getListModel().getElementAt(row);
        switch (fpColumn) {
        case LATITUDE:
            return steerpoint.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE);

        case LONGITUDE:
            return steerpoint.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE);

        case ALTITUDE:
            return steerpoint.getAltitude().longValue(NonSI.FOOT);

        case VELOCITY:
            return steerpoint.getVelocity();

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
        SteerPointsTableColumn fpColumn = SteerPointsTableColumn.valueOf(columnIndex);
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
