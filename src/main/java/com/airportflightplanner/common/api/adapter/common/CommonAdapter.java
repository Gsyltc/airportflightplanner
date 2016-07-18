/*
 * @(#)CommonAdapter.java
 *
 * Goubaud Sylvain - 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.api.adapter.common;

/**
 * @author Goubaud Sylvain
 *
 */

/**
 * @author Goubaud Sylvain
 * @param <M>
 *
 */
public interface CommonAdapter<M> {

    /**
     *
     * @return
     */
    String getAdapterName();

    /**
     *
     * @param name
     */
    void setAdapterName(String name);

    /**
     *
     * @param model
     */
    void setModel(M model);

    /**
     *
     * @return
     */
    M getModel();

}
