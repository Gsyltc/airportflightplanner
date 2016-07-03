/* @(#)FlightPlanVisaulizationPresenter.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.steerpoints;

import javax.swing.ListModel;

import com.airportflightplanner.common.model.SteerPointsCollectionModel;
import com.airportflightplanner.flightplanvisualization.adapter.steerpoints.SteerPointsTableAdapter;
import com.jgoodies.binding.PresentationModel;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsPresenter extends PresentationModel<SteerPointsCollectionModel> {
    /**
     *
     */
    private static final long      serialVersionUID = 2003878398284031619L;
    /** */
    private SteerPointsTableAdapter tableAdapter;

    /**
     *
     * @param bean
     */
    public SteerPointsPresenter(final SteerPointsCollectionModel bean) {
        setBean(bean);
    }

    /**
     * Get the table adapter for the flight plan visualization panel
     * @return
     */
    public SteerPointsTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            tableAdapter = new SteerPointsTableAdapter(getBean().getListModel());
        }
        return tableAdapter;
    }

    /**
     * Get the list model
     * @return
     */
    public SteerPointsListModel getListModel() {
        if (null == tableAdapter){
            getTableAdapter();
        }
        ListModel<?> model = tableAdapter.getListModel();
        if (model instanceof SteerPointsListModel) {
            return (SteerPointsListModel) model;
        }
        return null;
    }
}
