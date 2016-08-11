/*
 * @(#)CommandPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 31 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.signals.Signal;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;

/**
 * @author Goubaud Sylvain
 *
 */
public class CommandPanel extends AbstractCommandablePanel {
    
    
    /** the adapters. */
    private static final String FP_ADAPTER_NAME = FlightPlanModelAdapter.class.getSimpleName();
    
    /**
     *
     */
    private static final long serialVersionUID = 7075372771107433751L;
    /** */
    protected static final int FP_READER = 0;
    
    /**
     * @param fpPresenter
     */
    public CommandPanel(final PresentationModel<FlightPlanReader> fpPresenter) {
        super(fpPresenter);
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void build() {
        super.build();
        
        final FormLayout formLayout = new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("center:default:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, ///
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, });
        
        setLayout(formLayout);
        setBorder(new TitledBorder(null, FlightPlanCreationPanelMessages.FLIGHT_INFOS_LABEL));
        
        add(createValidateButton(), "2,2");
        add(createCancelButton(), "2,4");
    }
    
    /**
     * Create the cancel button.
     *
     * @return
     */
    private JButton createCancelButton() {
        final JButton button = new JButton("Cancel");
        button.addActionListener(new ActionListener() {
            
            
            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                final Signal signal = findSignal(TopicName.CANCELLATION_TOPIC);
                signal.fireSignal(ActionTypes.CANCEL);
            }
        });
        return button;
    }
    
    /**
     * Create the validation button.
     *
     * @return
     */
    private JButton createValidateButton() {
        final JButton button = new JButton("Validate");
        button.addActionListener(new ActionListener() {
            
            
            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void actionPerformed(final ActionEvent event) {
                final Signal signal = findSignal(TopicName.VALIDATION_TOPIC);
                signal.fireSignal(ActionTypes.VALIDATE);
            }
        });
        return button;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void createSignals() {
        super.createSignals();
        attachSignal(TopicName.VALIDATION_TOPIC);
        attachSignal(TopicName.CANCELLATION_TOPIC);
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(FP_ADAPTER_NAME);
    }
}
