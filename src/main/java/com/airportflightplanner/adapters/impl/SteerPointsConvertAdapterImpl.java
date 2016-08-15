/*
 * @(#)SteerPointsConvertAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 15 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.measure.unit.NonSI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jscience.geography.coordinates.Altitude;
import org.jscience.geography.coordinates.LatLong;

import com.airportflightplanner.adapters.api.SteerPointsConvertAdapter;
import com.airportflightplanner.common.domaintypes.BankingAngle;
import com.airportflightplanner.common.domaintypes.Heading;
import com.airportflightplanner.common.domaintypes.Speed;
import com.airportflightplanner.common.types.AltitudeType;
import com.airportflightplanner.common.types.FormationType;
import com.airportflightplanner.models.steerpoints.SteerPointModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

import fr.gsyltc.framework.adapters.AbstractAdapterImpl;

/**
 * @author Goubaud Sylvain
 *
 */

public class SteerPointsConvertAdapterImpl extends AbstractAdapterImpl implements SteerPointsConvertAdapter {
    
    
    /** The logger of this class. */
    private static final Logger LOGGER = LogManager.getLogger(SteerPointsConvertAdapterImpl.class);

    /**
     *
     */
    private static final long serialVersionUID = 8439855875388796503L;
    /** */
    private static final int MIN_LENGTH = 6;
    /** */
    private static final int LATITUDE_KEY = 0;
    /** */
    private static final int LONGITUDE_KEY = 1;
    /** */
    private static final int ALTITUDE_KEY = 2;
    /** */
    private static final int ALTTYPE_KEY = 3;
    /** */
    private static final int SPEED_KEY = 4;
    /** */
    private static final int MAX_BANKING_ANGLE_KEY = 5;
    /** */
    private static final int HEADING_KEY = 6;
    /** */
    private static final int FORMATION_KEY = 8;
    /** */
    private static final int WAYPOINT_NAME_KEY = 9;
    /** */
    private static final Object SPACE = " ";

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        // TODO Auto-generated method stub

    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public List<SteerPointModel> convertSteerPoints(final List<String> value) {
        final List<SteerPointModel> result = new ArrayList<SteerPointModel>();
        for (final String datas : value) {
            final SteerPointModel steerPoint = convertSteerPoint(datas);
            if (null != steerPoint) {
                result.add(steerPoint);
            }
        }

        return result;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public SteerPointModel convertSteerPoint(final String datas) {
        SteerPointModel result = null;
        if (validateSteerpoints(datas)) {
            result = (SteerPointModel) getSteerPoint(datas);
        } else {
            if (LOGGER.isErrorEnabled()) {
                LOGGER.error("Steer point not valid : " + datas);
            }
        }
        return result;
    }

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public String convertSteerPointToString(final SteerPointReader steerPoint) {
        final StringBuilder result = new StringBuilder();
        result.append(steerPoint.getLatLong().latitudeValue(NonSI.DEGREE_ANGLE)).append(SPACE) //
                .append(steerPoint.getLatLong().longitudeValue(NonSI.DEGREE_ANGLE)).append(SPACE) //
                .append(steerPoint.getAltitude().longValue(NonSI.FOOT)).append(SPACE) //
                .append(steerPoint.getAltType().name()).append(SPACE) //
                .append(steerPoint.getSpeed().getValue(NonSI.KNOT)).append(SPACE) //
                .append(steerPoint.getMaxBankingAngle().intValue(NonSI.DEGREE_ANGLE)).append(SPACE) //
                .append(steerPoint.getHeading().getSIValue()).append(SPACE).append("-1").append(SPACE) //
                .append(steerPoint.getFormation().name()).append(SPACE) //
                .append(steerPoint.getName());

        return result.toString();
    }

    /**
     *
     * @param steerpointsString
     *            steer point string formatted.
     * @return Steer point.
     */
    public SteerPointReader getSteerPoint(final String steerpointsString) {
        final SteerPointModel steerPoint = new SteerPointModel();
        final Pattern pattern = Pattern.compile(" +");
        // séparation en sous-chaînes
        final String[] items = pattern.split(steerpointsString, 10);
        steerPoint.setLatLong(LatLong.valueOf(Double.valueOf(items[LATITUDE_KEY]), //
                Double.valueOf(items[LONGITUDE_KEY]), NonSI.DEGREE_ANGLE));
        steerPoint.setAltitude(Altitude.valueOf(Double.valueOf(items[ALTITUDE_KEY]), NonSI.FOOT));
        steerPoint.setAltType(AltitudeType.valueOf(items[ALTTYPE_KEY]));

        steerPoint.setSpeed(new Speed(Double.valueOf(items[SPEED_KEY]), NonSI.KNOT));
        steerPoint.setMaxBankingAngle(new BankingAngle(Double.valueOf(items[MAX_BANKING_ANGLE_KEY]), NonSI.DEGREE_ANGLE));
        steerPoint.setHeading(new Heading(Double.valueOf(items[HEADING_KEY]), NonSI.DEGREE_ANGLE));
        steerPoint.setFormation(FormationType.valueFrom(items[FORMATION_KEY]));
        steerPoint.setName(items[WAYPOINT_NAME_KEY]);
        return steerPoint;
    }

    /**
     *
     * @param steerPointDatas
     * @return
     */
    public boolean validateSteerpoints(final String steerPointDatas) {
        boolean result = true;
        final String[] datas = steerPointDatas.split(" +");
        // Check number of param
        if (datas.length >= MIN_LENGTH) {
            result &= isPositionValid(datas[LATITUDE_KEY]);
            result &= isPositionValid(datas[LONGITUDE_KEY]);
            result &= isAltitudeValid(datas[ALTITUDE_KEY]);
            result &= AltitudeType.isValid(datas[ALTTYPE_KEY]);
            result &= isSpeedValid(datas[SPEED_KEY]);
            result &= isMaxBankAngleValid(datas[MAX_BANKING_ANGLE_KEY]);
            result &= FormationType.isValid(datas[FORMATION_KEY]);
            result &= null != datas[WAYPOINT_NAME_KEY];
        }

        if (!result && LOGGER.isErrorEnabled()) {
            LOGGER.error("steerpoint datas not valid : " + steerPointDatas);
        }
        return result;
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean isPositionValid(final String value) {
        final Pattern pattern = Pattern.compile("^[+-]?\\d{1,3}+\\.?\\d{0,10}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean isAltitudeValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,5}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean isSpeedValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,3}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     *
     * @param value
     * @return
     */
    private boolean isMaxBankAngleValid(final String value) {
        final Pattern pattern = Pattern.compile("^\\d{1,2}$");
        final Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
