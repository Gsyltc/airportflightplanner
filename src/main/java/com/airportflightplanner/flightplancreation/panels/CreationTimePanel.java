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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationTimePanel extends CommonPanel {

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
    public void keyPressed(final KeyEvent e) {
      //
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
    public void keyTyped(final KeyEvent e) {
      if (null == TimeUtils.getLocalTime(textField.getText() + e.getKeyChar())) {
        e.consume();
      }
    }
  }

  /**
   *
   * @author Goubaud Sylvain
   *
   */
  private enum TextFieldsEnum {
    END, START, TIME
  }

  /**
   *
   */
  private static final long                           serialVersionUID = 8098225641658386495L;
  /** */
  protected final PresentationModel<FlightPlanReader> currentFlightPlan;
  /** */
  protected JTextField                                endTextField;

  /** */
  protected JTextField                                startTextField;

  /** */
  protected JTextField                                timeTextField;

  /**
   * @param currentFlightPlan2
   *
   */
  public CreationTimePanel(final PresentationModel<FlightPlanReader> currentFlightPlan2) {
    currentFlightPlan = currentFlightPlan2;
    build();
  }

  /**
  *
  */
  @Override
  protected void build() {
    super.build();
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

    final TitledBorder timePanelBorder = new TitledBorder(
        FlightPlanCreationPanelMessages.SCHEDULE_LABEL);
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
    final BufferedValueModel model = currentFlightPlan.getBufferedModel(
        FligthPlanProperties.END_TIME);
    endTextField = BasicComponentFactory.createTextField(model);

    model.addPropertyChangeListener(new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof LocalTime) {
          endTextField.setText(((LocalTime) evt.getNewValue()).toString(TimeUtils.TIME_DISPLAYER));
        }
      }
    });

    endTextField.addKeyListener(new KeyTypingListener(endTextField));
    endTextField.addActionListener(new ActionListener() {

      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void actionPerformed(final ActionEvent e) {
        textFieldUpdater(TextFieldsEnum.END);
      }
    });
    endTextField.addFocusListener(new FocusListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusGained(final FocusEvent e) {
        //
      }

      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusLost(final FocusEvent e) {
        textFieldUpdater(TextFieldsEnum.END);
      }

    });
    return endTextField;
  }

  /**
   *
   * @return
   */
  private JTextField createStartTextField() {
    final BufferedValueModel model = currentFlightPlan.getBufferedModel(
        FligthPlanProperties.START_TIME);
    startTextField = BasicComponentFactory.createTextField(model);

    model.addPropertyChangeListener(new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof LocalTime) {
          startTextField.setText(((LocalTime) evt.getNewValue()).toString(
              TimeUtils.TIME_DISPLAYER));
        }
      }
    });
    //
    startTextField.addKeyListener(new KeyTypingListener(startTextField));
    startTextField.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        textFieldUpdater(TextFieldsEnum.START);
      }
    });
    startTextField.addFocusListener(new FocusListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusGained(final FocusEvent e) {
        //
      }

      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusLost(final FocusEvent e) {
        textFieldUpdater(TextFieldsEnum.START);
      }
    });
    return startTextField;
  }

  /**
   *
   * @return
   */
  private JTextField createTimeTextField() {

    final BufferedValueModel model = currentFlightPlan.getBufferedModel(
        FligthPlanProperties.DURATION);
    timeTextField = BasicComponentFactory.createTextField(model);

    model.addPropertyChangeListener(new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof Period) {
          timeTextField.setText(((Period) evt.getNewValue()).toString(TimeUtils.PERIOD_DISPLAYER));
        }
      }
    });
    timeTextField.addKeyListener(new KeyTypingListener(timeTextField));
    timeTextField.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(final ActionEvent e) {
        textFieldUpdater(TextFieldsEnum.TIME);
      }
    });
    timeTextField.addFocusListener(new FocusListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusGained(final FocusEvent e) {
        //
      }

      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void focusLost(final FocusEvent e) {
        textFieldUpdater(TextFieldsEnum.TIME);
      }
    });
    return timeTextField;
  }

  /**
   * Update the correct text field start, end not empty & time empty=> update time// if start ,time
   * not empty & end empty=> update end // if end, time not empty & start empty => update start// if
   * all not empty => if start or end updated => update time | if time updated => update end
   *
   * @param sender
   */
  protected void textFieldUpdater(final TextFieldsEnum sender) {
    final boolean isStartEmpty = startTextField.getText().isEmpty();
    final boolean isEndEmpty = endTextField.getText().isEmpty();
    final boolean isTimeEmpty = timeTextField.getText().isEmpty();
    switch (sender) {
    case START:
      if (!isTimeEmpty) {
        currentFlightPlan.setBufferedValue(FligthPlanProperties.END_TIME, //
            TimeUtils.getEndTime(startTextField.getText(), timeTextField.getText()));
      } else {
        if (!isEndEmpty) {
          currentFlightPlan.setBufferedValue(FligthPlanProperties.DURATION, //
              TimeUtils.getDuration(startTextField.getText(), endTextField.getText()));
        }
      }
      break;

    case END:
      if (!isTimeEmpty) {
        currentFlightPlan.setBufferedValue(FligthPlanProperties.START_TIME, //
            TimeUtils.getStartTime(endTextField.getText(), timeTextField.getText()));
      } else {
        if (!isStartEmpty) {
          currentFlightPlan.setBufferedValue(FligthPlanProperties.DURATION, //
              TimeUtils.getDuration(startTextField.getText(), endTextField.getText()));
        }
      }

      break;
    case TIME:
      if (!isTimeEmpty) {
        if (!isStartEmpty) {
          currentFlightPlan.setBufferedValue(FligthPlanProperties.END_TIME, //
              TimeUtils.getEndTime(startTextField.getText(), timeTextField.getText()));
        } else {
          if (!isEndEmpty) {
            currentFlightPlan.setBufferedValue(FligthPlanProperties.START_TIME, //
                TimeUtils.getStartTime(endTextField.getText(), timeTextField.getText()));
          } else {
            endTextField.setText("TO EMPTY 2");

          }
        }
      } else {
        if (!isStartEmpty && isEndEmpty) {
          currentFlightPlan.setBufferedValue(FligthPlanProperties.DURATION, //
              TimeUtils.getDuration(startTextField.getText(), endTextField.getText()));
        }
      }

      break;

    default:
      break;
    }
  }
}
