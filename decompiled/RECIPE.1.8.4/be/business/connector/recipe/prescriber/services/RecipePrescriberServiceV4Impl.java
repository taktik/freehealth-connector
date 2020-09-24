package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
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

public class RecipePrescriberServiceV4Impl implements RecipePrescriberServiceV4 {
   private static final String ENDPOINT_NAME = "endpoint.prescriber.v4";
   private static RecipePrescriberServiceV4 recipePrescriberService;

   private RecipePrescriberServiceV4Impl() {
   }

   public static RecipePrescriberServiceV4 getInstance() {
      if (recipePrescriberService == null) {
         recipePrescriberService = new RecipePrescriberServiceV4Impl();
      }

      return recipePrescriberService;
   }

   public GetPrescriptionResponse getPrescriptionForPrescriber(GetPrescriptionRequest request) throws IntegrationModuleException {
      return (GetPrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription\"");
   }

   public PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest request) throws IntegrationModuleException {
      return (PutFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(request, PutFeedbackFlagResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag\"");
   }

   public ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest request) throws IntegrationModuleException {
      return (ListFeedbacksResponse)GenericWebserviceCaller.callGenericWebservice(request, ListFeedbacksResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listFeedbacks\"");
   }

   public RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest request) throws IntegrationModuleException {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(request, RevokePrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"");
   }

   public SendNotificationResponse sendNotification(SendNotificationRequest request) throws IntegrationModuleException {
      return (SendNotificationResponse)GenericWebserviceCaller.callGenericWebservice(request, SendNotificationResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:sendNotification\"");
   }

   public CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest request) throws IntegrationModuleException {
      return (CreatePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(request, CreatePrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:createPrescription\"");
   }

   public GetValidationPropertiesResponse getValidationProperties(GetValidationPropertiesRequest request) throws IntegrationModuleException {
      return (GetValidationPropertiesResponse)GenericWebserviceCaller.callGenericWebservice(request, GetValidationPropertiesResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getValidationProperties\"");
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest request) throws IntegrationModuleException {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus\"");
   }

   public ListOpenRidsResponse listOpenRids(ListOpenRidsRequest request) throws IntegrationModuleException {
      return (ListOpenRidsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListOpenRidsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:ListOpenRids\"");
   }

   public PutVisionForPrescriberResponse putVisionForPrescriber(PutVisionForPrescriberRequest request) throws IntegrationModuleException {
      return (PutVisionForPrescriberResponse)GenericWebserviceCaller.callGenericWebservice(request, PutVisionForPrescriberResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPrescriber\"");
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest request) throws IntegrationModuleException {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class, EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory\"");
   }
}
