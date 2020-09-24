package be.business.connector.recipe.executor.services;

import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
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

public class RecipeExecutorServiceDevV4Impl implements RecipeExecutorServiceDevV4 {
   private static final String ENDPOINT_NAME = "endpoint.executor.v4";
   private static RecipeExecutorServiceDevV4 recipeExecutorService;

   public static RecipeExecutorServiceDevV4 getInstance() {
      if (recipeExecutorService == null) {
         recipeExecutorService = new RecipeExecutorServiceDevV4Impl();
      }

      return recipeExecutorService;
   }

   public RevokePrescriptionResponse revokePrescriptionForExecutor(RevokePrescription revokePrescriptionForExecutorRequest) {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(aliveCheckRequest, AliveCheckResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public CreateFeedbackResponse createFeedback(CreateFeedback createFeedbackRequest) {
      return (CreateFeedbackResponse)GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutor getPrescriptionForExecutorRequest) {
      return (GetPrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsArchivedResponse markAsArchived(MarkAsArchived markAsArchivedRequest) {
      return (MarkAsArchivedResponse)GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsDeliveredResponse markAsDelivered(MarkAsDelivered markAsDeliveredRequest) {
      return (MarkAsDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDelivered markAsUnDeliveredRequest) {
      return (MarkAsUnDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListNotificationsResponse listNotifications(ListNotifications request) {
      return (ListNotificationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListNotificationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptions request) {
      return (ListOpenPrescriptionsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListOpenPrescriptionsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request) {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistory request) {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListReservationsResponse listReservations(ListReservations request) {
      return (ListReservationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListReservationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListRidsInProcessResponse listRidsInProcess(ListRidsInProcess request) {
      return (ListRidsInProcessResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsInProcessResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public PutRidsInProcessResponse putRidsInProcess(PutRidsInProcess request) {
      return (PutRidsInProcessResponse)GenericWebserviceCaller.callGenericWebservice(request, PutRidsInProcessResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }

   public ListRelationsResponse listRelations(ListRelations request) {
      return (ListRelationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true);
   }
}
