package be.business.connector.recipe.patient;

import static be.business.connector.recipe.utils.RidValidator.validateRid;

import java.util.List;
import java.util.UUID;

import org.perf4j.aop.Profiled;

import com.sun.xml.ws.client.ClientTransportException;

import be.business.connector.common.ApplicationConfig;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.handlers.InsurabilityHandler;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.recipe.patient.services.RecipePatientServiceDevV4Impl;
import be.business.connector.recipe.utils.RidValidator;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.patient.CreateRelation;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResponse;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservation;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResponse;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatient;
import be.recipe.services.patient.GetPrescriptionForPatientResponse;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionStatus;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResponse;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVision;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResponse;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRids;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResponse;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListPatientPrescription;
import be.recipe.services.patient.ListPatientPrescriptionResponse;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelations;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResponse;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistory;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResponse;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVision;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResponse;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokePrescription;
import be.recipe.services.patient.RevokePrescriptionResponse;
import be.recipe.services.patient.RevokePrescriptionResult;
import be.recipe.services.patient.RevokeRelation;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResponse;
import be.recipe.services.patient.RevokeRelationResult;
import be.recipe.services.patient.UpdateFeedbackFlag;
import be.recipe.services.patient.UpdateFeedbackFlagParam;
import be.recipe.services.patient.UpdateFeedbackFlagResponse;
import be.recipe.services.patient.UpdateFeedbackFlagResult;

/**
 * The Class PatientIntegrationModuleV4Impl.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public class PatientIntegrationModuleDevV4Impl extends AbstractPatientIntegrationModule implements PatientIntegrationModuleDevV4 {

	/**
	 * Instantiates a new patient integration module V4 impl.
	 *
	 * @ the integration module exception
	 */
	public PatientIntegrationModuleDevV4Impl() {
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
				final UpdateFeedbackFlagResponse response = RecipePatientServiceDevV4Impl.getInstance().updateFeedbackFlag(request);
				final MarshallerHelper<UpdateFeedbackFlagResult, UpdateFeedbackFlagResult> helper1 = new MarshallerHelper<>(
						UpdateFeedbackFlagResult.class, UpdateFeedbackFlagResult.class);
				final UpdateFeedbackFlagResult result = helper1
						.unsealWithSymmKey(response.getUpdateFeedbackFlagResultSealed(), getSymmKey());
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
	@Override
	public void revokePrescription(final String rid, final String reason) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final byte[] sealedRevokePrescriptionParam = getSealedRevokePrescriptionParam(rid, reason);

			final RevokePrescription request = new RevokePrescription();
			request.setRevokePrescriptionParamSealed(sealedRevokePrescriptionParam);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			try {
				final RevokePrescriptionResponse response = RecipePatientServiceDevV4Impl.getInstance().revokePrescription(request);
				final MarshallerHelper<RevokePrescriptionResult, RevokePrescriptionResult> helper = new MarshallerHelper<>(
						RevokePrescriptionResult.class, RevokePrescriptionResult.class);
				final RevokePrescriptionResult result = helper
						.unsealWithSymmKey(response.getRevokePrescriptionResultSealed(), getSymmKey());
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
	public GetPrescriptionForPatientResult getPrescription(final String rid) {
		RidValidator.validateRid(rid);
		ApplicationConfig.getInstance().assertValidSession();
		InsurabilityHandler.setInsurability(null);
		InsurabilityHandler.setMessageId(null);

		try {
			final byte[] sealedContent = getSealedGetPrescriptionForPatientParam(rid);

			final GetPrescriptionForPatient request = new GetPrescriptionForPatient();
			request.setGetPrescriptionForPatientParamSealed(sealedContent);
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			GetPrescriptionForPatientResponse response = null;
			try {
				response = RecipePatientServiceDevV4Impl.getInstance().getPrescriptionForPatient(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.executor"), cte);
			}

			final GetPrescriptionForPatientResult finalResult = unsealPrescription(response.getGetPrescriptionForPatientResultSealed());
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
			final GetVision request = getVisionRequest(data);
			try {
				final GetVisionResponse getDataResponse = RecipePatientServiceDevV4Impl.getInstance().getVision(request);
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
		final GetVisionResult result = marshaller.unsealWithSymmKey(getVisionResponse.getGetVisionResultSealed(), getSymmKey());
		return result;
	}

	/**
	 * Unseal put vision.
	 *
	 * @param putVisionResponse
	 *            the data response
	 * @return the put vision response @ the integration module exception
	 */
	private PutVisionResult unsealPutVisionResponse(final PutVisionResponse putVisionResponse) {
		final MarshallerHelper<PutVisionResult, Object> marshaller = new MarshallerHelper<>(PutVisionResult.class, Object.class);
		final PutVisionResult result = marshaller.unsealWithSymmKey(putVisionResponse.getPutVisionResultSealed(), getSymmKey());
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
		final CreateRelationResult result = marshaller.unsealWithSymmKey(response.getCreateRelationResultSealed(), getSymmKey());
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
		final ListRelationsResult result = marshaller.unsealWithSymmKey(response.getListRelationsResultSealed(), getSymmKey());
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
		final RevokeRelationResult result = marshaller.unsealWithSymmKey(response.getRevokeRelationResultSealed(), getSymmKey());
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
		final CreateReservationResult result = marshaller.unsealWithSymmKey(dataResponse.getCreateReservationResultSealed(), getSymmKey());
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
			final PutVision request = putVision(putVisionParam);
			try {
				final PutVisionResponse response = RecipePatientServiceDevV4Impl.getInstance().putVision(request);
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
	public CreateReservationResult putData(final CreateReservationParam data) {
		RidValidator.validateRid(data.getRid());
		ApplicationConfig.getInstance().assertValidSession();
		try {
			final CreateReservation putReservation = putReservationRequest(data);
			try {
				final CreateReservationResponse response = RecipePatientServiceDevV4Impl.getInstance().createReservation(putReservation);
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
			// init helper
			final MarshallerHelper<ListPatientPrescriptionsResult, ListPatientPrescriptionsParam> helper = new MarshallerHelper<>(
					ListPatientPrescriptionsResult.class, ListPatientPrescriptionsParam.class);

			// get recipe etk
			final List<EncryptionToken> etkRecipes = getEtkHelper().getRecipe_ETK();

			// create param
			final ListPatientPrescriptionsParam param = new ListPatientPrescriptionsParam();
			param.setSymmKey(getSymmKey().getEncoded());

			// create request
			final ListPatientPrescription request = new ListPatientPrescription();
			request.setListPatientPrescriptionsParamSealed(sealRequest(etkRecipes.get(0), helper.toXMLByteArray(param)));
			request.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
			request.setMguid(UUID.randomUUID().toString());

			// call sealed WS
			ListPatientPrescriptionResponse response = null;
			try {
				response = RecipePatientServiceDevV4Impl.getInstance().listOpenPrescriptions(request);
			} catch (final ClientTransportException cte) {
				throw new IntegrationModuleException(I18nHelper.getLabel("error.connection.prescriber"), cte);
			}

			// unseal WS response
			final ListPatientPrescriptionsResult result = helper.unsealWithSymmKey(response.getListPatientPrescriptionsResultSealed(), getSymmKey());

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

			final GetPrescriptionStatus getPrescriptionStatus = getGetPrescriptionStatusRequest(data);
			try {
				final GetPrescriptionStatusResponse response = RecipePatientServiceDevV4Impl.getInstance()
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
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(ListRidsHistoryParam)")
	@Override
	public ListRidsHistoryResult getData(final ListRidsHistoryParam param) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final ListRidsHistory request = getListRidsHistory(param);
			try {
				final ListRidsHistoryResponse response = RecipePatientServiceDevV4Impl.getInstance().listRidsHistory(request);
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
		return marshaller1.unsealWithSymmKey(response.getListRidsHistoryResultSealed(), getSymmKey());
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PatientIntegrationModuleV4#getData(ListOpenPrescriptionsParam)")
	@Override
	public ListOpenRidsResult getData(final ListOpenRidsParam data) {
		ApplicationConfig.getInstance().assertValidSession();
		try {

			final ListOpenRids request = getListOpenRids(data);
			try {
				final ListOpenRidsResponse getDataResponse = RecipePatientServiceDevV4Impl.getInstance().listOpenRids(request);
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
		return marshaller.unsealWithSymmKey(response.getListOpenRidsResultSealed(), getSymmKey());
	}

	/**
	 * Gets the vision request.
	 *
	 * @param data
	 *            the data
	 * @return the vision request @ the integration module exception
	 */
	protected GetVision getVisionRequest(final GetVisionParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final GetVision getVision = new GetVision();
		getVision.setGetVisionParamSealed(getSealedData(data));
		getVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		getVision.setMguid(UUID.randomUUID().toString());
		return getVision;
	}

	/**
	 * Put vision request.
	 *
	 * @param data
	 *            the data
	 * @return the put data @ the integration module exception
	 */
	protected PutVision putVision(final PutVisionParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final PutVision putVision = new PutVision();
		putVision.setPutVisionParamSealed(getSealedData(data));
		putVision.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		putVision.setMguid(UUID.randomUUID().toString());
		return putVision;
	}

	/**
	 * Put reservation request.
	 *
	 * @param data
	 *            the data
	 * @return the put data @ the integration module exception
	 */
	protected CreateReservation putReservationRequest(final CreateReservationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final CreateReservation putReservation = new CreateReservation();
		putReservation.setCreateReservationParamSealed(getSealedData(data));
		putReservation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		putReservation.setMguid(UUID.randomUUID().toString());
		return putReservation;
	}

	/**
	 * Gets the gets the prescription status request.
	 *
	 * @param data
	 *            the data
	 * @return the gets the prescription status request @ the integration module exception
	 */
	protected GetPrescriptionStatus getGetPrescriptionStatusRequest(final GetPrescriptionStatusParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final GetPrescriptionStatus getPrescriptionStatus = new GetPrescriptionStatus();
		getPrescriptionStatus.setGetPrescriptionStatusParamSealed(getSealedData(data));
		getPrescriptionStatus.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		getPrescriptionStatus.setMguid(UUID.randomUUID().toString());
		return getPrescriptionStatus;
	}

	/**
	 * Gets the list prescription history request.
	 *
	 * @param data
	 *            the data
	 * @return the list prescription history request @ the integration module exception
	 */
	protected ListRidsHistory getListRidsHistory(final ListRidsHistoryParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListRidsHistory listPrescriptionHistory = new ListRidsHistory();
		listPrescriptionHistory.setListRidsHistoryParamSealed(getSealedData(data));

		listPrescriptionHistory.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		listPrescriptionHistory.setMguid(UUID.randomUUID().toString());
		return listPrescriptionHistory;
	}

	/**
	 * Gets the list open prescriptions request.
	 *
	 * @param data
	 *            the data
	 * @return the list open prescriptions request @ the integration module exception
	 */
	protected ListOpenRids getListOpenRids(final ListOpenRidsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListOpenRids listOpenPrescription = new ListOpenRids();
		listOpenPrescription.setListOpenRidsParamSealed(getSealedData(data));
		listOpenPrescription.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		listOpenPrescription.setMguid(UUID.randomUUID().toString());
		return listOpenPrescription;
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param request
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	private byte[] getSealedData(final GetPrescriptionStatusParam request) {
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
	private byte[] getSealedData(final PutVisionParam request) {
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

			final ListRelations request = getListPatientRelation(patientRelationParam);
			try {
				final ListRelationsResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().listRelations(request);
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

			final CreateRelation request = getCreateRelation(patientRelationParam);
			try {
				final CreateRelationResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().createRelation(request);
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
	private ListRelations getListPatientRelation(final ListRelationsParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final ListRelations patientRelation = new ListRelations();
		patientRelation.setListRelationsParamSealed(getSealedData(data));
		patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		patientRelation.setMguid(UUID.randomUUID().toString());
		return patientRelation;
	}

	/**
	 * Creates a {@link CreateRelationParam} request.
	 *
	 * @param data
	 *            the data
	 * @return the creates the relation @ the integration module exception
	 */
	private CreateRelation getCreateRelation(final CreateRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final CreateRelation patientRelation = new CreateRelation();
		patientRelation.setCreateRelationParamSealed(getSealedData(data));
		patientRelation.setProgramIdentification(PropertyHandler.getInstance().getProperty("programIdentification"));
		patientRelation.setMguid(UUID.randomUUID().toString());
		return patientRelation;
	}

	/**
	 * Creates a {@link RevokeRelationParam} request.
	 *
	 * @param data
	 *            the data
	 * @return the revoke relation @ the integration module exception
	 */
	private RevokeRelation getRevokeRelation(final RevokeRelationParam data) {
		data.setSymmKey(getSymmKey().getEncoded());
		final RevokeRelation patientRelation = new RevokeRelation();
		patientRelation.setRevokeRelationParamSealed(getSealedData(data));
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

			final RevokeRelation request = getRevokeRelation(patientRelationParam);
			try {
				final RevokeRelationResponse dataResponse = RecipePatientServiceDevV4Impl.getInstance().revokeRelation(request);
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