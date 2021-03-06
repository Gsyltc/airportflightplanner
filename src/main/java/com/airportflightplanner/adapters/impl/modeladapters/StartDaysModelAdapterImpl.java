/*
 * @(#)StartDaysModelAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl.modeladapters;

import java.util.Set;

import com.airportflightplanner.adapters.api.modeladapters.StartDaysModelAdapter;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.models.daysselection.DaysSelectionModel;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;

import fr.gsyltc.framework.adapters.AbstractReceiverModelAdapterImpl;
import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;

/**
 * @author Goubaud Sylvain
 */
public class StartDaysModelAdapterImpl extends AbstractReceiverModelAdapterImpl<DaysSelectionModel> implements
        StartDaysModelAdapter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = 8742702470474381772L;

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSlots() {
        final Slot slot = attachSlot(TopicName.FP_TABLE_SELECTED_TOPIC);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = -1849859877568448215L;

            /**
             *
             * {@inheritDoc}.
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
     *
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        createSlots();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateStartsDays(final Set<StartDays> startDays) {
        if (startDays.isEmpty()) {
            getModel().setMonday(true);
            getModel().setTuesday(true);
            getModel().setWednesday(true);
            getModel().setThrusday(true);
            getModel().setFriday(true);
            getModel().setSaturday(true);
            getModel().setSunday(true);
        } else {
            getModel().setMonday(startDays.contains(StartDays.MONDAY));
            getModel().setTuesday(startDays.contains(StartDays.TUESDAY));
            getModel().setWednesday(startDays.contains(StartDays.WEDNESDAY));
            getModel().setThrusday(startDays.contains(StartDays.THRUSDAY));
            getModel().setFriday(startDays.contains(StartDays.FRIDAY));
            getModel().setSaturday(startDays.contains(StartDays.SATURDAY));
            getModel().setSunday(startDays.contains(StartDays.SUNDAY));
        }
    }
}
