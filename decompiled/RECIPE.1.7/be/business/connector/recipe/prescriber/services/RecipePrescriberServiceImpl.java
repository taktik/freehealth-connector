package org.taktik.connector.business.recipe.prescriber.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceCaller;
import org.taktik.connector.business.recipeprojects.core.services.GenericWebserviceRequest;
import org.taktik.connector.business.recipeprojects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v1.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v1.SendNotificationResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdateFeedbackFlagResponse;
import org.apache.log4j.Logger;

public class RecipePrescriberServiceImpl implements RecipePrescriberService {
   private static final Logger LOG = Logger.getLogger(RecipePrescriberServiceImpl.class);
   private static final String ENDPOINT_NAME = "endpoint.prescriber";
   private static final String SERVICE_NAME = RecipePrescriberServiceImpl.class.getName();
   private static RecipePrescriberService recipePrescriberService;

   public static RecipePrescriberService getInstance() {
      if (recipePrescriberService == null) {
         recipePrescriberService = new RecipePrescriberServiceImpl();
      }

      return recipePrescriberService;
   }

   public AliveCheckResponse aliveCheck(AliveCheckRequest aliveCheckRequest) throws IntegrationModuleException {
      return (AliveCheckResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(aliveCheckRequest), AliveCheckResponse.class);
   }

   public CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest createPrescriptionRequest) throws IntegrationModuleException {
      return (CreatePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(createPrescriptionRequest), CreatePrescriptionResponse.class);
   }

   public RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest revokePrescriptionRequest) throws IntegrationModuleException {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(revokePrescriptionRequest), RevokePrescriptionResponse.class);
   }

   public GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriberRequest getPrescriptionForPrescriberRequest) throws IntegrationModuleException {
      return (GetPrescriptionForPrescriberResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest), GetPrescriptionForPrescriberResponse.class);
   }

   public ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest listOpenPrescriptionsRequest) throws IntegrationModuleException {
      return (ListOpenPrescriptionsResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listOpenPrescriptionsRequest), ListOpenPrescriptionsResponse.class);
   }

   public SendNotificationResponse sendNotification(SendNotificationRequest sendNotificationRequest) throws IntegrationModuleException {
      return (SendNotificationResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(sendNotificationRequest), SendNotificationResponse.class);
   }

   public UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlagRequest updateFeedbackFlagRequest) throws IntegrationModuleException {
      return (UpdateFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest), UpdateFeedbackFlagResponse.class);
   }

   public ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest listFeedbacksRequest) throws IntegrationModuleException {
      return (ListFeedbacksResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listFeedbacksRequest), ListFeedbacksResponse.class);
   }

   private GenericWebserviceRequest createDefaultGenericWebserviceRequest(Object requestObject) throws IntegrationModuleException {
      GenericWebserviceRequest request = new GenericWebserviceRequest();
      request.setRequest(requestObject);
      request.setEndpoint(EndpointResolver.getEndpointUrlString("endpoint.prescriber"));
      request.setServiceName(SERVICE_NAME);
      request.setAddLoggingHandler(true);
      request.setAddSoapFaultHandler(true);
      request.setAddMustUnderstandHandler(true);
      request.setAddInsurabilityHandler(false);
      return request;
   }
}
