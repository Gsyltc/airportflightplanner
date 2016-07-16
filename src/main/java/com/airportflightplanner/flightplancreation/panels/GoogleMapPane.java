/*
 * @(#)GoogleMapPane.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.airportflightplanner.common.types.MapType;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.flightplancreation.api.model.googlemap.GoogleMapModelProperties;
import com.airportflightplanner.flightplancreation.model.GoogleMapModel;
import com.airportflightplanner.flightplancreation.processor.GoogleMapProcessor;
import com.jgoodies.binding.PresentationModel;

/**
 * @author DCNS
 *
 */
public class GoogleMapPane extends JEditorPane {
    /**
     *
     */
    private static final long                                 serialVersionUID = -3128841669458527858L;
    /** */
    private transient String                                  zoomFactor       = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_ZOOM_FACTOR);
    /** */
    private final String                            apiKey           = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_KEY);
    /** */
    private transient MapType                                 roadmap          =                                                                           //
            MapType.valueOf(CommonProperties.getPropertyValue(CommonProperties.GOOGLE_MAPTYPE));
    /** */
    private transient final String                            polylineColor    = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_COLOR);
    /** */
    private transient final String                            polylineWeigth   = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_WIDTH);
    /** */
    private static final int                                  DEFAULT_SIZE     = 400;
    /** */
    private transient int                                     mapWidth         = DEFAULT_SIZE;
    /** */
    private transient int                                     mapHeight        = DEFAULT_SIZE;
    /** */
    private transient final PresentationModel<GoogleMapModel> googleMapModel;
    /** */
    private transient final HTMLEditorKit                     kit              = new HTMLEditorKit();
    /** */
    private transient final HTMLDocument                      htmlDoc          = (HTMLDocument) kit.createDefaultDocument();
    /** */
    private static final int                                  INIT_CAPACITY    = 250;

    /**
     * Constructeur: initialisation du EditorKit
     *
     * @param googleMapModel
     */
    public GoogleMapPane(final PresentationModel<GoogleMapModel> googleMapModel) {
        super();
        this.googleMapModel = googleMapModel;
        googleMapModel.addBeanPropertyChangeListener(GoogleMapModelProperties.POLYLINE, new PropertyChangeListener() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void propertyChange(final PropertyChangeEvent evt) {
                updateMap();
            }
        });

        build();
    }

    /**
     *
     */
    private void build() {
        setEditable(false);
        setContentType("text/html");
        setEditorKit(kit);
        setDocument(htmlDoc);
        final StringBuilder html = new StringBuilder(INIT_CAPACITY);
        html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head></head><body><img width=") //
        .append(mapWidth).append(" height=") //
        .append(mapHeight).append(" src='file:") //
        .append(getClass().getClassLoader().getResource("images/default.jpg").getPath()) //
        .append("'></body></html>");
        setText(html.toString());
    }

    /**
     * Fixer le zoom.
     *
     * @param zoom
     *            valeur de 0 a 21
     */
    public void setZoom(final int zoom) {
        zoomFactor = String.valueOf(zoom);
    }

    /**
     * Fixer le type de vue.
     *
     * @param roadMap
     *            the roadMap.
     */
    public void setRoadmap(final MapType roadMap) {
        roadmap = roadMap;
    }

    /**
     *
     */
    protected void updateMap() {
        if (apiKey.isEmpty()) {
            throw new IllegalArgumentException("Developper API Key not set !!!!");
        }
        final StringBuffer uri = new StringBuffer().append("http://maps.googleapis.com/maps/api/staticmap?&path=color:") //
                .append(polylineColor).append("|weight:") //
                .append(polylineWeigth).append("|geodesic:true") //
                .append(GoogleMapProcessor.getEncodedRoad(googleMapModel.getBean())) //
                .append("&size=").append(mapWidth).append('x').append(mapHeight) //
                .append("&maptype=").append(roadmap.name().toLowerCase()) //
                .append("&sensor=false") //
                .append("&zoom").append(zoomFactor) //
                .append("&key=").append(apiKey); //

        final StringBuilder html = new StringBuilder(INIT_CAPACITY);
        html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head></head><body><img width=") //
        .append(mapWidth).append(" height=") //
        .append(mapHeight).append(" src='") //
        .append(uri.toString()).append("'></body></html>");
        setText(html.toString());
    }

    /**
     *
     * @param bounds
     */
    public void setDimension(final Rectangle bounds) {
        mapWidth = (int) bounds.getWidth();
        mapHeight = (int) bounds.getHeight();
    }
}
