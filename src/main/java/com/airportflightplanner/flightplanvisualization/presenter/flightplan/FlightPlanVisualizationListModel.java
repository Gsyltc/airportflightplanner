/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */

package com.airportflightplanner.flightplanvisualization.presenter.flightplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.flightplanvisualization.api.FlightPlanVisualizationListModelListener;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanVisualizationListModel extends AbstractListModel<FlightPlanReader> implements
        FlightPlanVisualizationListModelListener {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -6702145855038118674L;
    /** */
    private static final int FIRST_ROW = 0;
    /** */
    private final List<FlightPlanReader> list = new ArrayList<FlightPlanReader>();
    
    /**
     *
     * @param flightPlan
     */
    @Override
    public final void addFlightPlan(final FlightPlanReader flightPlan) {
        if ((null != flightPlan) && !this.list.contains(flightPlan)) {
            this.list.add(flightPlan);
            fireContentsChanged(this, FIRST_ROW, this.list.size() - 1);
        }
    }
    
    /**
     *
     */
    public final void clear() {
        this.list.clear();
        fireContentsChanged(this, -1, -1);
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final FlightPlanReader getElementAt(final int index) {
        return this.list.get(index);
    }
    
    /**
     *
     * @return List of the flight plan
     */
    public final List<FlightPlanReader> getList() {
        return Collections.unmodifiableList(this.list);
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int getSize() {
        return this.list.size();
    }
    
    /**
     *
     * @param flightPlan
     *            a flight plan.
     * @return return the index of the flight plan.
     */
    public final int indexOf(final FlightPlanReader flightPlan) {
        return this.list.indexOf(flightPlan);
    }
    
    /**
     *
     * @param flightPlan
     */
    @Override
    public final void removeFlightPlan(final FlightPlanReader flightPlan) {
        if ((null != flightPlan) && this.list.contains(flightPlan)) {
            this.list.remove(flightPlan);
            fireContentsChanged(this, FIRST_ROW, this.list.size() - 1);
        }
    }
    
    /**
     *
     * @param newList
     *            List of flight plan.
     */
    public final void setList(final List<FlightPlanReader> newList) {
        this.list.clear();
        this.list.addAll(newList);
        fireContentsChanged(this, FIRST_ROW, Math.max(newList.size() - 1, 0));
    }
    
    /**
     *
     * @param elementCOunt
     *            Element count.
     */
    protected final void fireIntervalAdded(final int elementCOunt) {
        final int index0 = this.list.size() - elementCOunt;
        fireIntervalAdded(this, Math.max(FIRST_ROW, index0), Math.max(FIRST_ROW, this.list.size() - 1));
    }
}
