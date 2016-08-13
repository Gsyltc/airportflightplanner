/*
 * @(#)StartDaysModelAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.api.modeladapters;

import java.util.Set;

import com.airportflightplanner.common.types.StartDays;
import com.airportflightplanner.models.daysselection.DaysSelectionModel;

import fr.gsyltc.framework.adapters.api.DomainModelAdapter;

/**
 * @author Goubaud Sylvain
 */
public interface StartDaysModelAdapter extends DomainModelAdapter<DaysSelectionModel> {
    
    
    /**
     *
     * @param startDays
     */
    void updateStartsDays(Set<StartDays> startDays);
}
