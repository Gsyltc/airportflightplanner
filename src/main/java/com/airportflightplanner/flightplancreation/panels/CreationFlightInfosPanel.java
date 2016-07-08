/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.adapter.AircraftTypeAdapter;
import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FligthInfosProperties;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.model.FlighInfosModel;
import com.airportflightplanner.flightplancreation.renderers.CreationFlightInfosCompagnieCellRenderer;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationFlightInfosPanel extends CommonPanel {
  /** The logger of this class. */
  /**
   *
   */
  private static final long                          serialVersionUID   = -2692513903084994308L;
  /** */
  private final PresentationModel<FlightPlanReader>  currentFlightPlan;
  /** */
  protected final PresentationModel<FlighInfosModel> presenterInfoModel = new PresentationModel<FlighInfosModel>(new FlighInfosModel());

  /**
   * @param currentFlightPlan
   *
   */
  public CreationFlightInfosPanel(final PresentationModel<FlightPlanReader> currentFlightPlan) {
    this.currentFlightPlan = currentFlightPlan;
    build();
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  protected void build() {
    super.build();
    final FormLayout formLayout = new FormLayout(new ColumnSpec[] { //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
        FormSpecs.RELATED_GAP_COLSPEC, //
        ColumnSpec.decode("center:default:grow"), //
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

    final JLabel callSignLabel = new JLabel(FlightPlanCreationPanelMessages.CALLSIGN_LABEL);
    add(callSignLabel, "2, 4, right, default");
    add(createCallSignTextField(), "4, 4, 3, 1, fill, default");

    final JLabel typeLabel = new JLabel(FlightPlanCreationPanelMessages.TYPE_LABEL);
    add(typeLabel, "10, 4, right, default");
    add(createAircraftClassComboBox(), "12, 4, 3, 1, fill, default");

    final JLabel liveryLabel = new JLabel(FlightPlanCreationPanelMessages.AIRCRAFT_LIVERY);
    add(liveryLabel, "10, 6, right, default");
    add(createAircraftLiveryComboBox(), "12, 6, 3, 1, fill, default");

    final JLabel cpieLabel = new JLabel(FlightPlanCreationPanelMessages.CPIE_LABEL);
    add(cpieLabel, "2, 6, right, default");
    add(createAircraftCpieComboBox(), "4, 6, 3, 1, fill, default");

    currentFlightPlan.addPropertyChangeListener(new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof FlightPlanReader) {
          final String aircraftType = ((FlightPlanReader) evt.getNewValue()).getAircraftType();
          final FlighInfosModel bean = presenterInfoModel.getBean();
          bean.setLiveries(AircraftTypeAdapter.getAircraftLiveriesByType(aircraftType));
          bean.setCompanies(AircraftTypeAdapter.getAircraftCompaniesByType(aircraftType));

          final String aircraftClass = AircraftTypeAdapter.getAircraftClass(aircraftType);
          bean.setAircraftClass(aircraftClass);
          bean.setAircraftLivery(aircraftType);
          bean.setAircraftCie(AircraftTypeAdapter.getAircraftCie(aircraftType));
        }
      }
    });

  }

  /**
   *
   * @return
   */
  private JTextField createCallSignTextField() {

    final BufferedValueModel model = currentFlightPlan.getBufferedModel(FligthPlanProperties.CALLSIGN);
    final JTextField textField = BasicComponentFactory.createTextField(model);

    return textField;
  }

  /**
   *
   * @return
   */
  private JComboBox<String> createAircraftClassComboBox() {
    final Set<String> aircraftClass = AircraftTypeAdapter.getAircraftClass();
    final JComboBox<String> component = BasicComponentFactory.createComboBox(new SelectionInList<>(aircraftClass.toArray()));
    final FlighInfosModel model = presenterInfoModel.getBean();
    model.addPropertyChangeListener(FligthInfosProperties.AIRCRAFT_CLASS, new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        component.setSelectedItem(evt.getNewValue());
      }
    });

    component.addActionListener(new ActionListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void actionPerformed(final ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
          final String airCraft = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();
          model.setAircraftClass((String) ((JComboBox<?>) e.getSource()).getSelectedItem());
          model.setCompanies(AircraftTypeAdapter.getAircraftCompaniesByClass(airCraft));
        }
      }
    });

    return component;
  }

  /**
   *
   * @return
   */
  private JComboBox<String> createAircraftLiveryComboBox() {

    final ValueModel model = presenterInfoModel.getModel(FligthInfosProperties.LIVERIES);
    final SelectionInList<String> selectionInList = new SelectionInList<String>(model);
    final JComboBox<String> component = BasicComponentFactory.createComboBox(selectionInList);
    final FlighInfosModel bean = presenterInfoModel.getBean();
    bean.addPropertyChangeListener(FligthInfosProperties.AIRCRAFT_LIVERY, new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        component.setSelectedItem(evt.getNewValue());
      }
    });

    component.addActionListener(new ActionListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void actionPerformed(final ActionEvent e) {
        System.out.println("Set new aircraft liverie in the model");

      }
    });
    return component;
  }

  /**
   *
   * @return
   */
  private JComboBox<String> createAircraftCpieComboBox() {

    final ValueModel infosModel = presenterInfoModel.getModel(FligthInfosProperties.COMPANIES);
    final SelectionInList<String> selectionInList = new SelectionInList<String>(infosModel);
    final JComboBox<String> component = BasicComponentFactory.createComboBox(selectionInList, new CreationFlightInfosCompagnieCellRenderer());
    component.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
    final FlighInfosModel bean = presenterInfoModel.getBean();
    bean.addPropertyChangeListener(FligthInfosProperties.AIRCRAFT_CIE, new PropertyChangeListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void propertyChange(final PropertyChangeEvent evt) {
        component.setSelectedItem(evt.getNewValue());
      }
    });

    component.addActionListener(new ActionListener() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void actionPerformed(final ActionEvent e) {
        if (e.getSource() instanceof JComboBox) {
          final String company = (String) ((JComboBox) e.getSource()).getSelectedItem();
          bean.setAircraftCie(company);
          // bean.setLiveries(AircraftTypeAdapter.getAircraftLiveriesByType(airCraftType));
        }

      }
    });
    return component;
  }
}
