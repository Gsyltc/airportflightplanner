/*
 * @(#)AdapterNames.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters;

import com.airportflightplanner.adapters.api.AircraftTypeAdapter;
import com.airportflightplanner.adapters.api.AircraftsLiveriesAdapter;
import com.airportflightplanner.adapters.api.SteerPointsConvertAdapter;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanCollectionModelAdapter;
import com.airportflightplanner.adapters.api.modeladapters.FlightPlanModelAdapter;
import com.airportflightplanner.adapters.api.modeladapters.StartDaysModelAdapter;
import com.airportflightplanner.adapters.api.modeladapters.SteerPointModelAdapter;

/**
 * @author Goubaud Sylvain
 *
 */

public class AdapterNames {
    
    
    /** */
    public static final String FP_COLL_ADAPTER_NAME = FlightPlanCollectionModelAdapter.class.getSimpleName();
    /** */
    public static final String FP_ADAPTER_NAME = FlightPlanModelAdapter.class.getSimpleName();
    /** */
    public static final String STARTDAYS_ADAPTER_NAME = StartDaysModelAdapter.class.getSimpleName();
    /** */
    public static final String LIVERIES_ADAPTER_NAME = AircraftsLiveriesAdapter.class.getSimpleName();
    /** */
    public static final String AIR_CLASS_ADAPTER_NAME = AircraftTypeAdapter.class.getSimpleName();
    /** */
    public static final String STEERP_ADAPTER_NAME = SteerPointModelAdapter.class.getSimpleName();
    /** */
    public static final String SP_CONVERT_ADAPTER_NAME = SteerPointsConvertAdapter.class.getSimpleName();

    /**
     * Protected constructor.
     */
    protected AdapterNames() {
        // Nothing to do.
    }
}
