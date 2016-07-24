/*
 * @(#)DaysSelectionPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 28 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.dayselection.bean.DaySelectionProperties;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.visualelements.AbstractCommonPanel;

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
    private static final int DAYS_PRESENTER = 0;

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

        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.STARTDAYS_TITLE);
        setBorder(panelBorder);

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
}
