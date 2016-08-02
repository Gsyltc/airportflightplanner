/*
 * @(#)CommonComboBoxCellRenderer.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 31 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
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
    private final transient DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    /** */
    private final transient String prototypeDisplay;
    
    /**
     *
     * @param pDisplay
     */
    public CommonComboBoxCellRenderer(final String pDisplay) {
        this.prototypeDisplay = pDisplay;
    }
    
    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Component getListCellRendererComponent(final JList<? extends E> list, final E value, //
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
        
        final JLabel renderer = (JLabel) this.defaultRenderer.getListCellRendererComponent(//
                list, value, index, isSelected, cellHasFocus);
        if (null != value && !this.prototypeDisplay.equals(value)) {
            renderer.setText(result);
        }
        
        return renderer;
    }
    
}
