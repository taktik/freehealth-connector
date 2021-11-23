/*
 * 
 */
package be.business.connector.core.ehealth.services;

import java.util.List;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;

/**
 * The Interface EncryptionKeyService.
 */
public interface KgssService {

	/**
	 * Retrieve key from kgss.
	 * 
	 * @param keyId
	 *            the key id
	 * @param myEtk
	 *            the Pharmacy etk
	 * @param kgssEtk
	 *            the KGSS etk
	 * @return the key result
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	KeyResult retrieveKeyFromKgss(byte[] keyId, byte[] myEtk, byte[] kgssEtk) throws IntegrationModuleException;

	/**
	 * Retrieve new key.
	 * 
	 * @param etkKgss
	 *            the etk kgss
	 * @param credentialTypes
	 *            the credential types
	 * @param prescriberId
	 *            the prescriber id
	 * @param executorId
	 *            the executor id
	 * @param patientId
	 *            the patient id
	 * @param myEtk
	 *            the my etk
	 * @return the byte[] key
	 * @throws TechnicalConnectorException
	 *             the technical connector exception
	 */
	KeyResult retrieveNewKey(byte[] etkKgss, List<String> credentialTypes, String prescriberId, String executorId, String patientId, byte[] myEtk) throws IntegrationModuleException;
}
