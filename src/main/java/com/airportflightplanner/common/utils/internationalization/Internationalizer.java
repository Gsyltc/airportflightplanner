/* @(#)Internationalizer.java
 *
 * Copyright (c) 2016 Goubaud Sylvain. All rights reserved.
 */
package com.airportflightplanner.common.utils.internationalization;

import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Goubaud Sylvain
 *
 */
public class Internationalizer {
    /** The logger of this class. */
    private static final Log                   LOGGER                      = LogFactory.getLog(Internationalizer.class);

    /** */
    private static ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();

    /**
     * Get the internationalized String form properties files
     *
     * @param key
     * @return
     */
    public static String getI18String(final String key) {
        String message = "";
        try {
            message = resourceBundleMessageSource.getMessage(key, null, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            LOGGER.info("Key not internationalized : " + key);
            message = key;
        }
        return message;
    }

    /**
     * Set the bundles for internationalization Injected by Spring
     *
     * @param bundles
     */
    public void setBundles(final List<String> bundles) {
        String[] bundlesArrays = new String[bundles.size()];
        for (int i = 0; i < bundles.size(); i++) {
            bundlesArrays[i] = bundles.get(i);
        }
        resourceBundleMessageSource.setBasenames(bundlesArrays);
    }
}
