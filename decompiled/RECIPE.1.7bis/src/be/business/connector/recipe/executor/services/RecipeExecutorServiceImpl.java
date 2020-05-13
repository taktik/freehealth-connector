package org.taktik.connector.business.recipe.executor.services;

import org.apache.log4j.Logger;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v2.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v2.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v2.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v2.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v2.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v2.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v2.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v2.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v2.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v2.RevokePrescriptionForExecutorResponse;

public class RecipeExecutorServiceImpl implements RecipeExecutorService {

	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(RecipeExecutorServiceImpl.class);

	private static final String ENDPOINT_NAME = "endpoint.executor";

	private static RecipeExecutorService recipeExecutorService;

	private RecipeExecutorServiceImpl() {
	}

	/**
	 * Gets the singleton instance of RecipeExecutorServiceImpl.
	 * 
	 * @return singleton instance of RecipeExecutorServiceImpl
	 */
	public static RecipeExecutorService getInstance() {
		if (recipeExecutorService == null) {
			recipeExecutorService = new RecipeExecutorServiceImpl();
		}
		return recipeExecutorService;
	}

	@Override
	public RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest revokePrescriptionForExecutorRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public CreateFeedbackResponse createFeedback(CreateFeedbackRequest createFeedbackRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest getPrescriptionForExecutorRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest markAsArchivedRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest markAsDeliveredRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest markAsUnDeliveredRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class, EndpointResolver.getEndpointUrlString(ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

	@Override
	public ListNotificationsResponse listNotifications(ListNotificationsRequest listNotificationsRequest) throws IntegrationModuleException {
		return GenericWebserviceCaller.callGenericWebservice(listNotificationsRequest, ListNotificationsResponse.class,EndpointResolver.getEndpointUrlString( ENDPOINT_NAME), getClass().getName(), true, true, true, true);
	}

}
