/* @(#)SteepointdPanel.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplanvisualization.panel;

import java.util.ArrayList;
import java.util.List;

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
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.visualelement.AbstractCommonPanel;
import com.airportflightplanner.flightplancreation.messages.FlightPlanCreationPanelMessages;
import com.airportflightplanner.flightplanvisualization.presenter.flightplan.FlightPlanVisualizationPresenter;
import com.jgoodies.binding.PresentationModel;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanListPanel extends AbstractCommonPanel {
    /**
     *
     */
    protected Signal           signal;

    /**
     *
     */
    private static final long  serialVersionUID        = -6354635338489926005L;
    /** */
    protected static final int FP_COLLECTION_PRESENTER = AbstractCommonPanel.FIRST_PRESENTER;

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
                ColumnSpec.decode(PREF_GROW), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        final TitledBorder panelBorder = new TitledBorder(FlightPlanCreationPanelMessages.FLIGHTSLIST_TITLE);
        setBorder(panelBorder);

        add(createFlightScrollPane(), "2, 2, 3, 1");

        // final SteerPointsPresenter presenter = (SteerPointsPresenter)
        // getPresenter(CURRENT_FP_PRESENTER);
        // final SteerPointsCollectionReader reader = presenter.getBean();
        // reader.addSteerPointsListModelListener(presenter.getListModel());

    }

    /**
     * @return the panel.
     *
     */
    private JScrollPane createFlightScrollPane() {
        final FlightPlanCollectionAdapter adapter = (FlightPlanCollectionAdapter) getAdapterByName(//
                FlightPlanCollectionAdapter.class.getSimpleName());
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
                FlightPlanReader flightPlan = null;
                if (event.getValueIsAdjusting()) {
                    final ListSelectionModel lsm = (ListSelectionModel) event.getSource();

                    if (!lsm.isSelectionEmpty()) {
                        final int minIndex = lsm.getMinSelectionIndex();
                        final int maxIndex = lsm.getMaxSelectionIndex();
                        for (int i = minIndex; i <= maxIndex; i++) {
                            if (lsm.isSelectedIndex(i)) {
                                flightPlan = adapter.getModel().getFlightPlanByIndex(i);
                            }
                        }
                    }
                    signal.fireSignal(flightPlan);
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
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSlotAction() {

        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                final PresentationModel<FlightPlanReader> fpPresenter = (PresentationModel<FlightPlanReader>) getPresenter(FP_COLLECTION_PRESENTER);
                fpPresenter.triggerFlush();
                fpPresenter.setBean(bean);
            }
        });
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void attachSignal() {
        signal = new Signal(TopicName.FLIGHTPLAN_TABLE_SELECTED);
        createSignal(TopicName.FLIGHTPLAN_TABLE_SELECTED, signal);
    }
}
