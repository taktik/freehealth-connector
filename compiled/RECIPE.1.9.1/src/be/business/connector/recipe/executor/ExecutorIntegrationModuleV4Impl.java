package be.business.connector.recipe.executor;

import static be.business.connector.recipe.utils.RidValidator.validateRid;
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.joda.time.DateTime;
import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.executor.services.RecipeExecutorServiceV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.recipe.services.executor.CreateFeedbackResult;
import be.recipe.services.executor.GetPrescriptionForExecutorResultSealed;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListNotificationsResult;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.MarkAsArchivedResult;
import be.recipe.services.executor.MarkAsDeliveredResult;
import be.recipe.services.executor.MarkAsUndeliveredResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.RevokePrescriptionResult;

/**
 * The Class ExecutorIntegrationModuleV4Impl.
 */
public class ExecutorIntegrationModuleV4Impl extends AbstractExecutorIntegrationModuleV4 implements ExecutorIntegrationModuleV4 {

	private static final Map<MultiKey, byte[]> sessionMap = new HashMap<>();

	/**
	 * Instantiates a new executor integration module V 4 impl.
	 *
	 * @ the integration module exception
	 */
	public ExecutorIntegrationModuleV4Impl() {
		super();
	}

	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListOpenPrescriptionsParam)")
	@Override
	public ListOpenPrescriptionsResult getData(final ListOpenPrescriptionsParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		ValidationUtils.validatePatientId(param.getPatientId());
		ValidationUtils.validateMandateHolderId(param.getMandateHolderId(), true);
		final ListOpenPrescriptionsRequest listOpenPrescriptionsRequest = getOpenPrescriptionList(param);
		try {
			try {
				final ListOpenPrescriptionsResponse response = RecipeExecutorServiceV4Impl.getInstance()
						.listOpenPrescriptions(listOpenPrescriptionsRequest);
				final ListOpenPrescriptionsResult result = unsealGetDataResponse(response);
				checkStatus(result);
				sessionMap.put(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(),
						param.getMandateHolderId() != null ? param.getMandateHolderId() : param.getPatientId()), result.getSession());
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, listOpenPrescriptionsRequest.getId());
		}
		return null;
	}

	/**
	 * Unseal get data response.
	 *
	 * @param response
	 *            the get data response
	 * @return the gets the open prescription list response @ the integration module exception
	 */
	public ListOpenPrescriptionsResult unsealGetDataResponse(final ListOpenPrescriptionsResponse response) {
		final MarshallerHelper<ListOpenPrescriptionsResult, Object> marshaller = new MarshallerHelper<>(ListOpenPrescriptionsResult.class,
				Object.class);
		return marshaller.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * Gets the gets the open prescription list request.
	 *
	 * @param listOpenPrescriptionsParam
	 *            the data
	 * @return the gets the open prescription list request @ the integration module exception
	 */
	private ListOpenPrescriptionsRequest getOpenPrescriptionList(final ListOpenPrescriptionsParam listOpenPrescriptionsParam) {
		listOpenPrescriptionsParam.setSession(sessionMap.get(new MultiKey(StandaloneRequestorProvider.getRequestorIdInformation(),
				isNotBlank(listOpenPrescriptionsParam.getMandateHolderId()) ? listOpenPrescriptionsParam.getMandateHolderId()
						: listOpenPrescriptionsParam.getPatientId())));
		final ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
		request.setSecuredListOpenPrescriptionsRequest(createSecuredContentType(getSealedData(listOpenPrescriptionsParam)));
		request.setIssueInstant(new DateTime());
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setId(getId());
		return request;
	}

	private GetPrescriptionStatusRequest getPrescriptionStatus(final GetPrescriptionStatusParam getPrescriptionStatusParam) {
		final GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
		request.setSecuredGetPrescriptionStatusRequest(createSecuredContentType(getSealedData(getPrescriptionStatusParam)));
		request.setIssueInstant(new DateTime());
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listOpenPrescriptionsParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	public byte[] getSealedData(final ListOpenPrescriptionsParam listOpenPrescriptionsParam) {
		listOpenPrescriptionsParam.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(listOpenPrescriptionsParam, ListOpenPrescriptionsParam.class);
	}

	/**
	 * Creates the secured content type.
	 *
	 * @param content
	 *            the content
	 * @return the secured content type
	 */
	public SecuredContentType createSecuredContentType(final byte[] content) {
		final SecuredContentType secured = new SecuredContentType();
		secured.setSecuredContent(content);
		return secured;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(GetPrescriptionStatusParam)")
	@Override
	public GetPrescriptionStatusResult getData(final GetPrescriptionStatusParam param) {
		RidValidator.validateRid(param.getRid());
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final GetPrescriptionStatusRequest getPrescriptionStatus = getPrescriptionStatus(param);
		try {
			try {
				final GetPrescriptionStatusResponse response = RecipeExecutorServiceV4Impl.getInstance().getPrescriptionStatus(getPrescriptionStatus);
				final GetPrescriptionStatusResult result = unsealGetPrescriptionStatusResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, getPrescriptionStatus.getId());
		}
		return null;
	}

	/**
	 * Unseal get prescription status response.
	 *
	 * @param response
	 *            the get data response
	 * @return the gets the prescription status response @ the integration module exception
	 */
	public GetPrescriptionStatusResult unsealGetPrescriptionStatusResponse(final GetPrescriptionStatusResponse response) {
		final MarshallerHelper<GetPrescriptionStatusResult, Object> marshaller = new MarshallerHelper<>(GetPrescriptionStatusResult.class,
				Object.class);
		return marshaller.unsealWithSymmKey(response.getSecuredGetPrescriptionStatusResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsHistoryParam)")
	@Override
	public ListRidsHistoryResult getData(final ListRidsHistoryParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		ValidationUtils.validatePatientId(param.getPatientId());
		final ListRidsHistoryRequest listPrescriptionHistory = getListRidsHistory(param);
		try {
			try {
				final ListRidsHistoryResponse response = RecipeExecutorServiceV4Impl.getInstance().listRidsHistory(listPrescriptionHistory);
				final ListRidsHistoryResult result = unsealListRidsHistoryResponse(response);
				checkStatus(result);
				sessionMap.put(new MultiKey(param.getExecutorId(), param.getPatientId()), result.getSession());
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, listPrescriptionHistory.getId());
		}
		return null;
	}

	private ListRidsHistoryRequest getListRidsHistory(final ListRidsHistoryParam listRidsHistoryParam) {
		listRidsHistoryParam.setSession(sessionMap.get(new MultiKey(listRidsHistoryParam.getExecutorId(), listRidsHistoryParam.getPatientId())));
		final ListRidsHistoryRequest request = new ListRidsHistoryRequest();
		request.setSecuredListRidsHistoryRequest(createSecuredContentType(getSealedData(listRidsHistoryParam)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

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
		return marshaller.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "ExecutorIntegrationModule#getPrescription")
	@Override
	public be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult getPrescription(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		InsurabilityHandler.setInsurability(null);
		InsurabilityHandler.setMessageId(null);
		final String guid = getId();
		try {
			// create get prescription request
			final byte[] sealedGetPrescriptionForExecutorParam = getSealedGetPrescriptionForExecutorParam(rid);

			final GetPrescriptionForExecutorRequest request = new GetPrescriptionForExecutorRequest();
			request.setDisablePatientInsurabilityCheckParam(Boolean.parseBoolean(getPropertyHandler().getProperty("patient.insurability.disable")));
			request.setSecuredGetPrescriptionForExecutorRequest(createSecuredContentType(sealedGetPrescriptionForExecutorParam));
			request.setIssueInstant(new DateTime());
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setId(guid);
			GetPrescriptionForExecutorResponse response = null;

			try {
				response = RecipeExecutorServiceV4Impl.getInstance().getPrescriptionForExecutor(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
			final byte[] securedContent = response.getSecuredGetPrescriptionForExecutorResponse().getSecuredContent();
			final be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult finalResult = createGetPrescriptionForExecutorResult(
					securedContent);
			checkStatus(finalResult);
			getPrescriptionCache().put(rid, finalResult);
			return finalResult;
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, guid);
		}
		return null;
	}

	/**
	 * Creates the get prescription for executor result.
	 *
	 * @param securedContent
	 *            the secured content
	 * @return the be.business.connector.recipe.executor.domain. get prescription for executor result @ the integration module exception
	 */
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
		checkStatus(finalResult);
		finalResult.setSealedContent(sealedResult.getPrescription());

		final KeyResult key = getKeyFromKgss(sealedResult.getEncryptionKeyId(),
				getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
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
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#markAsArchived")
	@Override
	public void markAsArchived(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final MarkAsArchivedRequest request = new MarkAsArchivedRequest();
		try {

			final byte[] sealedMarkAsArchivedParam = getSealedMarkAsArchivedParam(rid);
			request.setSecuredMarkAsArchivedRequest(createSecuredContentType(sealedMarkAsArchivedParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call WS
			MarkAsArchivedResponse response;
			try {
				response = RecipeExecutorServiceV4Impl.getInstance().markAsArchived(request);
				final MarshallerHelper<MarkAsArchivedResult, Object> marshaller = new MarshallerHelper<>(MarkAsArchivedResult.class, Object.class);
				final MarkAsArchivedResult result = marshaller.unsealWithSymmKey(response.getSecuredMarkAsArchivedResponse().getSecuredContent(),
						getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
		final MarkAsDeliveredRequest request = new MarkAsDeliveredRequest();
		try {
			final byte[] sealedMarkAsDeliveredParam = getSealedMarkAsDeliveredParam(rid);

			request.setSecuredMarkAsDeliveredRequest(createSecuredContentType(sealedMarkAsDeliveredParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			MarkAsDeliveredResponse response = null;
			try {
				response = RecipeExecutorServiceV4Impl.getInstance().markAsDelivered(request);
				final MarshallerHelper<MarkAsDeliveredResult, Object> marshaller = new MarshallerHelper<>(MarkAsDeliveredResult.class, Object.class);
				final MarkAsDeliveredResult result = marshaller.unsealWithSymmKey(response.getSecuredMarkAsDeliveredResponse().getSecuredContent(),
						getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
		final MarkAsUnDeliveredRequest request = new MarkAsUnDeliveredRequest();
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setId(getId());
		try {
			final byte[] sealedMarkAsUnDeliveredParam = getSealedMarkAsUnDeliveredParam(rid);
			request.setSecuredMarkAsUnDeliveredRequest(createSecuredContentType(sealedMarkAsUnDeliveredParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			MarkAsUnDeliveredResponse response = null;
			try {
				response = RecipeExecutorServiceV4Impl.getInstance().markAsUnDelivered(request);
				final MarshallerHelper<MarkAsUndeliveredResult, Object> marshaller = new MarshallerHelper<>(MarkAsUndeliveredResult.class,
						Object.class);
				final MarkAsUndeliveredResult result = marshaller
						.unsealWithSymmKey(response.getSecuredMarkAsUnDeliveredResponse().getSecuredContent(), getSymmKey());
				checkStatus(result);
				if (getPrescriptionCache().containsKey(rid)) {
					getPrescriptionCache().remove(rid);
				}
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
		final RevokePrescriptionRequest request = new RevokePrescriptionRequest();
		try {
			final byte[] sealedRevokePrescriptionParam = getSealedRevokePrescriptionParam(rid, reason);

			request.setSecuredRevokePrescriptionRequest(createSecuredContentType(sealedRevokePrescriptionParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			RevokePrescriptionResponse response = null;
			try {
				response = RecipeExecutorServiceV4Impl.getInstance().revokePrescriptionForExecutor(request);
				final MarshallerHelper<RevokePrescriptionResult, Object> marshaller = new MarshallerHelper<>(RevokePrescriptionResult.class,
						Object.class);
				final RevokePrescriptionResult result = marshaller
						.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#listNotifications")
	@Override
	public List<ListNotificationsItem> listNotifications(final boolean readFlag) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final ListNotificationsRequest request = new ListNotificationsRequest();

		try {
			final byte[] sealedListNotificationsParam = getSealedListNotificationsParam(readFlag);

			request.setSecuredListNotificationsRequest(createSecuredContentType(sealedListNotificationsParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			ListNotificationsResponse response = null;
			try {
				response = RecipeExecutorServiceV4Impl.getInstance().listNotifications(request);
				final MarshallerHelper<ListNotificationsResult, Object> marshaller = new MarshallerHelper<>(ListNotificationsResult.class,
						Object.class);
				final ListNotificationsResult result = marshaller
						.unsealWithSymmKey(response.getSecuredListNotificationsResponse().getSecuredContent(), getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

			final byte[] securedContent = response.getSecuredListNotificationsResponse().getSecuredContent();
			return createListNotificationItems(securedContent);
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
		final CreateFeedbackRequest request = new CreateFeedbackRequest();
		try {
			getKmehrHelper().assertValidFeedback(feedbackText);

			final List<EncryptionToken> etkRecipients = new ArrayList<>();
			try {
				etkRecipients.addAll(getEtkHelper().getEtks(KgssIdentifierType.NIHII, prescriberId));
			} catch (Exception e) {
				try {
					etkRecipients.addAll(getEtkHelper().getEtks(KgssIdentifierType.NIHII_HOSPITAL, prescriberId));
				} catch (Exception e1) {
					Exceptionutils.errorHandler(e1, e.getMessage() + e1.getMessage());
				}
			}
			for (int i = 0; i < etkRecipients.size(); i++) {
				final EncryptionToken etkRecipient = etkRecipients.get(i);
				final byte[] sealedCreateFeedbackParam = getSealedCreateFeedbackParam(feedbackText, etkRecipient, rid, prescriberId);

				request.setSecuredCreateFeedbackRequest(createSecuredContentType(sealedCreateFeedbackParam));
				request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
				request.setIssueInstant(new DateTime());
				request.setId(getId());
				try {
					final CreateFeedbackResponse response = RecipeExecutorServiceV4Impl.getInstance().createFeedback(request);
					final CreateFeedbackResult result = unsealCreateFeedbackResponse(response);
					checkStatus(result);
				} catch (final ClientTransportException cte) {
					throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
				}
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
			final ListReservationsRequest request = getListReservations(param);
			final ListReservationsResponse response = RecipeExecutorServiceV4Impl.getInstance().listReservations(request);
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
	protected ListReservationsRequest getListReservations(final ListReservationsParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		param.setStartDate(param.getStartDate() == null ? readLastDateToDisk() : param.getStartDate());
		final ListReservationsRequest request = new ListReservationsRequest();
		request.setSecuredListReservationsRequest(createSecuredContentType(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	private byte[] getSealedData(final ListReservationsParam listReservationsParam) {
		listReservationsParam.setSymmKey(getSymmKey().getEncoded());
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
		final ListReservationsResult result = marshaller.unsealWithSymmKey(response.getSecuredListReservationsResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	private CreateFeedbackResult unsealCreateFeedbackResponse(final CreateFeedbackResponse response) {
		final MarshallerHelper<CreateFeedbackResult, Object> marshaller = new MarshallerHelper<>(CreateFeedbackResult.class, Object.class);
		final CreateFeedbackResult result = marshaller.unsealWithSymmKey(response.getSecuredCreateFeedbackResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	//
	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListRidsInProcessParam)")
	@Override
	public ListRidsInProcessResult getData(final ListRidsInProcessParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		try {
			final ListRidsInProcessRequest request = getListRidsInProcess(param);
			try {
				final ListRidsInProcessResponse response = RecipeExecutorServiceV4Impl.getInstance().listRidsInProcess(request);
				final ListRidsInProcessResult result = unsealListRidsInProcessResponse(response);
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
	 * Gets the all ridsi N process request.
	 *
	 * @param listRidsInProcessParam
	 *            the data
	 * @return the all ridsi N process request @ the integration module exception
	 */
	private ListRidsInProcessRequest getListRidsInProcess(final ListRidsInProcessParam listRidsInProcessParam) {
		listRidsInProcessParam.setSymmKey(getSymmKey().getEncoded());
		final ListRidsInProcessRequest request = new ListRidsInProcessRequest();
		request.setSecuredListRidsInProcessRequest(createSecuredContentType(getSealedData(listRidsInProcessParam)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param listRidsInProcessParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListRidsInProcessParam listRidsInProcessParam) {
		return sealForRecipe(listRidsInProcessParam, ListRidsInProcessParam.class);
	}

	//
	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#putData(PutAllRidInProcessParam)")
	@Override
	public PutRidsInProcessResult putData(final PutRidsInProcessParam param) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		final PutRidsInProcessRequest request = getPutAllRidInProcess(param);
		try {

			try {
				final PutRidsInProcessResponse response = RecipeExecutorServiceV4Impl.getInstance().putRidsInProcess(request);
				final PutRidsInProcessResult result = unsealPutRidsInProcessResponse(response);
				checkStatus(result);
				return result;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t, request.getId());
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
	public PutRidsInProcessRequest getPutAllRidInProcess(final PutRidsInProcessParam putRidsInProcessParam) {
		// Update param with symmKey
		putRidsInProcessParam.setSymmKey(getSymmKey().getEncoded());

		final PutRidsInProcessRequest request = new PutRidsInProcessRequest();
		request.setSecuredPutRidsInProcessRequest(createSecuredContentType(getSealedData(putRidsInProcessParam)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setId(getId());
		request.setIssueInstant(new DateTime());
		return request;
	}

	private byte[] getSealedData(final PutRidsInProcessParam putRidsInProcessParam) {
		putRidsInProcessParam.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(putRidsInProcessParam, PutRidsInProcessParam.class);
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
		final PutRidsInProcessResult result = marshaller.unsealWithSymmKey(response.getSecuredPutRidsInProcessResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * Unseal get all rid in process response.
	 *
	 * @param response
	 *            the get data response
	 * @return the gets the all rid in process result @ the integration module exception
	 */
	private ListRidsInProcessResult unsealListRidsInProcessResponse(final ListRidsInProcessResponse response) {
		final MarshallerHelper<ListRidsInProcessResult, Object> marshaller = new MarshallerHelper<>(ListRidsInProcessResult.class, Object.class);
		final ListRidsInProcessResult result = marshaller.unsealWithSymmKey(response.getSecuredListRidsInProcessResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @
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

	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#getData(ListRelationsParam)")
	@Override
	public ListRelationsResult getData(final ListRelationsParam patientRelationParam) {
		ApplicationConfig.getInstance().assertValidPharmacySession();
		ValidationUtils.validatePatientId(patientRelationParam.getMandateHolderId());
		try {
			final ListRelationsRequest request = getListPatientRelation(patientRelationParam);
			try {
				final ListRelationsResponse dataResponse = RecipeExecutorServiceV4Impl.getInstance().listRelations(request);
				final ListRelationsResult unsealedResponse = unsealListRelationsResponse(dataResponse);
				checkStatus(unsealedResponse);
				sessionMap.put(new MultiKey(patientRelationParam.getExecutorId(), patientRelationParam.getMandateHolderId()),
						unsealedResponse.getSession());
				return unsealedResponse;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
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
	private ListRelationsRequest getListPatientRelation(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		data.setSession(sessionMap.get(new MultiKey(data.getExecutorId(), data.getMandateHolderId())));

		final ListRelationsRequest request = new ListRelationsRequest();
		request.setSecuredListRelationsRequest(createSecuredContentType(getSealedData(data)));
		request.setIssueInstant(new DateTime());
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
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
		final ListRelationsResult result = marshaller.unsealWithSymmKey(response.getSecuredListRelationsResponse().getSecuredContent(), getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clearSession() {
		sessionMap.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListReservationsResult listReservationsDecrypted(final ListReservationsParam request) {
		return decryptListReservationsResult(getData(request));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenPrescriptionsResult listOpenPrescriptionsDecrypted(final ListOpenPrescriptionsParam request) {
		return decryptListOpenPrescriptionsResult(getData(request));
	}
}