package be.business.connector.recipe.executor.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
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
   private static final String ENDPOINT_NAME = "endpoint.executor";
   private static RecipeExecutorService recipeExecutorService;

   private RecipeExecutorServiceImpl() {
   }

   public static RecipeExecutorService getInstance() {
      if (recipeExecutorService == null) {
         recipeExecutorService = new RecipeExecutorServiceImpl();
      }

      return recipeExecutorService;
   }

   public RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest revokePrescriptionForExecutorRequest) throws IntegrationModuleException {
      return (RevokePrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public CreateFeedbackResponse createFeedback(CreateFeedbackRequest createFeedbackRequest) throws IntegrationModuleException {
      return (CreateFeedbackResponse)GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest getPrescriptionForExecutorRequest) throws IntegrationModuleException {
      return (GetPrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest markAsArchivedRequest) throws IntegrationModuleException {
      return (MarkAsArchivedResponse)GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest markAsDeliveredRequest) throws IntegrationModuleException {
      return (MarkAsDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest markAsUnDeliveredRequest) throws IntegrationModuleException {
      return (MarkAsUnDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }

   public ListNotificationsResponse listNotifications(ListNotificationsRequest listNotificationsRequest) throws IntegrationModuleException {
      return (ListNotificationsResponse)GenericWebserviceCaller.callGenericWebservice(listNotificationsRequest, ListNotificationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor"), this.getClass().getName(), true, true, true, true);
   }
}
