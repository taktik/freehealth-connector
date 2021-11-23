package be.business.connector.recipe.patient;

import static be.business.connector.recipe.utils.RidValidator.validateRid;

import org.joda.time.DateTime;
import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.recipe.patient.services.RecipePatientServiceV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationResponse;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationResponse;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokePrescriptionResult;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResult;
import be.recipe.services.patient.UpdateFeedbackFlagParam;
import be.recipe.services.patient.UpdateFeedbackFlagResult;

/**
 * The Class PatientIntegrationModuleV4Impl.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public class PatientIntegrationModuleV4Impl extends AbstractPatientIntegrationModule implements PatientIntegrationModuleV4 {

	/**
	 * Instantiates a new patient integration module V4 impl.
	 *
	 * @ the integration module exception
	 */
	public PatientIntegrationModuleV4Impl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateFeedbackFlag(final String rid, final boolean feedbackAllowed) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		try {

			// create param
			final UpdateFeedbackFlagParam param = new UpdateFeedbackFlagParam();
			param.setAllowFeedback(feedbackAllowed);
			param.setRid(rid);
			param.setSymmKey(getSymmKey().getEncoded());

			final PutFeedbackFlagRequest request = getPutFeedbackFlagRequest(param);

			// call sealed WS
			try {
				final PutFeedbackFlagResponse response = RecipePatientServiceV4Impl.getInstance().putFeedbackFlag(request);
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

	protected PutFeedbackFlagRequest getPutFeedbackFlagRequest(final UpdateFeedbackFlagParam param) {
		param.setSymmKey(getSymmKey().getEncoded());
		final PutFeedbackFlagRequest request = new PutFeedbackFlagRequest();
		request.setSecuredPutFeedbackFlagRequest(createSecuredContentTypeV4(getSealedData(param)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final UpdateFeedbackFlagParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, UpdateFeedbackFlagParam.class);
	}

	/**
	 * Creates the secured content type.
	 *
	 * @param content
	 *            the content
	 * @return the secured content type
	 */
	public SecuredContentType createSecuredContentTypeV4(final byte[] content) {
		final SecuredContentType secured = new SecuredContentType();
		secured.setSecuredContent(content);
		return secured;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void revokePrescription(final String rid, final String reason) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final byte[] sealedRevokePrescriptionParam = getSealedRevokePrescriptionParam(rid, reason);

			final RevokePrescriptionRequest request = new RevokePrescriptionRequest();
			request.setSecuredRevokePrescriptionRequest(createSecuredContentTypeV4(sealedRevokePrescriptionParam));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			try {
				final RevokePrescriptionResponse response = RecipePatientServiceV4Impl.getInstance().revokePrescription(request);
				final MarshallerHelper<RevokePrescriptionResult, RevokePrescriptionResult> helper = new MarshallerHelper<>(
						RevokePrescriptionResult.class, RevokePrescriptionResult.class);
				final RevokePrescriptionResult result = helper.unsealWithSymmKey(response.getSecuredRevokePrescriptionResponse().getSecuredContent(),
						getSymmKey());
				checkStatus(result);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public be.recipe.services.patient.GetPrescriptionForPatientResult getPrescription(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		InsurabilityHandler.setInsurability(null);
		InsurabilityHandler.setMessageId(null);

		try {
			final byte[] sealedContent = getSealedGetPrescriptionForPatientParam(rid);
			GetPrescriptionRequest request = new GetPrescriptionRequest();
			request.setSecuredGetPrescriptionRequest(createSecuredContentTypeV4(sealedContent));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			GetPrescriptionResponse response = null;
			try {
				response = RecipePatientServiceV4Impl.getInstance().getPrescriptionForPatient(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

			final GetPrescriptionForPatientResult finalResult = unsealPrescription(response.getSecuredGetPrescriptionResponse().getSecuredContent());
			return finalResult;
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(GetVisionParam)")
	@Override
	public GetVisionResult getData(final GetVisionParam data) {
		RidValidator.validateRid(data.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			try {
				final byte[] sealedContent = getSealedData(data);
				GetVisionRequest request = new GetVisionRequest();
				request.setSecuredGetVisionRequest(createSecuredContentTypeV4(sealedContent));
				request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
				request.setId(getId());
				request.setIssueInstant(new DateTime());

				final GetVisionResponse getDataResponse = RecipePatientServiceV4Impl.getInstance().getVision(request);
				final GetVisionResult getVisionResult = unsealGetVisionResponse(getDataResponse);
				checkStatus(getVisionResult);
				return getVisionResult;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}

	/**
	 * Unseal get data response.
	 *
	 * @param getVisionResponse
	 *            the get data response
	 * @return the gets the vision response @ the integration module exception
	 */
	private GetVisionResult unsealGetVisionResponse(final GetVisionResponse getVisionResponse) {
		final MarshallerHelper<GetVisionResult, Object> marshaller = new MarshallerHelper<>(GetVisionResult.class, Object.class);
		final GetVisionResult result = marshaller.unsealWithSymmKey(getVisionResponse.getSecuredGetVisionResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * Unseal put vision.
	 *
	 * @param putVisionResponse
	 *            the data response
	 * @return the put vision response @ the integration module exception
	 */
	private PutVisionResult unsealPutVisionResponse(final PutVisionForPatientResponse putVisionResponse) {
		final MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper<>(PutVisionResult.class, Object.class);
		final PutVisionResult result = marshaller.unsealWithSymmKey(putVisionResponse.getSecuredPutVisionForPatientResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * Unseal patient relation.
	 *
	 * @param response
	 *            the data response
	 * @return the patient relation result @ the integration module exception
	 */
	private CreateRelationResult unsealCreateResponse(final CreateRelationResponse response) {
		final MarshallerHelper<CreateRelationResult, Object> marshaller = new MarshallerHelper<>(CreateRelationResult.class, Object.class);
		final CreateRelationResult result = marshaller.unsealWithSymmKey(response.getSecuredCreateRelationResponse().getSecuredContent(),
				getSymmKey());
		return result;
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
	 * Unseal revoke relation response.
	 *
	 * @param response
	 *            the response
	 * @return the revoke relation result
	 */
	private RevokeRelationResult unsealRevokeRelationResponse(final RevokeRelationResponse response) {
		final MarshallerHelper<RevokeRelationResult, Object> marshaller = new MarshallerHelper<>(RevokeRelationResult.class, Object.class);
		final RevokeRelationResult result = marshaller.unsealWithSymmKey(response.getSecuredRevokeRelationResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * Unseal put reservation.
	 *
	 * @param dataResponse
	 *            the data response
	 * @return the put reservation response @ the integration module exception
	 */
	private CreateReservationResult unsealCreateReservationResponse(final CreateReservationResponse dataResponse) {
		final MarshallerHelper<CreateReservationResult, Object> marshaller = new MarshallerHelper<>(CreateReservationResult.class, Object.class);
		final CreateReservationResult result = marshaller.unsealWithSymmKey(dataResponse.getSecuredCreateReservationResponse().getSecuredContent(),
				getSymmKey());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#putVision")
	@Override
	public PutVisionResult putData(final PutVisionParam putVisionParam) {
		RidValidator.validateRid(putVisionParam.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final byte[] sealedContent = getSealedData(putVisionParam);
			PutVisionForPatientRequest request = new PutVisionForPatientRequest();
			request.setSecuredPutVisionForPatientRequest(createSecuredContentTypeV4(sealedContent));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			try {
				final PutVisionForPatientResponse response = RecipePatientServiceV4Impl.getInstance().putVision(request);
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
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#putReservation")
	@Override
	public CreateReservationResult putData(final CreateReservationParam param) {
		RidValidator.validateRid(param.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final CreateReservationRequest createReservationRequest = getCreateReservationRequest(param);
			try {
				final CreateReservationResponse response = RecipePatientServiceV4Impl.getInstance().createReservation(createReservationRequest);
				final CreateReservationResult result = unsealCreateReservationResponse(response);
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
	 * {@inheritDoc}
	 */
	@Override
	public ListPatientPrescriptionsResult listOpenPrescriptions(ListPatientPrescriptionsParam listPatientPrescriptionsParam) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			// create param
			final ListPatientPrescriptionsParam param = new ListPatientPrescriptionsParam();
			param.setSymmKey(getSymmKey().getEncoded());
			param.setPage(listPatientPrescriptionsParam.getPage());

			final byte[] sealedContent = getSealedData(param);

			// create request
			final ListOpenPrescriptionsRequest request = new ListOpenPrescriptionsRequest();
			request.setSecuredListOpenPrescriptionsRequest(createSecuredContentTypeV4(sealedContent));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());

			// call sealed WS
			ListOpenPrescriptionsResponse response = null;
			try {
				response = RecipePatientServiceV4Impl.getInstance().listOpenPrescriptions(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}

			final MarshallerHelper<ListPatientPrescriptionsResult, ListPatientPrescriptionsParam> helper = new MarshallerHelper<>(
					ListPatientPrescriptionsResult.class, ListPatientPrescriptionsParam.class);
			// unseal WS response
			final ListPatientPrescriptionsResult result = helper
					.unsealWithSymmKey(response.getSecuredListOpenPrescriptionsResponse().getSecuredContent(), getSymmKey());

			checkStatus(result);
			return result;

		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}

		return null;
	}


	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(GetPrescriptionStatusParam)")
	@Override
	public GetPrescriptionStatusResult getData(final GetPrescriptionStatusParam data) {
		validateRid(data.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final byte[] sealedContent = getSealedData(data);
			GetPrescriptionStatusRequest request = new GetPrescriptionStatusRequest();
			request.setSecuredGetPrescriptionStatusRequest(createSecuredContentTypeV4(sealedContent));
			request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setIssueInstant(new DateTime());
			request.setId(getId());
			try {
				final GetPrescriptionStatusResponse response = RecipePatientServiceV4Impl.getInstance().getPrescriptionStatus(request);
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
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(ListRidsHistoryParam)")
	@Override
	public ListRidsHistoryResult getData(final ListRidsHistoryParam param) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final ListRidsHistoryRequest request = getListRidsHistory(param);
			try {
				final be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse response = RecipePatientServiceV4Impl.getInstance()
						.listRidsHistory(request);
				final ListRidsHistoryResult result = unsealListRidsHistoryResponse(response);
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
	 * Unseal list prescription history response.
	 *
	 * @param response
	 *            the response
	 * @return the list prescription history response @ the integration module exception
	 */
	private ListRidsHistoryResult unsealListRidsHistoryResponse(final ListRidsHistoryResponse response) {
		final MarshallerHelper<ListRidsHistoryResult, Object> marshaller1 = new MarshallerHelper<>(ListRidsHistoryResult.class, Object.class);
		return marshaller1.unsealWithSymmKey(response.getSecuredListRidsHistoryResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(ListOpenPrescriptionsParam)")
	@Override
	public ListOpenRidsResult getData(final ListOpenRidsParam data) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final ListOpenRidsRequest request = getListOpenRids(data);
			try {
				final ListOpenRidsResponse getDataResponse = RecipePatientServiceV4Impl.getInstance().listOpenRids(request);
				final ListOpenRidsResult unsealedResponse = unsealListOpenPrescriptionResponse(getDataResponse);
				checkStatus(unsealedResponse);
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
	 * Unseal list open prescriptions response.
	 *
	 * @param response
	 *            the response
	 * @return the list open prescriptions result @ the integration module exception
	 */
	private ListOpenRidsResult unsealListOpenPrescriptionResponse(final ListOpenRidsResponse response) {
		final MarshallerHelper<ListOpenRidsResult, Object> marshaller = new MarshallerHelper<>(ListOpenRidsResult.class, Object.class);
		return marshaller.unsealWithSymmKey(response.getSecuredListOpenRidsResponse().getSecuredContent(), getSymmKey());
	}

	/**
	 * Gets the vision request.
	 *
	 * @param data
	 *            the data
	 * @return the vision request @ the integration module exception
	 */
	protected GetVisionRequest getVisionRequest(final GetVisionParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final GetVisionRequest request = new GetVisionRequest();
		request.setSecuredGetVisionRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Put vision request.
	 *
	 * @param data
	 *            the data
	 * @return the put data @ the integration module exception
	 */
	protected PutVisionForPatientRequest putVision(final PutVisionParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final PutVisionForPatientRequest request = new PutVisionForPatientRequest();
		request.setSecuredPutVisionForPatientRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Put reservation request.
	 *
	 * @param data
	 *            the data
	 * @return the put data @ the integration module exception
	 */
	public CreateReservationRequest getCreateReservationRequest(final CreateReservationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final CreateReservationRequest request = new CreateReservationRequest();
		request.setSecuredCreateReservationRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the list prescription history request.
	 *
	 * @param data
	 *            the data
	 * @return the list prescription history request @ the integration module exception
	 */
	public ListRidsHistoryRequest getListRidsHistory(final ListRidsHistoryParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListRidsHistoryRequest request = new ListRidsHistoryRequest();
		request.setSecuredListRidsHistoryRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the list open prescriptions request.
	 *
	 * @param data
	 *            the data
	 * @return the list open prescriptions request @ the integration module exception
	 */
	protected ListOpenRidsRequest getListOpenRids(final ListOpenRidsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListOpenRidsRequest request = new ListOpenRidsRequest();
		request.setSecuredListOpenRidsRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	public byte[] getSealedData(final GetPrescriptionStatusParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, GetPrescriptionStatusParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListRidsHistoryParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, ListRidsHistoryParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final ListOpenRidsParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, ListOpenRidsParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final CreateReservationParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, CreateReservationParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final GetVisionParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, GetVisionParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	public byte[] getSealedData(final PutVisionParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, PutVisionParam.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRelationsResult getData(final ListRelationsParam patientRelationParam) {
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final ListRelationsRequest request = getListPatientRelation(patientRelationParam);
			try {
				final ListRelationsResponse dataResponse = RecipePatientServiceV4Impl.getInstance().listRelations(request);
				final ListRelationsResult unsealedResponse = unsealListRelationsResponse(dataResponse);
				checkStatus(unsealedResponse);
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
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#putData(PatientRelationParam)")
	@Override
	public CreateRelationResult putData(final CreateRelationParam patientRelationParam) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final CreateRelationRequest request = getCreateRelation(patientRelationParam);
			try {
				final CreateRelationResponse dataResponse = RecipePatientServiceV4Impl.getInstance().createRelation(request);
				final CreateRelationResult unsealedResponse = unsealCreateResponse(dataResponse);
				checkStatus(unsealedResponse);
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
	public ListRelationsRequest getListPatientRelation(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListRelationsRequest request = new ListRelationsRequest();
		request.setSecuredListRelationsRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Creates a {@link CreateRelationParam} request.
	 *
	 * @param data
	 *            the data
	 * @return the creates the relation @ the integration module exception
	 */
	private CreateRelationRequest getCreateRelation(final CreateRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final CreateRelationRequest request = new CreateRelationRequest();
		request.setSecuredCreateRelationRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
		request.setIssueInstant(new DateTime());
		request.setId(getId());
		return request;
	}

	/**
	 * Creates a {@link RevokeRelationParam} request.
	 *
	 * @param data
	 *            the data
	 * @return the revoke relation @ the integration module exception
	 */
	private RevokeRelationRequest getRevokeRelation(final RevokeRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final RevokeRelationRequest request = new RevokeRelationRequest();
		request.setSecuredRevokeRelationRequest(createSecuredContentTypeV4(getSealedData(data)));
		request.setProgramId(PropertyHandler.getInstance().getProperty("programIdentification"));
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
	private byte[] getSealedData(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(data, ListRelationsParam.class);
	}

	/**
	 * Puts the sealed data.
	 *
	 * @param data
	 *            the data
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final CreateRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(data, CreateRelationParam.class);
	}

	/**
	 * Revokes the sealed data.
	 *
	 * @param data
	 *            the data
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final RevokeRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(data, RevokeRelationParam.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokeRelationResult revokeData(final RevokeRelationParam patientRelationParam) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final RevokeRelationRequest request = getRevokeRelation(patientRelationParam);
			try {
				final RevokeRelationResponse dataResponse = RecipePatientServiceV4Impl.getInstance().revokeRelation(request);
				final RevokeRelationResult unsealedResponse = unsealRevokeRelationResponse(dataResponse);
				checkStatus(unsealedResponse);
				return unsealedResponse;
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}
		} catch (final Throwable t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}
}