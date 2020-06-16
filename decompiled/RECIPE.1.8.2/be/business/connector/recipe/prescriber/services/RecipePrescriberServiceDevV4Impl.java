package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListFeedbacksResponse;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListOpenRidsResponse;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.RevokePrescriptionResponse;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.UpdateFeedbackFlagResponse;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesResponse;

public class RecipePrescriberServiceDevV4Impl implements RecipePrescriberServiceDevV4 {
   private static final String ENDPOINT_NAME = "endpoint.prescriber.v4";
   private static final String SERVICE_NAME = RecipePrescriberServiceDevV4Impl.class.getName();
   private static RecipePrescriberServiceDevV4 recipePrescriberService;

   private RecipePrescriberServiceDevV4Impl() {
   }

   public static RecipePrescriberServiceDevV4 getInstance() {
      if (recipePrescriberService == null) {
         recipePrescriberService = new RecipePrescriberServiceDevV4Impl();
      }

      return recipePrescriberService;
   }

   public GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriber getPrescriptionForPrescriberRequest) throws IntegrationModuleException {
      return (GetPrescriptionForPrescriberResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest), GetPrescriptionForPrescriberResponse.class);
   }

   public UpdateFeedbackFlagResponse putFeedbackFlag(UpdateFeedbackFlag updateFeedbackFlagRequest) throws IntegrationModuleException {
      return (UpdateFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest), UpdateFeedbackFlagResponse.class);
   }

   public ListFeedbacksResponse listFeedbacks(ListFeedbacks listFeedbacksRequest) throws IntegrationModuleException {
      return (ListFeedbacksResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listFeedbacksRequest), ListFeedbacksResponse.class);
   }

   private GenericWebserviceRequest createDefaultGenericWebserviceRequest(Object requestObject) throws IntegrationModuleException {
      GenericWebserviceRequest request = new GenericWebserviceRequest();
      request.setRequest(requestObject);
      request.setEndpoint(EndpointResolver.getEndpointUrlString("endpoint.prescriber.v4"));
      request.setServiceName(SERVICE_NAME);
      request.setAddLoggingHandler(true);
      request.setAddSoapFaultHandler(true);
      request.setAddMustUnderstandHandler(true);
      request.setAddInsurabilityHandler(false);
      return request;
   }

   public RevokePrescriptionResponse revokePrescription(RevokePrescription paramRevokePrescriptionRequest) throws IntegrationModuleException {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(paramRevokePrescriptionRequest), RevokePrescriptionResponse.class);
   }

   public SendNotificationResponse sendNotification(SendNotification paramSendNotificationRequest) throws IntegrationModuleException {
      return (SendNotificationResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(paramSendNotificationRequest), SendNotificationResponse.class);
   }

   public CreatePrescriptionResponse createPrescription(CreatePrescription request) throws IntegrationModuleException {
      return (CreatePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), CreatePrescriptionResponse.class);
   }

   public ValidationPropertiesResponse getValidationProperties(ValidationProperties request) throws IntegrationModuleException {
      return (ValidationPropertiesResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), ValidationPropertiesResponse.class);
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request) throws IntegrationModuleException {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), GetPrescriptionStatusResponse.class);
   }

   public ListOpenRidsResponse listOpenRids(ListOpenRids request) throws IntegrationModuleException {
      return (ListOpenRidsResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), ListOpenRidsResponse.class);
   }

   public PutVisionResponse putVisionForPrescriber(PutVision request) throws IntegrationModuleException {
      return (PutVisionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), PutVisionResponse.class);
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistory request) throws IntegrationModuleException {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), ListRidsHistoryResponse.class);
   }
}
