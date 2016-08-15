/*
 * @(#)SteerPointPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 16 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel;

import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsPresenter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class SteerPointPanel extends AbstractCommandablePanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -6354635338489926005L;
    /** */
    protected static final int STEERPOINT_PRESENTER = 0;
    /** The table. */
    private JTable table;
    
    /**
     *
     */
    
    /**
     * @param presenter
     *
     */
    public SteerPointPanel(final SteerPointsPresenter presenter) {
        super(presenter);
    }
    
    /**
     *
     */
    @Override
    public void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode(LayoutSpecs.PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));
        
        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.STEERPOINTS_TITLE);
        setBorder(panelBorder);
        
        add(createSteerPointsPanel(), "2, 2, 3, 1");
    }
    
    /**
     *
     * @return
     */
    private JScrollPane createSteerPointsPanel() {
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(STEERPOINT_PRESENTER);
        table = new JTable(presenter.getTableAdapter());
        table.setColumnSelectionAllowed(true);
        table.setDefaultRenderer(String.class, centerRenderer);
        
        table.setFillsViewportHeight(true);
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setViewportView(table);
        return scrollPane;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public final void createSlots() {
        super.createSlots();
        final Slot validationSlot = new Slot(TopicName.VALIDATION_TOPIC, getClass().getSimpleName());
        validationSlot.setSlotAction(new SlotAction<ActionTypes>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = -6027127491253834166L;
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final ActionTypes action) {
                final SteerPointsPresenter presenter = (SteerPointsPresenter) getPresenter(STEERPOINT_PRESENTER);
                presenter.getTableAdapter().fireTableDataChanged();
            }
        });
    }
    
    /**
     * @return the table
     */
    private JTable getTable() {
        return table;
    }
}
