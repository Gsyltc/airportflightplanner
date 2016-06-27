/* @(#)FlightPlanLoader.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.loader.flightplan;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Period;

import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
import com.airportflightplanner.common.model.FlighPlanCollectionModel;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.utils.internationalization.Internationalizer;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.flightplanprocessor.GeographicProcessor;
import com.airportflightplanner.flightplanprocessor.TimeProcessor;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanLoader {
    /** The logger of this class. */
    private static final Log LOGGER          = LogFactory.getLog(FlightPlanLoader.class);

    /** */
    private static final int FACTOR_THOUSAND = 1000;

    /**
     * @param fpcm
     * @param currentAirport
     *
     */
    public static void loadFlightPlans(final FlighPlanCollectionModel fpcm, final String currentAirport) {
        fpcm.getListModel().clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(CommonProperties.ROUTES_DIRECTORY.resolve(currentAirport), "*.{txt}")) {
            for (Path path : stream) {

                final FlighPlanModel fp = new FlighPlanModel();
                fp.addPropertyChangeListener(FligthPlanProperties.DURATION, new PropertyChangeListener() {
                    /**
                     *
                     * {@inheritDoc}
                     */
                    @Override
                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (null != fp.getStartTime()) {
                            fp.setEndTime(fp.getStartTime().plus(fp.getDuration()));
                        } else {
                            if (null != fp.getEndTime()) {
                                fp.setStartTime(fp.getEndTime().plus(fp.getDuration()));
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
                                    fp.setStartTime(TimeProcessor.convertUtcToCurrentTimeZone(line));
                                }
                                break;

                            case STARTAIRCRAFT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDAIRCRAFT.name())) {
                                    String[] split = line.split(" ");
                                    String[] splitAirCraft = split[0].split("_");
                                    String aircraftType = splitAirCraft[0];
                                    if (splitAirCraft.length > 1) {
                                        fp.setAircraftCie(Internationalizer.getI18String(splitAirCraft[1]));
                                    }
                                    fp.setAircraftType(aircraftType);
                                }
                                break;

                            case STARTDESTAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDESTAIRPORT.name())) {
                                    fp.setArrivalAirport(line);
                                }
                                break;

                            case STARTDEPAIRPORT:
                                line = reader.readLine();
                                if (!line.equals(FlightPlanInformationTypes.ENDDEPAIRPORT.name())) {
                                    fp.setDepartureAirport(line);
                                }
                                break;

                            case STARTSTEERPOINTS:
                                List<String> steerpoints = new ArrayList<String>();
                                line = reader.readLine();
                                while (!line.equals(FlightPlanInformationTypes.ENDSTEERPOINTS.name())) {
                                    steerpoints.add(line);
                                    line = reader.readLine();
                                }
                                GeographicProcessor gp = new GeographicProcessor();
                                long result = gp.getFlightTime(steerpoints);
                                fp.setDuration(new Period(result * FACTOR_THOUSAND));
                                break;

                            default:
                                break;
                            }
                        }
                    }
                }
                fpcm.addFlightPlan(fp);
            }
        } catch (IOException e) {
            LOGGER.error("Error while reading Flght plans", e);
        }
    }
}
