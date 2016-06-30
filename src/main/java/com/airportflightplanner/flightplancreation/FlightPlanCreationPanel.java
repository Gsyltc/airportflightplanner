/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JTextField;

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
     * *
     *
     */
    private final SimpleDateFormat         dateFormatter    = new SimpleDateFormat("hh:mm");
    /** */
    protected JTextField                   startTextField;
    /** */
    protected JTextField                   endTextField;
    private JTextField                     timeTextField;

    /**
     *
     */
    protected static final Pattern         PATTERN          = Pattern.compile("^([0-2]|[0-1][0-9]|2[0-3])((:[0-9])|(:[0-5][0-9]))?");

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
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
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
    private JTextField createTimeTextField() {
        timeTextField = new JTextField();
        timeTextField.addKeyListener(new KeyTypingListener(timeTextField));

        timeTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                if (endTextField.isEditable()) {
                    startTextField.setText("00:23");
                } else {
                    endTextField.setText("20:00");
                }
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent e) {
                //
            }
        });
        return timeTextField;
    }

    /**
     *
     * @return
     */
    private JTextField createEndTextField() {
        endTextField = new JTextField();
        endTextField.addKeyListener(new KeyTypingListener(endTextField));

        endTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                if (!endTextField.getText().isEmpty()) {
                    startTextField.setEditable(false);
                } else {
                    startTextField.setEditable(true);
                    endTextField.setText("");
                }
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent e) {
                //
            }
        });
        return endTextField;
    }

    /**
     *
     * @return
     */
    private JTextField createStartTextField() {
        startTextField = new JTextField();
        startTextField.addKeyListener(new KeyTypingListener(startTextField));

        startTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                if (!startTextField.getText().isEmpty()) {
                    endTextField.setEditable(false);
                } else {
                    endTextField.setEditable(true);
                    startTextField.setText("");
                }
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent e) {
                //
            }
        });
        return startTextField;
    }

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected class KeyTypingListener implements KeyListener {

        /** */
        private final JTextField textField;

        /**
         * @param textField
         *
         */
        public KeyTypingListener(final JTextField textField) {
            this.textField = textField;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyTyped(final KeyEvent e) {
            Matcher m = PATTERN.matcher(textField.getText() + e.getKeyChar());
            if (!m.matches()) {
                e.consume();
            }
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyReleased(final KeyEvent e) {
            String newText = textField.getText();
            if ((e.getKeyCode() == 8) && (newText.length() == 2)) {
                newText = newText.substring(0, 1);
                textField.setText(newText);
            } else {
                if ((newText.length() == 2) && !newText.endsWith(":")) {
                    newText = newText + ":";
                    textField.setText(newText);
                }
            }
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyPressed(final KeyEvent e) {
            //
        }
    }
}
