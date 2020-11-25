package be.business.connector.recipe.executor.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorResponse;
import org.apache.log4j.Logger;

public class RecipeExecutorServiceV3Impl implements RecipeExecutorServiceV3 {
   private static final Logger LOG = Logger.getLogger(RecipeExecutorServiceV3Impl.class);
   private static final String ENDPOINT_NAME = "endpoint.executor.v3";
   private static RecipeExecutorServiceV3 recipeExecutorService;

   private RecipeExecutorServiceV3Impl() {
   }

   public static RecipeExecutorServiceV3 getInstance() {
      if (recipeExecutorService == null) {
         recipeExecutorService = new RecipeExecutorServiceV3Impl();
      }

      return recipeExecutorService;
   }

   public RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest revokePrescriptionForExecutorRequest) throws IntegrationModuleException {
      return (RevokePrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public CreateFeedbackResponse createFeedback(CreateFeedbackRequest createFeedbackRequest) throws IntegrationModuleException {
      return (CreateFeedbackResponse)GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest getPrescriptionForExecutorRequest) throws IntegrationModuleException {
      return (GetPrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest markAsArchivedRequest) throws IntegrationModuleException {
      return (MarkAsArchivedResponse)GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest markAsDeliveredRequest) throws IntegrationModuleException {
      return (MarkAsDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest markAsUnDeliveredRequest) throws IntegrationModuleException {
      return (MarkAsUnDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }

   public ListNotificationsResponse listNotifications(ListNotificationsRequest listNotificationsRequest) throws IntegrationModuleException {
      return (ListNotificationsResponse)GenericWebserviceCaller.callGenericWebservice(listNotificationsRequest, ListNotificationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v3"), this.getClass().getName(), true, true, true, true);
   }
}
