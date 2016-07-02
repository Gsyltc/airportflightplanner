/* @(#)CreationTimePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanprocessor.TimeProcessor;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationTimePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 8098225641658386495L;
    /** */
    protected JTextField      startTextField;
    /** */
    protected JTextField      endTextField;
    /** */
    protected JTextField      timeTextField;

    /**
     *
     */
    public CreationTimePanel() {
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
                        FormSpecs.PREF_ROWSPEC }));

        TitledBorder timePanelBorder = new TitledBorder(FlightPlanCreationPanelMessages.SCHEDULE_LABEL);
        setBorder(timePanelBorder);

        JLabel startLabel = new JLabel(FlightPlanCreationPanelMessages.START_LABEL);
        add(startLabel, "2,2,3,1");
        add(createStartTextField(), "2,4,3,1");

        JLabel endLabel = new JLabel(FlightPlanCreationPanelMessages.END_LABEL);
        add(endLabel, "6,2,3,1");
        add(createEndTextField(), "6,4,3,1");

        JLabel timeLabel = new JLabel(FlightPlanCreationPanelMessages.TIME_LABEL);
        add(timeLabel, "10,2,3,1");
        add(createTimeTextField(), "10,4,3,1");
    }

    /**
     *
     * @return
     */
    private JTextField createTimeTextField() {
        timeTextField = new JTextField();
        timeTextField.addKeyListener(new KeyTypingListener(timeTextField));
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
        endTextField.addKeyListener(new KeyTypingListener(endTextField));
        endTextField.addActionListener(new ActionListener() {
            /**
             *
             * {@inheritDoc}
             */
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
        startTextField.addKeyListener(new KeyTypingListener(startTextField));
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
            if (!TimeProcessor.isMatch(textField.getText() + e.getKeyChar())) {
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

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    private enum TextFieldsEnum {
        START, END, TIME
    }
}
