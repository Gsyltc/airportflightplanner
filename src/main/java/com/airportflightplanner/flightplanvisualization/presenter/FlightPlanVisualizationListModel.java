/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter;

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
     * @param list
     */
    public void setList(final List<FlightPlanReader> list) {
        this.list.clear();
        this.list.addAll(list);
        fireContentsChanged(this, FIRST_ROW, Math.max(list.size() - 1, 0));
    }

    /**
     *
     */
    public void clear() {
        this.list.clear();
        fireContentsChanged(this, -1, -1);
    }

    /**
     *
     * @param elementCOunt
     */
    protected void fireIntervalAdded(final int elementCOunt) {
        int index0 = list.size() - elementCOunt;
        fireIntervalAdded(this, Math.max(FIRST_ROW, index0), Math.max(FIRST_ROW, list.size() - 1));
    }

    /**
     *
     * @return
     */
    public List<FlightPlanReader> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return list.size();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public FlightPlanReader getElementAt(final int index) {
        FlightPlanReader elementAt = list.get(index);
        return elementAt;
    }

    /**
     *
     * @param element
     * @return
     */
    public int indexOf(final FlightPlanReader element) {
        return list.indexOf(element);
    }

    public void add(final FlightPlanReader value) {
        list.add(value);
        fireContentsChanged(this, FIRST_ROW, list.size() - 1);
    }

    /**
     *
     * @param flightPlan
     */
    @Override
    public void addFlightPlan(final FlightPlanReader flightPlan) {
        if ((null != flightPlan) && !list.contains(flightPlan)) {
            list.add(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     *
     * @param flightPlan
     */
    @Override
    public void removeFlightPlan(final FlightPlanReader flightPlan) {
        if ((null != flightPlan) && list.contains(flightPlan)) {
            list.remove(flightPlan);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }
}
