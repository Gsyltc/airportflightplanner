/* @(#)SlotAction.java
 *
 * 2016 Goubaud Sylvain.
 *
 */
package com.airportflightplanner.common.slotsignal.api;

/**
 * @author Goubaud Sylvain
 * @param <E>
 *
 */
public interface SlotAction<E extends Object> {
    /**
     *
     * @param arg
     */
    void doAction(E arg);

}
