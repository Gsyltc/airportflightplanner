/*
 * @(#)FlightPlanFileReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 31 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.importexport.importers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanProperties;
import com.airportflightplanner.common.api.flightplan.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.common.models.flightplans.FlighPlanCollectionModel;
import com.airportflightplanner.common.models.flightplans.FlightPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanFileReader {
    
    
    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(FlightPlanFileReader.class);
    /**
     *
     */
    private FlightPlanModelAdapter flightPlanModelAdapter;
    
    /** */
    private FlighPlanCollectionModel flighPlanCollectionModel;

    /** Listener for update a flight plan. */
    public static final class FlightPlanPropertyChangeListener implements PropertyChangeListener {
        
        
        /** the flight plan to update. */
        private final transient FlightPlanModel flightPlan;

        /**
         * Creation an instance of the listener.
         *
         * @param newFlightPlan
         *            the flight plan to update.
         */
        protected FlightPlanPropertyChangeListener(final FlightPlanModel newFlightPlan) {
            flightPlan = newFlightPlan;
        }

        /**
         * {@inheritDoc}.
         */
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            if (null == flightPlan.getStartTime()) {
                if (null != flightPlan.getEndTime()) {
                    flightPlan.setStartTime(flightPlan.getEndTime().plus(flightPlan.getDuration()));
                }
            } else {
                flightPlan.setEndTime(flightPlan.getStartTime().plus(flightPlan.getDuration()));

            }

        }

    }
    
    /**
     * @return the flighPlanCollectionModel
     */
    public FlighPlanCollectionModel getFlighPlanCollectionModel() {
        return flighPlanCollectionModel;
    }

    /**
     * @return the flightPlanModelAdapter
     */
    public FlightPlanModelAdapter getFlightPlanModelAdapter() {
        return flightPlanModelAdapter;
    }

    /**
     *
     */
    @PostConstruct
    public void init() {
        flighPlanCollectionModel.addPropertyChangeListener(FlightPlanCollectionProperties.CURRENT_AIRPORT,
                new PropertyChangeListener() {
                    
                    
                    /**
                     *
                     * {@inheritDoc}
                     */
                    @Override
                    public void propertyChange(final PropertyChangeEvent evt) {
                        loadFlightPlans(evt.getNewValue().toString());
                    }
                });
    }

    /**
     * @param currentAirport
     *
     */
    protected void loadFlightPlans(final String currentAirport) {
        getFlighPlanCollectionModel().getFlightPlanListModel().clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY.resolve(currentAirport),
                "*.{txt}")) {
            for (final Path path : stream) {
                final Path fileName = path.getFileName();
                if (null != fileName) {
                    final FlightPlanModel newFlightPlan = new FlightPlanModel();
                    flightPlanModelAdapter.setModel(newFlightPlan);
                    newFlightPlan.setFileName(fileName.toString().replace("*.txt", ""));
                    // Attach listener for flight time
                    newFlightPlan.addPropertyChangeListener(FlightPlanProperties.DURATION, new FlightPlanPropertyChangeListener(
                            newFlightPlan));

                    try (InputStream inputStream = Files.newInputStream(path); //
                            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,
                                    StandardCharsets.UTF_8))) {
                        String line = reader.readLine();
                        while (line != null) {
                            if (line.startsWith("START")) {
                                final FlightPlanInformationTypes informationsType = FlightPlanInformationTypes.valueOf(line);
                                switch (informationsType) {
                                case STARTTIME:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDSTARTTIME.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTAIRCRAFT:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDAIRCRAFT.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTDESTAIRPORT:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDDESTAIRPORT.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTDEPAIRPORT:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDDEPAIRPORT.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case START_FLY_TO_COMPLETION:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.END_FLY_TO_COMPLETION.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case START_LANDING_LIGHT_ALT:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.END_LANDING_LIGHT_ALT.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTALTERNATEAIRPORT:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDALTERNATEAIRPORT.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTARRIVETYPE:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDARRIVETYPE.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTCALLSIGN:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDCALLSIGN.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTDAYS:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDDAYS.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }

                                    break;
                                
                                case STARTDEPARTTYPE:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDDEPARTTYPE.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTFLIGHTTYPE:
                                    line = reader.readLine();
                                    if (!FlightPlanInformationTypes.ENDFLIGHTTYPE.name().equals(line)) {
                                        getFlightPlanModelAdapter().updateFlightPlan(informationsType, line);
                                    }
                                    break;
                                
                                case STARTSTEERPOINTS:
                                    final List<String> steerpoints = new ArrayList<String>();
                                    line = reader.readLine();
                                    while (!FlightPlanInformationTypes.ENDSTEERPOINTS.name().equals(line)) {
                                        steerpoints.add(line);
                                        line = reader.readLine();
                                    }
                                    getFlightPlanModelAdapter().addSteerpoints(steerpoints);
                                    break;
                                
                                default:
                                    break;
                                }
                                line = reader.readLine();
                            } else {
                                line = reader.readLine();
                            }
                        }
                    }
                    flighPlanCollectionModel.addFlightPlan(newFlightPlan);
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     * Set the flight plan collection model.
     *
     * @param newFpCollectionModel
     */
    public void setFlighPlanCollectionModel(final FlighPlanCollectionModel newFpCollectionModel) {
        flighPlanCollectionModel = newFpCollectionModel;
    }

    /**
     *
     * @param newFlighPlanModelAdapter
     */
    public void setFlightPlanModelAdapter(final FlightPlanModelAdapter newFlighPlanModelAdapter) {
        flightPlanModelAdapter = newFlighPlanModelAdapter;
    }
}
