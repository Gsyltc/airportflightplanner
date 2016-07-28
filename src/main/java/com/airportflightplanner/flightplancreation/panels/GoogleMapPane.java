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
    private static final long serialVersionUID = -3128841669458527858L;
    /** */
    private static final int DEFAULT_SIZE = 400;
    /** */
    private static final int INIT_CAPACITY = 250;
    /** */
    private String zoomFactor = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_ZOOM_FACTOR);
    /** */
    private final transient String apiKey = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_KEY);
    /** */
    private MapType roadmap = //
            MapType.valueOf(CommonProperties.getPropertyValue(CommonProperties.GOOGLE_MAPTYPE));
    /** */
    private final transient String polylineColor = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_COLOR);
    /** */
    private final transient String polylineWeigth = CommonProperties.getPropertyValue(CommonProperties.GOOGLE_POLYLINE_WIDTH);
    /** */
    private transient int mapWidth = DEFAULT_SIZE;
    /** */
    private transient int mapHeight = DEFAULT_SIZE;
    /** */
    private final transient PresentationModel<GoogleMapModel> googleMapModel;
    /** */
    private final transient HTMLEditorKit kit = new HTMLEditorKit();
    /** */
    private final transient HTMLDocument htmlDoc = (HTMLDocument) this.kit.createDefaultDocument();

    /**
     * Constructeur: initialisation du EditorKit.
     *
     * @param newGoogleMapModel
     */
    public GoogleMapPane(final PresentationModel<GoogleMapModel> newGoogleMapModel) {
        super();
        this.googleMapModel = newGoogleMapModel;
        newGoogleMapModel.addBeanPropertyChangeListener(GoogleMapModelProperties.POLYLINE, new PropertyChangeListener() {
            
            
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
     * @return the roadmap
     */
    public MapType getRoadmap() {
        return this.roadmap;
    }

    /**
     * @return the zoomFactor
     */
    public String getZoomFactor() {
        return this.zoomFactor;
    }

    /**
     *
     * @param bounds
     */
    public void setDimension(final Rectangle bounds) {
        this.mapWidth = (int) bounds.getWidth();
        this.mapHeight = (int) bounds.getHeight();
    }

    /**
     * Fixer le type de vue.
     *
     * @param roadMap
     *            the roadMap.
     */
    public void setRoadmap(final MapType roadMap) {
        this.roadmap = roadMap;
    }

    /**
     * Fixer le zoom.
     *
     * @param zoom
     *            valeur de 0 a 21
     */
    public void setZoomFactor(final int zoom) {
        this.zoomFactor = String.valueOf(zoom);
    }

    /**
     *
     */
    private void build() {
        setEditable(false);
        setContentType("text/html");
        setEditorKit(this.kit);
        setDocument(this.htmlDoc);
        final StringBuilder html = new StringBuilder(INIT_CAPACITY);
        html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head></head><body><img width=") //
                .append(this.mapWidth).append(" height=") //
                .append(this.mapHeight).append(" src='file:") //
                .append(getClass().getClassLoader().getResource("images/default.jpg").getPath()) //
                .append("'></body></html>");
        setText(html.toString());
    }

    /**
     *
     */
    protected void updateMap() {
        if (this.apiKey.isEmpty()) {
            throw new IllegalArgumentException("Developper API Key not set !!!!");
        }
        final StringBuffer uri = new StringBuffer().append("http://maps.googleapis.com/maps/api/staticmap?&path=color:") //
                .append(this.polylineColor).append("|weight:") //
                .append(this.polylineWeigth).append("|geodesic:true") //
                .append(GoogleMapProcessor.getEncodedRoad(this.googleMapModel.getBean())) //
                .append("&size=").append(this.mapWidth).append('x').append(this.mapHeight) //
                .append("&maptype=").append(this.roadmap.name().toLowerCase()) //
                .append("&sensor=false") //
                .append("&zoom").append(this.zoomFactor) //
                .append("&key=").append(this.apiKey); //

        final StringBuilder html = new StringBuilder(INIT_CAPACITY);
        html.append("<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'><html><head></head><body><img width=") //
                .append(this.mapWidth).append(" height=") //
                .append(this.mapHeight).append(" src='") //
                .append(uri.toString()).append("'></body></html>");
        setText(html.toString());
    }
}
