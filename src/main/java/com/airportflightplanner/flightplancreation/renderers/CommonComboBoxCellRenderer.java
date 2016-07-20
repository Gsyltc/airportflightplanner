/* @(#)CreationFlightInfosCompagnieCellRender.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.utils.internationalization.Internationalizer;

/**
 * @author DCNS
 * @param <E>
 *
 */
public class CommonComboBoxCellRenderer<E> implements ListCellRenderer<E> {
    /** */
    private transient final DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    /** */
    private transient final String                  prototypeDisplay;

    /**
     *
     * @param prototypeDisplay
     */
    public CommonComboBoxCellRenderer(final String prototypeDisplay) {
        this.prototypeDisplay = prototypeDisplay;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Component getListCellRendererComponent(final JList list, final E value, //
            final int index, final boolean isSelected, final boolean cellHasFocus) {
        String result = "";

        if (value instanceof DepartureType) {
            result = DepartureType.getI18NName(((DepartureType) value).ordinal());
        } else if (value instanceof ArrivalType) {
            result = ArrivalType.getI18NName(((ArrivalType) value).ordinal());
        } else if (value instanceof FlightType) {
            result = FlightType.getI18NName(((FlightType) value).ordinal());
        } else {
            result = Internationalizer.getI18String((String) value);
        }

        final JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(//
                list, value, index, isSelected, cellHasFocus);
        if (null != value && !prototypeDisplay.equals(value)) {
            renderer.setText(result);
        }

        return renderer;
    }

}
