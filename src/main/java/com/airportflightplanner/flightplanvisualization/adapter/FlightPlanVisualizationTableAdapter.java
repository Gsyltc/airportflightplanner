/* @(#)FlightPlanTableModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.adapter;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplancollection.FlightPlanCollectionReader;
import com.airportflightplanner.flightplanprocessor.TimeProcessor;
import com.airportflightplanner.flightplanvisualization.presenter.FlightPlanVisualizationListModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationTableAdapter extends AbstractTableAdapter<FlightPlanCollectionReader> {
    /**
    *
    */
    private static final long                                 serialVersionUID         = -1614722326210452309L;
    /** */
    private final static FlightPlanVisualisationTableColumn[] COLUMN_NAME              = new FlightPlanVisualisationTableColumn[] {                 //
                                                                                               FlightPlanVisualisationTableColumn.START_AIRPORT,    //
                                                                                               FlightPlanVisualisationTableColumn.DEPARTURE_TIME,   //
                                                                                               FlightPlanVisualisationTableColumn.ARRIVAL_TIME,     //
                                                                                               FlightPlanVisualisationTableColumn.AIRCRAFT_TYPE,    //
                                                                                               FlightPlanVisualisationTableColumn.COMPANY,          //
                                                                                               FlightPlanVisualisationTableColumn.DEST_AIRPORT,     //
                                                                                               FlightPlanVisualisationTableColumn.DURATION };

    /**
     *
     * @param listModel
     */
    public FlightPlanVisualizationTableAdapter(final FlightPlanVisualizationListModel listModel) {
        setListModel(listModel);
//        periodFormatterBuilder.appendHours().appendSuffix(" h ", " h ").//
//                printZeroRarelyLast().appendMinutes().appendSuffix(" m", " m").toFormatter();
//        dateTimeFormatterBuilder.appendHourOfDay(2).appendLiteral(" :").appendMinuteOfHour(2).toFormatter();

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
        FlightPlanVisualisationTableColumn fpColumn = FlightPlanVisualisationTableColumn.valueOf(column);
        FlightPlanReader flightPlan = (FlightPlanReader) getListModel().getElementAt(row);
        switch (fpColumn) {
        case START_AIRPORT:
            return flightPlan.getDepartureAirport();

        case DEST_AIRPORT:
            return flightPlan.getArrivalAirport();

        case DEPARTURE_TIME:
            if (null != flightPlan.getStartTime()) {
                return flightPlan.getStartTime().toString(TimeProcessor.TIME_DISPLAYER);
            }
            return null;

        case ARRIVAL_TIME:
            if (null != flightPlan.getEndTime()) {
                return flightPlan.getEndTime().toString(TimeProcessor.TIME_DISPLAYER);
            }
            return null;

        case AIRCRAFT_TYPE:
            return flightPlan.getAircraftType();

        case COMPANY:
            return flightPlan.getAircraftCie();

        case DURATION:
            if (null != flightPlan.getDuration()) {
                return flightPlan.getDuration().toString(TimeProcessor.FLIGHTPLAN_PERIOD_DISPLAYER);
            }
            return "";

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
        return FlightPlanVisualisationTableColumn.getName(columnIndex);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        FlightPlanVisualisationTableColumn fpColumn = FlightPlanVisualisationTableColumn.valueOf(columnIndex);
        switch (fpColumn) {
        case START_AIRPORT:
            return String.class;

        case DEST_AIRPORT:
            return String.class;

        case DEPARTURE_TIME:
            return String.class;

        case ARRIVAL_TIME:
            return String.class;

        case AIRCRAFT_TYPE:
            return String.class;

        case COMPANY:
            return String.class;

        case DURATION:
            return String.class;

        default:
            break;
        }
        throw new IllegalArgumentException();
    }
}
