package be.business.connector.recipe.executor.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.recipe.services.executor.CreateFeedback;
import be.recipe.services.executor.CreateFeedbackResponse;
import be.recipe.services.executor.GetPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResponse;
import be.recipe.services.executor.GetPrescriptionStatus;
import be.recipe.services.executor.GetPrescriptionStatusResponse;
import be.recipe.services.executor.ListNotifications;
import be.recipe.services.executor.ListNotificationsResponse;
import be.recipe.services.executor.ListOpenPrescriptions;
import be.recipe.services.executor.ListOpenPrescriptionsResponse;
import be.recipe.services.executor.ListRelations;
import be.recipe.services.executor.ListRelationsResponse;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsResponse;
import be.recipe.services.executor.ListRidsHistory;
import be.recipe.services.executor.ListRidsHistoryResponse;
import be.recipe.services.executor.ListRidsInProcess;
import be.recipe.services.executor.ListRidsInProcessResponse;
import be.recipe.services.executor.MarkAsArchived;
import be.recipe.services.executor.MarkAsArchivedResponse;
import be.recipe.services.executor.MarkAsDelivered;
import be.recipe.services.executor.MarkAsDeliveredResponse;
import be.recipe.services.executor.MarkAsUnDelivered;
import be.recipe.services.executor.MarkAsUnDeliveredResponse;
import be.recipe.services.executor.PutRidsInProcess;
import be.recipe.services.executor.PutRidsInProcessResponse;
import be.recipe.services.executor.RevokePrescription;
import be.recipe.services.executor.RevokePrescriptionResponse;

/**
 * The Class RecipeExecutorServiceDevV4Impl.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public class RecipeExecutorServiceDevV4Impl implements RecipeExecutorServiceDevV4 {

	/** The Constant ENDPOINT_NAME. */
	private static final String ENDPOINT_NAME = "endpoint.executor.v4";

	/** The recipe executor service. */
	private static RecipeExecutorServiceDevV4 recipeExecutorService;

	/**
	 * Gets the single instance of RecipeExecutorServiceV4Impl.
	 *
	 * @return single instance of RecipeExecutorServiceV4Impl
	 */
	public static RecipeExecutorServiceDevV4 getInstance() {
		if (recipeExecutorService == null) {
			recipeExecutorService = new RecipeExecutorServiceDevV4Impl();
		}
		return recipeExecutorService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RevokePrescriptionResponse revokePrescriptionForExecutor(final RevokePrescription revokePrescriptionForExecutorRequest) {
		return GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CreateFeedbackResponse createFeedback(final CreateFeedback createFeedbackRequest) {
		return GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(final GetPrescriptionForExecutor getPrescriptionForExecutorRequest) {
		return GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsArchivedResponse markAsArchived(final MarkAsArchived markAsArchivedRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsDeliveredResponse markAsDelivered(final MarkAsDelivered markAsDeliveredRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MarkAsUnDeliveredResponse markAsUnDelivered(final MarkAsUnDelivered markAsUnDeliveredRequest) {
		return GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListNotificationsResponse listNotifications(final ListNotifications request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListNotificationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenPrescriptionsResponse listOpenPrescriptions(final ListOpenPrescriptions request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListOpenPrescriptionsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionStatusResponse getPrescriptionStatus(final GetPrescriptionStatus request) {
		return GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsHistoryResponse listRidsHistory(final ListRidsHistory request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListReservationsResponse listReservations(final ListReservations request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListReservationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsInProcessResponse listRidsInProcess(final ListRidsInProcess request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRidsInProcessResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutRidsInProcessResponse putRidsInProcess(final PutRidsInProcess request) {
		return GenericWebserviceCaller.callGenericWebservice(request, PutRidsInProcessResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public ListRelationsResponse listRelations(ListRelations request) {
		return GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class,
				EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}
}