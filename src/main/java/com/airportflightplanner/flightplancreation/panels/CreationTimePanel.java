/*
 * @(#)CreationTimePanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanProperties;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ValueModel;
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
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(CreationTimePanel.class);

    /**
    *
    */
    private static final long serialVersionUID = 8098225641658386495L;

    /** */
    private static final int FP_PRESENTER = 0;
    /** */
    private static final int TIME_TEXT_LENGHT = 5;
    /** */
    private static final int BACKSPACE_CHAR = 8;

    /** */
    protected transient JTextField endTextField;

    /** */
    protected transient JTextField startTextField;

    /** */
    protected transient JTextField timeTextField;

    /**
     *
     * @author Goubaud Sylvain
     *
     */
    protected static class KeyTypingListener implements KeyListener {
        
        
        /** */
        private final transient JTextField textField;

        /**
         * @param nTextField
         *
         */
        public KeyTypingListener(final JTextField nTextField) {
            textField = nTextField;
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
            String newText = textField.getText();
            if (BACKSPACE_CHAR == event.getKeyCode() && 2 == newText.length()) {
                newText = newText.substring(0, 1);
                textField.setText(newText);
            } else {
                if (newText.length() == 2 && !newText.endsWith(":")) {
                    final StringBuffer buff = new StringBuffer(newText);
                    buff.append(':');
                    textField.setText(buff.toString());
                }
            }

            if (TIME_TEXT_LENGHT == newText.length()) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Update Other Fextfield : Sender is " + textField);
                }
                textField.postActionEvent();
            }
        }

        /**
         *
         * {@inheritDoc}
         */
        @Override
        public void keyTyped(final KeyEvent event) {
            if (!TimeUtils.isMatch(textField.getText() + event.getKeyChar())) {
                event.consume();
            }
        }
    }

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

        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);

        final TitledBorder timePanelBorder = new TitledBorder(FlightPlanCreationPanelMessages.SCHEDULE_LABEL);
        setBorder(timePanelBorder);

        final JLabel startLabel = new JLabel(FlightPlanCreationPanelMessages.START_LABEL);
        add(startLabel, "2,2,3,1");
        add(createStartTextField(presenter), "2,4,3,1");

        final JLabel endLabel = new JLabel(FlightPlanCreationPanelMessages.END_LABEL);
        add(endLabel, "6,2,3,1");
        add(createEndTextField(presenter), "6,4,3,1");

        final JLabel timeLabel = new JLabel(FlightPlanCreationPanelMessages.TIME_LABEL);
        add(timeLabel, "10,2,3,1");
        add(createTimeTextField(presenter), "10,4,3,1");
    }

    /**
     * Create the end text field.
     *
     * @param presenter
     *            the presenter.
     * @return the text field.
     */
    private JTextField createEndTextField(final PresentationModel<FlightPlanReader> presenter) {
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.END_TIME);

        final ValueModel value = ConverterFactory.createStringConverter(model, new Format() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 2946901254606932313L;

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
                final StringBuffer result = new StringBuffer();
                if (obj instanceof LocalTime) {
                    result.append(((LocalTime) obj).toString(TimeUtils.TIME_DISPLAYER));
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Format end time : " + result);
                    }
                }
                return result;
            }

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                final LocalTime result = TimeUtils.getLocalTime(source);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Parse end time : " + result);
                }
                pos.setIndex(source.length() - 1);
                return result;
            }

        });

        endTextField = BasicComponentFactory.createTextField(value);
        endTextField.addKeyListener(new KeyTypingListener(endTextField));
        endTextField.addActionListener(new ActionListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("End text field - An action is performed");
                }
                textFieldUpdater(TextFieldsEnum.END, presenter);
            }
        });
        return endTextField;
    }

    /**
     * Create the start text field.
     *
     * @param presenter
     *            the presenter.
     *
     * @return the text field.
     */
    private JTextField createStartTextField(final PresentationModel<FlightPlanReader> presenter) {
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.START_TIME);

        final ValueModel value = ConverterFactory.createStringConverter(model, new Format() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 8021308744564004518L;

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
                final StringBuffer result = new StringBuffer();
                if (obj instanceof LocalTime) {
                    result.append(((LocalTime) obj).toString(TimeUtils.TIME_DISPLAYER));
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Format start time : " + result);
                    }
                }
                return result;
            }

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                final LocalTime result = TimeUtils.getLocalTime(source);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Parse start time : " + result);
                }
                pos.setIndex(source.length() - 1);
                return result;
            }

        });
        startTextField = BasicComponentFactory.createTextField(value);
        startTextField.addKeyListener(new KeyTypingListener(startTextField));
        startTextField.addActionListener(new ActionListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Start text field - An action is performed");
                }
                textFieldUpdater(TextFieldsEnum.START, presenter);
            }
        });
        return startTextField;
    }

    /**
     * Create the time text field.
     *
     * @param presenter
     *            the presenter.
     *
     * @return the text field.
     */
    private JTextField createTimeTextField(final PresentationModel<FlightPlanReader> presenter) {
        final BufferedValueModel model = presenter.getBufferedModel(FlightPlanProperties.DURATION);

        final ValueModel value = ConverterFactory.createStringConverter(model, new Format() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = 8021308744564004518L;

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
                final StringBuffer result = new StringBuffer();
                if (obj instanceof Period) {
                    result.append(((Period) obj).toString(TimeUtils.PERIOD_DISPLAYER));
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Format period time : " + result);
                    }
                }
                return result;
            }

            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                final Period result = TimeUtils.getPeriod(source);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Parse period time : " + result);
                }
                pos.setIndex(source.length() - 1);
                return result;
            }

        });

        timeTextField = BasicComponentFactory.createTextField(value);
        timeTextField.setEditable(false);
        return timeTextField;
    }

    /**
     * Update the correct text field start, end not empty & time empty=> update
     * time// if start ,time not empty & end empty=> update end // if end, time
     * not empty & start empty => update start// if all not empty => if start or
     * end updated => update time | if time updated => update end.
     *
     * @param sender
     *            the sender id.
     * @param presenter
     *            the presenter.
     */
    protected void textFieldUpdater(final TextFieldsEnum sender, final PresentationModel<FlightPlanReader> presenter) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Update text fields - Sender is " + sender.name());
        }

        switch (sender) {
        case START:
            presenter.setBufferedValue(FlightPlanProperties.END_TIME, //
                    TimeUtils.getEndTime(startTextField.getText(), timeTextField.getText()));
            break;
        
        case END:
            presenter.setBufferedValue(FlightPlanProperties.START_TIME, //
                    TimeUtils.getStartTime(endTextField.getText(), timeTextField.getText()));
            break;
        
        default:
            break;
        }
    }
}
