/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanCreationPanel extends FormDebugPanel {

    /**
     *
     */
    private static final long              serialVersionUID = 4047549681152943474L;
    /**
     *
     */
    private final FlighPlanCollectionModel flightPlansCollection;

    /**
    *
    */

    /**
     * @param flightPlansCollection
     *
     */
    public FlightPlanCreationPanel(final FlighPlanCollectionModel flightPlansCollection) {
        this.flightPlansCollection = flightPlansCollection;
        buildPanel();
    }

    /**
    *
    */
    private void buildPanel() {
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        JLabel startLabel = new JLabel(FlightPlanCreationPanelMessages.START_LABEL);
        add(startLabel, "2,2,3,1");
        add(createStartTextField(), "2,4,3,1");

        JLabel endLabel = new JLabel(FlightPlanCreationPanelMessages.END_LABEL);
        add(endLabel, "6,2,4,1");
        add(createEndTextField(), "6,4,3,1");

        JLabel timeLabel = new JLabel(FlightPlanCreationPanelMessages.TIME_LABEL);
        add(timeLabel, "10,2,4,1");
        add(createTimeTextField(), "10,4,3,1");

    }

    /**
     *
     * @return
     */
    private JFormattedTextField createTimeTextField() {
        JFormattedTextField textField = new JFormattedTextField();

        return textField;
    }

    /**
     *
     * @return
     */
    private JFormattedTextField createEndTextField() {
        JFormattedTextField textField = new JFormattedTextField();

        return textField;
    }

    /**
     *
     * @return
     */
    private JFormattedTextField createStartTextField() {
        JFormattedTextField textField = new JFormattedTextField();

        return textField;
    }

}
