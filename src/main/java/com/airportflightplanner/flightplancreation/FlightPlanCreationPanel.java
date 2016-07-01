/* @(#)FlightPlanCreation.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplancreation;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
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
	private static final long serialVersionUID = 4047549681152943474L;
	/**
	 *
	 */
	private final FlighPlanCollectionModel flightPlansCollection;
	/** */
	private JComboBox<String> routeSelector;

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
		add(timeLabel, "2,6,5,1");
		add(createRouteSelectorCombo(), "2,8,5,1");

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
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		return routeSelector;
	}

	/**
	 *
	 * @return
	 */
	private JFormattedTextField createTimeTextField() {
		JFormattedTextField textField = new JFormattedTextField();

		return textField;
	}

	/**
	 *
	 * @return
	 */
	private JFormattedTextField createEndTextField() {
		JFormattedTextField textField = new JFormattedTextField();

		return textField;
	}

	/**
	 *
	 * @return
	 */
	private JFormattedTextField createStartTextField() {
		JFormattedTextField textField = new JFormattedTextField();

		return textField;
	}

}
