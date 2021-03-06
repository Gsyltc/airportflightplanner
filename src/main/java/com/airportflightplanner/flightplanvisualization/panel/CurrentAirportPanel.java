/*
 * @(#)CurrentAirportPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.adapters.AdapterNames;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanCollectionModelAdapter;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.messages.FlightPlanVisualizationMessages;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.airportflightplanner.importexport.importers.AirportFileReader;
import com.jgoodies.binding.adapter.ComboBoxAdapter;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.signals.Signal;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class CurrentAirportPanel extends AbstractCommandablePanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -8872582029412974363L;

    /**
     * @param fpVizuPresenter
     *            The flight plan visualization presenter
     *
     */
    public CurrentAirportPanel(final FlightPlanVisualizationPresenter fpVizuPresenter) {
        super(fpVizuPresenter);

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(//
                new ColumnSpec[] { //
                        ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                        FormSpecs.RELATED_GAP_COLSPEC, //
                        ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                        FormSpecs.RELATED_GAP_COLSPEC, //
                        ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                        FormSpecs.RELATED_GAP_COLSPEC, //
                        ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                        FormSpecs.RELATED_GAP_COLSPEC, //
                        ColumnSpec.decode(LayoutSpecs.PREF_GROW), }, //
                new RowSpec[] { FormSpecs.PREF_ROWSPEC, });
        formLayout.setColumnGroups(new int[][] { new int[] { 1, 5 }, new int[] { 3, 7 } });
        setLayout(formLayout);

        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.AIRPORT_TITLE);
        setBorder(panelBorder);

        final JLabel airportLabel = DefaultComponentFactory.getInstance().createLabel(FlightPlanVisualizationMessages.AIRPORT);
        add(airportLabel, "1, 1, right, default");

        final JLabel timeLabel = DefaultComponentFactory.getInstance().createLabel(FlightPlanVisualizationMessages.TIME);
        add(timeLabel, "5, 1, right, default");

        add(createAirportComboxBox(), "3, 1, fill, default");
        add(createTimeComboxBox(), "7, 1, fill, default");
        add(createFlipFlopButton(), "9,1,fill, default");

    }

    /**
     * @return
     */
    private FormDebugPanel createFlipFlopButton() {
        final FormDebugPanel panel = new FormDebugPanel();
        final FormLayout formLayout = new FormLayout(//
                new ColumnSpec[] { //
                        FormSpecs.BUTTON_COLSPEC, //
                        FormSpecs.BUTTON_COLSPEC, }, //
                new RowSpec[] { FormSpecs.BUTTON_ROWSPEC, });
        panel.setLayout(formLayout);

        final JToggleButton arrivalButton = new JToggleButton("A");
        final JToggleButton departureButton = new JToggleButton("D");

        panel.add(arrivalButton, "1,1");
        panel.add(departureButton, "2,1");
        return panel;
    }

    /**
     *
     * @return combobox for the airport.
     */
    private JComboBox<?> createAirportComboxBox() {
        final JComboBox<?> comboBox = new JComboBox<String>();

        final ValueModel selectionHolder = new ValueHolder();
        comboBox.setModel(new ComboBoxAdapter<String>(AirportFileReader.getAirports(), selectionHolder));

        comboBox.addItemListener(new ItemListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void itemStateChanged(final ItemEvent event) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    final String currentAirport = event.getItem().toString();
                    final FlightPlanCollectionModelAdapter adapter = (FlightPlanCollectionModelAdapter) findAdapter(
                            AdapterNames.FP_COLL_ADAPTER_NAME);
                    adapter.setCurrentAirport(currentAirport);
                    final Signal signal = findSignal(TopicName.UPDATE_AIRPORT_TOPIC);
                    signal.fireSignal(currentAirport);
                }
            }
        });

        comboBox.setSelectedItem(CommonProperties.getPropertyValue(CommonProperties.DEFAULT_AIRPORT));
        return comboBox;
    }

    /**
     *
     * @return Combobox for the time.
     */
    private JComboBox<?> createTimeComboxBox() {
        final JComboBox<?> comboBox = new JComboBox<Object>();

        return comboBox;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void createSignals() {
        super.createSignals();
        attachSignal(TopicName.UPDATE_AIRPORT_TOPIC);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(AdapterNames.FP_COLL_ADAPTER_NAME);
    }
}
