/*
 * @(#)FlightPlanModelAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 7 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl.modeladapters;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.unit.NonSI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.common.processors.GeographicProcessor;
import com.airportflightplanner.common.slotsignal.TopicName;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.utils.time.TimeUtils;
import com.airportflightplanner.models.flightplans.FlightPlanModel;

import fr.gsyltc.framework.adapters.AbstractReceiverModelAdapterImpl;
import fr.gsyltc.framework.slotsignals.action.api.SlotAction;
import fr.gsyltc.framework.slotsignals.slots.Slot;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanModelAdapterImpl extends AbstractReceiverModelAdapterImpl<FlightPlanModel> implements
        FlightPlanModelAdapter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -7652261197111997309L;

    /** The logger of this class. */
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(FlightPlanModelAdapterImpl.class);

    /** */
    private static final int NUMBER_ZERO = 0;
    /** */
    private static final int NUMBER_ONE = 1;
    /** Definied if the current flight plan has been modified. */
    private transient boolean modificationtoCommit;

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void addSteerpoints(final List<String> steerpoints) {
        getModel().setSteerPoints(steerpoints);

        // calculate Flight Time
        final long result = GeographicProcessor.getFlightTime(steerpoints);
        final Period duration = new Period(result);
        getModel().setDuration(duration);
        getModel().setEndTime(getModel().getStartTime().plus(duration));
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void createSlots() {
        final Slot slot = attachSlot(TopicName.FP_TABLE_SELECTED_TOPIC);
        slot.setSlotAction(new SlotAction<FlightPlanModel>() {
            
            
            /** . */
            private static final long serialVersionUID = 4397834631502532479L;

            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void doAction(final FlightPlanModel bean) {
                setModel(bean);
            }
        });

        final Slot modifiedSlot = attachSlot(TopicName.FP_MODIFIED_TOPIC);
        modifiedSlot.setSlotAction(new SlotAction<Boolean>() {
            
            
            /** . */
            private static final long serialVersionUID = 4397834631502532479L;

            /**
             *
             * {@inheritDoc}.
             */
            @Override
            public void doAction(final Boolean value) {
                setModificationtoCommit(value);
            }
        });
    }

    /**
     * /** {@inheritDoc}..
     */
    @Override
    public void init() {
        this.createSlots();
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public final void updateFlightPlan(final FlightPlanInformationTypes informationsType, final String line) {
        switch (informationsType) {
        case START_FLY_TO_COMPLETION:
            boolean result = false;
            if (Integer.parseInt(line) == NUMBER_ONE) {
                result = true;
            }
            getModel().setFlightToCompletion(result);

            break;
        case START_LANDING_LIGHT_ALT:
            getModel().setLandingLightAltitude(Altitude.valueOf(Double.valueOf(line), NonSI.FOOT));
            break;
        
        case STARTAIRCRAFT:
            final String[] aircraftType = line.split(" +");
            if (aircraftType.length > NUMBER_ZERO) {
                getModel().setAircraftLivery(aircraftType[0]);
            }
            final String[] splitAirCraft = aircraftType[0].split("_");
            if (splitAirCraft.length > NUMBER_ONE) {
                getModel().setAircraftCie(splitAirCraft[1]);
            }

            break;
        case STARTALTERNATEAIRPORT:
            getModel().setAlternateAirport(line);
            break;
        
        case STARTARRIVETYPE:
            try {
                getModel().setArrivalType(ArrivalType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading arrival type");
                }
            }
            break;
        case STARTCALLSIGN:
            getModel().setCallSign(line);
            break;
        
        case STARTDEPAIRPORT:
            getModel().setDepartureAirport(line);
            break;
        
        case STARTDEPARTTYPE:
            try {
                getModel().setDepartureType(DepartureType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading departure type");
                }
            }
            break;
        case STARTDESTAIRPORT:
            getModel().setArrivalAirport(line);
            break;
        
        case STARTFLIGHTTYPE:
            try {
                getModel().setFlightType(FlightType.valueOf(Integer.parseInt(line)));
            } catch (final NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading start flight type");
                }
            }
            break;
        
        case STARTTIME:
            try {
                getModel().setStartTime(TimeUtils.getLocalTime(line));
                getModel().setEndTime(TimeUtils.getLocalTime(line));
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

            getModel().setStartDays(daysSet);
            break;
        
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean isModificationToCommit() {
        return modificationtoCommit;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void setModificationtoCommit(final boolean value) {
        modificationtoCommit = value;
    }
}
