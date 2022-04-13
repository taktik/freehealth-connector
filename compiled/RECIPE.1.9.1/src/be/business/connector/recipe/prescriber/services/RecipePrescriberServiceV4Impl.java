package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
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

/**
 * The Class RecipePrescriberServiceV4Impl.
 */
public class RecipePrescriberServiceV4Impl implements RecipePrescriberServiceV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.prescriber.v4";

	/** The recipe prescriber service. */
	private static RecipePrescriberServiceV4 recipePrescriberService;

	/**
	 * Instantiates a new recipe prescriber service V 4 impl.
	 */
	private RecipePrescriberServiceV4Impl() {
		super();
	}

	/**
	 * Gets the singleton instance of RecipePrescriberServiceImpl.
	 *
	 * @return singleton instance of RecipePrescriberServiceImpl
	 */
	public static RecipePrescriberServiceV4 getInstance() {
		if (recipePrescriberService == null) {
			recipePrescriberService = new RecipePrescriberServiceV4Impl();
		}
		return recipePrescriberService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionResponse getPrescriptionForPrescriber(final GetPrescriptionRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription\"");
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
	public ListFeedbacksResponse listFeedbacks(final ListFeedbacksRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListFeedbacksResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listFeedbacks\"");
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
	public SendNotificationResponse sendNotification(final SendNotificationRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, SendNotificationResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:sendNotification\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreatePrescriptionResponse createPrescription(final CreatePrescriptionRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, CreatePrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:createPrescription\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetValidationPropertiesResponse getValidationProperties(final GetValidationPropertiesRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetValidationPropertiesResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:getValidationProperties\"");
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
	public ListOpenRidsResponse listOpenRids(final ListOpenRidsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListOpenRidsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:ListOpenRids\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutVisionForPrescriberResponse putVisionForPrescriber(final PutVisionForPrescriberRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, PutVisionForPrescriberResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPrescriber\"");
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
}