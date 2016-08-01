/*
 * @(#)FlightPlanListPanel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 1 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplanvisualization.panel; // NOPMD by sylva on 31/07/16 15:42

import java.awt.Frame;
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

import com.airportflightplanner.common.api.adapter.FlightPlanCollectionAdapter;
import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
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
    /** The current flight plan. */
    protected FlightPlanReader currentFlightPlan;

    /**
     *
     */

    /**
     * @param presenter
     *
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
        table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void valueChanged(final ListSelectionEvent event) {
                final FlightPlanModelAdapter fpAdapter = (FlightPlanModelAdapter) findAdapter(FlightPlanModelAdapter.class
                        .getSimpleName());
                if (fpAdapter.hasModificationToCommit()) {
                    showConfirmDialog();
                }
                if (event.getValueIsAdjusting()) {
                    final ListSelectionModel lsm = (ListSelectionModel) event.getSource();

                    if (!lsm.isSelectionEmpty()) {
                        final int minIndex = lsm.getMinSelectionIndex();
                        final int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) { // NOPMD by sylva on
                                                          // 31/07/16 15:42
                                currentFlightPlan = adapter.getModel().getFlightPlanByIndex(i);
                            }
                        }
                    }
                    final Signal signal = findSignal(TopicName.FP_TABLE_SELECTED_TOPIC);
                    signal.fireSignal(currentFlightPlan);
                }
            }

            /**
             * Show the confirm dialog (Current flight plan is modified).
             */
            private void showConfirmDialog() {
                final JOptionPane confirmDialog = new JOptionPane("Do you want toto", JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.YES_NO_OPTION);

                final JDialog dialog = new JDialog(new Frame("Toto"), "Click a button", true);
                dialog.setContentPane(confirmDialog);
                dialog.pack();
                dialog.setVisible(true);

                final Signal signal = findSignal(TopicName.FP_MODIFIED_TOPIC);
                signal.fireSignal(false);
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
        attachSignal(TopicName.FP_MODIFIED_TOPIC);
        attachSignal(TopicName.FP_TABLE_SELECTED_TOPIC);
    }

}
