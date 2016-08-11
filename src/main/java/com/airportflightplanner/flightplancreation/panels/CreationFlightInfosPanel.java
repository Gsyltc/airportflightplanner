/*
 * @(#)CreationFlightInfosPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels; // NOPMD by sylva on 31/07/16 15:43

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.api.AircraftTypeAdapter;
import com.airportflightplanner.adapters.api.modeladapters.StartDaysAdapter;
import com.airportflightplanner.common.utils.aircraft.AircraftDecoder;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosProperties;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosReader;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosWriter;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.model.FlightInfosModel;
import com.airportflightplanner.flightplancreation.renderers.CommonComboBoxCellRenderer;
import com.airportflightplanner.models.daysselection.api.bean.DaySelectionReader;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanProperties;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.AbstractValueModel;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.visualelements.AbstractCommonPanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationFlightInfosPanel extends AbstractCommonPanel {
    
    
    /** The logger of this class. */
    protected static final Logger LOGGER = LogManager.getLogger(CreationFlightInfosPanel.class);
    /** The UID og the class. */
    private static final long serialVersionUID = -2692513903084994308L;
    /** the flight infos presenter index. */
    private static final int FL_INFOS_PRESENTER_INDEX = 1;
    /** the prototype display of the combobox. */
    private static final String PROTOTYPE_DISPLAY = "XXXXXXXXXXXXX";
    /** the figlht plan presenter index. */
    private static final int FP_PRESENTER = 0;
    /** */
    protected static final String AIRCRAFT_TYPE_ADAPTER = AircraftTypeAdapter.class.getSimpleName();
    /** */
    private static final String STARTDAYS_ADAPTER = StartDaysAdapter.class.getSimpleName();
    /** */
    protected static final int DEFAULT_ELEMENT = 0;
    /** */
    private static final int[] ROM_GROUP = new int[] { 6, 4, 2 };
    /** */
    private static final int[] COLLUMN_GROUP = new int[] { 2, 14, 12, 10, 8, 6, 4 };
    
    /**
     * Create the panel.
     *
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
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.CENTER_DEFAULT_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, ///
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, });
        
        formLayout.setRowGroups(new int[][] { ROM_GROUP });
        formLayout.setColumnGroups(new int[][] { COLLUMN_GROUP });
        setLayout(formLayout);
        
        final PresentationModel<FlightInfosReader> flInfosPresenter = //
                (PresentationModel<FlightInfosReader>) getPresenter(FL_INFOS_PRESENTER_INDEX);
        
        final StartDaysAdapter startDaysAdapter = (StartDaysAdapter) findAdapter(STARTDAYS_ADAPTER);
        
        // Listen the flight plan changes for update the display
        final PresentationModel<?> fpPresenter = getPresenter(FP_PRESENTER);
        fpPresenter.addPropertyChangeListener(PresentationModel.PROPERTY_BEFORE_BEAN, new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("FP_PRESENTER : " + evt.getPropertyName() + "=" + evt.getNewValue());
                }
                if (evt.getNewValue() instanceof FlightPlanReader) {
                    final String aircraftType = ((FlightPlanReader) evt.getNewValue()).getAircraftLivery();
                    refreshData(aircraftType, flInfosPresenter);
                    fpPresenter.triggerFlush();
                }
            }
        });
        fpPresenter.addPropertyChangeListener(PresentationModel.PROPERTY_BUFFERING, new PropertyChangeListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("FP_PRESENTER : " + evt.getPropertyName() + "=" + evt.getNewValue());
                }
            }
        });
        
        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.FLIGHT_INFOS_LABEL));
        final CreationStartDaysPanel panel = new CreationStartDaysPanel(new PresentationModel<DaySelectionReader>(//
                (DaySelectionReader) startDaysAdapter.getModel()), fpPresenter);
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
        add(createAircraftLiveryComboBox(flInfosPresenter, fpPresenter), "12, 6, 3, 1, fill, default");
        
        final JLabel cpieLabel = new JLabel(FlightPlanCreationPanelMessages.CPIE_LABEL);
        add(cpieLabel, "2, 6, right, default");
        add(createAircraftCpieComboBox(flInfosPresenter), "4, 6, 3, 1, fill, default");
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(STARTDAYS_ADAPTER);
        attachAdapter(AIRCRAFT_TYPE_ADAPTER);
    }
    
    /**
     * Create the aircraft class combo box.
     *
     * @param flInfosPresenter
     *            the presenter.
     * @return the combo box.
     */
    private JComboBox<String> createAircraftClassComboBox(final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final AircraftTypeAdapter adapter = (AircraftTypeAdapter) findAdapter(AIRCRAFT_TYPE_ADAPTER);
        final Set<String> aircraftClass = adapter.getAircraftClasses();
        
        final ValueModel selectedClass = flInfosPresenter.getModel(FlightInfosProperties.AIRCRAFT_CLASS);
        selectedClass.addValueChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (null != evt.getPropertyName()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Set aircraft class : " + evt.getNewValue());
                    }
                    final FlightInfosReader bean = flInfosPresenter.getBean();
                    final List<String> companies = adapter.getAircraftCompaniesByClass(evt.getNewValue().toString());
                    flInfosPresenter.setValue(FlightInfosProperties.COMPANIES, companies);
                    if (companies.contains(bean.getAircraftCie())) {
                        final String classCpie = bean.getAircraftClass() + "_" + bean.getAircraftCie();
                        final List<String> liveries = adapter.getAircraftLiveriesByClassCpie(classCpie);
                        flInfosPresenter.setValue(FlightInfosProperties.LIVERIES, liveries);
                    } else {
                        flInfosPresenter.setValue(FlightInfosProperties.LIVERIES, Collections.EMPTY_LIST);
                    }
                }
            }
        });
        final SelectionInList<?> selectionInList = new SelectionInList<>(aircraftClass.toArray());
        selectionInList.setSelectionHolder(selectedClass);
        final JComboBox<String> component = BasicComponentFactory.createComboBox(selectionInList);
        component.setPrototypeDisplayValue(PROTOTYPE_DISPLAY);
        
        return component;
    }
    
    /**
     * Create the aircraft company combo box.
     *
     * @param flInfosPresenter
     *            the flight info presenter.
     * @return the combo box.
     */
    private JComboBox<String> createAircraftCpieComboBox(final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final ValueModel infosModel = flInfosPresenter.getModel(FlightInfosProperties.COMPANIES);
        final ValueModel selectedCompany = flInfosPresenter.getModel(FlightInfosProperties.AIRCRAFT_CIE);
        final SelectionInList<String> selectionInList = new SelectionInList<String>(infosModel);
        selectionInList.setSelectionHolder(selectedCompany);
        final JComboBox<String> component = BasicComponentFactory.createComboBox(//
                selectionInList, new CommonComboBoxCellRenderer(PROTOTYPE_DISPLAY));
        component.setPrototypeDisplayValue(PROTOTYPE_DISPLAY);
        
        selectedCompany.addValueChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                final String newValue = (String) evt.getNewValue();
                if (newValue.isEmpty()) {
                    flInfosPresenter.setValue(FlightInfosProperties.LIVERIES, Collections.EMPTY_LIST);
                } else {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Set aircraft livery : " + evt.getNewValue());
                    }
                    final AircraftTypeAdapter adapter = (AircraftTypeAdapter) findAdapter(AIRCRAFT_TYPE_ADAPTER);
                    final AbstractValueModel aircraftClass = flInfosPresenter.getModel(FlightInfosProperties.AIRCRAFT_CLASS);
                    final String value = aircraftClass.getString() + "_" + evt.getNewValue().toString();
                    final List<String> liveries = adapter.getAircraftLiveriesByClassCpie(value);
                    flInfosPresenter.setValue(FlightInfosProperties.LIVERIES, liveries);
                    final AbstractValueModel aircraftLivery = flInfosPresenter.getModel(FlightInfosProperties.AIRCRAFT_LIVERY);
                    aircraftLivery.setValue(liveries.get(DEFAULT_ELEMENT));
                }
            }
        });
        
        return component;
    }
    
    /**
     * Create the aircraft livery combo box.
     *
     * @param fInfosPresenter
     *            the flight info presenter.
     * @param fpPresenter
     *            the flight plan presenter.
     * @return the combo box.
     */
    private JComboBox<String> createAircraftLiveryComboBox(final PresentationModel<?> fInfosPresenter,
            final PresentationModel<?> fpPresenter) {
        final ValueModel model = fInfosPresenter.getModel(FlightInfosProperties.LIVERIES);
        final ValueModel selectedLivery = fInfosPresenter.getModel(FlightInfosProperties.AIRCRAFT_LIVERY);
        
        selectedLivery.addValueChangeListener(new PropertyChangeListener() {
            
            
            /**
             *
             *
             * {@inheritDoc}.
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (!((String) evt.getNewValue()).isEmpty()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Set aircraft livery : " + evt.getNewValue());
                    }
                    final FlightInfosReader bean = (FlightInfosReader) fInfosPresenter.getBean();
                    fpPresenter.setBufferedValue(FlightPlanProperties.AIRCRAFT_CLASS, bean.getAircraftClass());
                    fpPresenter.setBufferedValue(FlightPlanProperties.AIRCRAFT_CIE, bean.getAircraftCie());
                    fpPresenter.setBufferedValue(FlightPlanProperties.AIRCRAFT_LIVERY, bean.getAircraftLivery());
                    
                }
            }
        });
        final SelectionInList<String> selectionInList = new SelectionInList<String>(model);
        selectionInList.setSelectionHolder(selectedLivery);
        final JComboBox<String> component = BasicComponentFactory.createComboBox(selectionInList);
        component.setPrototypeDisplayValue(PROTOTYPE_DISPLAY);
        
        return component;
    }
    
    /**
     * Create the call sign text field.
     *
     * @return the text field.
     */
    private JTextField createCallSignTextField() {
        final BufferedValueModel model = getPresenter(FP_PRESENTER).getBufferedModel(FlightPlanProperties.CALLSIGN);
        return BasicComponentFactory.createTextField(model);
    }
    
    /**
     * Refresh datas.
     *
     * @param aircraftType
     *            the aircraft type.
     * @param flInfosPresenter
     *            the presenter.
     */
    protected void refreshData(final String aircraftType, final PresentationModel<FlightInfosReader> flInfosPresenter) {
        final String aircraftClass = AircraftDecoder.getAircraftClass(aircraftType);
        final String aircraftCpie = AircraftDecoder.getAircraftCie(aircraftType);
        
        final FlightInfosWriter bean = (FlightInfosWriter) flInfosPresenter.getBean();
        
        bean.setAircraftClass(aircraftClass);
        bean.setAircraftCie(aircraftCpie);
        bean.setAircraftLivery(aircraftType);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Refresh Data with : " + aircraftClass + " - " + aircraftCpie + " - " + aircraftType);
        }
    }
}
