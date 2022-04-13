package be.business.connector.recipe.patient.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.recipe.services.patient.CreateRelation;
import be.recipe.services.patient.CreateRelationResponse;
import be.recipe.services.patient.CreateReservation;
import be.recipe.services.patient.CreateReservationResponse;
import be.recipe.services.patient.GetPrescriptionForPatient;
import be.recipe.services.patient.GetPrescriptionForPatientResponse;
import be.recipe.services.patient.GetPrescriptionStatus;
import be.recipe.services.patient.GetPrescriptionStatusResponse;
import be.recipe.services.patient.GetVision;
import be.recipe.services.patient.GetVisionResponse;
import be.recipe.services.patient.ListOpenRids;
import be.recipe.services.patient.ListOpenRidsResponse;
import be.recipe.services.patient.ListPatientPrescription;
import be.recipe.services.patient.ListPatientPrescriptionResponse;
import be.recipe.services.patient.ListRelations;
import be.recipe.services.patient.ListRelationsResponse;
import be.recipe.services.patient.ListRidsHistory;
import be.recipe.services.patient.ListRidsHistoryResponse;
import be.recipe.services.patient.PutVision;
import be.recipe.services.patient.PutVisionResponse;
import be.recipe.services.patient.RevokePrescription;
import be.recipe.services.patient.RevokePrescriptionResponse;
import be.recipe.services.patient.RevokeRelation;
import be.recipe.services.patient.RevokeRelationResponse;
import be.recipe.services.patient.UpdateFeedbackFlag;
import be.recipe.services.patient.UpdateFeedbackFlagResponse;

/**
 * The Class RecipePatientServiceImpl.
 */
public class RecipePatientServiceDevV4Impl implements RecipePatientServiceDevV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.patient.v4";

	/** The Constant SERVICE_NAME. */
	private static final String SERVICE_NAME = RecipePatientServiceDevV4Impl.class.getName();

	/** The recipe patient service. */
	private static RecipePatientServiceDevV4 recipePatientService;

	/**
	 * Instantiates a new recipe patient service impl.
	 */
	private RecipePatientServiceDevV4Impl() {
	}

	/**
	 * Gets the singleton instance of RecipePrescriberServiceImpl.
	 *
	 * @return singleton instance of RecipePrescriberServiceImpl
	 */
	public static RecipePatientServiceDevV4 getInstance() {
		if (recipePatientService == null) {
			recipePatientService = new RecipePatientServiceDevV4Impl();
		}
		return recipePatientService;
	}

	/**
	 * Override.
	 *
	 * @param getPrescriptionForPrescriberRequest
	 *            the get prescription for prescriber request
	 * @return the prescription for patient @ the integration module exception
	 */
	@Override
	public GetPrescriptionForPatientResponse getPrescriptionForPatient(final GetPrescriptionForPatient getPrescriptionForPrescriberRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest),
				GetPrescriptionForPatientResponse.class);
	}

	/**
	 * Override.
	 *
	 * @param revokePrescriptionRequest
	 *            the revoke prescription request
	 * @return the revoke patient prescription response @ the integration module exception
	 */
	@Override
	public RevokePrescriptionResponse revokePrescription(final RevokePrescription revokePrescriptionRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(revokePrescriptionRequest),
				RevokePrescriptionResponse.class);
	}

	/**
	 * Override.
	 *
	 * @param listOpenPrescriptionsRequest
	 *            the list open prescriptions request
	 * @return the list patient prescriptions response @ the integration module exception
	 */
	@Override
	public ListPatientPrescriptionResponse listOpenPrescriptions(final ListPatientPrescription listOpenPrescriptionsRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(listOpenPrescriptionsRequest),
				ListPatientPrescriptionResponse.class);
	}

	/**
	 * Override.
	 *
	 * @param updateFeedbackFlagRequest
	 *            the update feedback flag request
	 * @return the update patient feedback flag response @ the integration module exception
	 */
	@Override
	public UpdateFeedbackFlagResponse updateFeedbackFlag(final UpdateFeedbackFlag updateFeedbackFlagRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest),
				UpdateFeedbackFlagResponse.class);
	}

	/**
	 * Creates the default generic webservice request.
	 *
	 * @param requestObject
	 *            the request object
	 * @return the generic webservice request @ the integration module exception
	 */
	private GenericWebserviceRequest createDefaultGenericWebserviceRequest(final Object requestObject) {
		final GenericWebserviceRequest request = new GenericWebserviceRequest();
		request.setRequest(requestObject);
		request.setEndpoint(EndpointResolver.getEndpointUrlString(ENDPOINT_NAME));
		request.setServiceName(SERVICE_NAME);
		request.setAddLoggingHandler(true);
		request.setAddSoapFaultHandler(true);
		request.setAddMustUnderstandHandler(true);
		request.setAddInsurabilityHandler(false);
		return request;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetVisionResponse getVision(final GetVision request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), GetVisionResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutVisionResponse putVision(final PutVision request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), PutVisionResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRelationsResponse listRelations(final ListRelations request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), ListRelationsResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateRelationResponse createRelation(final CreateRelation request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), CreateRelationResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokeRelationResponse revokeRelation(final RevokeRelation request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), RevokeRelationResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateReservationResponse createReservation(final CreateReservation request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), CreateReservationResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionStatusResponse getPrescriptionStatus(final GetPrescriptionStatus request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), GetPrescriptionStatusResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsHistoryResponse listRidsHistory(final ListRidsHistory request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), ListRidsHistoryResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenRidsResponse listOpenRids(final ListOpenRids listOpenRids) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(listOpenRids), ListOpenRidsResponse.class);
	}
}