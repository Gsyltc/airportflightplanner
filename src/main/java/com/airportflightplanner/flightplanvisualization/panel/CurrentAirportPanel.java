/* @(#)CurrentAirpotPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.selection.Signal;
import com.airportflightplanner.common.selection.TopicName;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplanvisualization.messages.FlightPlanVisualizationMessages;
import com.airportflightplanner.loader.airport.AirportLoader;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CurrentAirportPanel extends CommonPanel {
    /**
     *
     */
    private static final long                serialVersionUID = -8872582029412974363L;
    /**
     *
     */
    protected final FlighPlanCollectionModel flightPlansCollection;

    /**
     * @param fpcm
     *
     */
    public CurrentAirportPanel(final FlighPlanCollectionModel fpcm) {
        this.flightPlansCollection = fpcm;
        build();
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    protected void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), }, //
                new RowSpec[] { FormSpecs.PREF_ROWSPEC, }));

        JLabel airportLabel = DefaultComponentFactory.getInstance().createLabel(FlightPlanVisualizationMessages.AIRPORT);

        add(airportLabel, "1, 1, right, default");

        JLabel timeLabel = DefaultComponentFactory.getInstance().createLabel(FlightPlanVisualizationMessages.TIME);
        add(timeLabel, "5, 1, right, default");

        add(createAirportComboxBox(), "3, 1, fill, default");
        add(createTimeComboxBox(), "7, 1, fill, default");

        Signal signal = new Signal(TopicName.UPDATE_AIRPORT_TOPIC);
        createSignal(TopicName.UPDATE_AIRPORT_TOPIC, signal);
    }

    /**
     *
     * @return
     */
    private JComboBox<?> createAirportComboxBox() {
        JComboBox<?> comboBox = new JComboBox<String>();

        ValueModel selectionHolder = new ValueHolder();
        ComboBoxAdapter<String> comboBoxAdapter = new ComboBoxAdapter<String>(AirportLoader.getAirports(), selectionHolder);
        comboBox.setModel(comboBoxAdapter);

        comboBox.addItemListener(new ItemListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void itemStateChanged(final ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    flightPlansCollection.setCurrentAirport(e.getItem().toString());
                    Signal signal = findSignal(TopicName.UPDATE_AIRPORT_TOPIC);
                    signal.fireSignal(this);
                }
            }
        });

        return comboBox;
    }

    /**
     *
     * @return
     */
    private JComboBox<?> createTimeComboxBox() {
        JComboBox<?> comboBox = new JComboBox<Object>();

        return comboBox;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void slotAction(final Object object) {
        System.out.println("Fire : CurrentAirportPanel - " + object.toString());
    }
}
