package be.business.connector.recipe.executor.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
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

/**
 * The Class RecipeExecutorServiceV4Impl.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public class RecipeExecutorServiceV4Impl implements RecipeExecutorServiceV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.executor.v4";

	/** The recipe executor service. */
	private static RecipeExecutorServiceV4 recipeExecutorService;

	/**
	 * Gets the single instance of RecipeExecutorServiceV4Impl.
	 *
	 * @return single instance of RecipeExecutorServiceV4Impl
	 */
	public static RecipeExecutorServiceV4 getInstance() {
		if (recipeExecutorService == null) {
			recipeExecutorService = new RecipeExecutorServiceV4Impl();
		}
		return recipeExecutorService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokePrescriptionResponse revokePrescriptionForExecutor(final RevokePrescriptionRequest revokePrescriptionForExecutorRequest) {
		return GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateFeedbackResponse createFeedback(final CreateFeedbackRequest createFeedbackRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:createFeedback\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(final GetPrescriptionForExecutorRequest getPrescriptionForExecutorRequest) {
		return GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionForExecutor\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsArchivedResponse markAsArchived(final MarkAsArchivedRequest markAsArchivedRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsArchived\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsDeliveredResponse markAsDelivered(final MarkAsDeliveredRequest markAsDeliveredRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsDelivered\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsUnDeliveredResponse markAsUnDelivered(final MarkAsUnDeliveredRequest markAsUnDeliveredRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsUnDelivered\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListNotificationsResponse listNotifications(final ListNotificationsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListNotificationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listNotifications\"");
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
	public ListReservationsResponse listReservations(final ListReservationsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListReservationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listReservations\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsInProcessResponse listRidsInProcess(final ListRidsInProcessRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRidsInProcessResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsInProcess\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutRidsInProcessResponse putRidsInProcess(final PutRidsInProcessRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, PutRidsInProcessResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:putRidsInProcess\"");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRelationsResponse listRelations(ListRelationsRequest request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true,
				"\"urn:be:fgov:ehealth:recipe:protocol:v4:listRelations\"");
	}
}