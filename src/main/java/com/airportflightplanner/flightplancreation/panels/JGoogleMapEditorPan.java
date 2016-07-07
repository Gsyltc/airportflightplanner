/* @(#)GoogleEditorPane.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.flightplancreation.panels;

import java.awt.Rectangle;

import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.airportflightplanner.common.types.MapType;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author DCNS
 *
 */
public class JGoogleMapEditorPan extends JEditorPane {
	/**
	 *
	 */
	private static final long serialVersionUID = -3128841669458527858L;
	private String zoomFactor = CommonProperties.GOOGLE_ZOOM_FACTOR;
	private final String apiKey = CommonProperties.GOOGLE_KEY;
	private MapType roadmap=MapType.valueOf(CommonProperties.GOOGLE_MAPTYPE);
	private int mapWidth;
	private double mapHeight;

	/**
	 * Constructeur: initialisation du EditorKit
	 */
	public JGoogleMapEditorPan() {
		HTMLEditorKit kit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) kit.createDefaultDocument();
		this.setEditable(false);
		this.setContentType("text/html");
		this.setEditorKit(kit);
		this.setDocument(htmlDoc);
	}

	/**
	 * Fixer le zoom
	 *
	 * @param zoom
	 *            valeur de 0 à 21
	 */
	public void setZoom(final int zoom) {
		this.zoomFactor = String.valueOf(zoom);
	}

	/**
	 * Fixer le type de vue
	 *
	 * @param roadMap
	 */
	public void setRoadmap(final MapType roadMap) {
		this.roadmap = roadMap;
	}

	public void createPolyline(final String string) {
		string.substring(0, string.length() - 2);
		if (this.apiKey.isEmpty()) {
			// throw new Exception("Developper API Key not set !!!!");
		}

		String url = "http://maps.googleapis.com/maps/api/staticmap?";
		url += "&path=color:0xff00ff|weight:5" + string;
		url += "&size=" + 400 + "x" + 400;
		url += "&maptype=" + this.roadmap.name().toLowerCase();
		// url += "&markers=color:blue" + "45.9215,8.1254";
		url += "&sensor=false";
		url += "&key=" + this.apiKey;

		String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
		html += "<html><head></head><body>";
		html += "<img src='" + url + "'>";
		html += "</body></html>";
		this.setText(html);
	}

	/**
	 * Afficher la carte d'après des coordonnées GPS
	 *
	 * @param latitude
	 * @param longitude
	 * @param width
	 * @param height
	 * @throws Exception
	 *             erreur si la APIKey est non renseignée
	 */
	public void showCoordinate(final String latitude, final String longitude, final int width, final int height)
			throws Exception {
		this.setMap(latitude, longitude, width, height);
	}

	/**
	 * Afficher la carte d'après une ville et son pays
	 *
	 * @param city
	 * @param country
	 * @param width
	 * @param height
	 * @throws Exception
	 *             erreur si la APIKey est non renseignée
	 */
	public void showLocation(final String city, final String country, final int width, final int height)
			throws Exception {
		this.setMap(city, country, width, height);
	}

	/**
	 * Assembler l'url et Générer le code HTML
	 *
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @throws Exception
	 */
	private void setMap(final String x, final String y, final Integer width, final Integer height) throws Exception {
		if (this.apiKey.isEmpty()) {
			throw new Exception("Developper API Key not set !!!!");
		}

		String url = "http://maps.googleapis.com/maps/api/staticmap?";
		url += "center=" + x + "," + y;
		url += "&zoom=" + this.zoomFactor;
		url += "&size=" + this.mapWidth + "x" + this.mapHeight;
		url += "&maptype=" + this.roadmap;
		url += "&markers=color:blue" + x + "," + y;
		url += "&sensor=false";
		url += "&key=" + this.apiKey;

		String html = "<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>";
		html += "<html><head></head><body>";
		html += "<img src='" + url + "'>";
		html += "</body></html>";
		this.setText(html);
	}

	/**
	 *
	 * @param bounds
	 */
	public void setDimension(final Rectangle bounds) {
		this.mapWidth = (int) bounds.getWidth();
		this.mapHeight = bounds.getHeight();
	}
}
