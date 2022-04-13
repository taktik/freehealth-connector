package be.business.connector.core.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;

import be.business.connector.core.exceptions.IntegrationModuleException;

/**
 * The Class PersistentCache.
 */
public class PersistentCache {

	/** The cache. */
	private Map<String, byte[]> cache = new LinkedHashMap<>();

	/** The persistence cache. */
	private static PersistentCache persistenceCache;

	/**
	 * Instantiates a new persistent key cache.
	 *
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	private PersistentCache() throws IntegrationModuleException {
	}

	/**
	 * Gets the single instance of PersistentCache.
	 *
	 * @return single instance of PersistentCache
	 */
	public static PersistentCache getInstance() {
		if (persistenceCache == null) {
			persistenceCache = new PersistentCache();
		}
		return persistenceCache;
	}

	/**
	 * Gets the key.
	 *
	 * @param keyId
	 *            the key id
	 * @return the key
	 */
	public byte[] get(final String keyId) {
		Validate.notNull(keyId, "keyId can't be Null");
		if (cache.containsKey(keyId)) {
			return cache.get(keyId);
		}
		return new byte[0];
	}

	/**
	 * Contains key.
	 *
	 * @param keyId
	 *            the key id
	 * @return true, if successful
	 */
	public boolean containsKey(final String keyId) {
		return cache.containsKey(keyId);
	}

	/**
	 * Put the key-value pair in the cache.
	 *
	 * @param key
	 *            the key id
	 * @param value
	 *            the key
	 */
	public void put(final String key, final byte[] value) {
		Validate.notNull(key, "key can't be Null");
		Validate.notNull(value, "value can't be Null");
		if (!cache.containsKey(key)) {
			cache.put(key, value);
		}
	}

	/**
	 * Rewrite key.
	 *
	 * @param key
	 *            the key
	 * @return the string
	 */
	public static String rewriteKey(String key) {
		Validate.notNull(key, "key can't be Null");
		return key.replaceAll("\\/", "_");
	}
}