/* @(#)FlghtPlanListModel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.presenter.steerpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.AbstractListModel;

import com.airportflightplanner.common.api.steerpoints.bean.SteerPointReader;
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
     * @param newList
     *            the list to update.
     */
    public final void setList(final List<SteerPointReader> newList) {
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
     *            elements.
     */
    protected final void fireIntervalAdded(final int elementCOunt) {
        final int index0 = list.size() - elementCOunt;
        fireIntervalAdded(this, Math.max(FIRST_ROW, index0), Math.max(FIRST_ROW, list.size() - 1));
    }

    /**
     *
     * @return list of steerpoints.
     */
    public final List<SteerPointReader> getList() {
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
    public final SteerPointReader getElementAt(final int index) {
        return list.get(index);
    }

    /**
     *
     * @param SteerPointReader
     *            a steerpoint.
     * @return Index of the steerpoint.
     */
    public final int indexOf(final SteerPointReader SteerPointReader) {
        return list.indexOf(SteerPointReader);
    }

    /**
     *
     * @param SteerPointReader
     *            a Steerpoint.
     */
    public final void add(final SteerPointReader SteerPointReader) {
        list.add(SteerPointReader);
        fireContentsChanged(this, FIRST_ROW, list.size() - 1);
    }

    /**
     *
     * @param steerPoint
     */
    @Override
    public final void addSteerPoint(final SteerPointReader steerPoint) {
        if (null != steerPoint && !list.contains(steerPoint)) {
            list.add(steerPoint);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }

    /**
     *
     * @param steerPoint
     */
    @Override
    public final void removeSteerPoint(final SteerPointReader steerPoint) {
        if (null != steerPoint && list.contains(steerPoint)) {
            list.remove(steerPoint);
            fireContentsChanged(this, FIRST_ROW, list.size() - 1);
        }
    }
}
