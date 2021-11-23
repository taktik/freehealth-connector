package be.business.connector.core.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * The Class OnlineProperties.
 */
public class OnlineProperties {

	/** The properties. */
	public Map<String, String> properties = new HashMap<>();

	/** The xsd validation files. */
	private Map<String, byte[]> xsdValidationFiles = new HashMap<>();

	private static boolean loaded = false;
	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties
	 *            the new properties
	 */
	public void setProperties(final Map<String, String> properties) {
		this.properties = properties;
	}

	/**
	 * Gets the xsd validation files.
	 *
	 * @return the xsd validation files
	 */
	public Map<String, byte[]> getXsdValidationFiles() {
		return xsdValidationFiles;
	}

	/**
	 * Sets the xsd validation files.
	 *
	 * @param xsdValidationFiles
	 *            the xsd validation files
	 */
	public void setXsdValidationFiles(Map<String, byte[]> xsdValidationFiles) {
		this.xsdValidationFiles = xsdValidationFiles;
	}

	/**
	 * Adds the xsd validation files.
	 *
	 * @param key
	 *            the key
	 * @param xsdValidationFile
	 *            the xsd validation file
	 */
	public void addXsdValidationFiles(String key, byte[] xsdValidationFile) {
		if (xsdValidationFiles == null) {
			xsdValidationFiles = new HashMap<String, byte[]>();
		}
		xsdValidationFiles.put(key, xsdValidationFile);
	}

	public static void setLoaded(boolean b) {
		loaded = b;
	}
	public static boolean isLoaded() {
		return loaded ;
	}
}