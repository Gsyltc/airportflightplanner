/*
 * @(#)CommandPanel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CommandPanel extends AbstractCommonPanel {

    /**
     *
     */
    private static final long serialVersionUID = 7075372771107433751L;
    /** */
    protected Signal            validationSignal;

    /**
     *
     */
    public CommandPanel() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();

        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("center:default:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, ///
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, });

        setLayout(formLayout);
        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.FLIGHT_INFOS_LABEL));

        add(createValidateButton(), "2,2");
        add(createCancelButton(), "2,4");
    }

    /**
     *
     * @return
     */
    private JButton createValidateButton() {
        final JButton button = new JButton("Validate");
        button.addActionListener(new ActionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                validationSignal.fireSignal(ActionTypes.VALIDATE);
            }
        });
        return button;
    }

    /**
     *
     * @return
     */
    private JButton createCancelButton() {
        final JButton button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent e) {
                validationSignal.fireSignal(ActionTypes.CANCEL);
            }
        });
        return button;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        super.attachSignal();
        validationSignal = findSignal(TopicName.VALIDATION_TOPIC);
        if (null == validationSignal) {
            validationSignal = new Signal(TopicName.VALIDATION_TOPIC);
        }
        createSignal(TopicName.VALIDATION_TOPIC, validationSignal);
    }
}
