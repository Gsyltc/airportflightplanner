/* @(#)CreationRoutePanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.value.BufferedValueModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class CreationOptionsPanel extends AbstractCommonPanel {
    /*** */
    private static final long serialVersionUID = -2692513903084994308L;
    /** */
    private static final int  FP_PRESENTER     = AbstractCommonPanel.FIRST_PRESENTER;

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
    private JComboBox<String> createDepartureTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        // final ValueModel infosModel =
        // presenter.getModel(FlightPlanProperties.DEPARTURE_TYPE);
        // final SelectionInList<String> selectionInList = new
        // SelectionInList<String>(infosModel);
        // final JComboBox<String> component =
        // BasicComponentFactory.createComboBox(//
        // selectionInList, new CreationFlightInfosCompagnieCellRenderer());
        // component.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        // final FlightInfosModel bean = (FlightInfosModel) presenter.getBean();
        // bean.addPropertyChangeListener(FlightPlanProperties.FILENAME, new
        // PropertyChangeListener() {
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public void propertyChange(final PropertyChangeEvent evt) {
        // if (null == evt.getNewValue()) {
        // component.setSelectedIndex(NO_SELECTION);
        // } else {
        // component.setSelectedItem(evt.getNewValue());
        // }
        // }
        // });
        //
        return new JComboBox<>();
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JTextField createLandingLightAltitudeTb(final PresentationModel<FlightPlanReader> presenter) {
        final BufferedValueModel model = presenter.getBufferedModel(//
                FlightPlanProperties.LANDING_LIGHT_ALTITUDE);
        // model.addPropertyChangeListener(new PropertyChangeListener() {
        //
        // @Override
        // public void propertyChange(final PropertyChangeEvent evt) {
        //// return evt.getNewValue().toString();
        //
        // }
        // });
        // return BasicComponentFactory.createTextField(model);
        return new JTextField();
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JComboBox<String> createArrivalTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        // final ValueModel infosModel =
        // presenter.getModel(FlightPlanProperties.ARRIVAL_TYPE);
        // final SelectionInList<String> selectionInList = new
        // SelectionInList<String>(infosModel);
        // final JComboBox<String> component =
        // BasicComponentFactory.createComboBox(//
        // selectionInList, new CreationFlightInfosCompagnieCellRenderer());
        // component.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        // final FlightInfosModel bean = (FlightInfosModel) presenter.getBean();
        // bean.addPropertyChangeListener(FlightPlanProperties.FILENAME, new
        // PropertyChangeListener() {
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public void propertyChange(final PropertyChangeEvent evt) {
        // if (null == evt.getNewValue()) {
        // component.setSelectedIndex(NO_SELECTION);
        // } else {
        // component.setSelectedItem(evt.getNewValue());
        // }
        // }
        // });
        //
        // return component;
        return new JComboBox<>();
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JComboBox<String> createFlightTypeCb(final PresentationModel<FlightPlanReader> presenter) {
        // final ValueModel infosModel =
        // presenter.getModel(FlightPlanProperties.FLIGHT_TYPE);
        // final SelectionInList<String> selectionInList = new
        // SelectionInList<String>(infosModel);
        // final JComboBox<String> component =
        // BasicComponentFactory.createComboBox(//
        // selectionInList, new CreationFlightInfosCompagnieCellRenderer());
        // component.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        // final FlightInfosModel bean = (FlightInfosModel) presenter.getBean();
        // bean.addPropertyChangeListener(FlightPlanProperties.FILENAME, new
        // PropertyChangeListener() {
        // /**
        // *
        // * {@inheritDoc}
        // */
        // @Override
        // public void propertyChange(final PropertyChangeEvent evt) {
        // if (null == evt.getNewValue()) {
        // component.setSelectedIndex(NO_SELECTION);
        // } else {
        // component.setSelectedItem(evt.getNewValue());
        // }
        // }
        // });
        //
        // return component;
        return new JComboBox<>();
    }

    /**
     *
     * @param presenter
     * @return
     */
    private JCheckBox createFlyToCompletionCkb(final PresentationModel<FlightPlanReader> presenter) {
        // final ValueModel value =
        // presenter.getBufferedModel(FlightPlanProperties.FLIGHT_TO_COMPLETION);
        // return BasicComponentFactory.createCheckBox(value,
        // FlightPlanCreationPanelMessages.FLY_TO_COMPLETION_LABEL);
        return new JCheckBox();
    }
}
