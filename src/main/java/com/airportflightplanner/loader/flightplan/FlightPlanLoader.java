/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.loader.flightplan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.utils.properties.CommonProperties;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanLoader {
    /** The logger of this class. */
    private static final Log       LOGGER = LogFactory.getLog(FlightPlanLoader.class);

    /**
    *
    */
    @Autowired(required = true)
    private FlightPlanModelAdapter adapter;

    /**
     * @param fpcm
     * @param currentAirport
     *
     */
    public void loadFlightPlans(final FlighPlanCollectionModel fpcm, final String currentAirport) {
        fpcm.getListModel().clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY.resolve(currentAirport), "*.{txt}")) {
            for (Path path : stream) {

                FlighPlanModel newFlightPlan = new FlighPlanModel();
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
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTAIRCRAFT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDAIRCRAFT.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDESTAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDESTAIRPORT.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDEPAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDEPAIRPORT.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case START_FLY_TO_COMPLETION:
                                if (!line.equals(FlightPlanInformationTypes.END_FLY_TO_COMPLETION.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case START_LANDING_LIGHT_ALT:
                                if (!line.equals(FlightPlanInformationTypes.END_LANDING_LIGHT_ALT.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTALTERNATEAIRPORT:
                                if (!line.equals(FlightPlanInformationTypes.ENDALTERNATEAIRPORT.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTARRIVETYPE:
                                if (!line.equals(FlightPlanInformationTypes.ENDARRIVETYPE.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTCALLSIGN:
                                if (!line.equals(FlightPlanInformationTypes.ENDCALLSIGN.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTDAYS:
                                Set<String> startDays = new HashSet<String>();
                                line = reader.readLine();
                                while (!line.equals(FlightPlanInformationTypes.ENDDAYS.name())) {
                                    startDays.add(line);
                                    line = reader.readLine();
                                }
                                adapter.addStartDays(newFlightPlan, startDays);
                                break;

                            case STARTDEPARTTYPE:
                                if (!line.equals(FlightPlanInformationTypes.ENDDEPARTTYPE.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTFLIGHTTYPE:
                                if (!line.equals(FlightPlanInformationTypes.ENDFLIGHTTYPE.name())) {
                                    adapter.updateFlightPlan(newFlightPlan, informationsType, line);
                                }
                                break;

                            case STARTSTEERPOINTS:
                                List<String> steerpoints = new ArrayList<String>();
                                line = reader.readLine();
                                while (!line.equals(FlightPlanInformationTypes.ENDSTEERPOINTS.name())) {
                                    steerpoints.add(line);
                                    line = reader.readLine();
                                }
                                adapter.addSteerpoints(newFlightPlan, steerpoints);
                                break;

                            default:
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }
}
