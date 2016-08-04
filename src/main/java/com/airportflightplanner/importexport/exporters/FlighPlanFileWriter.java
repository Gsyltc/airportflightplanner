/*
 * @(#)FlighPlanFileWriter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 4 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.importexport.exporters;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import javax.measure.unit.NonSI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.utils.properties.CommonProperties;
import com.airportflightplanner.common.utils.time.TimeUtils;

import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slotreceiver.api.SlotReceiver;
import fr.gsyltc.framework.slotsignals.slots.Slot;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlighPlanFileWriter implements SlotReceiver {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(FlighPlanFileWriter.class);
    
    /** */
    public static final DateTimeFormatter WRITER_FORMATTER = new DateTimeFormatterBuilder().appendHourOfDay(2). //
            appendMinuteOfHour(2).toFormatter();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Slot attachSlot(final String topicName) {
        // Nothing to do
        return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createSlots() {
        final Slot airportSlot = new Slot(TopicName.WRITE_FLIGHT_PLAN_TOPIC, getClass().getSimpleName());
        airportSlot.registerSlot();
        airportSlot.setSlotAction(new SlotAction<FlightPlanReader>() {
            
            
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader object) {
                writeFlightPlans(object);
            }
        });
    }
    
    /**
     * @param flightPlan
     *            a flgihtPlan.
     *
     */
    protected final void writeFlightPlans(final FlightPlanReader flightPlan) { // NOPMD
                                                                               // by
                                                                               // sylva
                                                                               // on
                                                                               // 31/07/16
                                                                               // 15:38
        if (null != flightPlan) {
            final String flightPlanFileName = flightPlan.getDepartureAirport() + "/" + flightPlan.getStartTime().toString(
                    WRITER_FORMATTER) + "_" + //
                    flightPlan.getDepartureAirport() + "TEST_" + flightPlan.getArrivalAirport() + "_" + //
                    flightPlan.getAircraftType() + ".txt";
            
            final Path fileName = CommonProperties.ROUTES_DIRECTORY.resolve(flightPlanFileName);
            if (null != fileName) {
                
                try (Writer fileWriter = new OutputStreamWriter(new FileOutputStream(fileName.toFile()), StandardCharsets.UTF_8)) {
                    fileWriter.write("THIS FILE WAS GENERATED BY THE AIRPORT FLIGHT PLANNER - GOUBAUD Sylvain - 2016");
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // STARTTIME
                    fileWriter.write(FlightPlanInformationTypes.STARTTIME.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(flightPlan.getStartTime().toString(TimeUtils.TIME_DISPLAYER));
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(FlightPlanInformationTypes.ENDSTARTTIME.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // STARTAIRCRAFT
                    fileWriter.write(FlightPlanInformationTypes.STARTAIRCRAFT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(flightPlan.getAircraftType());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(FlightPlanInformationTypes.ENDAIRCRAFT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // STARTDEPAIRPORT
                    fileWriter.write(FlightPlanInformationTypes.STARTDEPAIRPORT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(flightPlan.getDepartureAirport());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(FlightPlanInformationTypes.ENDDEPAIRPORT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // STARTDESTAIRPORT
                    fileWriter.write(FlightPlanInformationTypes.STARTDESTAIRPORT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(flightPlan.getArrivalAirport());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(FlightPlanInformationTypes.ENDDESTAIRPORT.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // STARTCALLSIGN
                    if (null != flightPlan.getCallSign() && !flightPlan.getCallSign().isEmpty()) { // NOPMD
                                                                                                   // by
                                                                                                   // sylva
                                                                                                   // on
                                                                                                   // 31/07/16
                                                                                                   // 15:38
                        fileWriter.write(FlightPlanInformationTypes.STARTCALLSIGN.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(flightPlan.getCallSign());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDCALLSIGN.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTDAYS
                    if (!flightPlan.getStartDays().isEmpty()) { // NOPMD by
                                                                // sylva on
                                                                // 31/07/16
                                                                // 15:38
                        fileWriter.write(FlightPlanInformationTypes.STARTDAYS.name());
                        fileWriter.write(System.lineSeparator());
                        for (final StartDays day : flightPlan.getStartDays()) {
                            fileWriter.write(StartDays.getIndex(day) + " ");
                        }
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDDAYS.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTSTEERPOINTS
                    fileWriter.write(FlightPlanInformationTypes.STARTSTEERPOINTS.name());
                    fileWriter.write(System.lineSeparator());
                    for (final String steerPoint : flightPlan.getSteerPoints()) {
                        fileWriter.write(steerPoint);
                        fileWriter.write(System.lineSeparator());
                    }
                    fileWriter.write(FlightPlanInformationTypes.ENDSTEERPOINTS.name());
                    fileWriter.write(System.lineSeparator());
                    fileWriter.write(System.lineSeparator());
                    
                    // START_FLY_TO_COMPLETION
                    if (null != flightPlan.getFlightToCompletion()) {
                        fileWriter.write(FlightPlanInformationTypes.START_FLY_TO_COMPLETION.name());
                        fileWriter.write(System.lineSeparator());
                        if (flightPlan.getFlightToCompletion()) { // NOPMD by
                                                                  // sylva on
                                                                  // 31/07/16
                                                                  // 15:38
                            fileWriter.write("1");
                        } else {
                            fileWriter.write("0");
                        }
                        
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.END_FLY_TO_COMPLETION.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // START_LANDING_LIGHT_ALT
                    if (null != flightPlan.getLandingLightAltitude()) {
                        fileWriter.write(FlightPlanInformationTypes.START_LANDING_LIGHT_ALT.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(String.valueOf((int) flightPlan.getLandingLightAltitude().doubleValue(NonSI.FOOT)));
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.END_LANDING_LIGHT_ALT.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTALTERNATEAIRPORT
                    if (null != flightPlan.getAlternateAirport() && !flightPlan.getAlternateAirport().isEmpty()) {
                        fileWriter.write(FlightPlanInformationTypes.STARTALTERNATEAIRPORT.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(flightPlan.getAlternateAirport());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDALTERNATEAIRPORT.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTARRIVETYPE
                    if (null != flightPlan.getArrivalType()) {
                        fileWriter.write(FlightPlanInformationTypes.STARTARRIVETYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(String.valueOf(ArrivalType.getIndex(flightPlan.getArrivalType())));
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDARRIVETYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTDEPARTTYPE
                    if (null != flightPlan.getDepartureType()) {
                        fileWriter.write(FlightPlanInformationTypes.STARTDEPARTTYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(String.valueOf(DepartureType.getIndex(flightPlan.getDepartureType())));
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDDEPARTTYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                    // STARTFLIGHTTYPE
                    if (null != flightPlan.getFlightType()) {
                        fileWriter.write(FlightPlanInformationTypes.STARTFLIGHTTYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(String.valueOf(FlightType.getIndex(flightPlan.getFlightType())));
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(FlightPlanInformationTypes.ENDFLIGHTTYPE.name());
                        fileWriter.write(System.lineSeparator());
                        fileWriter.write(System.lineSeparator());
                    }
                    
                } catch (final IOException e) {
                    if (LOGGER.isErrorEnabled()) {
                        LOGGER.error("Error while writing files", e);
                    }
                }
            }
        }
    }
    
    /**
     * {@inheritDoc}.
     */
    @Override
    public Slot findSlot(final String topicName) {
        // Nothing to do
        return null;
    }
}
