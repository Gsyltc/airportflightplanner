/* @(#)DaysSlectionPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */

package com.airportflightplanner.flightplancreation.panels;

import javax.swing.JCheckBox;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationStartDaysPanel extends AbstractCommonPanel {
    /**
     *
     */
    private static final long serialVersionUID     = 3382772953500242522L;
    /** */
    private static final int  DAYS_PRESENTER       = AbstractCommonPanel.FIRST_PRESENTER;

    /**
     * @param presenter
     *
     */
    public CreationStartDaysPanel(final PresentationModel<DaySelectionReader> presenter) {
        super(presenter);
    }

    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, //
                FormSpecs.RELATED_GAP_COLSPEC, //
                FormSpecs.DEFAULT_COLSPEC, }, //
                new RowSpec[] { //
                        FormSpecs.PREF_ROWSPEC, })); //

        final PresentationModel<DaySelectionReader> dayPresenter = //
                (PresentationModel<DaySelectionReader>) getPresenter(DAYS_PRESENTER);

        add(createMondayCb(dayPresenter), "1, 1");
        add(createTuesdayCb(dayPresenter), "3, 1");
        add(createWednesdayCb(dayPresenter), "5, 1");
        add(createThrusdayCb(dayPresenter), "7, 1");
        add(createFridayCb(dayPresenter), "9, 1");
        add(createSaturdayCb(dayPresenter), "11, 1");
        add(createSundayCb(dayPresenter), "13, 1");
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createMondayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.MONDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.MONDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createTuesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.TUESDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.TUESDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createWednesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.WEDNESDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.WEDNESDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createThrusdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.THRUSDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.THRUSDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createFridayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.FRIDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.FRIDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSaturdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SATURDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.SATURDAY.toString());
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSundayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getBufferedModel(DaySelectionProperties.SUNDAY);
        return BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED_TOPIC, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                final PresentationModel<DaySelectionReader> dayPresenter = //
                        (PresentationModel<DaySelectionReader>) getPresenter(DAYS_PRESENTER);
                dayPresenter.triggerFlush();
            }
        });
    }

}
