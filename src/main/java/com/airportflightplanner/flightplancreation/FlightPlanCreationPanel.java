/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;

import com.airportflightplanner.common.api.flightplan.FligthPlanReader;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapWriter;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.panels.CreationFlightInfosPanel;
import com.airportflightplanner.flightplancreation.panels.CreationOptionsPanel;
import com.airportflightplanner.flightplancreation.panels.CreationRoutePanel;
import com.airportflightplanner.flightplancreation.panels.CreationTimePanel;
import com.airportflightplanner.flightplancreation.panels.GoogleMapPane;
import com.google.maps.model.EncodedPolyline;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanCreationPanel extends CommonPanel {

  /** */
  protected static final int                          DEPARTURE_POINT   = 0;
  /**
   *
   */
  private static final long                           serialVersionUID  = 4047549681152943474L;
  /**
   *
   */
  protected final PresentationModel<FligthPlanReader> currentFlightPlan = new PresentationModel<FligthPlanReader>();
  /** */
  protected GoogleMapPane                             googleMap;

  /**
  *
  */
  protected final PresentationModel<GoogleMapModel>   googleMapModel    = new PresentationModel<GoogleMapModel>();

  /**
  *
  */

  /**
   */
  public FlightPlanCreationPanel() {
    build();
  }

  /**
   *
   * {@inheritDoc}
   */
  @Override
  public void attachSlotAction() {
    final Slot<FligthPlanReader> slot = new Slot<FligthPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
    slot.setSlotAction(new SlotAction<FligthPlanReader>() {
      /**
       *
       * {@inheritDoc}
       */
      @Override
      public void doAction(final FligthPlanReader flightPlanReader) {
        if (null != flightPlanReader) {

          currentFlightPlan.triggerFlush();
          currentFlightPlan.setBean(flightPlanReader);

          final GoogleMapWriter googleMapWriter = new GoogleMapModel();
          googleMapModel.setBean((GoogleMapModel) googleMapWriter);
          googleMapWriter.setMarkers(GeographicUtils.getSteerPoints(flightPlanReader
              .getSteerPoints()));
          final EncodedPolyline polyline = GeographicUtils.getEncodePolyline(flightPlanReader
              .getSteerPoints());
          googleMapWriter.setEncodedPolyline(polyline);
        }
      }
    });
  }

  /**
   *
   * {@inheritDoc}
   */
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
            FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.PREF_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC, }));

    final CreationTimePanel timePanel = new CreationTimePanel(currentFlightPlan);
    add(timePanel, "2,2,11,1");

    final CreationRoutePanel routePanel = new CreationRoutePanel(currentFlightPlan);
    add(routePanel, "2,4,11,1");

    final CreationFlightInfosPanel flightInfosPanel = new CreationFlightInfosPanel(
        currentFlightPlan);
    add(flightInfosPanel, "2,6,11,1");

    final CreationOptionsPanel optionsPanel = new CreationOptionsPanel(currentFlightPlan);
    add(optionsPanel, "2,8,11,1");

    add(createMap(), "2, 14, 11, 1, center, center");
  }

  /**
   *
   * @param googleMapModel
   * @return
   */
  private JPanel createMap() {
    final JPanel panel = new JPanel();
    panel.setSize(400, 400);
    panel.setMinimumSize(new Dimension(400, 400));

    googleMap = new GoogleMapPane(googleMapModel);
    googleMap.setDimension(new Rectangle(0, 0, 400, 400));
    googleMap.setSize(400, 400);
    panel.add(googleMap);

    return panel;
  }
}
