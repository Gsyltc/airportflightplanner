/* @(#)CreationStartDaysPanel.java
 *
 * 2016 Goubaud Sylvain.
 */
package com.airportflightplanner.flightplancreation.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.binding.PresentationModel;
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
    private static final long serialVersionUID         = -3484253740768601903L;

    /**
     * @param newCurrentFlightPlan
     *            flightplan.
     *
     */
    public CreationStartDaysPanel(final PresentationModel<FligthPlanReader> newCurrentFlightPlan) {
        super(newCurrentFlightPlan);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, ///
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, });

        formLayout.setRowGroups(new int[][] { new int[] { 6, 4, 2 } });
        formLayout.setColumnGroups(new int[][] { new int[] { 2, 14, 12, 10, 8, 6, 4 } });
        setLayout(formLayout);

        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.FLIGHT_INFOS_LABEL));

        final JCheckBox chckbxNewCheckBox = new JCheckBox(StartDays.MONDAY.toString());
        add(chckbxNewCheckBox, "2, 2");

        final JCheckBox chckbxNewCheckBox_1 = new JCheckBox(StartDays.TUESDAY.toString());
        add(chckbxNewCheckBox_1, "4, 2");

        final JCheckBox chckbxNewCheckBox_2 = new JCheckBox(StartDays.WEDNESDAY.toString());
        add(chckbxNewCheckBox_2, "6, 2");

        final JCheckBox chckbxNewCheckBox_3 = new JCheckBox(StartDays.THRUSDAY.toString());
        add(chckbxNewCheckBox_3, "8, 2");

        final JCheckBox chckbxNewCheckBox_4 = new JCheckBox(StartDays.FRIDAY.toString());
        add(chckbxNewCheckBox_4, "10, 2");

        final JCheckBox chckbxNewCheckBox_5 = new JCheckBox(StartDays.SATURDAY.toString());
        add(chckbxNewCheckBox_5, "12, 2");

        final JCheckBox chckbxNewCheckBox_6 = new JCheckBox(StartDays.SUNDAY.toString());
        add(chckbxNewCheckBox_6, "14, 2");

        getPresenter(FIRST_PRESENTER).addPropertyChangeListener(new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                // if (evt.getNewValue() instanceof FligthPlanReader) {
                // final String aircraftType = ((FligthPlanReader)
                // evt.getNewValue()).getAircraftType();
                // final FlighInfosModel bean = presenterInfoModel.getBean();
                //
                // final String aircraftClass =
                // AircraftTypeAdapter.getAircraftClass(aircraftType);
                // bean.setAircraftClass(aircraftClass);
                // bean.setAircraftCie(AircraftTypeAdapter.getAircraftCie(aircraftType));
                // bean.setAircraftLivery(aircraftType);
                // }
            }
        });

    }
}
