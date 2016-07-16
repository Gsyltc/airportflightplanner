/*
 * @(#)FlightPlanVisualizationPresenter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import javax.swing.ListModel;

import com.airportflightplanner.common.models.FlighPlanCollectionModel;
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
    private static final long                             serialVersionUID = 2003878398284031619L;
    /** */
    private transient FlightPlanVisualizationTableAdapter tableAdapter;

    /**
     *
     * @param bean
     *            the bean for presenter.
     */
    public FlightPlanVisualizationPresenter(final FlighPlanCollectionModel bean) {
        super(bean);
    }

    /**
     * Get the table adapter for the flight plan visualization panel.
     *
     * @return the Table adapter.
     */
    public FlightPlanVisualizationTableAdapter getTableAdapter() {
        if (null == tableAdapter) {
            tableAdapter = new FlightPlanVisualizationTableAdapter(getBean().getListModel());
        }
        return tableAdapter;
    }

    /**
     * Get the list model.
     *
     * @return the ListModel.
     */
    public FlightPlanVisualizationListModel getListModel() {
        if (null == tableAdapter) {
            getTableAdapter();
        }
        FlightPlanVisualizationListModel result = null;
        final ListModel<?> model = tableAdapter.getListModel();
        if (model instanceof FlightPlanVisualizationListModel) {
            result = (FlightPlanVisualizationListModel) model;
        }
        return result;
    }
}
