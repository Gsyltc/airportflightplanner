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
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;

import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.steerpoints.SteerPointReader;
import com.airportflightplanner.common.model.SteerPointsCollectionModel;
import com.airportflightplanner.common.slotsignal.Signal;
import com.airportflightplanner.common.slotsignal.Slot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.visualelement.CommonPanel;
import com.airportflightplanner.flightplanvisualization.presenter.steerpoints.SteerPointsPresenter;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

/**
 * @author DCNS
 *
 */
public class SteePointdPanel extends CommonPanel {
    /**
    *
    */
    @Autowired(required = true)
    protected final SteerPointsCollectionModel steerPointsCollectionModel = new SteerPointsCollectionModel();
    /**
     * <
     */
    private final SteerPointsPresenter         presenter;
    /** */
    private JTable                             table;
    /** */
    protected Signal                           signal;

    /**
    *
    */
    private static final long                  serialVersionUID           = -6354635338489926005L;

    /**
    *
    */

    /**
     * @param steerPointsCollectionModel
     *
     */
    public SteePointdPanel() {
        presenter = new SteerPointsPresenter(steerPointsCollectionModel);
        steerPointsCollectionModel.addSteerPointsListModelListener(presenter.getListModel());
        build();
    }

    /**
    *
    */
    @Override
    protected void build() {
        super.build();
        setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("3dlu:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, //
                ColumnSpec.decode("pref:grow"), //
                FormSpecs.RELATED_GAP_COLSPEC, }, //
                new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, //
                        FormSpecs.PREF_ROWSPEC, //
                        FormSpecs.RELATED_GAP_ROWSPEC, }));

        add(createSteerPointsPanel(), "2, 6, 3, 1, fill, top");

    }

    /**
     * @return
     *
     */
    private JScrollPane createSteerPointsPanel() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table = new JTable(presenter.getTableAdapter());
        table.setDefaultRenderer(String.class, centerRenderer);
        table.setFillsViewportHeight(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));

        sorter.setSortKeys(sortKeys);
        sorter.sort();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        return scrollPane;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        Slot<FlightPlanReader> slot = new Slot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader flightPlanReader) {
                steerPointsCollectionModel.getListModel().clear();
                if (null != flightPlanReader) {
                    List<SteerPointReader> steerPoints = GeographicUtils.getSteerPoints(flightPlanReader.getSteerPoints());
                    steerPointsCollectionModel.addSteerPoints(steerPoints);
                }
            }
        });
    }
}
