/*
 * @(#)FlightPlanVisualizationListModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 10 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationListModel extends AbstractListModel<FlightPlanReader> implements
        FlightPlanVisualizationListModelListener {
    
    
    /** */
    private static final long serialVersionUID = -6702145855038118674L;
    /** */
    private static final int FIRST_ROW = 0;
    /** list of flight plans. */
    private final List<FlightPlanReader> list = new ArrayList<FlightPlanReader>();

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void addFlightPlan(final FlightPlanReader flightPlan) {
        if (null != flightPlan && !list.contains(flightPlan)) {
            list.add(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     * Clear the model.
     */
    public final void clear() {
        list.clear();
        fireContentsChanged(this, -1, -1);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final FlightPlanReader getElementAt(final int index) {
        return list.get(index);
    }

    /**
     * Return the list of flight plans.
     *
     * @return List of the flight plan
     */
    public final List<FlightPlanReader> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final int getSize() {
        return list.size();
    }

    /**
     * Return the index a of flight plan.
     *
     * @param flightPlan
     *            a flight plan.
     * @return return the index of the flight plan.
     */
    public final int indexOf(final FlightPlanReader flightPlan) {
        return list.indexOf(flightPlan);
    }

    /**
     * Remove a flight plan.
     *
     * @param flightPlan
     *            flight plan to remove.
     */
    @Override
    public final void removeFlightPlan(final FlightPlanReader flightPlan) {
        if (null != flightPlan && list.contains(flightPlan)) {
            list.remove(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     * Set a list of flight plans.
     *
     * @param newList
     *            List of flight plan.
     */
    public final void setList(final List<FlightPlanReader> newList) {
        list.clear();
        list.addAll(newList);
        fireContentsChanged(this, FIRST_ROW, Math.max(newList.size() - 1, 0));
    }

    /**
     * Fire modification.
     *
     * @param elementCOunt
     *            Element count.
     */
    protected final void fireIntervalAdded(final int elementCOunt) {
        final int index0 = list.size() - elementCOunt;
        fireIntervalAdded(this, Math.max(FIRST_ROW, index0), Math.max(FIRST_ROW, list.size() - 1));
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void resetFlightPlans() {
        clear();
    }
}
