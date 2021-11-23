package be.business.connector.recipe.patient.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
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

/**
 * The Class RecipePatientServiceImpl.
 */
public class RecipePatientServiceV4Impl implements RecipePatientServiceV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.patient.v4";

	/** The Constant SERVICE_NAME. */
	private static final String SERVICE_NAME = RecipePatientServiceV4Impl.class.getName();

	/** The recipe patient service. */
	private static RecipePatientServiceV4 recipePatientService;

	/**
	 * Private constructor of {@link RecipePatientServiceV4Impl}.
	 */
	private RecipePatientServiceV4Impl() {
	}

	/**
	 * Gets the singleton instance of {@link RecipePatientServiceV4Impl}.
	 *
	 * @return singleton instance of {@link RecipePatientServiceV4Impl}
	 */
	public static RecipePatientServiceV4 getInstance() {
		if (recipePatientService == null) {
			recipePatientService = new RecipePatientServiceV4Impl();
		}
		return recipePatientService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionResponse getPrescriptionForPatient(final GetPrescriptionRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokePrescriptionResponse revokePrescription(final RevokePrescriptionRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, RevokePrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenPrescriptionsResponse listOpenPrescriptions(final ListOpenPrescriptionsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListOpenPrescriptionsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listOpenPrescriptions\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutFeedbackFlagResponse putFeedbackFlag(final PutFeedbackFlagRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, PutFeedbackFlagResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetVisionResponse getVision(final GetVisionRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetVisionResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME),
				getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getVision\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutVisionForPatientResponse putVision(final PutVisionForPatientRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, PutVisionForPatientResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPatient\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRelationsResponse listRelations(final ListRelationsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listRelations\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateRelationResponse createRelation(final CreateRelationRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, CreateRelationResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:createRelation\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokeRelationResponse revokeRelation(final RevokeRelationRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, RevokeRelationResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:revokeRelation\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateReservationResponse createReservation(final CreateReservationRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, CreateReservationResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:createReservation\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionStatusResponse getPrescriptionStatus(final GetPrescriptionStatusRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsHistoryResponse listRidsHistory(final ListRidsHistoryRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenRidsResponse listOpenRids(final ListOpenRidsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListOpenRidsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listOpenRids\"");
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
}
