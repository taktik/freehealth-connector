package be.business.connector.recipe.prescriber;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jvnet.jaxb2_commons.lang.Validate;
import org.perf4j.aop.Profiled;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.ehealth.services.KgssService;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.exceptions.IntegrationModuleValidationException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.STSHelper;
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.business.connector.recipe.utils.KmehrHelper;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.commons.core.v1.IdentifierType;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.ValidationPropertiesParam;

/**
 * The Class AbstractPrescriberIntegrationModule.
 */
public abstract class AbstractPrescriberIntegrationModule extends AbstractIntegrationModule {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(AbstractPrescriberIntegrationModule.class);

	/** The prescription cache. */
	private final Map<String, String> prescriptionCache = new HashMap<>();

	/** The kmehr helper. */
	private final KmehrHelper kmehrHelper = new KmehrHelper();

	/** The key cache. */
	protected Map<String, KeyResult> keyCache = new HashMap<>();

	/** The kgss service. */
	protected KgssService kgssService = be.business.connector.core.ehealth.services.KgssServiceImpl.getInstance();

	/**
	 * Instantiates a new abstract prescriber integration module.
	 *
	 * @ the integration module exception
	 */
	public AbstractPrescriberIntegrationModule() {
		super();
		LOG.info("*************** Prescriber System module init correctly *******************");
	}

	/**
	 * Check status.
	 *
	 * @param response
	 *            the response @ the integration module exception
	 */
	public void checkStatus(final ResponseType response) {
		if (!EHEALTH_SUCCESS_CODE_100.equals(response.getStatus().getCode()) && !EHEALTH_SUCCESS_CODE_200.equals(response.getStatus().getCode())) {
			LOG.error("Error Status received : " + response.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(response.getStatus()));
		}
	}

	/**
	 * Check status.
	 *
	 * @param response
	 *            the response @ the integration module exception
	 */
	public void checkStatus(final be.recipe.services.core.ResponseType response) {
		if (!EHEALTH_SUCCESS_CODE_100.equals(response.getStatus().getCode()) && !EHEALTH_SUCCESS_CODE_200.equals(response.getStatus().getCode())) {
			LOG.error("Error Status received : " + response.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(response.getStatus()), response);
		}
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (!status.getMessages().isEmpty()) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final be.recipe.services.core.StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final be.recipe.services.core.LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (!status.getMessages().isEmpty()) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Creates the identifier type.
	 *
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @return the identifier type
	 */
	protected IdentifierType createIdentifierType(final String id, final String type) {
		final IdentifierType ident = new IdentifierType();
		ident.setId(id);
		ident.setType(type);
		return ident;
	}

	/**
	 * Sets the personal password.
	 *
	 * @param personalPassword
	 *            the new personal password @ the integration module exception
	 */
	public void setPersonalPassword(final String personalPassword) {
		final SessionItem sessionItem = Session.getInstance().getSession();
		SessionValidator.assertValidSession(sessionItem);

		try {
			final String niss = STSHelper.getNiss(sessionItem.getSAMLToken().getAssertion());
			final String nihii = STSHelper.getNihii(sessionItem.getSAMLToken().getAssertion());

			final EncryptionUtils encryptionUtils = EncryptionUtils.getInstance();
			encryptionUtils.unlockPersonalKey(StringUtils.isNotBlank(niss) ? niss : nihii, personalPassword);
			dataUnsealer = encryptionUtils.initUnsealing();
			final List<EncryptionToken> tokens = getEtkHelper().getEtks(getIdentifierType(), StandaloneRequestorProvider.getRequestorIdInformation());
			encryptionUtils.verifyDecryption(tokens.get(0));
		} catch (final Exception e) {
			throw new IntegrationModuleException(e);
		}

	}

	private KgssIdentifierType getIdentifierType() {
		final String type = STSHelper.getType(Session.getInstance().getSession().getSAMLToken().getAssertion());
		return type.equals("HOSPITAL") ? KgssIdentifierType.NIHII_HOSPITAL : KgssIdentifierType.NIHII;
	}

	/**
	 * Gets the kmehr helper.
	 *
	 * @return the kmehr helper
	 */
	protected KmehrHelper getKmehrHelper() {
		return kmehrHelper;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param validationPropertiesParam
	 *            the validation properties param
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final ValidationPropertiesParam validationPropertiesParam) {
		return sealForRecipe(validationPropertiesParam, ValidationPropertiesParam.class);
	}

	/**
	 * Seal for recipe.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 * @return the byte[] @ the integration module exception
	 */
	private <T> byte[] sealForRecipe(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		final EncryptionToken etkRecipe = getEtkHelper().getRecipe_ETK().get(0);
		return sealRequest(etkRecipe, helper.toXMLByteArray(data));
	}

	/**
	 * Gets the new key.
	 *
	 * @param patientId
	 *            the patient id
	 * @param prescriptionType
	 *            the prescription type
	 * @return the new key @ the integration module exception
	 */
	public KeyResult getNewKey(final String patientId, final String prescriptionType) {
		if (keyCache.containsKey(patientId)) {
			return keyCache.get(patientId);
		} else {
			KeyResult key = getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), null, patientId,
					getEtkHelper().getSystemETK().get(0).getEncoded());
			keyCache.put(patientId, key);
			return key;
		}
	}

	/**
	 * Gets the new key from kgss.
	 *
	 * @param prescriptionType
	 *            the prescription type
	 * @param prescriberId
	 *            the prescriber id
	 * @param executorId
	 *            the executor id
	 * @param patientId
	 *            the patient id
	 * @param myEtk
	 *            the my etk
	 * @return the new key from kgss @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getNewKeyFromKgss")
	protected KeyResult getNewKeyFromKgss(final String prescriptionType, final String prescriberId, final String executorId, final String patientId,
			final byte[] myEtk) {
		// For test, when a sim key is specified in the config
		if (getPropertyHandler().hasProperty("test_kgss_key")) {
			return getKeyFromKgss(null, null);
		}

		final EncryptionToken etkKgss = getEtkHelper().getKGSS_ETK().get(0);
		final List<String> credentialTypes = getPropertyHandler().getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType);

		try {
			return kgssService.retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Seal prescription for unknown.
	 *
	 * @param key
	 *            the key
	 * @param messageToProtect
	 *            the message to protect
	 * @return the byte[] @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#sealPrescriptionForUnknown")
	public byte[] sealPrescriptionForUnknown(final KeyResult key, final byte[] messageToProtect) {
		return Crypto.seal(messageToProtect, key.getSecretKey(), key.getKeyId());
	}

	/**
	 * Unseal feedback.
	 *
	 * @param message
	 *            the message
	 * @return the byte[] @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#unsealFeedback")
	protected byte[] unsealFeedback(final byte[] message) {
		return unsealNotiffeed(message);
	}

	/**
	 * Seal notification.
	 *
	 * @param paramEncryptionToken
	 *            the param encryption token
	 * @param paramArrayOfByte
	 *            the param array of byte
	 * @return the byte[] @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#sealNotification")
	protected byte[] sealNotification(final EncryptionToken paramEncryptionToken, final byte[] paramArrayOfByte) {
		return Crypto.seal(paramEncryptionToken, paramArrayOfByte);
	}

	/**
	 * Marshall.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 * @return the byte[] @ the integration module exception
	 */
	private <T> byte[] marshall(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		return helper.toXMLByteArray(data);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listRidHistoryParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final ListRidsHistoryParam listRidHistoryParam) {
		return sealForRecipe(listRidHistoryParam, ListRidsHistoryParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final GetPrescriptionStatusParam request) {
		return sealForRecipe(request, GetPrescriptionStatusParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param putVisionParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final PutVisionParam putVisionParam) {
		return sealForRecipe(putVisionParam, PutVisionParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listOpenRidsParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final ListOpenRidsParam listOpenRidsParam) {
		return sealForRecipe(listOpenRidsParam, ListOpenRidsParam.class);
	}

	/**
	 * @param dtos
	 */
	protected void validateCreatePrescriptionDTOs(final List<CreatePrescriptionDTO> dtos) {
		try {
			Validate.notNull(dtos);
			Validate.notEmpty(dtos);
		} catch (Exception e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.list.empty.or.null"));
		}
		if (dtos.size() > 30) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.too.many.items"));
		}
		final List<Integer> seqNbrs = new ArrayList<Integer>();
		for (final CreatePrescriptionDTO dto : dtos) {
			if (seqNbrs.contains(dto.getSequenceNumber())) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.validation.duplicate.sequencenumbers"));
			}
			seqNbrs.add(dto.getSequenceNumber());
		}

	}

	/**
	 * Perform validation.
	 *
	 * @param prescription
	 *            the prescription
	 * @param prescriptionType
	 *            the prescription type
	 * @param expirationDateFromRequest
	 *            the expiration date from request @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4Impl#validateKmehr", logger = "org.perf4j.TimingLogger_Common")
	public void validateKmehr(final byte[] prescription, final String prescriptionType, final String expirationDateFromRequest) {
		final List<String> errors = new ArrayList<>();
		try {
			getKmehrHelper().assertValidKmehrPrescription(prescription, prescriptionType);
		} catch (final IntegrationModuleValidationException e) {
			errors.addAll(e.getValidationErrors());
		}
		validateExpirationDateFromKmehr(prescription, errors, expirationDateFromRequest);
		if (CollectionUtils.isNotEmpty(errors)) {
			LOG.info("******************************************************");
			for (final String error : errors) {
				LOG.info("Errors found in the kmehr:" + error);
			}
			LOG.info("******************************************************");
			throw new IntegrationModuleValidationException(I18nHelper.getLabel("error.xml.invalid"), errors);
		}
	}

	/**
	 * Validate expiration date from kmehr.
	 *
	 * @param xmlDocument
	 *            the xml document
	 * @param errors
	 *            the errors
	 * @param expirationDateFromRequest
	 *            the expiration date from request @ the integration module exception
	 */
	private void validateExpirationDateFromKmehr(final byte[] xmlDocument, final List<String> errors, final String expirationDateFromRequest) {
		try {
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(false);
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document kmehrDocument = builder.parse(new ByteArrayInputStream(xmlDocument));
			final PropertyHandler propertyHandler = PropertyHandler.getInstance();
			final XPath xpath = XPathFactory.newInstance().newXPath();
			final String xpathStr = propertyHandler.getProperty("expirationdate.xpath");
			final NodeList expirationDateNodeList = (NodeList) xpath.evaluate(xpathStr, kmehrDocument, XPathConstants.NODESET);
			if (expirationDateNodeList.item(0) != null) {
				final String expirationDateFromKmehr = expirationDateNodeList.item(0).getTextContent();
				if (!expirationDateFromKmehr.contentEquals(expirationDateFromRequest)) {
					errors.add(I18nHelper.getLabel("error.validation.expirationdate.different.message",
							new Object[] { expirationDateFromRequest, expirationDateFromKmehr }));
				}
			} else {
				errors.add(I18nHelper.getLabel("error.validation.expirationdate.kmehr"));
			}
		} catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException e) {
			Exceptionutils.errorHandler(e);
		}
	}

	/**
	 * Creates the secured content type.
	 *
	 * @param content
	 *            the content
	 * @return the secured content type
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4Impl#createSecuredContentType", logger = "org.perf4j.TimingLogger_Common")
	public SecuredContentType createSecuredContentType(final byte[] content) {
		final SecuredContentType secured = new SecuredContentType();
		secured.setSecuredContent(content);
		return secured;
	}
}