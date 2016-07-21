/*
 * @(#)FlightPlanModelAdapterImpl.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.adapter.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.unit.NonSI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.bean.FlightPlanReader;
import com.airportflightplanner.common.models.flightplans.FlightPlanModel;
import com.airportflightplanner.common.slotsignal.SelectionSlot;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.slotsignal.api.SlotAction;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.utils.geographics.GeographicUtils;
import com.airportflightplanner.common.utils.internationalization.Internationalizer;
import com.airportflightplanner.common.utils.time.TimeUtils;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanModelAdapterImpl implements FlightPlanModelAdapter {
    /**
     *
     */
    private static final long serialVersionUID = 5605981511974415244L;
    /** The logger of this class. */
    private static final Log  LOGGER           = LogFactory.getLog(FlightPlanModelAdapterImpl.class);
    /** */
    private static final int  NUMBER_ZERO      = 0;
    /** */
    private static final int  NUMBER_ONE       = 1;
    /** */
    private String            adapterName;
    /** */
    private FlightPlanModel   model;

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void addSteerpoints(final List<String> steerpoints) {
        model.setSteerPoints(steerpoints);

        // calculate Flight Time
        final long result = GeographicUtils.getFlightTime(steerpoints);
        model.setDuration(new Period(result));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final void updateFlightPlan(final FlightPlanInformationTypes informationsType, final String line) {
        switch (informationsType) {
        case START_FLY_TO_COMPLETION:
            boolean result = false;
            if (Integer.parseInt(line) == NUMBER_ONE) {
                result = true;
            }
            model.setFlightToCompletion(result);

            break;
        case START_LANDING_LIGHT_ALT:
            model.setLandingLightAltitude(Altitude.valueOf(Double.valueOf(line), NonSI.FOOT));
            break;

        case STARTAIRCRAFT:
            final String[] aircraftType = line.split(" +");
            if (aircraftType.length > NUMBER_ZERO) {
                model.setAircraftType(aircraftType[0]);
            }
            final String[] splitAirCraft = aircraftType[0].split("_");
            if (splitAirCraft.length > NUMBER_ONE) {
                model.setAircraftCie(Internationalizer.getI18String(splitAirCraft[1]));
            }

            break;
        case STARTALTERNATEAIRPORT:
            model.setAlternateAirport(line);
            break;

        case STARTARRIVETYPE:
            try {
                model.setArrivalType(ArrivalType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading arrival type");
                }
            }
            break;
        case STARTCALLSIGN:
            model.setCallSign(line);
            break;

        case STARTDEPAIRPORT:
            model.setDepartureAirport(line);
            break;

        case STARTDEPARTTYPE:
            try {
                model.setDepartureType(DepartureType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading departure type");
                }
            }
            break;
        case STARTDESTAIRPORT:
            model.setArrivalAirport(line);
            break;

        case STARTFLIGHTTYPE:
            try {
                model.setFlightType(FlightType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading start flight type");
                }
            }
            break;

        case STARTTIME:
            try {
                model.setStartTime(TimeUtils.getLocalTime(line));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading start time");
                }
            }
            break;

        case STARTDAYS:
            final String[] days = line.split(" +");

            final Set<StartDays> daysSet = new HashSet<StartDays>();
            for (final String day : days) {
                daysSet.add(StartDays.valueOf(Integer.parseInt(day)));
            }

            model.setStartDays(daysSet);
            break;

        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAdapterName() {
        return adapterName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAdapterName(final String name) {
        adapterName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setModel(final FlightPlanModel model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FlightPlanModel getModel() {
        return model;
    }

    /**
     *
     */
    public void init() {
        attachSlotAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSlotAction() {
        final SelectionSlot<FlightPlanReader> slot = new SelectionSlot<FlightPlanReader>(TopicName.FLIGHTPLAN_TABLE_SELECTED_TOPIC, this);
        slot.setSlotAction(new SlotAction<FlightPlanReader>() {
            /**
             *
             * {@inheritDoc}
             */
            @Override
            public void doAction(final FlightPlanReader bean) {
                setModel((FlightPlanModel) bean);
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attachSignal() {
        // Nothing to do

    }
}
