/*
 * @(#)SteerPointsPresenter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 28 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
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
    private static final long serialVersionUID = 2003878398284031619L;
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
     * Get the list model.
     *
     * @return
     */
    public SteerPointsListModel getListModel() {
        if (null == this.tableAdapter) {
            getTableAdapter();
        }
        return (SteerPointsListModel) getTableAdapter().getListModel();
    }
    
    /**
     * Get the table adapter for the flight plan visualization panel.
     *
     * @return
     */
    public SteerPointsTableAdapter getTableAdapter() {
        if (null == this.tableAdapter) {
            setTableAdapter(new SteerPointsTableAdapter(getBean().getSteerPointsListModel()));
        }
        return this.tableAdapter;
    }
    
    /**
     * @param nTableAdapter
     *            the tableAdapter to set
     */
    private void setTableAdapter(final SteerPointsTableAdapter nTableAdapter) {
        this.tableAdapter = nTableAdapter;
    }
}
