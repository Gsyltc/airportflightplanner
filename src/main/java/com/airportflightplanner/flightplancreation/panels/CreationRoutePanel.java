/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
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
public class CreationRoutePanel extends AbstractCommonPanel {
  /** The logger of this class. */
  /**
   *
   */
  private static final long                         serialVersionUID = -2692513903084994308L;
  /** */
  private JComboBox<String>                         routeSelector;
  /** */
  private final PresentationModel<FligthPlanReader> currentFlightPlan;
  /** */
  protected GoogleMapPane                           googleMap;

  /**
   * @param currentFlightPlan
   *
   */
  public CreationRoutePanel(final PresentationModel<FligthPlanReader> currentFlightPlan) {
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
        FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("pref:grow"),
        FormSpecs.RELATED_GAP_COLSPEC, }, new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC, }));

    final TitledBorder timePanelBorder = new TitledBorder(
        FlightPlanCreationPanelMessages.ROUTE_PANEL_LABEL);
    setBorder(timePanelBorder);

    final JLabel routeLabel = new JLabel(FlightPlanCreationPanelMessages.ROUTE_LABEL);
    add(routeLabel, "2, 2, 5, 1");
    add(createRouteSelectorCombo(), "2, 4, 11, 1");

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
}
