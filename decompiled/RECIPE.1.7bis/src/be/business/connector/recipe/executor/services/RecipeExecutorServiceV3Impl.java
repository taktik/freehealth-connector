package org.taktik.connector.business.recipe.executor.services;

import org.apache.log4j.Logger;

import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorResponse;


public class RecipeExecutorServiceV3Impl implements RecipeExecutorServiceV3 {

	/** The Constant LOG. */
	private final static Logger LOG = Logger.getLogger(RecipeExecutorServiceV3Impl.class);

	private static final String ENDPOINT_NAME = "endpoint.executor.v3";

	private static RecipeExecutorServiceV3 recipeExecutorService;

	private RecipeExecutorServiceV3Impl() {
	}

	/**
	 * Gets the singleton instance of RecipeExecutorServiceV3Impl.
	 * 
	 * @return singleton instance of RecipeExecutorServiceV3Impl
	 */
	public static RecipeExecutorServiceV3 getInstance() {
		if (recipeExecutorService == null) {
			recipeExecutorService = new RecipeExecutorServiceV3Impl();
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
