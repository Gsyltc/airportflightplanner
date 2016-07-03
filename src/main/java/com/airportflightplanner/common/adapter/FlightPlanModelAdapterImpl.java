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
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.model.FlighPlanModel;
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
        long result = GeographicUtils.getFlightTime(steerpoints);
        newFlightPlan.setDuration(new Period(result));
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
            String[] aircraftType = line.split(" +");
            if (aircraftType.length > 0){
                newFlightPlan.setAircraftType(aircraftType[0]);
            }
            String[] splitAirCraft = aircraftType[0].split("_");
            if (splitAirCraft.length > 1) {
                newFlightPlan.setAircraftCie(Internationalizer.getI18String(splitAirCraft[1]));
            }

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
            try {
                newFlightPlan.setStartTime(TimeUtils.getLocalTime(line));
            } catch (NumberFormatException e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Error while reading start time");
                }
            }
            break;

        case STARTDAYS:
            String days[] = line.split(" +");

            Set<StartDays> tmpSet = new HashSet<StartDays>();
            for (String day : days) {
                tmpSet.add(StartDays.valueOf(Integer.parseInt(day)));
            }

            newFlightPlan.setStartDays(tmpSet);
            break;


        default:
            break;
        }
    }
}
