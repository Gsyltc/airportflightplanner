/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationListModel extends AbstractListModel<FlightPlanReader> implements FlightPlanVisualizationListModelListener {

    /**
     *
     */
    private static final long            serialVersionUID = -6702145855038118674L;
    /** */
    private final List<FlightPlanReader> list             = new ArrayList<FlightPlanReader>();
    /** */
    private static final int             FIRST_ROW        = 0;

    /**
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
     *
     */
    public final void clear() {
        list.clear();
        fireContentsChanged(this, -1, -1);
    }

    /**
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
     * @return List of the flight plan
     */
    public final List<FlightPlanReader> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int getSize() {
        return list.size();
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
     *
     * @param flightPlan
     *            a flight plan.
     * @return return the index of the flight plan.
     */
    public final int indexOf(final FlightPlanReader flightPlan) {
        return list.indexOf(flightPlan);
    }

    /**
     *
     * @param flightPlan
     *            a flight plan.
     */
    public final void add(final FlightPlanReader flightPlan) {
        list.add(flightPlan);
        fireContentsChanged(this, FIRST_ROW, list.size() - 1);
    }

    /**
     *
     * @param flightPlan
     */
    @Override
    public final void addFlightPlan(final FlightPlanReader flightPlan) {
        if (null != flightPlan && !list.contains(flightPlan)) {
            list.add(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     *
     * @param flightPlan
     */
    @Override
    public final void removeFlightPlan(final FlightPlanReader flightPlan) {
        if (null != flightPlan && list.contains(flightPlan)) {
            list.remove(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }
}
