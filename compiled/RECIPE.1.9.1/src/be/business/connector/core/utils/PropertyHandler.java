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
package be.business.connector.core.utils;

import java.io.File;
import java.io.IOException;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import be.business.connector.core.exceptions.IntegrationModuleException;

/**
 * The Class PropertyHandler.
 */
public class PropertyHandler {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PropertyHandler.class);

	/** The instance. */
	private static PropertyHandler instance = null; // Singleton pattern

	/** The properties. */
	private Properties properties;

	/** The flag indicating if log4j is initialized. */
	private boolean log4jInitialized = false;

	/**
	 * Instantiates a new property handler.
	 */
	// Mocking should only need the default connector properties loaded.
	public PropertyHandler() {
		this(null, null, null);
	}

	/**
	 * Instantiates a new property handler.
	 *
	 * @param propertyfile
	 *            the propertyfile
	 * @param validationFile
	 *            the validation file
	 */
	public PropertyHandler(final String propertyfile, final String validationFile) {
		this(propertyfile, validationFile, null);
	}

	/**
	 * Create a new property object using the given property file and config directory locations.
	 *
	 * @param propertyfile
	 *            the location of the property file
	 * @param validationFile
	 *            the validation file
	 * @param urlConf
	 *            the location for the conf directory @
	 */
	public PropertyHandler(final String propertyfile, final String validationFile, final String urlConf) {
		final Boolean onlyLoadDefaultProperties = StringUtils.isBlank(propertyfile);

		try {
			properties = new Properties();
			LOG.debug("Loading the default File");
			properties.load(IOUtils.getResourceAsStream("/connector-default.properties"));

			final Path propertyFilePath = Paths.get(OsUtils.isWindows() ? propertyfile.replaceAll("^/+", "") : propertyfile);
			final Path validationFilePath = Paths.get(OsUtils.isWindows() ? validationFile.replaceAll("^/+", "") : validationFile);

			if (!onlyLoadDefaultProperties && Files.exists(propertyFilePath) && Files.exists(validationFilePath)) {
				LOG.debug("Loading the custom File");
				final Path output = Paths.get(System.getProperty("java.io.tmpdir"), "connector-generated-properties.properties");;
				if (Files.notExists(output)) {
					Files.createFile(output);
				}
				final Charset charset = StandardCharsets.UTF_8;
				Files.write(output, Files.readAllLines(propertyFilePath, charset), charset, StandardOpenOption.TRUNCATE_EXISTING);
				Files.write(output, Files.readAllLines(validationFilePath, charset), charset, StandardOpenOption.APPEND);
				try {
					String content = new String(Files.readAllBytes(output), charset);
					final String pathToFile = propertyFilePath.getParent().toAbsolutePath().toString().replace("\\", "/");
					LOG.debug("%CONF% will be replaced by [" + pathToFile + "].");
					content = content.replaceAll("%CONF%", pathToFile);
					Files.write(output, content.getBytes(charset));
				} finally {
					properties.load(Files.newInputStream(output));
				}
			}

			instance = this;

			LoggingUtil.initLog4J(getInstance());
			log4jInitialized = true;
			LOG.debug("Properties updated.....");
			if (LOG.isDebugEnabled()) {
				LOG.debug("Current folder is : " + new File(".").getCanonicalPath());
				LOG.debug("Current properties are : ");
				for (final Object key : properties.keySet()) {
					LOG.debug(key + " = " + properties.getProperty((String) key));
				}
			}
			LOG.debug("Log4j initialized.....");
		} catch (final Exception e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.propertyfile"), e);
		}
	}

	/**
	 * Instantiates a new property handler.
	 *
	 * @param properties
	 *            the properties
	 */
	public PropertyHandler(final Properties properties) {
		this.properties = properties;
		instance = this;
	}

	/**
	 * Gets the single instance of PropertyHandler.
	 *
	 * @return single instance of PropertyHandler
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PropertyHandler#getInstance", logger = "org.perf4j.TimingLogger_Common")
	public static PropertyHandler getInstance() {
		return instance;
	}

	/**
	 * Gets the property.
	 *
	 * @param string
	 *            the string
	 * @return the property
	 */
	public String getProperty(final String string) {
		return getProperty(string, null);
	}

	/**
	 * Gets the integer property.
	 *
	 * @param string
	 *            the string
	 * @return the integer property
	 */
	public Integer getIntegerProperty(final String string) {
		return Integer.parseInt(getProperty(string));
	}

	/**
	 * Gets the uRL property.
	 *
	 * @param string
	 *            the string
	 * @return the uRL property
	 */
	public URL getURLProperty(final String string) {
		try {
			final String prop = getProperty(string);
			if (prop != null && prop.contains("META-INF")) {
				return this.getClass().getResource(prop);
			}

			final String wsdl = getProperty(string);
			if (wsdl == null) {
				return null;
			}

			final File f = new File(wsdl);
			if (f.exists()) {
				return f.toURI().toURL();
			} else {
				URL url = null;

				try {
					url = new URL(wsdl);
				} catch (final MalformedURLException e) {
					throw new IntegrationModuleException(I18nHelper.getLabel("wsdl.not.found", new String[] { prop }));
				}
				try {
					LOG.debug("Checking connection with " + wsdl);
					url.openStream().close();
				} catch (final IOException e) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.could.not.reach.url", new Object[] { wsdl }), e);
				}
				return url;
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Gets the integer property.
	 *
	 * @param string
	 *            the string
	 * @param defaultValue
	 *            the default value
	 * @return the integer property
	 */
	public Integer getIntegerProperty(final String string, final String defaultValue) {
		return Integer.parseInt(getProperty(string, defaultValue));
	}

	/**
	 * Gets the property.
	 *
	 * @param string
	 *            the string
	 * @param defaultValue
	 *            the default value
	 * @return the property
	 */
	public String getProperty(final String string, final String defaultValue) {
		if (properties == null) {
			LOG.warn("Properties are not initialized");
			return defaultValue;
		}

		if (!properties.containsKey(string)) {
			LOG.warn("Undefined property : " + string);
		}
		final String propertyValue = properties.getProperty(string, defaultValue);

		// Printing a password in the logfile is a security issue
		if (!StringUtils.contains(string.toLowerCase(), "password")) {
			LOG.debug("loading property " + string + " DefaultValue : " + defaultValue + " value returned : " + propertyValue);
		}
		return propertyValue != null ? propertyValue.trim() : propertyValue;
	}

	/**
	 * Checks for property.
	 *
	 * @param key
	 *            the key
	 * @return true, if successful
	 */
	public boolean hasProperty(final String key) {
		return properties != null && properties.containsKey(key);
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Properties getProperties() {
		return this.properties;
	}

	/**
	 * Returns a copy of the current properties.
	 *
	 * @return the properties copy
	 */
	public Properties getPropertiesCopy() {
		final Properties copy = new Properties();
		copy.putAll(properties);
		return copy;
	}

	/**
	 * Gets the properties that match a root key.
	 *
	 * @param rootKey
	 *            the root key
	 * @return the properties
	 */
	public List<String> getMatchingProperties(final String rootKey) {
		int i = 1;
		final List<String> ret = new ArrayList<String>();
		while (true) {
			final String key = rootKey + "." + i;
			if (properties.getProperty(key) == null) {
				return ret;
			} else {
				ret.add(getProperty(key));
			}
			i++;
		}
	}

	/**
	 * Sets the property.
	 *
	 * @param key
	 *            the key
	 * @param value
	 *            the value
	 */
	public void setProperty(final String key, final String value) {
		properties.setProperty(key, value);
	}

	/**
	 * Checks if is log 4 j initialized.
	 *
	 * @return true, if is log 4 j initialized
	 */
	public boolean isLog4jInitialized() {
		return log4jInitialized;
	}

	/**
	 * Sets the log 4 j initialized.
	 *
	 * @param log4jInitialized
	 *            the new log 4 j initialized
	 */
	public void setLog4jInitialized(final boolean log4jInitialized) {
		this.log4jInitialized = log4jInitialized;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties
	 *            the new properties
	 */
	public void setProperties(final Properties properties) {
		this.properties = properties;
	}

	/**
	 * Gets the boolean property.
	 *
	 * @param value
	 *            the value
	 * @param defaultValue
	 *            the default value
	 * @return the boolean property
	 */
	public boolean getBooleanProperty(final String value, final String defaultValue) {
		return Boolean.valueOf(getProperty(value, defaultValue));
	}
}
