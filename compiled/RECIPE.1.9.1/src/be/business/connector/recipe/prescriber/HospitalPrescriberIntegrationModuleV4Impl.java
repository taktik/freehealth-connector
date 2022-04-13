package be.business.connector.recipe.prescriber;

import static be.business.connector.core.utils.RecipeConstants.PROGRAM_IDENTIFICATION;
import static be.business.connector.core.utils.RecipeConstants.YYYY_MM_DD;
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
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.validation.Schema;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
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
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceV4Impl;
import be.business.connector.recipe.utils.PrescriberEncryptionUtils;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationResponse;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResult;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberParam;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListFeedbacksParam;
import be.recipe.services.prescriber.ListFeedbacksResult;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.RevokePrescriptionParam;
import be.recipe.services.prescriber.RevokePrescriptionResult;
import be.recipe.services.prescriber.SendNotificationParam;
import be.recipe.services.prescriber.SendNotificationResult;
import be.recipe.services.prescriber.UpdateFeedbackFlagParam;
import be.recipe.services.prescriber.UpdateFeedbackFlagResult;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResult;

/**
 * The Class PrescriberIntegrationModuleV4Impl.
 */
public class HospitalPrescriberIntegrationModuleV4Impl extends AbstractPrescriberIntegrationModule implements PrescriberIntegrationModuleV4 {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(HospitalPrescriberIntegrationModuleV4Impl.class);

	private final PrescriberEncryptionUtils encryptionUtils;

	/**
	 * Instantiates a new {@link HospitalPrescriberIntegrationModuleV4Impl}.
	 *
	 * @ the IntegrationModuleException
	 */
	public HospitalPrescriberIntegrationModuleV4Impl() {
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

		ValidationUtils.validatePatientIdNotBlank(patientId);
		ValidationUtils.validatePatientId(patientId);
		ValidationUtils.validateVisi(visi, false);

		try {
			final PropertyHandler propertyHandler = PropertyHandler.getInstance();
			ValidationUtils.validateExpirationDate(expirationDate);
			validateKmehr(prescription, prescriptionType, expirationDate);
			// init helper
			final MarshallerHelper<CreatePrescriptionResult, CreatePrescriptionParam> helper = new MarshallerHelper<>(CreatePrescriptionResult.class,
					CreatePrescriptionParam.class);

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

			// New params for CreatePrescriptionParam in V4: begin
			params.setExpirationDate(expirationDate);
			params.setVision(visi);
			// New params for CreatePrescriptionParam in V4: end

			// create request
			final CreatePrescriptionRequest request = new CreatePrescriptionRequest();
			request.setSecuredCreatePrescriptionRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params))));
			request.setProgramId(propertyHandler.getProperty(PROGRAM_IDENTIFICATION));
			request.setId(getId());
			request.setIssueInstant(new DateTime());

			CreatePrescriptionAdministrativeInformationType adminValue = new CreatePrescriptionAdministrativeInformationType();
			adminValue.setKeyIdentifier(key.getKeyId().getBytes());
			adminValue.setPrescriptionVersion(PropertyHandler.getInstance().getProperty("prescription.version"));
			adminValue.setReferenceSourceVersion(extractReferenceSourceVersionFromKmehr(prescription));
			adminValue.setPrescriptionType(prescriptionType);
			request.setAdministrativeInformation(adminValue);

			// WS call
			final CreatePrescriptionResponse response = RecipePrescriberServiceV4Impl.getInstance().createPrescription(request);

			// unseal response
			final CreatePrescriptionResult result = helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(),
					getSymmKey());
			checkStatus(result);

			return result.getRid();
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}

		return null;
	}

	private String getDefaultExpirationDate() {
		final Calendar defaultExpirationDate = Calendar.getInstance();
		defaultExpirationDate.add(Calendar.MONTH, 3);
		final String pattern = YYYY_MM_DD;
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(defaultExpirationDate.getTime());
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
	 * Gets a prescription.
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

			// create sealed request
			final GetPrescriptionForPrescriberParam param = new GetPrescriptionForPrescriberParam();
			param.setRid(rid);
			param.setSymmKey(getSymmKey().getEncoded());

			// build request
			final GetPrescriptionRequest request = new GetPrescriptionRequest();
			request.setSecuredGetPrescriptionRequest(createSecuredContentType(sealForRecipe(param, GetPrescriptionForPrescriberParam.class)));
			request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call sealed WS
			GetPrescriptionResponse response = null;
			try {
				response = RecipePrescriberServiceV4Impl.getInstance().getPrescriptionForPrescriber(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}
			// unseal WS response
			final GetPrescriptionForPrescriberResult result = helper
					.unsealWithSymmKey(response.getSecuredGetPrescriptionResponse().getSecuredContent(), getSymmKey());
			checkStatus(result);
			// final KeyResult key = getKeyFromKgss(result.getEncryptionKeyId(), getEtkHelper().getSystemETK().get(0).getEncoded());
			final KeyResult key = getKeyFromKgss(result.getEncryptionKeyId(), getEtkHelper()
					.getEtks(KgssIdentifierType.NIHII_HOSPITAL, StandaloneRequestorProvider.getRequestorIdInformation()).get(0).getEncoded());
			final byte[] unsealedPrescription = IOUtils.decompress(unsealPrescriptionForUnknown(key, result.getPrescription()));
			result.setPrescription(unsealedPrescription);
			return result;
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Cancels a prescription.
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
			final RevokePrescriptionRequest request = new RevokePrescriptionRequest();
			request.setSecuredRevokePrescriptionRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params))));
			request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call WS
			try {
				final RevokePrescriptionResponse response = RecipePrescriberServiceV4Impl.getInstance().revokePrescription(request);
				final MarshallerHelper<RevokePrescriptionResult, Object> marshaller = new MarshallerHelper<>(RevokePrescriptionResult.class,
						Object.class);
				final RevokePrescriptionResult result = marshaller
						.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(), getSymmKey());
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
		ApplicationConfig.getInstance().assertValidSession();
		try {
			getKmehrHelper().assertValidNotification(notificationText);
			ValidationUtils.validatePatientId(patientId);

			// init helper
			final MarshallerHelper<Object, SendNotificationParam> helper = new MarshallerHelper<>(Object.class, SendNotificationParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// get recipient etk
			final List<EncryptionToken> etkRecipients = getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, executorId);

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
				final SendNotificationRequest request = new SendNotificationRequest();
				request.setSecuredSendNotificationRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
				request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
				request.setIssueInstant(new DateTime());
				request.setId(getId());

				// call sealed WS
				try {
					final SendNotificationResponse response = RecipePrescriberServiceV4Impl.getInstance().sendNotification(request);
					final MarshallerHelper<SendNotificationResult, SendNotificationResult> helper1 = new MarshallerHelper<>(
							SendNotificationResult.class, SendNotificationResult.class);
					final SendNotificationResult result = helper1.unsealWithSymmKey(response.getSecuredSendNotificationResponse().getSecuredContent(),
							getSymmKey());
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

			final PutFeedbackFlagRequest request = new PutFeedbackFlagRequest();
			request.setSecuredPutFeedbackFlagRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
			request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call sealed WS
			try {
				final PutFeedbackFlagResponse response = RecipePrescriberServiceV4Impl.getInstance().putFeedbackFlag(request);
				final MarshallerHelper<UpdateFeedbackFlagResult, UpdateFeedbackFlagResult> helper2 = new MarshallerHelper<>(
						UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
				final UpdateFeedbackFlagResult result = helper2.unsealWithSymmKey(response.getSecuredPutFeedbackFlagResponse().getSecuredContent(),
						getSymmKey());
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
			final ListFeedbacksRequest request = new ListFeedbacksRequest();
			request.setSecuredListFeedbacksRequest(createSecuredContentType(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param))));
			request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call sealed WS
			ListFeedbacksResponse response = null;

			try {
				response = RecipePrescriberServiceV4Impl.getInstance().listFeedbacks(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}
			final ListFeedbacksResult result = helper.unsealWithSymmKey(response.getSecuredListFeedbacksResponse().getSecuredContent(), getSymmKey());
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
		ApplicationConfig.getInstance().assertValidSession();
		if (!OnlineProperties.isLoaded()) {
			try {
				try {
					// 1) Get the properties from the server
					final GetValidationPropertiesRequest validationProperties = getValidationProperties(param);
					final GetValidationPropertiesResponse response = RecipePrescriberServiceV4Impl.getInstance()
							.getValidationProperties(validationProperties);
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
	protected GetValidationPropertiesRequest getValidationProperties(final ValidationPropertiesParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final GetValidationPropertiesRequest request = new GetValidationPropertiesRequest();
		request.setSecuredGetValidationPropertiesRequest(createSecuredContentType(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param data
	 *            the data
	 * @return the sealed data @ the integration module exception
	 */
	@Override
	protected byte[] getSealedData(final ValidationPropertiesParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(data, ValidationPropertiesParam.class);
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
	protected <T> byte[] sealForRecipe(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		final EncryptionToken etkRecipe = getEtkHelper().getRecipe_ETK().get(0);
		return sealRequest(etkRecipe, helper.toXMLByteArray(data));
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

	private static void unzip(byte[] compressedData, String destDir) {
		File dir = new File(destDir);
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
			final GetPrescriptionStatusRequest getPrescriptionStatusRequest = getGetPrescriptionStatus(param);
			try {
				final GetPrescriptionStatusResponse response = RecipePrescriberServiceV4Impl.getInstance()
						.getPrescriptionStatus(getPrescriptionStatusRequest);
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
	protected GetPrescriptionStatusRequest getGetPrescriptionStatus(final GetPrescriptionStatusParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
		request.setSecuredGetPrescriptionStatusRequest(createSecuredContentType(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
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
		return marshaller.unsealWithSymmKey(response.getSecuredGetPrescriptionStatusResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * Unseal validation properties response.
	 *
	 * @param getDataResponse
	 *            the get data response
	 * @return the validation properties result @ the integration module exception
	 */
	private ValidationPropertiesResult unsealValidationPropertiesResponse(final GetValidationPropertiesResponse response) {
		final MarshallerHelper<ValidationPropertiesResult, Schema> marshaller = new MarshallerHelper<>(ValidationPropertiesResult.class,
				Schema.class);
		return marshaller.unsealWithSymmKey(response.getSecuredGetValidationPropertiesResponse().getSecuredContent(), getSymmKey());
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
			final ListRidsHistoryRequest listRidsHistory = getListPrescriptionHistoryRequest(param);
			try {
				final ListRidsHistoryResponse response = RecipePrescriberServiceV4Impl.getInstance().listRidsHistory(listRidsHistory);
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

	private ListRidsHistoryRequest getListPrescriptionHistoryRequest(final ListRidsHistoryParam listRidHistoryParam) {
		listRidHistoryParam.setSymmKey(getSymmKey().getEncoded());
		final ListRidsHistoryRequest listRidsHistory = new ListRidsHistoryRequest();
		listRidsHistory.setSecuredListRidsHistoryRequest(createSecuredContentType(getSealedData(listRidHistoryParam)));
		listRidsHistory.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
		listRidsHistory.setId(getId());
		listRidsHistory.setIssueInstant(new DateTime());
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
		return marshaller.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), getSymmKey());
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
			final PutVisionForPrescriberRequest putVision = putVisionRequest(param);
			try {
				final PutVisionForPrescriberResponse response = RecipePrescriberServiceV4Impl.getInstance().putVisionForPrescriber(putVision);
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
	 * @param param
	 *            the put vision param
	 * @return the put data @ the integration module exception
	 */
	protected PutVisionForPrescriberRequest putVisionRequest(final PutVisionParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final PutVisionForPrescriberRequest request = new PutVisionForPrescriberRequest();
		request.setSecuredPutVisionForPrescriberRequest(createSecuredContentType(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Unseal put vision.
	 *
	 * @param response
	 *            the data response
	 * @return the put vision response @ the integration module exception
	 */
	private PutVisionResult unsealPutVisionResponse(final PutVisionForPrescriberResponse response) {
		final MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper<>(PutVisionResult.class, Object.class);
		final PutVisionResult result = marshaller.unsealWithSymmKey(response.getSecuredPutVisionForPrescriberResponse().getSecuredContent(),
				getSymmKey());
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
			final ListOpenRidsRequest listOpenRids = getListOpenRids(param);
			try {
				final ListOpenRidsResponse response = RecipePrescriberServiceV4Impl.getInstance().listOpenRids(listOpenRids);
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
	protected ListOpenRidsRequest getListOpenRids(final ListOpenRidsParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final ListOpenRidsRequest request = new ListOpenRidsRequest();
		request.setSecuredListOpenRidsRequest(createSecuredContentType(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty(PROGRAM_IDENTIFICATION));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
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
		return marshaller.unsealWithSymmKey(response.getSecuredListOpenRidsResponse().getSecuredContent(), getSymmKey());
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