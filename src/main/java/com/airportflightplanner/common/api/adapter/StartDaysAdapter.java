/*
 * @(#)StartDaysAdapter.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 27 juil. 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter;

import java.util.Set;

import com.airportflightplanner.common.models.daysselection.DaysSelectionModel;
import com.airportflightplanner.common.types.StartDays;

import fr.gsyltc.framework.adapters.api.DomainModelAdapter;

/**
 * @author Goubaud Sylvain
 *
 */
public interface StartDaysAdapter extends DomainModelAdapter<DaysSelectionModel> {
    
    
    /**
     *
     * @param startDays
     */
    void updateStartsDays(Set<StartDays> startDays);
}
