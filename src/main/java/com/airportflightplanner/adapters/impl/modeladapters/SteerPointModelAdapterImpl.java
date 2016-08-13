/*
 * @(#)SteerPointModelAdapterImpl.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 13 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.adapters.impl.modeladapters;

import com.airportflightplanner.adapters.api.modeladapters.SteerPointModelAdapter;
import com.airportflightplanner.flightplanvisualization.api.SteerPointsListModelListener;
import com.airportflightplanner.models.steerpoints.SteerPointsCollectionModel;
import com.airportflightplanner.models.steerpoints.api.bean.SteerPointReader;

import fr.gsyltc.framework.adapters.AbstractModelAdapterImpl;

/**
 * @author Goubaud Sylvain
 *
 */

public class SteerPointModelAdapterImpl extends AbstractModelAdapterImpl<SteerPointsCollectionModel> //
        implements SteerPointModelAdapter {
    
    
    /**
     *
     */
    private static final long serialVersionUID = -4401948080326943883L;

    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addListener(final SteerPointsListModelListener listener) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeListener(final SteerPointsListModelListener listener) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void addSteerPoint(final SteerPointReader value) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void removeSteerPoint(final SteerPointReader value) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     *
     * {@inheritDoc}.
     */
    @Override
    public void init() {
        // TODO Auto-generated method stub
        
    }
    
}
