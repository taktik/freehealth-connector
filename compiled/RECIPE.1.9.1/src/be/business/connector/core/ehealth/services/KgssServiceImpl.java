package be.business.connector.core.ehealth.services;

import java.security.Key;
import java.util.Arrays;
import java.util.List;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.PersistentCache;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.UnsealConnectorException;
import be.ehealth.technicalconnector.service.ServiceFactory;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.etee.crypto.status.NotificationError;
import be.fgov.ehealth.etee.crypto.status.NotificationWarning;
import be.fgov.ehealth.etee.kgss._1_0.protocol.CredentialType;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetKeyResponseContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyRequestContent;
import be.fgov.ehealth.etee.kgss._1_0.protocol.GetNewKeyResponseContent;

/**
 * The Class EncryptionKeyServiceImpl.
 */
public class KgssServiceImpl implements KgssService {

	/**
	 * The Constant LOG.
	 */
	private static final Logger LOG = Logger.getLogger(KgssServiceImpl.class);

	/**
	 * The encryption key service.
	 */
	private static KgssService kgssService;

	/** The kgss key cache. */
	private final PersistentCache kgssKeyCache;

	/**
	 * Instantiates a new kgss service impl.
	 *
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	private KgssServiceImpl() throws IntegrationModuleException {
		kgssKeyCache = PersistentCache.getInstance();
	}

	/**
	 * Gets the singleton instance of KeyDepotServiceImpl.
	 *
	 * @return singleton instance of KeyDepotServiceImpl
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	public static KgssService getInstance() throws IntegrationModuleException {
		if (kgssService == null) {
			kgssService = new KgssServiceImpl();
		}
		return kgssService;
	}

	/**
	 * Override.
	 *
	 * @param keyId
	 *            the key id
	 * @param myEtk
	 *            the my etk
	 * @param kgssEtk
	 *            the kgss etk
	 * @return the key result
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.KgssServiceImpl#retrieveKeyFromKgss", logger = "org.perf4j.TimingLogger_Common")
	public KeyResult retrieveKeyFromKgss(final byte[] keyId, final byte[] myEtk, final byte[] kgssEtk) throws IntegrationModuleException {
		LOG.debug("KeyIdentifier : " + Arrays.toString(keyId));
		if (kgssKeyCache.containsKey(new String(keyId))) {
			LOG.debug("KGSSKeyCache item found");
			final byte[] key = kgssKeyCache.get(new String(keyId));
			return new KeyResult(new SecretKeySpec(key, "AES"), new String(keyId));
		}

		final GetKeyRequestContent getKeyRequestContent = new GetKeyRequestContent();
		Key key = null;

		if (myEtk != null) {
			// Mode1 : using ETK
			getKeyRequestContent.setETK(myEtk);
		} else {
			// Using sym Key
			try {
				final KeyGenerator keyGen = KeyGenerator.getInstance("AES");
				synchronized (keyGen) {
					key = keyGen.generateKey();
				}
			} catch (final Exception e) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.technical"), e);
			}
			getKeyRequestContent.setKeyEncryptionKey(key.getEncoded());
		}

		getKeyRequestContent.setKeyIdentifier(Base64.decodeBase64(keyId));

		try {
			final be.ehealth.technicalconnector.service.kgss.KgssService kgss = be.ehealth.technicalconnector.service.ServiceFactory.getKgssService();
			final SessionItem sessionItem = Session.getInstance().getSession();
			final GetKeyResponseContent getKeyResponseContent = kgss.getKey(getKeyRequestContent, sessionItem.getHolderOfKeyCredential(),
					sessionItem.getEncryptionCredential(), sessionItem.getSAMLToken().getAssertion(), sessionItem.getEncryptionPrivateKeys(),
					kgssEtk);
			final KeyResult keyResultToReturn = new KeyResult(new SecretKeySpec(getKeyResponseContent.getKey(), "AES"), new String(keyId));
			kgssKeyCache.put(new String(keyId), getKeyResponseContent.getKey());
			return keyResultToReturn;
		} catch (final SOAPFaultException se) {
			if (se.getFault() != null && se.getFault().getFaultCode() != null && se.getFault().getFaultCode().contains("InvalidSecurity")) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey"), se);
			} else {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.kgss.getKey.other"), se);
			}
		} catch (final ClientTransportException cte) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.kgss"), cte);
		} catch (final TechnicalConnectorException e) {
			LOG.error("Error retrieving key", e);
			throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.key"), e);
		}
	}

	/**
	 * Override.
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
	 * @return the key result
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.KgssServiceImpl#retrieveNewKey", logger = "org.perf4j.TimingLogger_Common")
	public KeyResult retrieveNewKey(final byte[] etkKgss, final List<String> credentialTypes, final String prescriberId, final String executorId,
			final String patientId, final byte[] myEtk) throws IntegrationModuleException {

		final GetNewKeyRequestContent req = new GetNewKeyRequestContent();
		req.setETK(myEtk);

		// --- Building the Access Control List
		final List<CredentialType> allowedReaders = req.getAllowedReaders();

		for (final String credentialTypeStr : credentialTypes) {
			final String[] atrs = credentialTypeStr.split(",");
			if (atrs.length != 3 && atrs.length != 2) {
				throw new IntegrationModuleException("Invalid property : kgss.createPrescription.ACL.XXX = " + credentialTypeStr);
			}

			String value = "";
			if (atrs.length == 3) {
				value = atrs[2];
				value = value.replaceAll("%PRESCRIBER_ID%", prescriberId);
				value = value.replaceAll("%EXECUTOR_ID%", executorId);
				value = value.replaceAll("%PATIENT_ID%", patientId);
			}

			final CredentialType ct = new CredentialType();
			ct.setNamespace(atrs[0]);
			ct.setName(atrs[1]);
			ct.getValues().add(value);

			allowedReaders.add(ct);
		}

		try {
			final be.ehealth.technicalconnector.service.kgss.KgssService kgss = ServiceFactory.getKgssService();
			final SessionItem sessionItem = Session.getInstance().getSession();

			final GetNewKeyResponseContent getNewKeyResponseContent = kgss.getNewKey(req, sessionItem.getEncryptionCredential(),
					sessionItem.getEncryptionPrivateKeys(), etkKgss);
			final byte[] keyResponse = getNewKeyResponseContent.getNewKey();
			final byte[] keyId = getNewKeyResponseContent.getNewKeyIdentifier();

			final KeyResult keyResultToReturn = new KeyResult(new SecretKeySpec(keyResponse, "AES"), new String(Base64.encodeBase64(keyId)));
			kgssKeyCache.put(keyResultToReturn.getKeyId(), keyResponse);
			return keyResultToReturn;
		} catch (final TechnicalConnectorException e) {
			LOG.error("Error retrieving new key", e);
			if (e instanceof UnsealConnectorException) {
				if (((UnsealConnectorException) e).getUnsealResult() != null) {
					final List<NotificationError> decryptionFailure = ((UnsealConnectorException) e).getUnsealResult().getErrors();
					for (final NotificationError error : decryptionFailure) {
						LOG.error("NotificationError: " + error.toString());
					}
					final List<NotificationWarning> warnings = ((UnsealConnectorException) e).getUnsealResult().getWarnings();
					for (final NotificationWarning warning : warnings) {
						LOG.error("NotificationWarning: " + warning.toString());
					}
				}
			}
			throw new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.new.key"), e);
		}
	}
}