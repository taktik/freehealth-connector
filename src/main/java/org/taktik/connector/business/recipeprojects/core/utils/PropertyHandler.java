/**
 * Copyright (C) 2010 Recip-e
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.taktik.connector.business.recipeprojects.core.utils;

import org.taktik.connector.technical.config.ConfigFactory;
import org.taktik.connector.technical.config.ConfigValidator;

import java.util.ArrayList;
import java.util.List;

public class PropertyHandler {
    private static PropertyHandler instance = null; // Singleton pattern
    private ConfigValidator config = ConfigFactory.getConfigValidator();

    public static PropertyHandler getInstance() {
        synchronized(PropertyHandler.class) { if (instance==null) { instance = new PropertyHandler(); } }
        return instance;
    }

    public String getProperty(String string) {
        return getProperty(string, null);
    }

    /**
     * Gets the integer property.
     *
     * @param string the string
     * @return the integer property
     */
    public Integer getIntegerProperty(String string) {
        return Integer.parseInt(getProperty(string));
    }


    /**
     * Gets the integer property.
     *
     * @param string       the string
     * @param defaultValue the default value
     * @return the integer property
     */
    public Integer getIntegerProperty(String string, String defaultValue) {
        return Integer.parseInt(getProperty(string, defaultValue));
    }

    /**
     * Gets the property.
     *
     * @param key       the string
     * @param defaultValue the default value
     * @return the property
     */
    public String getProperty(String key, String defaultValue) {
        return config.getProperty(key, defaultValue);
    }

    public boolean hasProperty(String key) {
        return config.hasProperty(key);
    }


    /**
     * Gets the properties that match a root key.
     *
     * @param rootKey the root key
     * @return the properties
     */
    public List<String> getMatchingProperties(String rootKey) {
        int i = 1;
        List<String> ret = new ArrayList<>();
        String key = rootKey + "." + i;
        while (config.hasProperty(key)) {
            ret.add(getProperty(key));
            key = rootKey + "." + (++i);
        }
        return ret;
    }
}
