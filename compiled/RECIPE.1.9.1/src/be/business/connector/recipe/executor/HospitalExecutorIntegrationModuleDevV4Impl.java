package be.business.connector.recipe.executor;

import static be.business.connector.recipe.utils.RidValidator.validateRid;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.executor.services.RecipeExecutorServiceDevV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.executor.CreateFeedback;
import be.recipe.services.executor.CreateFeedbackResponse;
import be.recipe.services.executor.CreateFeedbackResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResponse;
import be.recipe.services.executor.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatus;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResponse;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListNotifications;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsResponse;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListOpenPrescriptions;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResponse;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelations;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResponse;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResponse;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistory;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResponse;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcess;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResponse;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.MarkAsArchived;
import be.recipe.services.executor.MarkAsArchivedResponse;
import be.recipe.services.executor.MarkAsArchivedResult;
import be.recipe.services.executor.MarkAsDelivered;
import be.recipe.services.executor.MarkAsDeliveredResponse;
import be.recipe.services.executor.MarkAsDeliveredResult;
import be.recipe.services.executor.MarkAsUnDelivered;
import be.recipe.services.executor.MarkAsUnDeliveredResponse;
import be.recipe.services.executor.MarkAsUndeliveredResult;
import be.recipe.services.executor.PutRidsInProcess;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResponse;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.RevokePrescription;
import be.recipe.services.executor.RevokePrescriptionResponse;
import be.recipe.services.executor.RevokePrescriptionResult;

/**
 * The Class ExecutorIntegrationModuleDevV4Impl.
 */
public class HospitalExecutorIntegrationModuleDevV4Impl extends AbstractExecutorIntegrationModuleV4 implements ExecutorIntegrationModuleDevV4 {

	private static Map<MultiKey, byte[]> sessionMap = new HashMap<>();

	/**
	 * Instantiates a new executor integration module V 4 impl.
	 *
	 * @ the integration module exception
	 */
	public HospitalExecutorIntegrationModuleDevV4Impl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "ExecutorIntegrationModuleV4#getData(GetOpenPrescriptionListParam)")
	@Override
	public ListOpenPrescriptionsResult getData(final ListOpenPrescriptionsParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		ValidationUtils.validatePatientId(param.getPatientId());
		ValidationUtils.validateMandateHolderId(param.getMandateHolderId(), true);
		final ListOpenPrescriptions getOpenPrescriptionList = getOpenPrescriptionList(param);
		try {
			try {
				final ListOpenPrescriptionsResponse response = RecipeExecutorServiceDevV4Impl.getInstance()
						.listOpenPrescriptions(getOpenPrescriptionList);
				final ListOpenPrescriptionsResult result = unsealGetDataResponse(response);
				checkStatus(result);
				sessionMap.put(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(),
						param.getMandateHolderId() != null ? param.getMandateHolderId() : param.getPatientId()), result.getSession());
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, getOpenPrescriptionList.getMguid());
		}
		return null;
	}

	/**
	 * Gets the gets the open prescription list request.
	 *
	 * @param listOpenPrescriptionsParam
	 *            the data
	 * @return the gets the open prescription list request @ the integration module exception
	 */
	private ListOpenPrescriptions getOpenPrescriptionList(final ListOpenPrescriptionsParam listOpenPrescriptionsParam) {
		listOpenPrescriptionsParam.setSession(sessionMap.get(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(),
				isNotBlank(listOpenPrescriptionsParam.getMandateHolderId()) ? listOpenPrescriptionsParam.getMandateHolderId()
						: listOpenPrescriptionsParam.getPatientId())));
		final ListOpenPrescriptions request = new ListOpenPrescriptions();
		request.setListOpenPrescriptionsParamSealed(getSealedData(listOpenPrescriptionsParam));
		request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setMguid(UUID.randomUUID().toString());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listOpenPrescriptionsParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListOpenPrescriptionsParam listOpenPrescriptionsParam) {
		listOpenPrescriptionsParam.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(listOpenPrescriptionsParam, ListOpenPrescriptionsParam.class);
	}

	/**
	 * Unseal get data response.
	 *
	 * @param response
	 *            the get data response
	 * @return the gets the open prescription list response @ the integration module exception
	 */
	private ListOpenPrescriptionsResult unsealGetDataResponse(final ListOpenPrescriptionsResponse response) {
		final MarshallerHelper<ListOpenPrescriptionsResult, Object> marshaller = new MarshallerHelper<>(ListOpenPrescriptionsResult.class,
				Object.class);
		return marshaller.unsealWithSymmKey(response.getListOpenPrescriptionsResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(GetPrescriptionStatusParam)")
	@Override
	public GetPrescriptionStatusResult getData(final GetPrescriptionStatusParam param) {
		RidValidator.validateRid(param.getRid());
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final GetPrescriptionStatus getPrescriptionStatus = getPrescriptionStatus(param);
		try {
			try {
				final GetPrescriptionStatusResponse response = RecipeExecutorServiceDevV4Impl.getInstance()
						.getPrescriptionStatus(getPrescriptionStatus);
				final GetPrescriptionStatusResult result = unsealGetPrescriptionStatusResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, getPrescriptionStatus.getMguid());
		}
		return null;
	}

	/**
	 * Gets the gets the prescription status request.
	 *
	 * @param getPrescriptionStatusParam
	 *            the data
	 * @return the gets the prescription status request @ the integration module exception
	 */
	private GetPrescriptionStatus getPrescriptionStatus(final GetPrescriptionStatusParam getPrescriptionStatusParam) {
		final GetPrescriptionStatus getPrescriptionStatus = new GetPrescriptionStatus();
		getPrescriptionStatus.setGetPrescriptionStatusParamSealed(getSealedData(getPrescriptionStatusParam));
		getPrescriptionStatus.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		getPrescriptionStatus.setMguid(UUID.randomUUID().toString());
		return getPrescriptionStatus;
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
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsHistoryParam)")
	@Override
	public ListRidsHistoryResult getData(final ListRidsHistoryParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		ValidationUtils.validatePatientId(param.getPatientId());
		final ListRidsHistory listPrescriptionHistory = getListRidsHistory(param);
		try {
			try {
				final ListRidsHistoryResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
				final ListRidsHistoryResult result = unsealListRidsHistoryResponse(response);
				checkStatus(result);
				sessionMap.put(new MultiKey(param.getExecutorId(), param.getPatientId()), result.getSession());
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, listPrescriptionHistory.getMguid());
		}
		return null;
	}

	private ListRidsHistory getListRidsHistory(final ListRidsHistoryParam listRidsHistoryParam) {
		listRidsHistoryParam.setSession(sessionMap.get(new MultiKey(listRidsHistoryParam.getExecutorId(), listRidsHistoryParam.getPatientId())));
		final ListRidsHistory listPrescriptionHistory = new ListRidsHistory();
		listPrescriptionHistory.setListRidsHistoryParamSealed(getSealedData(listRidsHistoryParam));
		listPrescriptionHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		listPrescriptionHistory.setMguid(UUID.randomUUID().toString());
		return listPrescriptionHistory;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listRidsHistoryParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListRidsHistoryParam listRidsHistoryParam) {
		listRidsHistoryParam.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(listRidsHistoryParam, ListRidsHistoryParam.class);
	}

	/**
	 * Unseal list prescription history response.
	 *
	 * @param response
	 *            the get data response
	 * @return the list prescription history response @ the integration module exception
	 */
	private ListRidsHistoryResult unsealListRidsHistoryResponse(final ListRidsHistoryResponse response) {
		final MarshallerHelper<ListRidsHistoryResult, Object> marshaller = new MarshallerHelper<>(ListRidsHistoryResult.class, Object.class);
		return marshaller.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "ExecutorIntegrationModuleV4#getPrescription")
	@Override
	public be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult getPrescription(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		InsurabilityHandler.setInsurability(null);
		InsurabilityHandler.setMessageId(null);

		final String guid = UUID.randomUUID().toString();
		try {
			// create get prescription request
			final byte[] sealedGetPrescriptionForExecutorParam = getSealedGetPrescriptionForExecutorParam(rid);

			final GetPrescriptionForExecutor request = new GetPrescriptionForExecutor();
			request.setDisablePatientInsurabilityCheckParam(Boolean.parseBoolean(getPropertyHandler().getProperty("patient.insurability.disable")));
			request.setGetPrescriptionForExecutorParamSealed(sealedGetPrescriptionForExecutorParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(guid);
			GetPrescriptionForExecutorResponse response = null;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().getPrescriptionForExecutor(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

			final GetPrescriptionForExecutorResult executorResult = response.getGetPrescriptionForExecutorResult();
			final be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult finalResult = createGetPrescriptionForExecutorResult(
					executorResult.getGetPrescriptionForExecutorResultSealed());
			checkStatus(finalResult);
			getPrescriptionCache().put(rid, finalResult);
			return finalResult;
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, guid);
		}
		return null;
	}

	protected be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult createGetPrescriptionForExecutorResult(
			final byte[] securedContent) {
		final MarshallerHelper<GetPrescriptionForExecutorResultSealed, Object> marshaller = new MarshallerHelper<>(
				GetPrescriptionForExecutorResultSealed.class, Object.class);
		final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
		final String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();
		final GetPrescriptionForExecutorResultSealed sealedResult = marshaller.unsealWithSymmKey(securedContent, getSymmKey());

		final be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult finalResult = new be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult(
				sealedResult);
		finalResult.setStatus(sealedResult.getStatus());
		// Only do checkStatus in dev
		checkStatus(finalResult);
		finalResult.setSealedContent(sealedResult.getPrescription());

		final KeyResult key = getKeyFromKgss(sealedResult.getEncryptionKeyId(),
				getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, requestorIdInformation).get(0).getEncoded());
		final byte[] unsealedPrescription = unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);

		finalResult.setPrescription(unsealedPrescription);
		finalResult.setEncryptionKey(key.getSecretKey().getEncoded());
		finalResult.setInsurabilityResponse(InsurabilityHandler.getInsurability());
		finalResult.setMessageId(InsurabilityHandler.getMessageId());
		return finalResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#decryptGetOpenPrescriptionForExecutor")
	@Override
	public GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(final GetOpenPrescriptionForExecutor gopfe) {
		try {
			final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
			final KeyResult key = getKeyFromKgss(gopfe.getEncryptionKeyId(),
					getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
			final byte[] unsealedPrescription = unsealPrescriptionForUnknown(key, gopfe.getPrescription());
			final GetOpenPrescriptionForExecutor finalResult = new GetOpenPrescriptionForExecutor();
			finalResult.setCreationDate(gopfe.getCreationDate());
			finalResult.setEncryptionKeyId(gopfe.getEncryptionKeyId());
			finalResult.setFeedbackAllowed(gopfe.isFeedbackAllowed());
			finalResult.setId(gopfe.getId());
			finalResult.setPatientId(gopfe.getPatientId());
			finalResult.setPrescriberId(gopfe.getPrescriberId());
			finalResult.setPrescription(IOUtils.decompress(unsealedPrescription));
			finalResult.setPrescriptionType(gopfe.getPrescriptionType());
			finalResult.setRid(gopfe.getRid());
			finalResult.setStatus(gopfe.getStatus());
			finalResult.setExpirationDate(gopfe.getExpirationDate());
			return finalResult;
		} catch (final IOException ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#markAsArchived")
	@Override
	public void markAsArchived(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final MarkAsArchived request = new MarkAsArchived();
		request.setMguid(UUID.randomUUID().toString());
		try {

			// create param
			final byte[] sealedMarkAsArchivedParam = getSealedMarkAsArchivedParam(rid);
			// create request
			request.setMarkAsArchivedParamSealed(sealedMarkAsArchivedParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));

			// call WS
			MarkAsArchivedResponse response;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().markAsArchived(request);
				final MarshallerHelper<MarkAsArchivedResult, Object> marshaller = new MarshallerHelper<>(MarkAsArchivedResult.class, Object.class);
				final MarkAsArchivedResult result = marshaller.unsealWithSymmKey(response.getMarkAsArchivedResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#markAsDelivered")
	@Override
	public void markAsDelivered(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final MarkAsDelivered request = new MarkAsDelivered();
		request.setMguid(UUID.randomUUID().toString());
		try {
			final byte[] sealedMarkAsDeliveredParam = getSealedMarkAsDeliveredParam(rid);

			request.setMarkAsDeliveredParamSealed(sealedMarkAsDeliveredParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			MarkAsDeliveredResponse response = null;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().markAsDelivered(request);
				final MarshallerHelper<MarkAsDeliveredResult, Object> marshaller = new MarshallerHelper<>(MarkAsDeliveredResult.class, Object.class);
				final MarkAsDeliveredResult result = marshaller.unsealWithSymmKey(response.getMarkAsDeliveredResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#markAsUnDelivered")
	@Override
	public void markAsUndelivered(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final MarkAsUnDelivered request = new MarkAsUnDelivered();
		request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setMguid(UUID.randomUUID().toString());
		try {
			final byte[] sealedMarkAsUnDeliveredParam = getSealedMarkAsUnDeliveredParam(rid);
			request.setMarkAsUndeliveredParamSealed(sealedMarkAsUnDeliveredParam);

			MarkAsUnDeliveredResponse response = null;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().markAsUnDelivered(request);
				final MarshallerHelper<MarkAsUndeliveredResult, Object> marshaller = new MarshallerHelper<>(MarkAsUndeliveredResult.class,
						Object.class);
				final MarkAsUndeliveredResult result = marshaller.unsealWithSymmKey(response.getMarkAsUnDeliveredResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#revokePrescription")
	@Override
	public void revokePrescription(final String rid, final String reason) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final RevokePrescription request = new RevokePrescription();
		try {
			final byte[] sealedRevokePrescriptionParam = getSealedRevokePrescriptionParam(rid, reason);

			request.setRevokePrescriptionParamSealed(sealedRevokePrescriptionParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			RevokePrescriptionResponse response = null;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().revokePrescriptionForExecutor(request);
				final MarshallerHelper<RevokePrescriptionResult, Object> marshaller = new MarshallerHelper<>(RevokePrescriptionResult.class,
						Object.class);
				final RevokePrescriptionResult result = marshaller.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#listNotifications")
	@Override
	public List<ListNotificationsItem> listNotifications(final boolean readFlag) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final ListNotifications request = new ListNotifications();
		request.setMguid(UUID.randomUUID().toString());

		try {
			final byte[] sealedListNotificationsParam = getSealedListNotificationsParam(readFlag);

			request.setListNotificationsParamSealed(sealedListNotificationsParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));

			ListNotificationsResponse response = null;
			try {
				response = RecipeExecutorServiceDevV4Impl.getInstance().listNotifications(request);
				final MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper<>(ListNotificationsResult.class,
						Object.class);
				final ListNotificationsResult result = marshaller.unsealWithSymmKey(response.getListNotificationsResultSealed(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

			final byte[] securedContent = response.getListNotificationsResultSealed();
			return createListNotificationItems(securedContent);
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#createFeedback")
	@Override
	public void createFeedback(final String prescriberId, final String rid, final byte[] feedbackText) {
		validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final CreateFeedback request = new CreateFeedback();
		request.setMguid(UUID.randomUUID().toString());
		try {
			getKmehrHelper().assertValidFeedback(feedbackText);
			final List<EncryptionToken> etkRecipients = new ArrayList<>();
			try {
				etkRecipients.addAll(getEtkHelper().getEtks(KgssIdentifierType.NIHII, prescriberId));
			} catch (Exception e) {
				try {
					etkRecipients.addAll(getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, prescriberId));
				} catch (Exception e1) {
					try {
						etkRecipients.addAll(getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, prescriberId));
					} catch (Exception e2) {
						Exceptionutils.errorHandler(e1, e.getMessage() + e2.getMessage());
					}
				}
			}

			for (int i = 0; i < etkRecipients.size(); i++) {
				final EncryptionToken etkRecipient = etkRecipients.get(i);
				final byte[] sealedCreateFeedbackParam = getSealedCreateFeedbackParam(feedbackText, etkRecipient, rid, prescriberId);

				request.setCreateFeedbackParamSealed(sealedCreateFeedbackParam);
				request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));

				CreateFeedbackResponse response;
				try {
					response = RecipeExecutorServiceDevV4Impl.getInstance().createFeedback(request);

					final MarshallerHelper<CreateFeedbackResult, Object> marshaller1 = new MarshallerHelper<>(CreateFeedbackResult.class,
							Object.class);
					final CreateFeedbackResult result = marshaller1.unsealWithSymmKey(response.getCreateFeedbackResultSealed(), getSymmKey());
					checkStatus(result);
				} catch (final ClientTransportException cte) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
				}

			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListReservationsParam)")
	@Override
	public ListReservationsResult getData(final ListReservationsParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final Calendar lastSyncDate = Calendar.getInstance();
		try {
			final ListReservations request = getListReservations(param);
			final ListReservationsResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listReservations(request);
			final ListReservationsResult result = unsealListReservationsResponse(response);
			checkStatus(result);
			writeReservationsOnDisk(param, result, lastSyncDate);

			return result;

		} catch (final ClientTransportException cte) {
			throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Gets the all reservations request.
	 *
	 * @param param
	 *            the data
	 * @return the all reservations request @ the integration module exception
	 */
	private ListReservations getListReservations(final ListReservationsParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		param.setStartDate(param.getStartDate() == null ? readLastDateToDisk() : param.getStartDate());
		final ListReservations listReservations = new ListReservations();
		listReservations.setListReservationsParamSealed(getSealedData(param));
		listReservations.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		listReservations.setMguid(UUID.randomUUID().toString());

		return listReservations;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listReservationsParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListReservationsParam listReservationsParam) {
		return sealForRecipe(listReservationsParam, ListReservationsParam.class);
	}

	/**
	 * Unseal get all reservations response.
	 * 
	 * @param response
	 *            the get data response
	 * @return the gets the all reservations response @ the integration module exception
	 */
	private ListReservationsResult unsealListReservationsResponse(final ListReservationsResponse response) {
		final MarshallerHelper<ListReservationsResult, Object> marshaller = new MarshallerHelper<>(ListReservationsResult.class, Object.class);
		final ListReservationsResult result = marshaller.unsealWithSymmKey(response.getListReservationsResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsInProcessParam)")
	@Override
	public ListRidsInProcessResult getData(final ListRidsInProcessParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final ListRidsInProcess request = getListRidsInProcess(param);
		try {
			try {
				final ListRidsInProcessResponse response = RecipeExecutorServiceDevV4Impl.getInstance().listRidsInProcess(request);
				final ListRidsInProcessResult result = unsealListRidsInProcessResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
		return null;
	}

	/**
	 * Gets the all ridsi N process request.
	 *
	 * @param listRidsInProcessParam
	 *            the data
	 * @return the all ridsi N process request @ the integration module exception
	 */
	private ListRidsInProcess getListRidsInProcess(final ListRidsInProcessParam listRidsInProcessParam) {
		// Update param with symmKey
		listRidsInProcessParam.setSymmKey(getSymmKey().getEncoded());

		// Create request object
		final ListRidsInProcess getAllRidInProcess = new ListRidsInProcess();
		getAllRidInProcess.setListRidsInProcessParamSealed(getSealedData(listRidsInProcessParam));
		getAllRidInProcess.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		getAllRidInProcess.setMguid(UUID.randomUUID().toString());

		return getAllRidInProcess;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#putData(PutAllRidInProcessParam)")
	@Override
	public PutRidsInProcessResult putData(final PutRidsInProcessParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final PutRidsInProcess request = getPutAllRidInProcess(param);
		try {
			try {
				final PutRidsInProcessResponse response = RecipeExecutorServiceDevV4Impl.getInstance().putRidsInProcess(request);
				final PutRidsInProcessResult result = unsealPutRidsInProcessResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
		return null;
	}

	/**
	 * Put all ridsi N process request.
	 *
	 * @param putRidsInProcessParam
	 *            the data
	 * @return the put data @ the integration module exception
	 */
	private PutRidsInProcess getPutAllRidInProcess(final PutRidsInProcessParam putRidsInProcessParam) {
		// Update param with symmKey
		putRidsInProcessParam.setSymmKey(getSymmKey().getEncoded());

		// Create request object
		final PutRidsInProcess putAllRidInProcess = new PutRidsInProcess();
		putAllRidInProcess.setPutRidsInProcessParamSealed(getSealedData(putRidsInProcessParam));
		putAllRidInProcess.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		putAllRidInProcess.setMguid(UUID.randomUUID().toString());

		return putAllRidInProcess;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param putRidsInProcessParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final PutRidsInProcessParam putRidsInProcessParam) {
		return sealForRecipe(putRidsInProcessParam, PutRidsInProcessParam.class);
	}

	/**
	 * Lists the sealed data.
	 *
	 * @param litRidsInProcessParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListRidsInProcessParam litRidsInProcessParam) {
		return sealForRecipe(litRidsInProcessParam, ListRidsInProcessParam.class);
	}

	/**
	 * Unseal put all rid in process response.
	 *
	 * @param response
	 *            the get data response
	 * @return the put all rid in process response @ the integration module exception
	 */
	private PutRidsInProcessResult unsealPutRidsInProcessResponse(final PutRidsInProcessResponse response) {
		final MarshallerHelper<PutRidsInProcessResult, Object> marshaller = new MarshallerHelper<>(PutRidsInProcessResult.class, Object.class);
		final PutRidsInProcessResult result = marshaller.unsealWithSymmKey(response.getPutRidsInProcessResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * Unseal get all rid in process response.
	 *
	 * @param getDataResponse
	 *            the get data response
	 * @return the gets the all rid in process result @ the integration module exception
	 */
	private ListRidsInProcessResult unsealListRidsInProcessResponse(final ListRidsInProcessResponse response) {
		final MarshallerHelper<ListRidsInProcessResult, Object> marshaller = new MarshallerHelper<>(ListRidsInProcessResult.class, Object.class);
		final ListRidsInProcessResult result = marshaller.unsealWithSymmKey(response.getListRidsInProcessResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc} @
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getAndMarkAsDelivered")
	@Override
	public be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult getAndMarkAsDelivered(final String rid) {
		try {
			final be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult getPrescriptionForExecutorResult;
			if (getPrescriptionCache().containsKey(rid)) {
				getPrescriptionForExecutorResult = getPrescriptionCache().get(rid);
			} else {
				getPrescriptionForExecutorResult = getPrescription(rid);
			}
			markAsDelivered(rid);
			return getPrescriptionForExecutorResult;
		} catch (final Exception t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRelationsResult getData(final ListRelationsParam patientRelationParam) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final ListRelations request = getListPatientRelation(patientRelationParam);
		try {
			try {
				final ListRelationsResponse dataResponse = RecipeExecutorServiceDevV4Impl.getInstance().listRelations(request);
				final ListRelationsResult unsealedResponse = unsealListRelationsResponse(dataResponse);
				checkStatus(unsealedResponse);
				sessionMap.put(new MultiKey(patientRelationParam.getExecutorId(), patientRelationParam.getMandateHolderId()),
						unsealedResponse.getSession());
				return unsealedResponse;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getMguid());
		}
		return null;
	}

	/**
	 * Creates a {@link ListRelationsParam} request.
	 *
	 * @param data
	 *            the data
	 * @return the patient relation @ the integration module exception
	 */
	private ListRelations getListPatientRelation(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		data.setSession(sessionMap.get(new MultiKey(data.getExecutorId(), data.getMandateHolderId())));
		final ListRelations patientRelation = new ListRelations();
		patientRelation.setListRelationsParamSealed(getSealedData(data));
		patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		patientRelation.setMguid(UUID.randomUUID().toString());
		return patientRelation;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param data
	 *            the data
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(data, ListRelationsParam.class);
	}

	/**
	 * Unseal list relations response.
	 *
	 * @param response
	 *            the response
	 * @return the list relations result
	 */
	private ListRelationsResult unsealListRelationsResponse(final ListRelationsResponse response) {
		final MarshallerHelper<ListRelationsResult, Object> marshaller = new MarshallerHelper<>(ListRelationsResult.class, Object.class);
		final ListRelationsResult result = marshaller.unsealWithSymmKey(response.getListRelationsResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearSession() {
		sessionMap.clear();
	}
}