package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListOpenRidsResponse;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesResponse;

/**
 * The Class RecipePrescriberServiceDevV4Impl.
 */
public class RecipePrescriberServiceDevV4Impl implements RecipePrescriberServiceDevV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.prescriber.v4";

	/** The Constant SERVICE_NAME. */
	private static final String SERVICE_NAME = RecipePrescriberServiceDevV4Impl.class.getName();

	/** The recipe prescriber service. */
	private static RecipePrescriberServiceDevV4 recipePrescriberService;

	/**
	 * Instantiates a new recipe prescriber service V 4 impl.
	 */
	private RecipePrescriberServiceDevV4Impl() {
		super();
	}

	/**
	 * Gets the singleton instance of RecipePrescriberServiceImpl.
	 *
	 * @return singleton instance of RecipePrescriberServiceImpl
	 */
	public static RecipePrescriberServiceDevV4 getInstance() {
		if (recipePrescriberService == null) {
			recipePrescriberService = new RecipePrescriberServiceDevV4Impl();
		}
		return recipePrescriberService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(
			final GetPrescriptionForPrescriber getPrescriptionForPrescriberRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest),
				be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public be.recipe.services.prescriber.UpdateFeedbackFlagResponse putFeedbackFlag(final UpdateFeedbackFlag updateFeedbackFlagRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest),
				be.recipe.services.prescriber.UpdateFeedbackFlagResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public be.recipe.services.prescriber.ListFeedbacksResponse listFeedbacks(final ListFeedbacks listFeedbacksRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(listFeedbacksRequest),
				be.recipe.services.prescriber.ListFeedbacksResponse.class);
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
	public be.recipe.services.prescriber.RevokePrescriptionResponse revokePrescription(final RevokePrescription paramRevokePrescriptionRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(paramRevokePrescriptionRequest),
				be.recipe.services.prescriber.RevokePrescriptionResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SendNotificationResponse sendNotification(final SendNotification paramSendNotificationRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(paramSendNotificationRequest),
				SendNotificationResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreatePrescriptionResponse createPrescription(final CreatePrescription request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request),
				be.recipe.services.prescriber.CreatePrescriptionResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValidationPropertiesResponse getValidationProperties(final ValidationProperties request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request),
				be.recipe.services.prescriber.ValidationPropertiesResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionStatusResponse getPrescriptionStatus(final GetPrescriptionStatus request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request),
				be.recipe.services.prescriber.GetPrescriptionStatusResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenRidsResponse listOpenRids(final ListOpenRids request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request), ListOpenRidsResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutVisionResponse putVisionForPrescriber(final PutVision request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request),
				be.recipe.services.prescriber.PutVisionResponse.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsHistoryResponse listRidsHistory(final ListRidsHistory request) {
		return GenericWebserviceCaller.callGenericWebservice(createDefaultGenericWebserviceRequest(request),
				be.recipe.services.prescriber.ListRidsHistoryResponse.class);
	}
}