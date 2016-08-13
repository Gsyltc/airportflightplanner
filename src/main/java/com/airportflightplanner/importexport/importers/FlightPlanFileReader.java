/*
 * @(#)FlightPlanFileReader.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 14 août 2016.
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.airportflightplanner.adapters.api.SteerPointsConvertAdapter;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanCollectionModelAdapter;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.models.flightplans.FlighPlanCollectionModel;
import com.airportflightplanner.models.flightplans.FlightPlanModel;
import com.airportflightplanner.models.flightplans.api.collection.FlightPlanCollectionProperties;
import com.airportflightplanner.models.steerpoints.SteerPointModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanFileReader {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(FlightPlanFileReader.class);

    /**
     *
     */
    private FlightPlanModelAdapter flightPlanModelAdapter;

    /** */
    private FlighPlanCollectionModel flighPlanCollectionModel;
    /** */
    private FlightPlanCollectionModelAdapter flightPlanCollectionAdapter;
    /** */
    private SteerPointsConvertAdapter steerPointsConvertAdapter;

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

        flightPlanCollectionAdapter.addListener(flighPlanCollectionModel);
    }

    /**
     * @param currentAirport
     *
     */
    protected void loadFlightPlans(final String currentAirport) { // NOPMD by
                                                                  // sylva on
                                                                  // 06/08/16
                                                                  // 16:23
        flightPlanCollectionAdapter.resetFlightPlans();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY.resolve(currentAirport),
                "*.{txt}")) {
            for (final Path path : stream) {
                final Path fileName = path.getFileName();
                if (null != fileName) {
                    final FlightPlanModel newFlightPlan = new FlightPlanModel();
                    flightPlanModelAdapter.setModel(newFlightPlan);
                    newFlightPlan.setFileName(fileName.toString().replace("*.txt", ""));
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
                                    final List<SteerPointReader> steerpoints = new ArrayList<SteerPointReader>();
                                    line = reader.readLine();
                                    while (!FlightPlanInformationTypes.ENDSTEERPOINTS.name().equals(line)) {
                                        final SteerPointModel steerPoint = steerPointsConvertAdapter.convertSteerPoint(line);
                                        steerpoints.add(steerPoint);
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
                    flightPlanCollectionAdapter.addFlightPlan(newFlightPlan);
                }
            }
        } catch (final IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     * Set the flight plan collection model.
     *
     * @param newFpCollectionReader
     */
    public void setFlighPlanCollectionReader(final FlighPlanCollectionModel newFpCollectionReader) {
        flighPlanCollectionModel = newFpCollectionReader;
    }

    /**
     *
     * @param newFPlanCollectionAdapter
     */
    public void setFlightPlanCollectionAdapter(final FlightPlanCollectionModelAdapter newFPlanCollectionAdapter) {
        flightPlanCollectionAdapter = newFPlanCollectionAdapter;
    }

    /**
     *
     * @param newFlighPlanModelAdapter
     */
    public void setFlightPlanModelAdapter(final FlightPlanModelAdapter newFlighPlanModelAdapter) {
        flightPlanModelAdapter = newFlighPlanModelAdapter;
    }

    /**
     *
     * @param newAdapter
     */
    public void setSteerPointsConvertAdapter(final SteerPointsConvertAdapter newAdapter) {
        steerPointsConvertAdapter = newAdapter;
    }
}
