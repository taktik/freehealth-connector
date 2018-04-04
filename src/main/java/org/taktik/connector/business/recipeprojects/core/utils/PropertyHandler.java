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

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyHandler {

    private final static Logger LOG = Logger.getLogger(PropertyHandler.class);
    private static PropertyHandler instance = null; // Singleton pattern

    private Properties properties;

    //Mocking should only need the default connector properties loaded.
    public PropertyHandler() throws IntegrationModuleException {
        this(null, null, null);
    }

    public PropertyHandler(String propertyfile, String validationFile) throws IntegrationModuleException {
        this(propertyfile, validationFile, null);
    }

    /**
     * Create a new property object using the given property file and config directory locations
     *
     * @param propertyfile the location of the property file
     * @param urlConf      the location for the conf directory
     * @throws IntegrationModuleException
     */
    public PropertyHandler(String propertyfile, String validationFile, String urlConf) throws IntegrationModuleException {
        Boolean onlyLoadDefaultProperties = StringUtils.isBlank(propertyfile);

        try {
            properties = new Properties();

            LOG.debug("Loading the default File");
            properties.load(IOUtils.getResourceAsStream("/connector-default.properties"));

            Path propertyFilePath = Paths.get(propertyfile);
            Path validationFilePath = Paths.get(validationFile);
            if (!onlyLoadDefaultProperties && Files.exists(propertyFilePath) && Files.exists(validationFilePath)) {
                LOG.debug("Loading the custom File");
                Path output = Paths.get(System.getProperty("java.io.tmpdir"), "connector-generated-properies.properties");
                if(Files.notExists(output)){
                    Files.createFile(output);
                }
                Charset charset = StandardCharsets.UTF_8;
                Files.write(output, Files.readAllLines(propertyFilePath, charset), charset, StandardOpenOption.TRUNCATE_EXISTING);
                Files.write(output, Files.readAllLines(validationFilePath, charset), charset, StandardOpenOption.APPEND);
                try {
                    String content = new String(Files.readAllBytes(output), charset);
                    String pathToFile = propertyFilePath.getParent().toAbsolutePath().toString().replace("\\", "/");
                    LOG.debug("%CONF% will be replaced by [" + pathToFile + "].");
                    content = content.replaceAll("%CONF%", pathToFile);
                    Files.write(output, content.getBytes(charset));
                } finally {
                    properties.load(Files.newInputStream(output));
                }
            }
            instance = this;
            LoggingUtil.initLog4J(this);
            LOG.info("Properties updated.....");
            if (LOG.isDebugEnabled()) {
                LOG.debug("Current folder is : " + new File(".").getCanonicalPath());
                LOG.debug("Current properties are : ");
                for (Object key : properties.keySet()) {
                    LOG.debug(key + " = " + properties.getProperty((String) key));
                }
            }
            LOG.info("Log4j initialized.....");
        } catch (Exception e) {
            throw new IntegrationModuleException(I18nHelper.getLabel("error.propertyfile"), e);
        }
    }


    public PropertyHandler(Properties properties) {
        this.properties = properties;
        instance = this;
    }

    public static PropertyHandler getInstance() {
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
     * Gets the uRL property.
     *
     * @param string the string
     * @return the uRL property
     */
    public URL getURLProperty(String string) throws IntegrationModuleException {
        try {
            String prop = getProperty(string);
            if (prop != null && prop.contains("META-INF")) {
                return this.getClass().getResource(prop);
            }

            String wsdl = getProperty(string);
            if (wsdl == null) {
                return null;
            }

            File f = new File(wsdl);
            if (f.exists()) {
                return f.toURI().toURL();
            } else {
                URL url = null;

                try {
                    url = new URL(wsdl);
                } catch (MalformedURLException e) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("wsdl.not.found", new String[]{prop}));
                }
                try {
                    LOG.debug("Checking connection with " + wsdl);
                    url.openStream().close();
                } catch (IOException e) {
                    throw new IntegrationModuleException(I18nHelper.getLabel("error.could.not.reach.url", new Object[]{wsdl}), e);
                }
                return url;
            }
        } catch (Throwable t) {
            Exceptionutils.errorHandler(t);
        }
        return null;
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
     * @param string       the string
     * @param defaultValue the default value
     * @return the property
     */
    public String getProperty(String string, String defaultValue) {
        if (properties == null) {
            LOG.warn("Properties are not initialized");
            return defaultValue;
        }

        if (!properties.containsKey(string)) {
            LOG.warn("Undefined property : " + string);
        }
        String propertyValue = properties.getProperty(string, defaultValue);

        // Printing a password in the logfile is a security issue
        if (!StringUtils.contains(string.toLowerCase(), "password")) {
            LOG.info("loading property " + string + " DefaultValue : " + defaultValue + " value returned : " + propertyValue);
        }
        return propertyValue != null ? propertyValue.trim() : propertyValue;
    }

    public boolean hasProperty(String key) {
        return properties != null && properties.containsKey(key);
    }

    public Properties getProperties() {
        return this.properties;
    }

    /**
     * Returns a copy of the current properties
     *
     * @return
     */
    public Properties getPropertiesCopy() {
        final Properties copy = new Properties();
        copy.putAll(properties);
        return copy;
    }

    /**
     * Gets the properties that match a root key.
     *
     * @param rootKey the root key
     * @return the properties
     */
    public List<String> getMatchingProperties(String rootKey) {
        int i = 1;
        List<String> ret = new ArrayList<String>();
        while (true) {
            String key = rootKey + "." + i;
            if (properties.getProperty(key) == null) {
                return ret;
            } else {
                ret.add(getProperty(key));
            }
            i++;
        }
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
