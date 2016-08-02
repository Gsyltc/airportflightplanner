/*
 * @(#)Internationalizer.java
 *
 * Goubaud Sylvain
 * Created : 2016
 * Modified : 2 août 2016.
 *
 * This code may be freely used and modified on any personal or professional
 * project.  It comes with no warranty.
 *
 */

package com.airportflightplanner.common.utils.internationalization;

import java.util.List;
import java.util.Locale;

import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Goubaud Sylvain
 *
 */
public class Internationalizer {
    
    
    /** */
    private static ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
    
    /**
     * Get the internationalized String form properties files
     *
     * @param key
     * @return
     */
    public static String getI18String(final String key) {
        String message = "";
        try {
            message = bundleMessageSource.getMessage(key, null, Locale.getDefault());
        } catch (final NoSuchMessageException e) {
            message = key + AbstractMessages.UNKNOWN;
        }
        return message;
    }
    
    /**
     * Set the bundles for internationalization Injected by Spring
     *
     * @param bundles
     */
    public void setBundles(final List<String> bundles) {
        final String[] bundlesArrays = new String[bundles.size()];
        for (int i = 0; i < bundles.size(); i++) {
            bundlesArrays[i] = bundles.get(i);
        }
        bundleMessageSource.setBasenames(bundlesArrays);
    }
}
