package be.business.connector.core.ehealth.services;

import java.security.GeneralSecurityException;
import java.util.List;

import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;

/**
 * The Interface KeyDepotService.
 */
public interface KeyDepotService {

	/**
	 * Retrieve etk.
	 * 
	 * @param etkType
	 *            the etk type
	 * @param ektValue
	 *            the ekt value
	 * @param ektApplicationName
	 *            the ekt application name
	 * @return the byte[]
	 * @throws TechnicalConnectorException
	 *             the technical connector exception
	 */
	List<EncryptionToken> retrieveEtk(KgssIdentifierType etkType, String ektValue, String ektApplicationName) throws IntegrationModuleException, GeneralSecurityException;

}
