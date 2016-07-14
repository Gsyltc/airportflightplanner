/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationListModel extends AbstractListModel<FligthPlanReader> implements FlightPlanVisualizationListModelListener {

    /**
     *
     */
    private static final long            serialVersionUID = -6702145855038118674L;
    /** */
    private final List<FligthPlanReader> list             = new ArrayList<FligthPlanReader>();
    /** */
    private static final int             FIRST_ROW        = 0;

    /**
     *
     * @param newList
     *            List of flight plan.
     */
    public final void setList(final List<FligthPlanReader> newList) {
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
    public final List<FligthPlanReader> getList() {
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
    public final FligthPlanReader getElementAt(final int index) {
        final FligthPlanReader elementAt = list.get(index);
        return elementAt;
    }

    /**
     *
     * @param flightPlan
     *            a flight plan.
     * @return return the index of the flight plan.
     */
    public final int indexOf(final FligthPlanReader flightPlan) {
        return list.indexOf(flightPlan);
    }

    /**
     *
     * @param flightPlan
     *            a flight plan.
     */
    public final void add(final FligthPlanReader flightPlan) {
        list.add(flightPlan);
        fireContentsChanged(this, FIRST_ROW, list.size() - 1);
    }

    /**
     *
     * @param flightPlan
     */
    @Override
    public final void addFlightPlan(final FligthPlanReader flightPlan) {
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
    public final void removeFlightPlan(final FligthPlanReader flightPlan) {
        if (null != flightPlan && list.contains(flightPlan)) {
            list.remove(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }
}
