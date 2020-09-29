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

public class RecipeExecutorServiceV4Impl implements RecipeExecutorServiceV4 {
   private static final String ENDPOINT_NAME = "endpoint.executor.v4";
   private static RecipeExecutorServiceV4 recipeExecutorService;

   public static RecipeExecutorServiceV4 getInstance() {
      if (recipeExecutorService == null) {
         recipeExecutorService = new RecipeExecutorServiceV4Impl();
      }

      return recipeExecutorService;
   }

   public RevokePrescriptionResponse revokePrescriptionForExecutor(RevokePrescriptionRequest revokePrescriptionForExecutorRequest) {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(revokePrescriptionForExecutorRequest, RevokePrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"");
   }

   public CreateFeedbackResponse createFeedback(CreateFeedbackRequest createFeedbackRequest) {
      return (CreateFeedbackResponse)GenericWebserviceCaller.callGenericWebservice(createFeedbackRequest, CreateFeedbackResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:createFeedback\"");
   }

   public GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest getPrescriptionForExecutorRequest) {
      return (GetPrescriptionForExecutorResponse)GenericWebserviceCaller.callGenericWebservice(getPrescriptionForExecutorRequest, GetPrescriptionForExecutorResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionForExecutor\"");
   }

   public MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest markAsArchivedRequest) {
      return (MarkAsArchivedResponse)GenericWebserviceCaller.callGenericWebservice(markAsArchivedRequest, MarkAsArchivedResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsArchived\"");
   }

   public MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest markAsDeliveredRequest) {
      return (MarkAsDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsDeliveredRequest, MarkAsDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsDelivered\"");
   }

   public MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest markAsUnDeliveredRequest) {
      return (MarkAsUnDeliveredResponse)GenericWebserviceCaller.callGenericWebservice(markAsUnDeliveredRequest, MarkAsUnDeliveredResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:markAsUnDelivered\"");
   }

   public ListNotificationsResponse listNotifications(ListNotificationsRequest request) {
      return (ListNotificationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListNotificationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listNotifications\"");
   }

   public ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest request) {
      return (ListOpenPrescriptionsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListOpenPrescriptionsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listOpenPrescriptions\"");
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest request) {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus\"");
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest request) {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory\"");
   }

   public ListReservationsResponse listReservations(ListReservationsRequest request) {
      return (ListReservationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListReservationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listReservations\"");
   }

   public ListRidsInProcessResponse listRidsInProcess(ListRidsInProcessRequest request) {
      return (ListRidsInProcessResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsInProcessResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsInProcess\"");
   }

   public PutRidsInProcessResponse putRidsInProcess(PutRidsInProcessRequest request) {
      return (PutRidsInProcessResponse)GenericWebserviceCaller.callGenericWebservice(request, PutRidsInProcessResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putRidsInProcess\"");
   }

   public ListRelationsResponse listRelations(ListRelationsRequest request) {
      return (ListRelationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.executor.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRelations\"");
   }
}
