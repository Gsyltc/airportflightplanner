/*
 * @(#)SteerPointsPresenter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.presenter.steerpoints;

import com.airportflightplanner.flightplanvisualization.adapter.steerpoints.SteerPointsTableAdapter;
import com.airportflightplanner.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.LinkedListModel;

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
     * @param steerpointsModel
     */
    public SteerPointsPresenter(final Model steerpointsModel) { // NOPMD
                                                                // by
                                                                // sylva
                                                                // on
        // 31/07/16 15:42
        super((SteerPointsCollectionModel) steerpointsModel);
    }
    
    /**
     * Get the table adapter for the flight plan visualization panel.
     *
     * @return
     */
    public SteerPointsTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            final LinkedListModel<SteerPointReader> listModel = getBean().getSteerPointsListModel();
            setTableAdapter(new SteerPointsTableAdapter(listModel));
        }
        return tableAdapter;
    }

    /**
     * @param nTableAdapter
     *            the tableAdapter to set
     */
    private void setTableAdapter(final SteerPointsTableAdapter nTableAdapter) {
        tableAdapter = nTableAdapter;
    }
    
}
