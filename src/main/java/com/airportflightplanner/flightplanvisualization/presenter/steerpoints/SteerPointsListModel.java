/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.steerpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointsListModel extends AbstractListModel<SteerPointReader> implements SteerPointsListModelListener {

    /**
     *
     */
    private static final long            serialVersionUID = -6702145855038118674L;
    /** */
    private final List<SteerPointReader> list             = new ArrayList<SteerPointReader>();
    /** */
    private static final int             FIRST_ROW        = 0;

    /**
     *
     * @param list
     */
    public void setList(final List<SteerPointReader> list) {
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
    public List<SteerPointReader> getList() {
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
    public SteerPointReader getElementAt(final int index) {
        SteerPointReader elementAt = list.get(index);
        return elementAt;
    }

    /**
     *
     * @param element
     * @return
     */
    public int indexOf(final SteerPointReader element) {
        return list.indexOf(element);
    }

    public void add(final SteerPointReader value) {
        list.add(value);
        fireContentsChanged(this, FIRST_ROW, list.size() - 1);
    }

    /**
     *
     * @param steerPoint
     */
    @Override
    public void addSteerPoint(final SteerPointReader steerPoint) {
        if ((null != steerPoint) && !list.contains(steerPoint)) {
            list.add(steerPoint);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     *
     * @param steerPoint
     */
    @Override
    public void removeSteerPoint(final SteerPointReader steerPoint) {
        if ((null != steerPoint) && list.contains(steerPoint)) {
            list.remove(steerPoint);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }
}
