/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanprocessor.TimeProcessor;
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
    /** */
    private JComboBox<String>              routeSelector;
    /** */
    protected JTextField                   startTextField;
    /** */
    protected JTextField                   endTextField;
    /** */
    protected JTextField                   timeTextField;

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
        add(endLabel, "6,2,3,1");
        add(createEndTextField(), "6,4,3,1");

        JLabel timeLabel = new JLabel(FlightPlanCreationPanelMessages.TIME_LABEL);
        add(timeLabel, "10,2,3,1");
        add(createTimeTextField(), "10,4,3,1");

        JLabel routeLabel = new JLabel(FlightPlanCreationPanelMessages.ROUTE_LABEL);
        add(routeLabel, "2,6,5,1");
        add(createRouteSelectorCombo(), "2,8,7,1");

    }

    /**
     *
     * @return
     */
    private JComboBox<String> createRouteSelectorCombo() {
        routeSelector = new JComboBox<>();
        routeSelector.addItemListener(new ItemListener() {

            /** */
            @Override
            public void itemStateChanged(final ItemEvent arg0) {
                // TODO Auto-generated method stub

            }
        });

        return routeSelector;
    }

    /**
     *
     * @return
     */
    private JTextField createTimeTextField() {
        timeTextField = new JTextField();
        timeTextField.addKeyListener(new KeyTypingListener(timeTextField, TextFieldsEnum.TIME));
        timeTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textFieldUpdater(TextFieldsEnum.TIME, timeTextField.getText());
            }
        });
        timeTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                textFieldUpdater(TextFieldsEnum.TIME, timeTextField.getText());
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
        endTextField.addKeyListener(new KeyTypingListener(endTextField, TextFieldsEnum.END));
        endTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textFieldUpdater(TextFieldsEnum.END, endTextField.getText());
            }
        });
        endTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                textFieldUpdater(TextFieldsEnum.END, endTextField.getText());
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
        startTextField.addKeyListener(new KeyTypingListener(startTextField, TextFieldsEnum.START));
        startTextField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                textFieldUpdater(TextFieldsEnum.START, startTextField.getText());
            }
        });
        startTextField.addFocusListener(new FocusListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent e) {
                textFieldUpdater(TextFieldsEnum.START, startTextField.getText());
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
     * Update the correct text field start, end not empty & time empty=> update
     * time// if start ,time not empty & end empty=> update end // if end, time
     * not empty & start empty => update start// if all not empty => if start or
     * end updated => update time | if time updated => update end
     *
     * @param sender
     * @param value
     */
    protected void textFieldUpdater(final TextFieldsEnum sender, final String value) {
        boolean isStartEmpty = startTextField.getText().isEmpty();
        boolean isEndEmpty = endTextField.getText().isEmpty();
        boolean isTimeEmpty = timeTextField.getText().isEmpty();

        switch (sender) {
        case START:
            if (!isTimeEmpty) {
                endTextField.setText(TimeProcessor.getEndTime(value, timeTextField.getText()));
            } else {
                if (!isEndEmpty) {
                    timeTextField.setText(TimeProcessor.getDuration(value, endTextField.getText()));
                }
            }
            break;

        case END:
            if (!isTimeEmpty) {
                startTextField.setText(TimeProcessor.getStartTime(value, timeTextField.getText()));
            } else {
                if (!isStartEmpty) {
                    timeTextField.setText(TimeProcessor.getDuration(startTextField.getText(), value));
                }
            }

            break;
        case TIME:
            if (!isTimeEmpty) {
                if (!isStartEmpty) {
                    endTextField.setText(TimeProcessor.getEndTime(startTextField.getText(), value));

                } else {
                    if (!isEndEmpty) {
                        startTextField.setText(TimeProcessor.getStartTime(endTextField.getText(), value));
                    } else {
                        endTextField.setText("TO EMPTY 2");

                    }
                }
            } else {
                if (!isStartEmpty && isEndEmpty) {
                    timeTextField.setText(TimeProcessor.getDuration(startTextField.getText(), endTextField.getText()));
                }
            }

            break;

        default:
            break;
        }
    }

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected class KeyTypingListener implements KeyListener {

        /** */
        private final JTextField textField;
        /** */
        private final TextFieldsEnum   type;

        /**
         * @param textField
         * @param type
         *
         */
        public KeyTypingListener(final JTextField textField, final TextFieldsEnum type) {
            this.textField = textField;
            this.type = type;
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyTyped(final KeyEvent e) {
            if (!TimeProcessor.isMatch(textField.getText() + e.getKeyChar())) {
                e.consume();
            }
//            if (e.getKeyCode() == ){
//                textFieldUpdater(type, textField.getText());
//            }
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

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    private enum TextFieldsEnum {
        START, END, TIME
    }
}
