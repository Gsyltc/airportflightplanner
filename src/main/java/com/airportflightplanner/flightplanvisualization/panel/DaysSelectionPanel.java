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
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
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
    private static final long serialVersionUID = 3382772953500242522L;
    /** */
    private static final int  DAYS_PRESENTER   = 1;

    DaysSelectionModel        daysModel        = new DaysSelectionModel();

    /**
     * @param presenter
     */
    public DaysSelectionPanel(final FlightPlanVisualizationPresenter presenter) {
        super(presenter, new PresentationModel<DaysSelectionModel>());
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
        dayPresenter.setBean(daysModel);

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
    private JCheckBox createMondayCb(final PresentationModel<DaysSelectionModel> dayPresenter) {
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.MONDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.TUESDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.WEDNESDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.THRUSDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.FRIDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.SATURDAY);
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
        final ValueModel value =  dayPresenter.getModel(DaySelectionProperties.SUNDAY);
        final JCheckBox checkBox = BasicComponentFactory.createCheckBox(value, StartDays.SUNDAY.toString());
        checkBox.setEnabled(false);
        return checkBox;
    }

}
