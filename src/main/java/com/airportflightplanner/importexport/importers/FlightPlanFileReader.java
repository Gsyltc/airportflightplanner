/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.importexport.importers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.api.flightplancollection.flightplan.FligthPlanCollectionProperties;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanFileReader {
    /** The logger of this class. */
    private static final Log         LOGGER = LogFactory.getLog(FlightPlanFileReader.class);

    /**
    *
    */
    private FlightPlanModelAdapter   flightPlanModelAdapter;
    /** */
    private FlighPlanCollectionModel flighPlanCollectionModel;

    /**
     *
     */
    @PostConstruct
    public void init() {

        flighPlanCollectionModel.addPropertyChangeListener(FligthPlanCollectionProperties.CURRENT_AIRPORT, new PropertyChangeListener() {
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
        flighPlanCollectionModel.getListModel().clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY.resolve(currentAirport), "*.{txt}")) {
            for (Path path : stream) {
                FlighPlanModel newFlightPlan = new FlighPlanModel();

                // Attach listener for flight time
                newFlightPlan.addPropertyChangeListener(FligthPlanProperties.DURATION, new PropertyChangeListener() {
                    /**
                     *
                     * {@inheritDoc}
                     */
                    @Override
                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (null != newFlightPlan.getStartTime()) {
                            newFlightPlan.setEndTime(newFlightPlan.getStartTime().plus(newFlightPlan.getDuration()));
                        } else {
                            if (null != newFlightPlan.getEndTime()) {
                                newFlightPlan.setStartTime(newFlightPlan.getEndTime().plus(newFlightPlan.getDuration()));
                            }
                        }
                    }
                });

                try (InputStream in = Files.newInputStream(path); //
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("START")) {
                            FlightPlanInformationTypes informationsType = FlightPlanInformationTypes.valueOf(line);
                            switch (informationsType) {
                            case STARTTIME:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDSTARTTIME.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTAIRCRAFT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDAIRCRAFT.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDESTAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDESTAIRPORT.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDEPAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDEPAIRPORT.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case START_FLY_TO_COMPLETION:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.END_FLY_TO_COMPLETION.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case START_LANDING_LIGHT_ALT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.END_LANDING_LIGHT_ALT.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTALTERNATEAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDALTERNATEAIRPORT.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTARRIVETYPE:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDARRIVETYPE.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTCALLSIGN:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDCALLSIGN.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDAYS:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDAYS.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }

                                break;

                            case STARTDEPARTTYPE:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDEPARTTYPE.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTFLIGHTTYPE:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDFLIGHTTYPE.name())) {
                                    flightPlanModelAdapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTSTEERPOINTS:
                                List<String> steerpoints = new ArrayList<String>();
                                line = reader.readLine();
                                while (!line.equals(FlightPlanInformationTypes.ENDSTEERPOINTS.name())) {
                                    steerpoints.add(line);
                                    line = reader.readLine();
                                }
                                flightPlanModelAdapter.addSteerpoints(newFlightPlan, steerpoints);
                                break;

                            default:
                                break;
                            }
                        }
                    }
                }

                flighPlanCollectionModel.addFlightPlan(newFlightPlan);
            }
        } catch (IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }

    /**
     *
     * @param flightPlanModelAdapter
     */
    public void setFlightPlanModelAdapter(final FlightPlanModelAdapter flightPlanModelAdapter) {
        this.flightPlanModelAdapter = flightPlanModelAdapter;
    }

    /**
     *
     * @param flighPlanCollectionModel
     */
    public void setFlighPlanCollectionModel(final FlighPlanCollectionModel flighPlanCollectionModel) {
        this.flighPlanCollectionModel = flighPlanCollectionModel;

    }
}
