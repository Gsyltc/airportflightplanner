/*
 * @(#)CreationFlightInfosPanel.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.adapter.AircraftTypeAdapter;
import com.airportflightplanner.common.api.adapter.StartDaysAdapter;
import com.airportflightplanner.common.api.dayselection.bean.DaySelectionReader;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosProperties;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosReader;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosWriter;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.model.FlightInfosModel;
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
public class CreationFlightInfosPanel extends AbstractCommonPanel {
    /** The logger of this class. */
    /**
     *
     */
    private static final long  serialVersionUID         = -2692513903084994308L;
    /** */
    protected static final int NO_SELECTION             = -1;
    /** */
    private static final int   FL_INFOS_PRESENTER_INDEX = 1;

    /**
     * @param newCurrentFlightPlan
     *
     */
    public CreationFlightInfosPanel(final PresentationModel<FlightPlanReader> newCurrentFlightPlan) {
        super(newCurrentFlightPlan, new PresentationModel<FlightInfosReader>(new FlightInfosModel()));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void build() {
        super.build();
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, ///
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, });

        formLayout.setRowGroups(new int[][] { new int[] { 6, 4, 2 } });
        formLayout.setColumnGroups(new int[][] { new int[] { 2, 14, 12, 10, 8, 6, 4 } });
        setLayout(formLayout);

        final PresentationModel<FlightInfosReader> flInfosPresenter = //
                (PresentationModel<FlightInfosReader>) getPresenter(FL_INFOS_PRESENTER_INDEX);

        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.FLIGHT_INFOS_LABEL));
        final StartDaysAdapter adapter = (StartDaysAdapter) getAdapterByName(StartDaysAdapter.class.getSimpleName());
        final CreationStartDaysPanel panel = new CreationStartDaysPanel(new PresentationModel<DaySelectionReader>(//
                adapter.getModel()));
        panel.build();
        add(panel, "2, 2,11,1");

        final JLabel callSignLabel = new JLabel(FlightPlanCreationPanelMessages.CALLSIGN_LABEL);
        add(callSignLabel, "10, 4, right, default");
        add(createCallSignTextField(), "12, 4, 3, 1, fill, default");

        final JLabel typeLabel = new JLabel(FlightPlanCreationPanelMessages.TYPE_LABEL);
        add(typeLabel, "2, 4, right, default");
        add(createAircraftClassComboBox(flInfosPresenter), "4, 4, 3, 1, fill, default");

        final JLabel liveryLabel = new JLabel(FlightPlanCreationPanelMessages.AIRCRAFT_LIVERY_LABEL);
        add(liveryLabel, "10, 6, right, default");
        add(createAircraftLiveryComboBox(flInfosPresenter), "12, 6, 3, 1, fill, default");

        final JLabel cpieLabel = new JLabel(FlightPlanCreationPanelMessages.CPIE_LABEL);
        add(cpieLabel, "2, 6, right, default");
        add(createAircraftCpieComboBox(flInfosPresenter), "4, 6, 3, 1, fill, default");

        getPresenter(FIRST_PRESENTER).addPropertyChangeListener(new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (evt.getNewValue() instanceof FlightPlanReader) {
                    final String aircraftType = ((FlightPlanReader) evt.getNewValue()).getAircraftType();
                    final FlightInfosModel bean = (FlightInfosModel) getPresenter(FL_INFOS_PRESENTER_INDEX).getBean();

                    final String aircraftClass = AircraftDecoder.getAircraftClass(aircraftType);
                    bean.setAircraftClass(aircraftClass);
                    bean.setAircraftCie(AircraftDecoder.getAircraftCie(aircraftType));
                    bean.setAircraftLivery(aircraftType);
                }
            }
        });

    }

    /**
     *
     * @return
     */
    private JTextField createCallSignTextField() {
        final BufferedValueModel model = getPresenter(FIRST_PRESENTER).getBufferedModel(FlightPlanProperties.CALLSIGN);
        return BasicComponentFactory.createTextField(model);
    }

    /**
     *
     * @param flInfosPresenter
     * @return
     */
    private JComboBox<String> createAircraftClassComboBox(final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final AircraftTypeAdapter adapter = (AircraftTypeAdapter) getAdapterByName(AircraftTypeAdapter.class.getSimpleName());
        final Set<String> aircraftClass = adapter.getAircraftClasses();
        final JComboBox<String> component = BasicComponentFactory.createComboBox(new SelectionInList<>(aircraftClass.toArray()));
        final FlightInfosModel bean = (FlightInfosModel) flInfosPresenter.getBean();
        bean.addPropertyChangeListener(FlightInfosProperties.AIRCRAFT_CLASS, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                refreshData(evt.getPropertyName(), flInfosPresenter);
                component.setSelectedItem(evt.getNewValue());
            }
        });

        component.addActionListener(new ActionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                if (event.getSource() instanceof JComboBox) {
                    bean.setAircraftClass((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
                }
            }
        });

        return component;
    }

    /**
     *
     * @param flInfosPresenter
     * @return
     */
    private JComboBox<String> createAircraftLiveryComboBox(final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final ValueModel model = flInfosPresenter.getModel(FlightInfosProperties.LIVERIES);

        final SelectionInList<String> selectionInList = new SelectionInList<String>(model);
        final JComboBox<String> component = BasicComponentFactory.createComboBox(selectionInList);
        final FlightInfosModel bean = (FlightInfosModel) flInfosPresenter.getBean();
        bean.addPropertyChangeListener(FlightInfosProperties.AIRCRAFT_LIVERY, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                refreshData(evt.getPropertyName(), flInfosPresenter);
                if (null == evt.getNewValue()) {
                    component.setSelectedIndex(NO_SELECTION);
                } else {

                    component.setSelectedItem(evt.getNewValue());
                }
            }
        });

        bean.addPropertyChangeListener(FlightInfosProperties.COMPANIES, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                refreshData(evt.getPropertyName(), flInfosPresenter);
            }
        });

        component.addActionListener(new ActionListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                final String livery = (String) ((JComboBox<?>) event.getSource()).getSelectedItem();
                if (null != livery) {
                    bean.setAircraftLivery((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
                }
            }
        });
        return component;

    }

    /**
     *
     * @param flInfosPresenter
     * @return
     */
    private JComboBox<String> createAircraftCpieComboBox(final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final ValueModel infosModel = flInfosPresenter.getModel(FlightInfosProperties.COMPANIES);
        final SelectionInList<String> selectionInList = new SelectionInList<String>(infosModel);
        final JComboBox<String> component = BasicComponentFactory.createComboBox(//
                selectionInList, new CreationFlightInfosCompagnieCellRenderer());
        component.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXX");
        final FlightInfosModel bean = (FlightInfosModel) flInfosPresenter.getBean();
        bean.addPropertyChangeListener(FlightInfosProperties.AIRCRAFT_CIE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                refreshData(evt.getPropertyName(), flInfosPresenter);
                if (null == evt.getNewValue()) {
                    component.setSelectedIndex(NO_SELECTION);
                } else {
                    component.setSelectedItem(evt.getNewValue());
                }
            }
        });

        component.addActionListener(new ActionListener() {

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                if (event.getSource() instanceof JComboBox) {
                    bean.setAircraftCie((String) ((JComboBox<?>) event.getSource()).getSelectedItem());
                }
            }
        });
        return component;
    }

    /**
     * @param propertyName
     *            Property to refresh.
     * @param flInfosPresenter
     */
    protected void refreshData(final String propertyName, final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final AircraftTypeAdapter adapter = (AircraftTypeAdapter) getAdapterByName(AircraftTypeAdapter.class.getSimpleName());
        final FlightInfosWriter bean = (FlightInfosModel) flInfosPresenter.getBean();
        switch (propertyName) {
        // La classe a changee, on reset le model des compagnies et des livrees
        case FlightInfosProperties.AIRCRAFT_CLASS:
            bean.setCompanies(adapter.getAircraftCompaniesByClass(bean.getAircraftClass()));
            break;

        case FlightInfosProperties.COMPANIES:
        case FlightInfosProperties.AIRCRAFT_CIE:
            bean.setLiveries(adapter.getAircraftLiveriesByClassCpie(bean.getAircraftClass() + "_" + bean.getAircraftCie()));
            break;

        default:
            break;
        }
    }
}
