/*
 * @(#)CreationTimePanel.java
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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.visualelements.AbstractCommonPanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationTimePanel extends AbstractCommonPanel {
    
    
    /**
     *
     * @author Goubaud Sylvain
     *
     */
    private enum TextFieldsEnum {
        /** */
        END,
        /** */
        START,
        /** */
        TIME
    }
    
    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected static class KeyTypingListener implements KeyListener {
        
        
        /** */
        private transient final JTextField textField;

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
        public void keyPressed(final KeyEvent event) {
            //
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyReleased(final KeyEvent event) {
            String newText = this.textField.getText();
            if ((event.getKeyCode() == 8) && (newText.length() == 2)) {
                newText = newText.substring(0, 1);
                this.textField.setText(newText);
            } else {
                if ((newText.length() == 2) && !newText.endsWith(":")) {
                    final StringBuffer buff = new StringBuffer(newText);
                    buff.append(':');
                    this.textField.setText(buff.toString());
                }
            }
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyTyped(final KeyEvent event) {
            if (null == TimeUtils.getLocalTime(this.textField.getText() + event.getKeyChar())) {
                event.consume();
            }
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = 8098225641658386495L;

    /** */
    private static final int FIRST_PRESENTER = 0;

    /** */
    protected transient JTextField endTextField;

    /** */
    protected transient JTextField startTextField;

    /** */
    protected transient JTextField timeTextField;

    /**
     * @param newCcurrentFlightPlan
     *            Flightplan.
     */
    public CreationTimePanel(final PresentationModel<FlightPlanReader> newCcurrentFlightPlan) {
        super(newCcurrentFlightPlan);
    }

    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC }));

        final TitledBorder timePanelBorder = new TitledBorder(FlightPlanCreationPanelMessages.SCHEDULE_LABEL);
        setBorder(timePanelBorder);

        final JLabel startLabel = new JLabel(FlightPlanCreationPanelMessages.START_LABEL);
        add(startLabel, "2,2,3,1");
        add(createStartTextField(), "2,4,3,1");

        final JLabel endLabel = new JLabel(FlightPlanCreationPanelMessages.END_LABEL);
        add(endLabel, "6,2,3,1");
        add(createEndTextField(), "6,4,3,1");

        final JLabel timeLabel = new JLabel(FlightPlanCreationPanelMessages.TIME_LABEL);
        add(timeLabel, "10,2,3,1");
        add(createTimeTextField(), "10,4,3,1");
    }

    /**
     *
     * @return
     */
    private JTextField createEndTextField() {
        final PresentationModel<?> presenter = getPresenter(FIRST_PRESENTER);
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.END_TIME);
        this.endTextField = BasicComponentFactory.createTextField(model);

        model.addPropertyChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof LocalTime) {
                    CreationTimePanel.this.endTextField.setText(((LocalTime) evt.getNewValue()).toString(TimeUtils.TIME_DISPLAYER));
                }
            }
        });

        this.endTextField.addKeyListener(new KeyTypingListener(this.endTextField));
        this.endTextField.addActionListener(new ActionListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                textFieldUpdater(TextFieldsEnum.END);
            }
        });
        this.endTextField.addFocusListener(new FocusListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent event) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent event) {
                textFieldUpdater(TextFieldsEnum.END);
            }

        });
        return this.endTextField;
    }

    /**
     *
     * @return
     */
    private JTextField createStartTextField() {
        final PresentationModel<?> presenter = getPresenter(FIRST_PRESENTER);
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.START_TIME);
        this.startTextField = BasicComponentFactory.createTextField(model);

        model.addPropertyChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof LocalTime) {
                    CreationTimePanel.this.startTextField.setText(((LocalTime) evt.getNewValue()).toString(
                            TimeUtils.TIME_DISPLAYER));
                }
            }
        });
        //
        this.startTextField.addKeyListener(new KeyTypingListener(this.startTextField));
        this.startTextField.addActionListener(new ActionListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                textFieldUpdater(TextFieldsEnum.START);
            }
        });
        this.startTextField.addFocusListener(new FocusListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent event) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent event) {
                textFieldUpdater(TextFieldsEnum.START);
            }
        });
        return this.startTextField;
    }

    /**
     *
     * @return
     */
    private JTextField createTimeTextField() {
        final PresentationModel<?> presenter = getPresenter(FIRST_PRESENTER);
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.DURATION);
        this.timeTextField = BasicComponentFactory.createTextField(model);

        model.addPropertyChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof Period) {
                    CreationTimePanel.this.timeTextField.setText(((Period) evt.getNewValue()).toString(TimeUtils.PERIOD_DISPLAYER));
                }
            }
        });
        this.timeTextField.addKeyListener(new KeyTypingListener(this.timeTextField));
        this.timeTextField.addActionListener(new ActionListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                textFieldUpdater(TextFieldsEnum.TIME);
            }
        });

        this.timeTextField.addFocusListener(new FocusListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusGained(final FocusEvent event) {
                //
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void focusLost(final FocusEvent event) {
                textFieldUpdater(TextFieldsEnum.TIME);
            }
        });
        return this.timeTextField;
    }

    /**
     * Update the correct text field start, end not empty & time empty=> update
     * time// if start ,time not empty & end empty=> update end // if end, time
     * not empty & start empty => update start// if all not empty => if start or
     * end updated => update time | if time updated => update end
     *
     * @param sender
     */
    protected void textFieldUpdater(final TextFieldsEnum sender) {
        final PresentationModel<?> presenter = getPresenter(FIRST_PRESENTER);

        final boolean isStartEmpty = this.startTextField.getText().isEmpty();
        final boolean isEndEmpty = this.endTextField.getText().isEmpty();
        final boolean isTimeEmpty = this.timeTextField.getText().isEmpty();
        switch (sender) {
        case START:
            if (isTimeEmpty) {
                if (!isEndEmpty) {
                    presenter.setBufferedValue(FlightPlanProperties.DURATION, //
                            TimeUtils.getDuration(this.startTextField.getText(), this.endTextField.getText()));
                }
            } else {
                presenter.setBufferedValue(FlightPlanProperties.END_TIME, //
                        TimeUtils.getEndTime(this.startTextField.getText(), this.timeTextField.getText()));
            }
            break;
        
        case END:
            if (isTimeEmpty) {
                if (!isStartEmpty) {
                    presenter.setBufferedValue(FlightPlanProperties.DURATION, //
                            TimeUtils.getDuration(this.startTextField.getText(), this.endTextField.getText()));
                }
            } else {
                presenter.setBufferedValue(FlightPlanProperties.START_TIME, //
                        TimeUtils.getStartTime(this.endTextField.getText(), this.timeTextField.getText()));

            }

            break;
        case TIME:
            if (isTimeEmpty) {
                if (!isStartEmpty && isEndEmpty) {
                    presenter.setBufferedValue(FlightPlanProperties.DURATION, //
                            TimeUtils.getDuration(this.startTextField.getText(), this.endTextField.getText()));
                }
            } else {
                if (isStartEmpty) {
                    presenter.setBufferedValue(FlightPlanProperties.START_TIME, //
                            TimeUtils.getStartTime(this.endTextField.getText(), this.timeTextField.getText()));
                } else {
                    if (isEndEmpty) {
                        this.endTextField.setText("TO EMPTY 2");
                    } else {
                        presenter.setBufferedValue(FlightPlanProperties.END_TIME, //
                                TimeUtils.getEndTime(this.startTextField.getText(), this.timeTextField.getText()));
                    }
                }
            }

            break;
        
        default:
            break;
        }
    }
}
