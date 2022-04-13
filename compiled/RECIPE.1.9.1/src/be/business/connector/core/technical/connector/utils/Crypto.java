package be.business.connector.core.technical.connector.utils;

import java.util.List;

import javax.crypto.SecretKey;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.decrypt.DataUnsealer;
import be.fgov.ehealth.etee.crypto.encrypt.DataSealer;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;

/**
 * The Class Crypto.
 */
public class Crypto {

	private static final String TECHNICAL_CONNECTOR_ERROR_DATA_SEAL = "technical.connector.error.data.seal";

	/** The data sealer. */
	private static DataSealer dataSealer;

	/** The data unsealer. */
	private static DataUnsealer dataUnsealer;

	/** The encryption utils. */
	private static EncryptionUtils encryptionUtils;

	/**
	 * Instantiates a new {@link Crypto}.
	 */
	public Crypto() {
		try {
			dataSealer = EncryptionUtils.getInstance().initSealing();
			dataUnsealer = EncryptionUtils.getInstance().initUnsealing();
			encryptionUtils = EncryptionUtils.getInstance();
		} catch (final Exception ex) {
			throw new IntegrationModuleException(TECHNICAL_CONNECTOR_ERROR_DATA_SEAL, ex);
		}
	}

	/**
	 * Seal an unencrypted message.
	 *
	 * @param etk
	 *            the etk
	 * @param data
	 *            the data
	 * @return the byte[]
	 */
	public static byte[] seal(final EncryptionToken etk, final byte[] data) {
		try {
			return getDataSealer().seal(etk, data);
		} catch (final Exception ex) {
			throw new IntegrationModuleException(TECHNICAL_CONNECTOR_ERROR_DATA_SEAL, ex);
		}
	}

	/**
	 * Seal an unencrypted message.
	 *
	 * @param data
	 *            the data
	 * @param secretKey
	 *            the secret key
	 * @param keyId
	 *            the key id
	 * @return the byte[]
	 * @throws Exception
	 */
	public static byte[] seal(final byte[] data, final SecretKey secretKey, final String keyId) {
		try {
			return getDataSealer().seal(data, secretKey, keyId);
		} catch (final Exception ex) {
			throw new IntegrationModuleException(TECHNICAL_CONNECTOR_ERROR_DATA_SEAL, ex);
		}
	}

	/**
	 * Seal an unencrypted message.
	 *
	 * @param etks
	 *            the etks
	 * @param data
	 *            the data
	 * @return the byte[]
	 */
	public static byte[] seal(final List<EncryptionToken> etks, final byte[] data) {
		return seal(etks.get(0), data);
	}

	/**
	 * Unseal an encrypted message.
	 *
	 * @param data
	 *            the data
	 * @return the byte[]
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static byte[] unseal(final byte[] data) throws IntegrationModuleException {
		try {
			return getEncryptionUtils().unsealingData(getDataUnsealer().unseal(data));
		} catch (final Exception ex) {
			throw new IntegrationModuleException(TECHNICAL_CONNECTOR_ERROR_DATA_SEAL, ex);
		}
	}

	/**
	 * Unseal an encrypted message.
	 *
	 * @param keyResult
	 *            the key result
	 * @param data
	 *            the data
	 * @return the byte[]
	 */
	public static byte[] unsealForUnknown(final KeyResult keyResult, final byte[] data) {
		return unseal(keyResult.getSecretKey(), data);
	}

	/**
	 * Unseal an encrypted message.
	 *
	 * @param secretKey
	 *            the secret key
	 * @param data
	 *            the data
	 * @return the byte[]
	 */
	private static byte[] unseal(final SecretKey secretKey, final byte[] data) {
		try {
			return getEncryptionUtils().unsealingData(getDataUnsealer().unseal(data, secretKey));
		} catch (final Exception ex) {
			throw new IntegrationModuleException(TECHNICAL_CONNECTOR_ERROR_DATA_SEAL, ex);
		}
	}

	/**
	 * @return
	 */
	private static DataSealer getDataSealer() throws Exception {
		if (dataSealer == null) {
			dataSealer = EncryptionUtils.getInstance().initSealing();
		}
		return dataSealer;
	}

	/**
	 * @return
	 */
	private static DataUnsealer getDataUnsealer() throws Exception {
		if (dataUnsealer == null) {
			dataUnsealer = EncryptionUtils.getInstance().initUnsealing();
		}
		return dataUnsealer;
	}

	/**
	 * @return
	 */
	private static EncryptionUtils getEncryptionUtils() {
		if (encryptionUtils == null) {
			encryptionUtils = EncryptionUtils.getInstance();
		}
		return encryptionUtils;
	}

}