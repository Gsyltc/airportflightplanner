/* @(#)FlightPlanModelAdapter.java
 *
 * 2016 Goubaud Sylvain
 */
package com.airportflightplanner.common.adapter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.measure.unit.NonSI;

import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.jscience.geography.coordinates.Altitude;

import com.airportflightplanner.common.api.adapter.FlightPlanModelAdapter;
import com.airportflightplanner.common.api.flightplan.FlightPlanReader;
import com.airportflightplanner.common.api.flightplan.FligthPlanProperties;
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
    /** */
    private final List<FlighPlanModel> flighPlans = new CopyOnWriteArrayList<FlighPlanModel>();

    @Override
    public void update(final FlightPlanInformationTypes dataType, final String data) {
        // TODO Auto-generated method stub

    }

    /**
     *
     * @param newFlightPlan
     */
    private void checkIsFlightPlanExist(final FlighPlanModel newFlightPlan) {
        if (!flighPlans.contains(newFlightPlan)) {
            flighPlans.add(newFlightPlan);
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
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addSteerpoints(final FlighPlanModel newFlightPlan, final List<String> steerpoints) {
        if (null != newFlightPlan) {
            checkIsFlightPlanExist(newFlightPlan);

            newFlightPlan.setSteerPoints(steerpoints);

            // calculate Flight Time
            GeographicProcessor gp = new GeographicProcessor();
            long result = gp.getFlightTime(steerpoints);
            newFlightPlan.setDuration(new Period(result));
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void addStartDays(final FlighPlanModel newFlightPlan, final Set<String> startDays) {
        if (null != newFlightPlan) {
            checkIsFlightPlanExist(newFlightPlan);
            Set<StartDays> tmpSet = new HashSet<StartDays>();
            for (String string : startDays) {
                tmpSet.add(StartDays.valueOf(string));
            }

            newFlightPlan.setStartDays(tmpSet);
        }

    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public void updateFlightPlan(final FlighPlanModel newFlightPlan, final FlightPlanInformationTypes informationsType, final String line) {
        if (null != newFlightPlan) {

            checkIsFlightPlanExist(newFlightPlan);
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
                newFlightPlan.setArrivalType(ArrivalType.valueOf(line));
                break;
            case STARTCALLSIGN:
                newFlightPlan.setCallSign(line);
                break;
            case STARTDEPAIRPORT:
                newFlightPlan.setDepartureAirport(line);
                break;
            case STARTDEPARTTYPE:
                newFlightPlan.setDepartureType(DepartureType.valueOf(line));
                break;
            case STARTDESTAIRPORT:
                newFlightPlan.setArrivalAirport(line);
                break;
            case STARTFLIGHTTYPE:
                newFlightPlan.setFlighType(FlightType.valueOf(line));
                break;

            case STARTTIME:
                newFlightPlan.setStartTime(LocalTime.parse(line));
                break;

            default:
                break;
            }
        }
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public List<FlightPlanReader> getFlighPlans() {
        return Collections.unmodifiableList(flighPlans);
    }

}
