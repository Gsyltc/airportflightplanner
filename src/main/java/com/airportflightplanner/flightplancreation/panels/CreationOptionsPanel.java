/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.visualelement.CommonPanel;
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
public class CreationOptionsPanel extends CommonPanel {
  /** The logger of this class. */
  /**
   *
   */
  private static final long                         serialVersionUID = -2692513903084994308L;
  /** */
  private JComboBox<String>                         routeSelector;
  /** */
  private final PresentationModel<FlightPlanReader> currentFlightPlan;
  /** */
  protected GoogleMapPane                           googleMap;

  /**
   * @param currentFlightPlan
   *
   */
  public CreationOptionsPanel(final PresentationModel<FlightPlanReader> currentFlightPlan) {
    this.currentFlightPlan = currentFlightPlan;
    build();
  }

  @Override
  protected void build() {
    super.build();
    setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(
        "pref:grow"), FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
        FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
        FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
        FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
        FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC, }));

    setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.OPTIONS_LABEL));

    final JCheckBox chckbxNewCheckBox = new JCheckBox(
        FlightPlanCreationPanelMessages.DEPATURETYPE_LABEL);
    add(chckbxNewCheckBox, "2, 2, 3, 1");

    final JCheckBox chckbxNewCheckBox_2 = new JCheckBox(
        FlightPlanCreationPanelMessages.LANDINGLIGHT_LABEL);
    add(chckbxNewCheckBox_2, "6, 2, 3, 1");

    final JCheckBox chckbxNewCheckBox_4 = new JCheckBox("New check box");
    add(chckbxNewCheckBox_4, "10, 2");

    final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
    add(chckbxNewCheckBox_1, "2, 4, 3, 1");

    final JCheckBox chckbxNewCheckBox_3 = new JCheckBox("New check box");
    add(chckbxNewCheckBox_3, "6, 4, 3, 1");

  }
  //
  // /**
  // *
  // * @return
  // */
  // private JComboBox<String> createRouteSelectorCombo() {
  // routeSelector = new JComboBox<>();
  // routeSelector.addItemListener(new ItemListener() {
  //
  // /** */
  // @Override
  // public void itemStateChanged(final ItemEvent arg0) {
  // // TODO Auto-generated method stub
  //
  // }
  // });
  //
  // return routeSelector;
  // }
  //
  // /**
  // *
  // * @return
  // */
  // private JComboBox<String> createRouteSelectorCombo() {
  // routeSelector = new JComboBox<>();
  // routeSelector.addItemListener(new ItemListener() {
  //
  // /** */
  // @Override
  // public void itemStateChanged(final ItemEvent arg0) {
  // // TODO Auto-generated method stub
  //
  // }
  // });
  //
  // return routeSelector;
  // }
  //
  // /**
  // *
  // * @return
  // */
  // private JComboBox<String> createRouteSelectorCombo() {
  // routeSelector = new JComboBox<>();
  // routeSelector.addItemListener(new ItemListener() {
  //
  // /** */
  // @Override
  // public void itemStateChanged(final ItemEvent arg0) {
  // // TODO Auto-generated method stub
  //
  // }
  // });
  //
  // return routeSelector;
  // }
  //
  // /**
  // *
  // * @return
  // */
  // private JComboBox<String> createRouteSelectorCombo() {
  // routeSelector = new JComboBox<>();
  // routeSelector.addItemListener(new ItemListener() {
  //
  // /** */
  // @Override
  // public void itemStateChanged(final ItemEvent arg0) {
  // // TODO Auto-generated method stub
  //
  // }
  // });
  //
  // return routeSelector;
  // }
  //
  // /**
  // *
  // * @return
  // */
  // private JComboBox<String> createRouteSelectorCombo() {
  // routeSelector = new JComboBox<>();
  // routeSelector.addItemListener(new ItemListener() {
  //
  // /** */
  // @Override
  // public void itemStateChanged(final ItemEvent arg0) {
  // // TODO Auto-generated method stub
  //
  // }
  // });
  //
  // return routeSelector;
  // }
}
