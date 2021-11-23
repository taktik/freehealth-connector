package be.business.connector.recipe.prescriber;

import static be.business.connector.recipe.utils.RidValidator.validateRid;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.validation.Schema;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.OnlineProperties;
import be.business.connector.core.utils.OnlinePropertiesHolder;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.core.utils.SessionValidator;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceDevV4Impl;
import be.business.connector.recipe.utils.PrescriberEncryptionUtils;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.ehealth.technicalconnector.session.Session;
import be.ehealth.technicalconnector.session.SessionItem;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListFeedbacksResponse;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResponse;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.RevokePrescriptionResponse;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResponse;
import be.recipe.services.prescriber.ValidationPropertiesResult;

/**
 * The Class PrescriberIntegrationModuleDevV4Impl.
 */
public class PrescriberIntegrationModuleDevV4Impl extends AbstractPrescriberIntegrationModule implements PrescriberIntegrationModuleDevV4 {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PrescriberIntegrationModuleDevV4Impl.class);

	private final PrescriberEncryptionUtils encryptionUtils;

	/**
	 * Instantiates a new prescriber integration module V 4 impl.
	 *
	 * @ the integration module exception
	 */
	public PrescriberIntegrationModuleDevV4Impl() {
		encryptionUtils = new PrescriberEncryptionUtils(PropertyHandler.getInstance(), EncryptionUtils.getInstance(), keyCache);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#createPrescription")
	public String createPrescription(final boolean feedbackRequested, final String patientId, final byte[] prescription,
			final String prescriptionType) {
		return createPrescription(feedbackRequested, patientId, prescription, prescriptionType, null, getDefaultExpirationDate());

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#createPrescription")
	public String createPrescription(final boolean feedbackRequested, final String patientId, final byte[] prescription,
			final String prescriptionType, final String visi, final String expirationDate) {
		ApplicationConfig.getInstance().assertValidSession();

		ValidationUtils.validatePatientId(patientId);
		ValidationUtils.validateVisi(visi, false);
		try {
			final PropertyHandler propertyHandler = PropertyHandler.getInstance();
			ValidationUtils.validateExpirationDate(expirationDate);
			validateKmehr(prescription, prescriptionType, expirationDate);
			// init helper
			final MarshallerHelper<CreatePrescriptionResult, CreatePrescriptionParam> helper = new MarshallerHelper<>(CreatePrescriptionResult.class,
					CreatePrescriptionParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create sealed prescription
			byte[] message = IOUtils.compress(prescription);

			final KeyResult key = getNewKey(patientId, prescriptionType);
			message = sealPrescriptionForUnknown(key, message);

			// create sealed content
			final CreatePrescriptionParam params = new CreatePrescriptionParam();
			params.setPrescription(message);
			params.setPrescriptionType(prescriptionType);
			params.setFeedbackRequested(feedbackRequested);
			params.setKeyId(key.getKeyId());
			params.setSymmKey(getSymmKey().getEncoded());
			params.setPatientId(patientId);

			// New parameters since V4: start
			params.setExpirationDate(getDefaultExpirationDate(expirationDate));
			params.setVision(visi);
			// New parameters since V4: end

			// create request
			final CreatePrescription request = new CreatePrescription();
			request.setCreatePrescriptionParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params)));

			/** Added because we call directly recipe, without ehealth: begin **/
			request.setKeyId(key.getKeyId());// Is this still required?
			request.setPrescriptionType(prescriptionType);
			request.setDocumentId(generateRid(prescriptionType));
			/** Added because we call directly recipe, without ehealth: end **/

			// New parameters since V4: start
			request.setPrescriptionVersion(PropertyHandler.getInstance().getProperty("prescription.version"));
			request.setReferenceSourceVersion(extractReferenceSourceVersionFromKmehr(prescription));
			request.setProgramIdentification(propertyHandler.getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());
			// New parameters since V4: end

			// Create a "dummy" session for development on localhost. which mocks eHealth
			final SessionItem sessionItem = Session.getInstance().getSession();
			SessionValidator.assertValidSession(sessionItem);
			request.setSecurityToken(sessionItem.getSAMLToken().getAssertion());

			// WS call
			final CreatePrescriptionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().createPrescription(request);

			// unseal response
			final CreatePrescriptionResult result = helper.unsealWithSymmKey(response.getCreatePrescriptionResultSealed(), getSymmKey());
			checkStatus(result);

			return result.getRid();
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}

		return null;
	}

	private String getDefaultExpirationDate() {
		return getDefaultExpirationDate(null);
	}

	private String getDefaultExpirationDate(final String expirationDate) {
		if (StringUtils.isBlank(expirationDate)) {
			final Calendar defaultExpirationDate = Calendar.getInstance();
			defaultExpirationDate.add(Calendar.MONTH, 3);
			final String pattern = "yyyy-MM-dd";
			final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(defaultExpirationDate.getTime());
		}
		return expirationDate;
	}

	/**
	 * Gets the generated rid.
	 *
	 * @return the generated rid
	 */
	private String generateRid(final String prescriptionType) {
		String rid = "BE" + prescriptionType + "JNT" + RandomStringUtils.random(5, true, false).toUpperCase();
		rid = rid.replace('I', 'J').replace('O', 'A').replace('U', 'V');
		return rid;
	}

	/**
	 * Prepare create prescription.
	 *
	 * @param patientId
	 *            the patient id
	 * @param prescriptionType
	 *            the prescription type @ the integration module exception
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "PrescriberIntegrationModule#prepareCreatePrescription")
	public void prepareCreatePrescription(final String patientId, final String prescriptionType) {
		ApplicationConfig.getInstance().assertValidSession();

		getEtkHelper().getRecipe_ETK();
		getEtkHelper().getKGSS_ETK();
		getEtkHelper().getSystemETK();

		if (!keyCache.containsKey(patientId)) {
			keyCache.put(patientId, getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), null, patientId,
					getEtkHelper().getSystemETK().get(0).getEncoded()));
		}
		try {
			EncryptionUtils.getInstance().initSealing();
			EncryptionUtils.getInstance().initUnsealing();
		} catch (final Exception e) {
			LOG.error("Failed to store the online properties to disk", e);
		}
	}

	/**
	 * Gets the prescription.
	 *
	 * @param rid
	 *            the rid
	 * @return the prescription @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getPrescription")
	@Override
	public GetPrescriptionForPrescriberResult getPrescription(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		try {
			// init helper
			final MarshallerHelper<GetPrescriptionForPrescriberResult, GetPrescriptionForPrescriberParam> helper = new MarshallerHelper<GetPrescriptionForPrescriberResult, GetPrescriptionForPrescriberParam>(
					GetPrescriptionForPrescriberResult.class, GetPrescriptionForPrescriberParam.class);
			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create sealed request
			final GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
			param.setRid(rid);
			param.setSymmKey(getSymmKey().getEncoded());

			// build request
			final GetPrescriptionForPrescriber request = new GetPrescriptionForPrescriber();
			request.setGetPrescriptionForPrescriberParamSealed((sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());
			// call sealed WS
			GetPrescriptionForPrescriberResponse response = null;
			try {
				response = RecipePrescriberServiceDevV4Impl.getInstance().getPrescriptionForPrescriber(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}
			// unseal WS response
			final GetPrescriptionForPrescriberResult result = helper.unsealWithSymmKey(response.getGetPrescriptionForPrescriberResultSealed(),
					getSymmKey());
			checkStatus(result);
			final KeyResult key = getKeyFromKgss(result.getEncryptionKeyId(), getEtkHelper().getSystemETK().get(0).getEncoded());
			final byte[] unsealedPrescription = IOUtils.decompress(unsealPrescriptionForUnknown(key, result.getPrescription()));
			result.setPrescription(unsealedPrescription);
			return result;
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Cancel prescription.
	 *
	 * @param rid
	 *            the rid
	 * @param reason
	 *            the reason @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#revokePrescription")
	@Override
	public void revokePrescription(final String rid, final String reason) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();

		try {
			// init helper
			final MarshallerHelper<Object, RevokePrescriptionParam> helper = new MarshallerHelper<Object, RevokePrescriptionParam>(Object.class,
					RevokePrescriptionParam.class);

			// get Recipe ETK
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create params
			final RevokePrescriptionParam params = new RevokePrescriptionParam();
			params.setRid(rid);
			params.setReason(reason);
			params.setSymmKey(getSymmKey().getEncoded());

			// create request
			final RevokePrescription request = new RevokePrescription();
			request.setRevokePrescriptionParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params)));
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			// call WS
			try {
				final RevokePrescriptionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().revokePrescription(request);
				final MarshallerHelper<RevokePrescriptionResult, Object> marshaller = new MarshallerHelper<>(RevokePrescriptionResult.class,
						Object.class);
				final RevokePrescriptionResult result = marshaller.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}

	}

	/**
	 * Address prescription.
	 *
	 * @param notificationText
	 *            the notification text
	 * @param patientId
	 *            the patient id
	 * @param executorId
	 *            the executor id @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "PrescriberIntegrationModule#sendNotification")
	@Override
	public void sendNotification(final byte[] notificationText, final String patientId, final String executorId) {
		ValidationUtils.validatePatientId(patientId);
		ApplicationConfig.getInstance().assertValidSession();
		try {
			getKmehrHelper().assertValidNotification(notificationText);
			ValidationUtils.validatePatientId(patientId);

			// init helper
			final MarshallerHelper<Object, SendNotificationParam> helper = new MarshallerHelper<>(Object.class, SendNotificationParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// get recipient etk
			final List<EncryptionToken> etkRecipients = getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, executorId);

			final byte[] notificationZip = IOUtils.compress(notificationText);

			for (int i = 0; i < etkRecipients.size(); i++) {
				final EncryptionToken etkRecipient = etkRecipients.get(0);

				final byte[] notificationSealed = sealNotification(etkRecipient, notificationZip);

				// create param
				final SendNotificationParam param = new SendNotificationParam();
				param.setContent(notificationSealed);
				param.setExecutorId(executorId);
				param.setPatientId(patientId);
				param.setSymmKey(getSymmKey().getEncoded());

				// create request
				final SendNotification request = new SendNotification();
				request.setSendNotificationParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param)));
				request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
				request.setMguid(UUID.randomUUID().toString());

				// call sealed WS
				try {
					final SendNotificationResponse response = RecipePrescriberServiceDevV4Impl.getInstance().sendNotification(request);
					final MarshallerHelper<SendNotificationResult, SendNotificationResult> helper1 = new MarshallerHelper<>(
							SendNotificationResult.class, SendNotificationResult.class);
					final SendNotificationResult result = helper1.unsealWithSymmKey(response.getSendNotificationResultSealed(), getSymmKey());
					checkStatus(result);
				} catch (final ClientTransportException cte) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
				}
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
	}

	/**
	 * Update feedback flag.
	 *
	 * @param rid
	 *            the rid
	 * @param feedbackAllowed
	 *            the feedback allowed @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#updateFeedbackFlag")
	@Override
	public void updateFeedbackFlag(final String rid, final boolean feedbackAllowed) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		try {

			// init helper
			final MarshallerHelper<Object, UpdateFeedbackFlagParam> helper = new MarshallerHelper<>(Object.class, UpdateFeedbackFlagParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create param
			final UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
			param.setAllowFeedback(feedbackAllowed);
			param.setRid(rid);
			param.setSymmKey(getSymmKey().getEncoded());

			final UpdateFeedbackFlag request = new UpdateFeedbackFlag();
			request.setUpdateFeedbackFlagParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param)));
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			// call sealed WS
			try {
				final UpdateFeedbackFlagResponse response = RecipePrescriberServiceDevV4Impl.getInstance().putFeedbackFlag(request);
				final MarshallerHelper<UpdateFeedbackFlagResult, UpdateFeedbackFlagResult> helper1 = new MarshallerHelper<>(
						UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
				final UpdateFeedbackFlagResult result = helper1.unsealWithSymmKey(response.getUpdateFeedbackFlagResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#listFeedback")
	@Override
	public List<ListFeedbackItem> listFeedback(final boolean readFlag) {
		ApplicationConfig.getInstance().assertValidSession();

		try {
			// init helper
			final MarshallerHelper<ListFeedbacksResult, ListFeedbacksParam> helper = new MarshallerHelper<ListFeedbacksResult, ListFeedbacksParam>(
					ListFeedbacksResult.class, ListFeedbacksParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create param
			final ListFeedbacksParam param = new ListFeedbacksParam();
			param.setReadFlag(readFlag);
			param.setSymmKey(getSymmKey().getEncoded());

			// create request
			final ListFeedbacks request = new ListFeedbacks();
			request.setListFeedbacksParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param)));
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			// call sealed WS
			ListFeedbacksResponse response = null;

			try {
				response = RecipePrescriberServiceDevV4Impl.getInstance().listFeedbacks(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}
			final ListFeedbacksResult result = helper.unsealWithSymmKey(response.getListFeedbacksResultSealed(), getSymmKey());
			checkStatus(result);

			// unseal WS response
			final List<ListFeedbackItem> feedbacks = result.getFeedbacks();

			for (int i = 0; i < feedbacks.size(); i++) {
				final be.business.connector.recipe.prescriber.domain.ListFeedbackItem item = new be.business.connector.recipe.prescriber.domain.ListFeedbackItem(
						feedbacks.get(i));
				byte[] content = item.getContent();
				try {
					content = unsealFeedback(content);
					content = content == null ? content : IOUtils.decompress(content);
					item.setContent(content);
				} catch (final Throwable t) {
					item.setLinkedException(t);
				}
				feedbacks.set(i, item);
			}
			return feedbacks;

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getData(ValidationPropertiesParam)")
	@Override
	public ValidationPropertiesResult getData(final ValidationPropertiesParam param) {
		if (!OnlineProperties.isLoaded()) {
			ApplicationConfig.getInstance().assertValidSession();
			try {
				try {
					// 1) Get the properties from the server
					final ValidationProperties request = getValidationProperties(param);
					final ValidationPropertiesResponse response = RecipePrescriberServiceDevV4Impl.getInstance().getValidationProperties(request);
					final ValidationPropertiesResult result = unsealValidationPropertiesResponse(response);
					checkStatus(result);
					final OnlineProperties onlineProperties = OnlinePropertiesHolder.getInstance();
					// Replace the default properties with the default ones
					final ValidationPropertiesResult.Properties properties = result.getProperties();
					final String clientVersion = readPropertiesVersionFromDisk();
					if (result.getServerPropertiesVersion() == null
							|| result.getServerPropertiesVersion() != null && result.getServerPropertiesVersion().equals("")) {
						deleteOnlineProperties();
					} else if (result.getServerPropertiesVersion() != null && clientVersion != null
							&& !result.getServerPropertiesVersion().equals(clientVersion)) {
						deleteOnlineProperties();
					}
					if (StringUtils.isNotBlank(result.getServerPropertiesVersion()) && !result.getServerPropertiesVersion().equals(clientVersion)) {

						final Map<String, String> targetProperties = new HashMap<>();
						if (properties != null && CollectionUtils.isNotEmpty(properties.getEntries())) {
							for (final ValidationPropertiesResult.Properties.Entry obj : properties.getEntries()) {
								targetProperties.put(obj.getKey(), obj.getValue());
							}
							onlineProperties.setProperties(targetProperties);

						}
						// Replace the default xsd files with the default ones
						if (result.getXsdValidationFiles() != null && CollectionUtils.isNotEmpty(result.getXsdValidationFiles().getEntries())) {
							final ValidationPropertiesResult.XsdValidationFiles xsdFiles = result.getXsdValidationFiles();
							final Map<String, byte[]> targetMap = new HashMap<>();
							for (final ValidationPropertiesResult.XsdValidationFiles.Entry item : xsdFiles.getEntries()) {
								targetMap.put(item.getKey(), item.getValue());
							}
							onlineProperties.setXsdValidationFiles(targetMap);
							OnlinePropertiesHolder.setXsdSet(true);
							storeXsdsOnDisk(targetMap);
							for (final ValidationPropertiesResult.XsdValidationFiles.Entry item : xsdFiles.getEntries()) {
								targetProperties.put(item.getKey().split(":")[0].replace("_", "."),
										getPropertyHandler().getProperty("online.xsd.path") + File.separator + item.getKey().split(":")[0]
												+ File.separator + item.getKey().split(":")[1]);
							}
						}
						// 2) Store the properties to disk for later use.
						storePropertiesVersionToDisk(result.getServerPropertiesVersion());
						storePropertiesOnDisk(targetProperties);
						OnlinePropertiesHolder.reloadProperties();
					} else {
						readFromDisk();
					}
					return result;
				} catch (final Exception cte) {
					// 3) If the online-check fails, read the properties from disk (which where stored by step 2)
					readFromDisk();
				}
			} catch (final Throwable t) {
				Exceptionutils.errorHandler(t);
			} finally {
				OnlineProperties.setLoaded(true);
			}
		}
		return null;

	}

	private void deleteOnlineProperties() {
		final String directory = getPropertyHandler().getProperty("online.properties.file");
		try {
			FileUtils.deleteDirectory(new File(directory));
		} catch (final IOException e) {
			LOG.error("Failed to delete online properties from local cache", e);
		}
	}

	/**
	 * Gets the validation properties request.
	 *
	 * @param param
	 *            the data
	 * @return the validation properties request @ the integration module exception
	 */
	protected ValidationProperties getValidationProperties(final ValidationPropertiesParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final ValidationProperties request = new ValidationProperties();
		request.setValidationPropertiesParamSealed(getSealedData(param));
		request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setMguid(UUID.randomUUID().toString());
		return request;
	}

	private void storePropertiesOnDisk(final Map<String, String> map) {
		if (CollectionUtils.isNotEmpty(map.values())) {
			try {
				final Properties temp = new Properties();
				for (final String key : map.keySet()) {
					temp.put(key, map.get(key));
				}
				temp.store(new FileOutputStream(getPropertyHandler().getProperty("online.properties.file") + "/online.properties.txt"), null);
			} catch (final IOException e) {
				LOG.error("Failed to store the online properties to disk", e);
			}
		}
	}

	private void storeXsdsOnDisk(final Map<String, byte[]> xsdValidationFile) {
		if (CollectionUtils.isNotEmpty(xsdValidationFile.values())) {
			for (final String key : xsdValidationFile.keySet()) {
				try {
					unzip(xsdValidationFile.get(key), getPropertyHandler().getProperty("online.xsd.path") + File.separator + key.split(":")[0]);
				} catch (final Exception e) {
					LOG.error("Failed to store the online xsds to disk", e);
				}
			}
		}
	}

	private static void unzip(final byte[] compressedData, final String destDir) {
		final File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists()) {
			dir.mkdirs();

		}
		ByteArrayInputStream fis;
		// buffer for read and write data to file
		final byte[] buffer = new byte[1024];
		try {
			fis = new ByteArrayInputStream(compressedData);
			final ZipInputStream zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				final String fileName = ze.getName();
				final File newFile = new File(destDir + File.separator + fileName);
				LOG.debug("Unzipping to " + newFile.getAbsolutePath());
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				final FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
			zis.close();
			fis.close();
		} catch (final IOException e) {
			LOG.error("Error while unzipping the xsd files from the server: ", e);
		}

	}

	private void readFromDisk() throws Exception {
		try {
			final Properties prop = new Properties();
			final File file = new File(getPropertyHandler().getProperty("online.properties.file") + "/online.properties.txt");
			if (file != null && file.exists()) {
				// Read property-files from disk
				prop.load(new FileInputStream(file));
				final OnlineProperties onlineProperties = OnlinePropertiesHolder.getInstance();
				final Map<String, String> targetProperties = new HashMap<>();
				final Enumeration<Object> iterator = prop.keys();
				while (iterator.hasMoreElements()) {
					final String key = (String) iterator.nextElement();
					targetProperties.put(key, prop.getProperty(key));
				}
				onlineProperties.setProperties(targetProperties);
				// Reload the peroperties
				OnlinePropertiesHolder.reloadProperties();
			}
		} catch (Exception e) {
			LOG.info("Failed to read online properties from disk, using default properties as default.", e);
		}
	}

	/**
	 * Write last date to disk.
	 *
	 * @param data
	 *            the data
	 * @param lastSyncDate
	 *            the last sync date @ the integration module exception
	 */
	protected void storePropertiesVersionToDisk(final String serverPropertiesVersion) {
		try {
			final String path = getPropertyHandler().getProperty("online.properties.version.path");
			final File file = new File(path + "/online.properties.version.txt");
			FileUtils.writeStringToFile(file, serverPropertiesVersion);
		} catch (final IOException e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), e);
		}
	}

	/**
	 * Read last date to disk.
	 *
	 * @return the calendar @ the integration module exception
	 */
	private String readPropertiesVersionFromDisk() {
		try {
			final String path = getPropertyHandler().getProperty("online.properties.version.path");
			final File file = new File(path + "/online.properties.version.txt");
			if (!file.exists()) {
				return getPropertyHandler().getProperty("online.properties.version");
			}
			try (final BufferedReader br = new BufferedReader(new FileReader(file))) {
				final String st = br.readLine();
				if (st == null) {
					return getPropertyHandler().getProperty("online.properties.version");
				}
				return st;
			}
		} catch (final Exception e) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#getData(GetPrescriptionStatusParam)")
	@Override
	public GetPrescriptionStatusResult getData(final GetPrescriptionStatusParam param) {
		validateRid(param.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final GetPrescriptionStatus getPrescriptionStatus = getGetPrescriptionStatus(param);
			try {
				final GetPrescriptionStatusResponse response = RecipePrescriberServiceDevV4Impl.getInstance()
						.getPrescriptionStatus(getPrescriptionStatus);
				final GetPrescriptionStatusResult result = unsealGetPrescriptionStatusResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Gets the gets the prescription status request.
	 *
	 * @param param
	 *            the data
	 * @return the gets the prescription status request @ the integration module exception
	 */
	protected GetPrescriptionStatus getGetPrescriptionStatus(final GetPrescriptionStatusParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final GetPrescriptionStatus request = new GetPrescriptionStatus();
		request.setGetPrescriptionStatusParamSealed(getSealedData(param));
		request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setMguid(UUID.randomUUID().toString());
		return request;
	}

	/**
	 * Unseal get prescription status response.
	 *
	 * @param response
	 *            the get data response
	 * @return the gets the prescription status response @ the integration module exception
	 */
	private GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(final GetPrescriptionStatusResponse response) {
		final MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper<>(GetPrescriptionStatusResult.class,
				Object.class);
		return marshaller.unsealWithSymmKey(response.getGetPrescriptionStatusResultSealed(), getSymmKey());
	}

	/**
	 * Unseal validation properties response.
	 *
	 * @param getDataResponse
	 *            the get data response
	 * @return the validation properties result @ the integration module exception
	 */
	private ValidationPropertiesResult unsealValidationPropertiesResponse(final ValidationPropertiesResponse response) {
		final MarshallerHelper<ValidationPropertiesResult, Schema> marshaller = new MarshallerHelper<>(ValidationPropertiesResult.class,
				Schema.class);
		return marshaller.unsealWithSymmKey(response.getValidationPropertiesResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#getData(ListPrescriptionHistoryParam)")
	@Override
	public ListRidsHistoryResult getData(final ListRidsHistoryParam param) {
		ApplicationConfig.getInstance().assertValidSession();
		ValidationUtils.validatePatientId(param.getPatientId());
		try {
			final ListRidsHistory listPrescriptionHistory = getListPrescriptionHistoryRequest(param);
			try {
				final ListRidsHistoryResponse response = RecipePrescriberServiceDevV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
				final ListRidsHistoryResult result = unsealListPrescriptionHistoryResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Gets the list prescription history request.
	 *
	 * @param listRidHistoryParam
	 *            the data
	 * @return the list prescription history request @ the integration module exception
	 */
	private ListRidsHistory getListPrescriptionHistoryRequest(final ListRidsHistoryParam listRidHistoryParam) {
		listRidHistoryParam.setSymmKey(getSymmKey().getEncoded());
		final ListRidsHistory listRidsHistory = new ListRidsHistory();
		listRidsHistory.setListRidsHistoryParamSealed(getSealedData(listRidHistoryParam));
		listRidsHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		listRidsHistory.setMguid(UUID.randomUUID().toString());
		return listRidsHistory;
	}

	/**
	 * Unseal list prescription history response.
	 *
	 * @param response
	 *            the get data response
	 * @return the list prescription history response @ the integration module exception
	 */
	private ListRidsHistoryResult unsealListPrescriptionHistoryResponse(final ListRidsHistoryResponse response) {
		final MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper<>(ListRidsHistoryResult.class, Object.class);
		return marshaller.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#putData(PutVisionParam)")
	@Override
	public PutVisionResult putData(final PutVisionParam param) {
		validateRid(param.getRid());
		ValidationUtils.validateVisi(param.getVision(), false);
		ApplicationConfig.getInstance().assertValidSession();
		try {
			try {
				final PutVision putVision = putVisionRequest(param);
				final PutVisionResponse response = RecipePrescriberServiceDevV4Impl.getInstance().putVisionForPrescriber(putVision);
				final PutVisionResult result = unsealPutVisionResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Put vision request.
	 *
	 * @param putVisionParam
	 *            the put vision param
	 * @return the put data @ the integration module exception
	 */
	protected PutVision putVisionRequest(final PutVisionParam putVisionParam) {
		putVisionParam.setSymmKey(getSymmKey().getEncoded());
		final PutVision putVision = new PutVision();
		putVision.setPutVisionParamSealed(getSealedData(putVisionParam));
		putVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		putVision.setMguid(UUID.randomUUID().toString());
		return putVision;
	}

	/**
	 * Unseal put vision.
	 *
	 * @param response
	 *            the data response
	 * @return the put vision response @ the integration module exception
	 */
	private PutVisionResult unsealPutVisionResponse(final PutVisionResponse response) {
		final MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper<>(PutVisionResult.class, Object.class);
		final PutVisionResult result = marshaller.unsealWithSymmKey(response.getPutVisionResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModuleV4#getData(ListOpenRidsParam)")
	public ListOpenRidsResult getData(final ListOpenRidsParam param) {
		ApplicationConfig.getInstance().assertValidSession();
		ValidationUtils.validatePatientId(param.getPatientId());
		try {
			final ListOpenRids listOpenPrescriptions = getListOpenRids(param);
			try {
				final ListOpenRidsResponse response = RecipePrescriberServiceDevV4Impl.getInstance().listOpenRids(listOpenPrescriptions);
				final ListOpenRidsResult result = unsealListOpenRidsResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Gets the list open prescriptions request.
	 *
	 * @param param
	 *            the data
	 * @return the list open prescriptions request @ the integration module exception
	 */
	protected ListOpenRids getListOpenRids(final ListOpenRidsParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final ListOpenRids request = new ListOpenRids();
		request.setListOpenRidsParamSealed(getSealedData(param));
		request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setMguid(UUID.randomUUID().toString());
		return request;
	}

	/**
	 * Unseal list open prescriptions response.
	 *
	 * @param response
	 *            the get data response
	 * @return the list open prescriptions result @ the integration module exception
	 */
	private ListOpenRidsResult unsealListOpenRidsResponse(final ListOpenRidsResponse response) {
		final MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper<>(ListOpenRidsResult.class, Object.class);
		return marshaller.unsealWithSymmKey(response.getListOpenRidsResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CreatePrescriptionDTO> createPrescriptions(List<CreatePrescriptionDTO> dtos) {
		ApplicationConfig.getInstance().assertValidSession();
		validateCreatePrescriptionDTOs(dtos);
		return encryptionUtils.doEncryptions(dtos, this);
	}
}