/**
 * Copyright (C) 2010 Recip-e
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.taktik.connector.business.recipeprojects.core.utils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The Class I18nHelper.
 */
public class I18nHelper {

    private static final Logger LOG = LogManager.getLogger(I18nHelper.class);

    /**
     * The properties.
     */
    private static ResourceBundle bundle;

    private static ResourceBundle bundleNL;
    private static ResourceBundle bundleFR;
    private static ResourceBundle bundleENG;

    /**
     * Gets the label.
     *
     * @param key the key
     * @return the label
     */
    public static String getLabel(String key) {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("label", Locale.getDefault());
            LOG.info("Loading resource bundle for locale " + bundle.getLocale());
        }
        return bundle.getString(key);
    }

    public static String getLabel(String key, Object[] context) {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle("label", Locale.getDefault());
            LOG.info("Loading resource bundle for locale " + bundle.getLocale());
        }

        String message = bundle.getString(key);

        return MessageFormat.format(message, context);
    }

    public static Map<String, String> getAllLanguagesLabels(String key) {
        Map<String, String> labels = new HashMap<>();

        if (bundleNL == null) {
            bundleNL = ResourceBundle.getBundle("label", new Locale("NL"));
            LOG.info("Loading resource bundle for locale " + bundleNL.getLocale());
        }

        if (bundleFR == null) {
            bundleFR = ResourceBundle.getBundle("label", Locale.FRENCH);
            LOG.info("Loading resource bundle for locale " + bundleFR.getLocale());
        }

        if (bundleENG == null) {
            bundleENG = ResourceBundle.getBundle("label", Locale.ROOT);
            LOG.info("Loading resource bundle for locale " + bundleENG.getLocale());
        }

        labels.put("NL", bundleNL.getString(key));
        labels.put("FR", bundleFR.getString(key));
        labels.put("ENG", bundleENG.getString(key));
        return labels;
    }

    public static Map<String, String> getAllLanguagesLabels(String key, Object[] context) {
        LOG.info("get value for key " + key);
        Map<String, String> labels = new HashMap<>();

        if (bundleNL == null) {
            bundleNL = ResourceBundle.getBundle("label", new Locale("NL"));
            LOG.info("Loading resource bundle for locale " + bundleNL.getLocale());
        }

        if (bundleFR == null) {
            bundleFR = ResourceBundle.getBundle("label", Locale.FRENCH);
            LOG.info("Loading resource bundle for locale " + bundleFR.getLocale());
        }

        if (bundleENG == null) {
            bundleENG = ResourceBundle.getBundle("label", Locale.ROOT);
            LOG.info("Loading resource bundle for locale " + bundleENG.getLocale());
        }

        labels.put("NL", MessageFormat.format(bundleNL.getString(key), context));
        labels.put("FR", MessageFormat.format(bundleFR.getString(key), context));
        labels.put("ENG", MessageFormat.format(bundleENG.getString(key), context));
        return labels;
    }
}
