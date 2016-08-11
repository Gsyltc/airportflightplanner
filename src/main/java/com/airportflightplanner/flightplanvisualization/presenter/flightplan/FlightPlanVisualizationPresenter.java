/*
 * @(#)FlightPlanVisualizationPresenter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 11 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import com.airportflightplanner.flightplanvisualization.adapter.flightplan.FlightPlanVisualizationTableAdapter;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationPresenter extends PresentationModel<FlightPlanCollectionReader> {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 2003878398284031619L;
    /** */
    private FlightPlanVisualizationTableAdapter tableAdapter;

    /**
     *
     * @param bean
     *            the bean for presenter.
     */
    public FlightPlanVisualizationPresenter(final Model bean) { // NOPMD by
                                                                // sylva on
                                                                // 31/07/16
                                                                // 15:41
        super((FlightPlanCollectionReader) bean);
    }

    /**
     * Get the table adapter for the flight plan visualization panel.
     *
     * @return the Table adapter.
     */
    public FlightPlanVisualizationTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            final FlightPlanCollectionReader listModel = getBean();
            setTableAdapter(new FlightPlanVisualizationTableAdapter(listModel));
        }
        return tableAdapter;
    }

    /**
     * Set the table adapter.
     *
     * @param newTableAdapter
     *            the tableAdapter to set
     */
    private void setTableAdapter(final FlightPlanVisualizationTableAdapter newTableAdapter) {
        tableAdapter = newTableAdapter;
    }
}
