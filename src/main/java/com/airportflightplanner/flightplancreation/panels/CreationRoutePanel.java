/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
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
public class CreationRoutePanel extends CommonPanel {
	/** The logger of this class. */
	private static final Log LOGGER = LogFactory.getLog(CreationRoutePanel.class);

	/**
	 *
	 */
	private static final long serialVersionUID = -2692513903084994308L;
	/** */
	private JComboBox<String> routeSelector;
	/** */
	private final PresentationModel<FlighPlanModel> currentFlightPlan;
	/** */
	protected JGoogleMapEditorPan googleMap;

	/**
	 * @param currentFlightPlan
	 *
	 */
	public CreationRoutePanel(final PresentationModel<FlighPlanModel> currentFlightPlan) {
		this.currentFlightPlan = currentFlightPlan;
		build();
	}

	@Override
	protected void build() {
		super.build();
		setLayout(new FormLayout(
				new ColumnSpec[] { //
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
						FormSpecs.PREF_ROWSPEC }));

		TitledBorder timePanelBorder = new TitledBorder(FlightPlanCreationPanelMessages.ROUTE_PANEL_LABEL);
		setBorder(timePanelBorder);

		JLabel routeLabel = new JLabel(FlightPlanCreationPanelMessages.ROUTE_LABEL);
		add(routeLabel, "2,4,5,1");
		add(createRouteSelectorCombo(), "2,6,11,1,fill,fill");

		add(createMap(), "2,8,11,1");
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

	private JGoogleMapEditorPan createMap(){
    	 googleMap = new JGoogleMapEditorPan();
    	 googleMap.setDimension(getBounds());
    	 return googleMap;
    }

	/**
	 *
	 * {@inheritDoc}
	 */
	@Override
	public void attachSlotAction() {
		Slot<FlightPlanReader> slot = new Slot<>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
		slot.setSlotAction(new SlotAction<FlightPlanReader>() {
			/**
			 *
			 * {@inheritDoc}
			 */
			@Override
			public void doAction(final FlightPlanReader flightPlanReader) {
				if (null != flightPlanReader) {
					List<String> steerPointsList = flightPlanReader.getSteerPoints();
					String polyline = GeographicUtils.getEncodePolyline(steerPointsList);
					googleMap.createPolyline(polyline);
				}
			}
		});
	}
}
