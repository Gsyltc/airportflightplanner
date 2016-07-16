/*
 * @(#)FlightPlanVisualizationTableAdapter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.adapter.flightplan;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FlightPlanCollectionReader;
import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationListModel;
import com.jgoodies.binding.adapter.AbstractTableAdapter;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationTableAdapter extends AbstractTableAdapter<FlightPlanCollectionReader> {
    /**
     *
     */
    private static final long                                 serialVersionUID = -1614722326210452309L;
    /** */
    private static final FlightPlanVisualisationTableColumn[] COLUMN_NAME      =                       //
            new FlightPlanVisualisationTableColumn[] {                                                 //
                    FlightPlanVisualisationTableColumn.START_AIRPORT,                                  //
                    FlightPlanVisualisationTableColumn.DEPARTURE_TIME,                                 //
                    FlightPlanVisualisationTableColumn.ARRIVAL_TIME,                                   //
                    FlightPlanVisualisationTableColumn.AIRCRAFT_TYPE,                                  //
                    FlightPlanVisualisationTableColumn.COMPANY,                                        //
                    FlightPlanVisualisationTableColumn.DEST_AIRPORT,                                   //
                    FlightPlanVisualisationTableColumn.DURATION };

    /**
     *
     * @param listModel
     */
    public FlightPlanVisualizationTableAdapter(final FlightPlanVisualizationListModel listModel) {
        super(listModel);

        listModel.addListDataListener(new ListDataListener() {
            /**
             *
             * @param e
             */
            @Override
            public void intervalRemoved(final ListDataEvent event) {
                //
            }

            /**
             *
             * @param e
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
        final FlightPlanVisualisationTableColumn fpColumn = FlightPlanVisualisationTableColumn.valueOf(column);
        final FligthPlanReader flightPlan = (FligthPlanReader) getListModel().getElementAt(row);
        switch (fpColumn) {
        case START_AIRPORT:
            result = flightPlan.getDepartureAirport();
            break;

        case DEST_AIRPORT:
            result = flightPlan.getArrivalAirport();
            break;

        case DEPARTURE_TIME:
            if (null != flightPlan.getStartTime()) {
                result = flightPlan.getStartTime().toString(TimeUtils.TIME_DISPLAYER);
            }
            break;

        case ARRIVAL_TIME:
            if (null != flightPlan.getEndTime()) {
                result = flightPlan.getEndTime().toString(TimeUtils.TIME_DISPLAYER);
            }
            break;

        case AIRCRAFT_TYPE:
            result = flightPlan.getAircraftType().split("_")[0];
            break;

        case COMPANY:
            result = flightPlan.getAircraftCie();
            break;

        case DURATION:
            if (null != flightPlan.getDuration()) {
                result = flightPlan.getDuration().toString(TimeUtils.FP_PERIOD_DISPLAYER);
            }
            break;

        default:
            break;
        }
        //        if (null == result) {
        //            throw new IllegalArgumentException();
        //        }
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
        return FlightPlanVisualisationTableColumn.getName(columnIndex);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(final int columnIndex) {
        Class<?> result = null;
        final FlightPlanVisualisationTableColumn fpColumn = FlightPlanVisualisationTableColumn.valueOf(columnIndex);
        switch (fpColumn) {
        case START_AIRPORT:
            result = String.class;
            break;

        case DEST_AIRPORT:
            result = String.class;
            break;

        case DEPARTURE_TIME:
            result = String.class;
            break;

        case ARRIVAL_TIME:
            result = String.class;
            break;

        case AIRCRAFT_TYPE:
            result = String.class;
            break;

        case COMPANY:
            result = String.class;
            break;

        case DURATION:
            result = String.class;
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
