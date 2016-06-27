/* @(#)CurrentAirpotPanel.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.airportflightplanner.common.api.flightplancollection.FligthPlanCollectionProperties;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.loader.airport.AirportLoader;
import com.airportflightplanner.loader.flightplan.FlightPlanLoader;
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
public class CurrentAirportPanel extends JPanel {
    /**
     *
     */
    private static final long serialVersionUID = -8872582029412974363L;
    /**
     *
     */
    protected final FlighPlanCollectionModel fpcm;

    /**
     * @param fpcm
     *
     */
    public CurrentAirportPanel(final FlighPlanCollectionModel fpcm) {
        this.fpcm = fpcm;
        fpcm.addPropertyChangeListener(FligthPlanCollectionProperties.CURRENT_AIRPORT, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                FlightPlanLoader.loadFlightPlans(fpcm, evt.getNewValue().toString());
            }
        });
        setLayout(new FormLayout(new ColumnSpec[] { //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), }, //
                new RowSpec[] { FormSpecs.PREF_ROWSPEC, }));

        JLabel airportLabel = DefaultComponentFactory.getInstance().createLabel("Airport");

        add(airportLabel, "1, 1, right, default");

        JLabel timeLabel = DefaultComponentFactory.getInstance().createLabel("Time");
        add(timeLabel, "5, 1, right, default");

        add(createAirportComboxBox(), "3, 1, fill, default");
        add(createTimeComboxBox(), "7, 1, fill, default");

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
                    fpcm.setCurrentAirport(e.getItem().toString());
                }
            }
        });

        return comboBox;
    }

    /**
     *
     * @return
     */
    private JComboBox createTimeComboxBox() {
        JComboBox comboBox = new JComboBox();

        return comboBox;
    }
}
