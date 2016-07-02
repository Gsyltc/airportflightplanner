/* @(#)FlightPlanModelAdapter.java
 *
 * 2016 Goubaud Sylvain
 */
package com.airportflightplanner.common.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.measure.unit.NonSI;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.model.FlighPlanModel;
import com.airportflightplanner.common.types.ArrivalType;
import com.airportflightplanner.common.types.DepartureType;
import com.airportflightplanner.common.types.FlightPlanInformationTypes;
import com.airportflightplanner.common.types.FlightType;
import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.common.utils.internationalization.Internationalizer;
import com.airportflightplanner.flightplanprocessor.GeographicProcessor;

/**
 * @author Goubaud Sylvain
 *
 */
public class FlightPlanModelAdapterImpl implements FlightPlanModelAdapter {

    /** The logger of this class. */
    private static final Log LOGGER = LogFactory.getLog(FlightPlanModelAdapterImpl.class);

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addSteerpoints(final FlighPlanModel newFlightPlan, final List<String> steerpoints) {
        newFlightPlan.setSteerPoints(steerpoints);

        // calculate Flight Time
        GeographicProcessor gp = new GeographicProcessor();
        long result = gp.getFlightTime(steerpoints);
        newFlightPlan.setDuration(new Period(result));
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addStartDays(final FlighPlanModel newFlightPlan, final Set<String> startDays) {
        Set<StartDays> tmpSet = new HashSet<StartDays>();
        for (String string : startDays) {
            tmpSet.add(StartDays.valueOf(string));
        }

        newFlightPlan.setStartDays(tmpSet);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void updateFlightPlan(final FlighPlanModel newFlightPlan, final FlightPlanInformationTypes informationsType, final String line) {
        switch (informationsType) {
        case START_FLY_TO_COMPLETION:
            newFlightPlan.setFlightToCompletion(Boolean.valueOf(line));

            break;
        case START_LANDING_LIGHT_ALT:
            newFlightPlan.setLandingLightAltitude(Altitude.valueOf((Double.valueOf(line)), NonSI.FOOT));
            break;

        case STARTAIRCRAFT:
            String[] split = line.split(" ");
            String[] splitAirCraft = split[0].split("_");
            String aircraftType = splitAirCraft[0];
            if (splitAirCraft.length > 1) {
                newFlightPlan.setAircraftCie(Internationalizer.getI18String(splitAirCraft[1]));
            }
            newFlightPlan.setAircraftType(aircraftType);

            break;
        case STARTALTERNATEAIRPORT:
            newFlightPlan.setAlternateAirport(line);
            break;

        case STARTARRIVETYPE:
            try {
                newFlightPlan.setArrivalType(ArrivalType.valueOf(Integer.parseInt(line)));
            } catch (NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading arrival type");
                }
            }
            break;
        case STARTCALLSIGN:
            newFlightPlan.setCallSign(line);
            break;
        case STARTDEPAIRPORT:
            newFlightPlan.setDepartureAirport(line);
            break;
        case STARTDEPARTTYPE:
            try {
                newFlightPlan.setDepartureType(DepartureType.valueOf(Integer.parseInt(line)));
            } catch (NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading departure type");
                }
            }
            break;
        case STARTDESTAIRPORT:
            newFlightPlan.setArrivalAirport(line);
            break;

        case STARTFLIGHTTYPE:
            try {
            newFlightPlan.setFlighType(FlightType.valueOf(Integer.parseInt(line)));
            } catch (NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading start flight type");
                }
            }
            break;

        case STARTTIME:
            newFlightPlan.setStartTime(LocalTime.parse(line));
            break;


        default:
            break;
        }
    }
}
