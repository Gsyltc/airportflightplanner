/* @(#)DaysSlectionPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import javax.swing.JCheckBox;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
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
public class DaysSelectionPanel extends AbstractCommonPanel {
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
    public DaysSelectionPanel(final PresentationModel<DaysSelectionModel> presenter) {
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

        final PresentationModel<DaysSelectionModel> dayPresenter = //
                (PresentationModel<DaysSelectionModel>) getPresenter(DAYS_PRESENTER);

        add(createMondayCb(dayPresenter), "1, 1");
        add(createTuesdayCb(dayPresenter), "3, 1");
        add(createWednesdayCb(dayPresenter), "5, 1");
        add(createThrusdayCb(dayPresenter), "7, 1");
        add(createFridayCb(dayPresenter), "9, 1");
        add(createSaturdayCb(dayPresenter), "11, 1");
        add(createSundayCb(dayPresenter), "13, 1");

        //        validateDays(dayPresenter.getBean());
    }

    //    /**
    //     * @param daysSelectionModel
    //     *
    //     */
    //    private void validateDays(final DaysSelectionModel daysSelectionModel) {
    //        final PresentationModel<FlightPlanReader> presentationModel = //
    //                (PresentationModel<FlightPlanReader>) getPresenter(CURRENT_FP_PRESENTER);
    //        presentationModel.addPropertyChangeListener(BeanAdapter.PROPERTY_AFTER_BEAN, new PropertyChangeListener() {
    //            /**
    //             *
    //             * {@inheritDoc}
    //             */
    //            @Override
    //            public void propertyChange(final PropertyChangeEvent evt) {
    //                if (evt.getNewValue() instanceof FlightPlanReader) {
    //                    final FlightPlanReader fplReader = (FlightPlanReader) evt.getNewValue();
    //                    final Set<StartDays> startDays = fplReader.getStartDays();
    //                    if (!startDays.isEmpty()) {
    //                        daysSelectionModel.setMonday(startDays.contains(StartDays.MONDAY));
    //                        daysSelectionModel.setTuesday(startDays.contains(StartDays.TUESDAY));
    //                        daysSelectionModel.setWednesday(startDays.contains(StartDays.WEDNESDAY));
    //                        daysSelectionModel.setThrusday(startDays.contains(StartDays.THRUSDAY));
    //                        daysSelectionModel.setFriday(startDays.contains(StartDays.FRIDAY));
    //                        daysSelectionModel.setSaturday(startDays.contains(StartDays.SATURDAY));
    //                        daysSelectionModel.setSunday(startDays.contains(StartDays.SUNDAY));
    //                    } else {
    //                        daysSelectionModel.setMonday(true);
    //                        daysSelectionModel.setTuesday(true);
    //                        daysSelectionModel.setWednesday(true);
    //                        daysSelectionModel.setThrusday(true);
    //                        daysSelectionModel.setFriday(true);
    //                        daysSelectionModel.setSaturday(true);
    //                        daysSelectionModel.setSunday(true);
    //                    }
    //                }
    //            }
    //        });
    //    }

    /**
     *
     * @param dayPresenter
     * @return
     */
    private JCheckBox createMondayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createTuesdayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createWednesdayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createThrusdayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createFridayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createSaturdayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
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
    private JCheckBox createSundayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
        final ValueModel value = dayPresenter.getModel(DaySelectionProperties.SUNDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }
}
