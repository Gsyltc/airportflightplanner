/*
 * @(#)StartDaysAdapterImpl.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.adapter.impl;

import java.util.Set;

import com.airportflightplanner.common.api.adapter.StartDaysAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.types.StartDays;

/**
 * @author Goubaud Sylvain
 *
 */
public class StartDaysAdapterImpl implements StartDaysAdapter {
    /**
     *
     */
    private static final long  serialVersionUID = 8742702470474381772L;
    /** */
    private String             adapterName;
    /** */
    private DaysSelectionModel model;

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStartsDays(final Set<StartDays> startDays) {
        if (startDays.isEmpty()) {
            model.setMonday(true);
            model.setTuesday(true);
            model.setWednesday(true);
            model.setThrusday(true);
            model.setFriday(true);
            model.setSaturday(true);
            model.setSunday(true);
        } else {
            model.setMonday(startDays.contains(StartDays.MONDAY));
            model.setTuesday(startDays.contains(StartDays.TUESDAY));
            model.setWednesday(startDays.contains(StartDays.WEDNESDAY));
            model.setThrusday(startDays.contains(StartDays.THRUSDAY));
            model.setFriday(startDays.contains(StartDays.FRIDAY));
            model.setSaturday(startDays.contains(StartDays.SATURDAY));
            model.setSunday(startDays.contains(StartDays.SUNDAY));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdapterName() {
        return adapterName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdapterName(final String adapterName) {
        this.adapterName = adapterName;

    }

    /**
     * @param model
     *            the model to set
     */
    @Override
    public void setModel(final DaysSelectionModel model) {
        this.model = model;
    }

    /**
     *
     * @return
     */
    @Override
    public DaysSelectionModel getModel() {
        return model;
    }

    /**
     *
     */
    public void init() {
        attachSlotAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                if (null != bean) {
                    updateStartsDays(bean.getStartDays());
                }
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        // TODO Auto-generated method stub

    }
}
