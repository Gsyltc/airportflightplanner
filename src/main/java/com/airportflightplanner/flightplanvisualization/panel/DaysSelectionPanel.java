/* @(#)DaysSlectionPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;

import com.airportflightplanner.common.api.adapter.StartDaysAdapter;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.beans.BeanAdapter;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class DaysSelectionPanel extends AbstractCommonPanel {
    /**
     *
     */
    private static final long serialVersionUID     = 3382772953500242522L;
    /** */
    private static final int  DAYS_PRESENTER       = AbstractCommonPanel.FIRST_PRESENTER;
    /** */
    private static final int  CURRENT_FP_PRESENTER = 1;

    /**
     * @param presenter
     * @param currentFpPresenter
     *
     */
    public DaysSelectionPanel(final PresentationModel<DaySelectionReader> presenter, //
            final PresentationModel<FlightPlanReader> currentFpPresenter) {
        super(presenter, currentFpPresenter);
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

        validateDays();
    }

    /**
     *
     */
    private void validateDays() {
        final PresentationModel<FlightPlanReader> presentationModel = //
                (PresentationModel<FlightPlanReader>) getPresenter(CURRENT_FP_PRESENTER);
        presentationModel.addPropertyChangeListener(BeanAdapter.PROPERTY_AFTER_BEAN, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof FlightPlanReader) {
                    FlightPlanReader bean = (FlightPlanReader) evt.getNewValue();
                    StartDaysAdapter adapter = (StartDaysAdapter) getAdapter();
                    adapter.updateStartsDays(bean.getStartDays());
                }
            }
        });
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createMondayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.MONDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.MONDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createTuesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.TUESDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.TUESDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createWednesdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.WEDNESDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.WEDNESDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createThrusdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.THRUSDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.THRUSDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createFridayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.FRIDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.FRIDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSaturdayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.SATURDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.SATURDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createSundayCb(final PresentationModel<DaySelectionReader> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.SUNDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }
}
