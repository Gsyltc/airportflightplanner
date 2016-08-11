/*
 * @(#)FlightPlanListPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel; // NOPMD by sylva on 31/07/16 15:42

import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.airportflightplanner.adapters.api.modeladapters.FlightPlanCollectionAdapter;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ActionTypes;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.messages.FlightPlanVisualizationMessages;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.airportflightplanner.models.flightplans.api.bean.FlightPlanReader;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.signals.Signal;
import fr.gsyltc.framework.slotsignals.slots.Slot;
import fr.gsyltc.framework.visualelements.AbstractCommandablePanel;
import fr.gsyltc.framework.visualelements.types.LayoutSpecs;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanListPanel extends AbstractCommandablePanel {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -6354635338489926005L;
    
    /** */
    protected static final int FP_COLLECTION_PRESENTER = 0;
    /** */
    protected static final int CURRENT_FP_PRESENTER = 1;
    /** The current flight plan. */
    protected FlightPlanReader currentFlightPlan;
    
    /**
     * @param presenter
     *            the list presenter.
     */
    public FlightPlanListPanel(final FlightPlanVisualizationPresenter presenter) {
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
        
        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.FLIGHTSLIST_TITLE);
        setBorder(panelBorder);
        
        add(createFlightScrollPane(), "2, 2, 3, 1");
        
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void createAdapters() {
        super.createAdapters();
        attachAdapter(FlightPlanCollectionAdapter.class.getSimpleName());
        attachAdapter(FlightPlanModelAdapter.class.getSimpleName());
    }
    
    /**
     * @return the panel.
     *
     */
    private JScrollPane createFlightScrollPane() {
        final FlightPlanCollectionAdapter adapter = (FlightPlanCollectionAdapter) findAdapter(FlightPlanCollectionAdapter.class
                .getSimpleName());
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        final FlightPlanVisualizationPresenter presenter = (FlightPlanVisualizationPresenter) getPresenter(FP_COLLECTION_PRESENTER);
        final JTable table = new JTable(presenter.getTableAdapter());
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setFillsViewportHeight(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                final FlightPlanModelAdapter fpAdapter = (FlightPlanModelAdapter) findAdapter(FlightPlanModelAdapter.class
                        .getSimpleName());
                if (event.getValueIsAdjusting()) {
                    if (fpAdapter.isModificationToCommit()) {
                        // use repaint to avoid graphical problem with the
                        // selected row
                        table.repaint();
                        showConfirmDialog();
                    }
                    final ListSelectionModel lsm = (ListSelectionModel) event.getSource();
                    
                    if (!lsm.isSelectionEmpty()) {
                        final int minIndex = lsm.getMinSelectionIndex();
                        final int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) { // NOPMD by sylva on
                                                          // 31/07/16 15:42
                                
                                setCurrentFlightPlan(adapter.getModel().getFlightPlanByIndex(i));
                            }
                        }
                    }
                    final Signal signal = findSignal(TopicName.FP_TABLE_SELECTED_TOPIC);
                    signal.fireSignal(getCurrentFlightPlan());
                }
            }
            
            /**
             * Show the confirm dialog (Current flight plan is modified).
             */
            private void showConfirmDialog() {
                final JOptionPane confirmDialog = new JOptionPane(FlightPlanVisualizationMessages.CONFIRM_DIALOG_TEXT,
                        JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
                
                final JDialog dialog = confirmDialog.createDialog(null, FlightPlanVisualizationMessages.CONFIRM_DIALOG_TITLE);
                dialog.setVisible(true);
                final Object result = confirmDialog.getValue();
                final Signal signal = findSignal(TopicName.VALIDATION_TOPIC);
                if (result.equals(JOptionPane.YES_OPTION)) {
                    signal.fireSignal(ActionTypes.VALIDATE);
                } else {
                    signal.fireSignal(ActionTypes.CANCEL);
                }
            }
        });
        
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        final List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        
        sorter.setSortKeys(sortKeys);
        sorter.sort();
        
        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        return scrollPane;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public final void createSlots() {
        super.createSlots();
        
        final Slot slot = new Slot(TopicName.FP_TABLE_SELECTED_TOPIC, getClass().getSimpleName());
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            
            
            /**
             *
             */
            private static final long serialVersionUID = -6027127491253834166L;
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) getPresenter(
                        FP_COLLECTION_PRESENTER);
                fpPresenter.triggerFlush();
                fpPresenter.setBean(bean);
            }
        });
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public void createSignals() {
        super.createSignals();
        attachSignal(TopicName.FP_TABLE_SELECTED_TOPIC);
        attachSignal(TopicName.VALIDATION_TOPIC);
    }
    
    /**
     * @return the currentFlightPlan
     */
    protected FlightPlanReader getCurrentFlightPlan() {
        return currentFlightPlan;
    }
    
    /**
     *
     * @param newFp
     *            the currentFlightPlan to set
     */
    protected void setCurrentFlightPlan(final FlightPlanReader newFp) {
        currentFlightPlan = newFp;
    }
}
