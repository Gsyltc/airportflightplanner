/* @(#)FlightPlanVisaulizationPresenter.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import javax.swing.ListModel;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplanvisualization.adapter.flightplan.FlightPlanVisualizationTableAdapter;
import com.jgoodies.binding.PresentationModel;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationPresenter extends PresentationModel<FlighPlanCollectionModel> {
    /**
     *
     */
    private static final long      serialVersionUID = 2003878398284031619L;
    /** */
    private FlightPlanVisualizationTableAdapter tableAdapter;

    /**
     *
     * @param bean
     */
    public FlightPlanVisualizationPresenter(final FlighPlanCollectionModel bean) {
        setBean(bean);
    }

    /**
     * Get the table adapter for the flight plan visualization panel
     * @return
     */
    public FlightPlanVisualizationTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            tableAdapter = new FlightPlanVisualizationTableAdapter(getBean().getListModel());
        }
        return tableAdapter;
    }

    /**
     * Get the list model
     * @return
     */
    public FlightPlanVisualizationListModel getListModel() {
        if (null == tableAdapter){
            getTableAdapter();
        }
        ListModel<?> model = tableAdapter.getListModel();
        if (model instanceof FlightPlanVisualizationListModel) {
            return (FlightPlanVisualizationListModel) model;
        }
        return null;
    }
}
