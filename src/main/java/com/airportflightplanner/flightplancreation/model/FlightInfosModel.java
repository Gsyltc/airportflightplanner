/*
 * @(#)FlightInfosModel.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.flightplancreation.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosProperties;
import com.airportflightplanner.flightplancreation.api.model.flightinfos.FlightInfosWriter;
import com.jgoodies.binding.beans.Model;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightInfosModel extends Model implements FlightInfosWriter {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(FlightInfosModel.class);

    /**
     *
     */
    private static final long serialVersionUID = 9068391147414760708L;
    /** */
    private String aircraftCie = "";
    /** */
    private String aircraftClass = "";
    /** */
    private String aircraftLivery = "";
    /** */
    private List<String> liveries = new ArrayList<String>();
    /** */
    private List<String> companies = new ArrayList<String>();

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftCie() {
        return aircraftCie;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftClass() {
        return aircraftClass;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public String getAircraftLivery() {
        return aircraftLivery;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<String> getLiveries() {
        return liveries;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<String> getCompanies() {
        return companies;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftCie(final String value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Set aricraft cpie : " + value);
        }
        final String oldValue = getAircraftCie();
        if (null != value && !value.equals(oldValue)) {
            aircraftCie = value;
            firePropertyChange(FlightInfosProperties.AIRCRAFT_CIE, oldValue, aircraftCie);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftClass(final String value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Set Aircraft class : " + value);
        }
        final String oldValue = getAircraftClass();
        if (null != value && !value.equals(oldValue)) {
            aircraftClass = value;
            firePropertyChange(FlightInfosProperties.AIRCRAFT_CLASS, oldValue, aircraftClass);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setAircraftLivery(final String value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Set Aircraft livery : " + value);
        }
        final String oldValue = getAircraftLivery();
        if (null != value && !value.equals(oldValue)) {
            aircraftLivery = value;
            firePropertyChange(FlightInfosProperties.AIRCRAFT_LIVERY, oldValue, aircraftLivery);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setLiveries(final List<String> value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Set Liveries : " + value);
        }
        final List<String> oldValue = getLiveries();
        if (!value.equals(oldValue)) {
            liveries = value;
            firePropertyChange(FlightInfosProperties.LIVERIES, oldValue, liveries);
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void setCompanies(final List<String> value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Set Companies : " + value);
        }
        final List<String> oldValue = getCompanies();
        if (!value.equals(oldValue)) {
            companies = value;
            firePropertyChange(FlightInfosProperties.COMPANIES, oldValue, companies);
        }
    }
}
