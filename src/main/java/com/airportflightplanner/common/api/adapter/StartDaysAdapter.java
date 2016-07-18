
/*
 * @(#)StartDaysAdapter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter;

import java.util.Set;

import com.airportflightplanner.common.api.adapter.common.CommonAdapter;
import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
import com.airportflightplanner.common.slotsignal.api.SlotReceiver;
import com.airportflightplanner.common.types.StartDays;

/**
 * @author Goubaud Sylvain
 *
 */
public interface StartDaysAdapter extends CommonAdapter<DaysSelectionModel> , SlotReceiver{
    /**
     *
     * @param startDays
     */
    void updateStartsDays(Set<StartDays> startDays);
}
