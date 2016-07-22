/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import javax.measure.unit.NonSI;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.flightplans.FlightPlanModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplancreation.renderers.CommonComboBoxCellRenderer;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.binding.value.ConverterFactory;
import com.jgoodies.binding.value.ValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationOptionsPanel extends AbstractCommonPanel {
    /** The logger of this class. */
    protected static final Log  LOGGER            = LogFactory.getLog(CreationOptionsPanel.class);

    /*** */
    private static final long   serialVersionUID  = -2692513903084994308L;
    /** */
    private static final int    FP_PRESENTER      = AbstractCommonPanel.FIRST_PRESENTER;
    /** */
    private static final String PROTOTYPE_DISPLAY = "XXXXXXXXXXXXX";

    /**
     * @param model
     *
     */
    public CreationOptionsPanel(final PresentationModel<FlightPlanReader> model) {
        super(model);
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
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, });

        setLayout(formLayout);
        formLayout.setRowGroups(new int[][] { new int[] { 4, 2 } });
        formLayout.setColumnGroups(new int[][] { new int[] { 2, 6 }, new int[] { 4, 8 } });

        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.OPTIONS_LABEL));

        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        add(new JLabel(FlightPlanCreationPanelMessages.DEPATURETYPE_LABEL), "2,2");
        add(createDepartureTypeCb(presenter), "4, 2");

        add(new JLabel(FlightPlanCreationPanelMessages.ARRIVALTYPE_LABEL), "2,4");
        add(createArrivalTypeCb(presenter), "4, 4");

        add(new JLabel(FlightPlanCreationPanelMessages.FLIGHTTYPE_LABEL), "6,2");
        add(createFlightTypeCb(presenter), "8, 2");

        add(new JLabel(FlightPlanCreationPanelMessages.LANDINGLIGHT_LABEL), "6,4");
        add(createLandingLightAltitudeTb(presenter), "8, 4");

        add(new JLabel(FlightPlanCreationPanelMessages.FLY_TO_COMPLETION_LABEL), "2,6");
        add(createFlyToCompletionCkb(presenter), "4, 6");

    }

    /**
     *
     * @param presenter
     * @return
     */
    private JComboBox<DepartureType> createDepartureTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        final ValueModel infosModel = presenter.getBufferedModel(FlightPlanProperties.DEPARTURE_TYPE);
        final SelectionInList<DepartureType> selectionInList = new SelectionInList<DepartureType>(DepartureType.values(), infosModel);
        final JComboBox<DepartureType> component = BasicComponentFactory.createComboBox(//
                selectionInList, new CommonComboBoxCellRenderer(PROTOTYPE_DISPLAY));
        final FlightPlanModel bean = (FlightPlanModel) presenter.getBean();
        bean.addPropertyChangeListener(FlightPlanProperties.DEPARTURE_TYPE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (null == evt.getNewValue()) {
                    component.setSelectedIndex(NO_SELECTION);
                } else {
                    component.setSelectedItem(evt.getNewValue());
                }
            }
        });
        return component;
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JTextField createLandingLightAltitudeTb(final PresentationModel<FlightPlanReader> presenter) {
        final BufferedValueModel model = presenter.getBufferedModel(//
                FlightPlanProperties.LANDING_LIGHT_ALTITUDE);

        final ValueModel value = ConverterFactory.createStringConverter(model, new Format() {
            /**
             *
             */
            private static final long serialVersionUID = 5858092520252522599L;

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public Object parseObject(final String source, final ParsePosition pos) {
                Double result = 0.0;
                if (source.contains("m")) {
                    // source in meters
                    result = Double.parseDouble(source.replace("m", "").replace(" ", "")) * 3.28084;
                } else {
                    result = Double.parseDouble(source.replace("ft", "").replace(" ", ""));
                }
                return Altitude.valueOf(result, NonSI.FOOT);
            }

            /**
             *
             * {@inheritDoc}
             */
            @Override
            public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
                long result = 0;
                if (obj instanceof Altitude) {
                    final Altitude altitude = (Altitude) obj;
                    result = altitude.longValue(NonSI.FOOT);
                }
                return new StringBuffer(String.valueOf(result)).append(" ft");
            }
        });

        return BasicComponentFactory.createTextField(value);
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JComboBox<ArrivalType> createArrivalTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        final ValueModel infosModel = presenter.getBufferedModel(FlightPlanProperties.ARRIVAL_TYPE);
        final SelectionInList<ArrivalType> selectionInList = new SelectionInList<ArrivalType>(ArrivalType.values(), infosModel);
        final JComboBox<ArrivalType> component = BasicComponentFactory.createComboBox(//
                selectionInList, new CommonComboBoxCellRenderer(PROTOTYPE_DISPLAY));
        final FlightPlanModel bean = (FlightPlanModel) presenter.getBean();
        bean.addPropertyChangeListener(FlightPlanProperties.ARRIVAL_TYPE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (null == evt.getNewValue()) {
                    component.setSelectedIndex(NO_SELECTION);
                } else {
                    component.setSelectedItem(evt.getNewValue());
                }
            }
        });

        return component;
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JComboBox<FlightType> createFlightTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        final ValueModel infosModel = presenter.getBufferedModel(FlightPlanProperties.FLIGHT_TYPE);
        final SelectionInList<FlightType> selectionInList = new SelectionInList<FlightType>(FlightType.values(), infosModel);
        final JComboBox<FlightType> component = BasicComponentFactory.createComboBox(//
                selectionInList, new CommonComboBoxCellRenderer(PROTOTYPE_DISPLAY));
        final FlightPlanModel bean = (FlightPlanModel) presenter.getBean();
        bean.addPropertyChangeListener(FlightPlanProperties.FLIGHT_TYPE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                if (null == evt.getNewValue()) {
                    component.setSelectedIndex(NO_SELECTION);
                } else {
                    component.setSelectedItem(evt.getNewValue());
                }
            }
        });

        return component;
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JCheckBox createFlyToCompletionCkb(final PresentationModel<FlightPlanReader> presenter) {
        final ValueModel value = presenter.getBufferedModel(FlightPlanProperties.FLIGHT_TO_COMPLETION);
        return BasicComponentFactory.createCheckBox(value, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        super.attachSlotAction();
        final SelectionSlot<ActionTypes> validationSlot = new SelectionSlot<ActionTypes>(TopicName.VALIDATION_TOPIC, this);
        FlightPlanModelAdapter adapter = (FlightPlanModelAdapter) getAdapterByName(FlightPlanModelAdapter.class.getSimpleName());
        final PresentationModel<FlightPlanReader> presenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_PRESENTER);
        validationSlot.setSlotAction(new SlotAction<ActionTypes>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final ActionTypes action) {
                FlightPlanReader bean = presenter.getBean();
                switch (action) {
                case VALIDATE:
                    System.out.println("Commit");
                    presenter.triggerCommit();
                    presenter.triggerFlush();
                    break;

                case CANCEL:
                    System.out.println("Flush");
                    presenter.triggerFlush();
                    break;

                case REFRESH:
                    System.out.println("refresh");
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("");
                    }
                    break;
                default:
                    break;
                }
            }
        });
    }
}
