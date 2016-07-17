/* @(#)FlightPlanVisaulizationPresenter.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.steerpoints;

import com.airportflightplanner.common.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.flightplanvisualization.adapter.steerpoints.SteerPointsTableAdapter;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsPresenter extends PresentationModel<SteerPointsCollectionModel> {
    /**
     *
     */
    private static final long       serialVersionUID = 2003878398284031619L;
    /** */
    private SteerPointsTableAdapter tableAdapter;

    /**
     *
     * @param bean
     */
    public SteerPointsPresenter(final Model bean) {
        super((SteerPointsCollectionModel) bean);
    }

    /**
     * Get the table adapter for the flight plan visualization panel
     *
     * @return
     */
    public SteerPointsTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            setTableAdapter(new SteerPointsTableAdapter(getBean().getSteerPointsListModel()));
        }
        return tableAdapter;
    }

    /**
     * Get the list model
     *
     * @return
     */
    public SteerPointsListModel getListModel() {
        if (null == tableAdapter) {
            getTableAdapter();
        }
        return (SteerPointsListModel) getTableAdapter().getListModel();
    }

    /**
     * @param tableAdapter
     *            the tableAdapter to set
     */
    private void setTableAdapter(final SteerPointsTableAdapter tableAdapter) {
        this.tableAdapter = tableAdapter;
    }
}
