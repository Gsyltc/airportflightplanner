/*
 * @(#)FlightPlanVisualizationTableAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 11 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.adapter.flightplan;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionReader;
import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.common.collect.LinkedListModel;

import fr.gsyltc.framework.utils.internationalizer.Internationalizer;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationTableAdapter extends AbstractTableAdapter<FlightPlanCollectionReader> {
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(FlightPlanVisualizationTableAdapter.class);
    /**
     *
     */
    private static final long serialVersionUID = -1614722326210452309L;
    /** */
    private static final FlightPlanVisualisationTableColumn[] COLUMN_NAME = //
            new FlightPlanVisualisationTableColumn[] { //
                    FlightPlanVisualisationTableColumn.START_AIRPORT, //
                    FlightPlanVisualisationTableColumn.DEPARTURE_TIME, //
                    FlightPlanVisualisationTableColumn.ARRIVAL_TIME, //
                    FlightPlanVisualisationTableColumn.AIRCRAFT_TYPE, //
                    FlightPlanVisualisationTableColumn.COMPANY, //
                    FlightPlanVisualisationTableColumn.DEST_AIRPORT, //
                    FlightPlanVisualisationTableColumn.DURATION };

    /**
     *
     * @param flightPlansCollReader
     */
    public FlightPlanVisualizationTableAdapter(final FlightPlanCollectionReader flightPlansCollReader) {
        super();
        final LinkedListModel<FlightPlanReader> listModel = flightPlansCollReader.getFlightPlanListModel();
        setListModel(flightPlansCollReader.getFlightPlanListModel());
        listModel.addListDataListener(new ListDataListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalRemoved(final ListDataEvent event) {
                // Nothing to do
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void intervalAdded(final ListDataEvent event) {
                // Nothing to do
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
    public Object getValueAt(final int row, final int column) {
        Object result = null;
        final FlightPlanVisualisationTableColumn fpColumn = FlightPlanVisualisationTableColumn.valueOf(column);
        final FlightPlanReader flightPlan = (FlightPlanReader) getListModel().getElementAt(row);
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
            result = flightPlan.getAircraftLivery().split("_")[0];
            break;
        
        case COMPANY:
            result = Internationalizer.getI18String(flightPlan.getAircraftCie());
            break;
        
        case DURATION:
            if (null != flightPlan.getDuration()) {
                result = flightPlan.getDuration().toString(TimeUtils.FP_PERIOD_DISPLAYER);
            }
            break;
        
        default:
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error(fpColumn + " not exist");
            }
            break;
        }
        return result;
    }
}
