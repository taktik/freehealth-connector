package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationResponse;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationResponse;

public class RecipePatientServiceV4Impl implements RecipePatientServiceV4 {
   private static final String ENDPOINT_NAME = "endpoint.patient.v4";
   private static final String SERVICE_NAME = RecipePatientServiceV4Impl.class.getName();
   private static RecipePatientServiceV4 recipePatientService;

   private RecipePatientServiceV4Impl() {
   }

   public static RecipePatientServiceV4 getInstance() {
      if (recipePatientService == null) {
         recipePatientService = new RecipePatientServiceV4Impl();
      }

      return recipePatientService;
   }

   public GetPrescriptionResponse getPrescriptionForPatient(GetPrescriptionRequest request) throws IntegrationModuleException {
      return (GetPrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescription\"");
   }

   public RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest request) throws IntegrationModuleException {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(request, RevokePrescriptionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:revokePrescription\"");
   }

   public ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest request) throws IntegrationModuleException {
      return (ListOpenPrescriptionsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListOpenPrescriptionsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listOpenPrescriptions\"");
   }

   public PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest request) throws IntegrationModuleException {
      return (PutFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(request, PutFeedbackFlagResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putFeedbackFlag\"");
   }

   public GetVisionResponse getVision(GetVisionRequest request) throws IntegrationModuleException {
      return (GetVisionResponse)GenericWebserviceCaller.callGenericWebservice(request, GetVisionResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getVision\"");
   }

   public PutVisionForPatientResponse putVision(PutVisionForPatientRequest request) throws IntegrationModuleException {
      return (PutVisionForPatientResponse)GenericWebserviceCaller.callGenericWebservice(request, PutVisionForPatientResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:putVisionForPatient\"");
   }

   public ListRelationsResponse listRelations(ListRelationsRequest request) throws IntegrationModuleException {
      return (ListRelationsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRelationsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRelations\"");
   }

   public CreateRelationResponse createRelation(CreateRelationRequest request) throws IntegrationModuleException {
      return (CreateRelationResponse)GenericWebserviceCaller.callGenericWebservice(request, CreateRelationResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:createRelation\"");
   }

   public RevokeRelationResponse revokeRelation(RevokeRelationRequest request) throws IntegrationModuleException {
      return (RevokeRelationResponse)GenericWebserviceCaller.callGenericWebservice(request, RevokeRelationResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:revokeRelation\"");
   }

   public CreateReservationResponse createReservation(CreateReservationRequest request) throws IntegrationModuleException {
      return (CreateReservationResponse)GenericWebserviceCaller.callGenericWebservice(request, CreateReservationResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:createReservation\"");
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest request) throws IntegrationModuleException {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(request, GetPrescriptionStatusResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:getPrescriptionStatus\"");
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest request) throws IntegrationModuleException {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(request, ListRidsHistoryResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listRidsHistory\"");
   }

   public ListOpenRidsResponse listOpenRids(ListOpenRidsRequest request) throws IntegrationModuleException {
      return (ListOpenRidsResponse)GenericWebserviceCaller.callGenericWebservice(request, ListOpenRidsResponse.class, EndpointResolver.getEndpointUrlString("endpoint.patient.v4"), this.getClass().getName(), true, true, true, true, "\"urn:be:fgov:ehealth:recipe:protocol:v4:listOpenRids\"");
   }

   private GenericWebserviceRequest createDefaultGenericWebserviceRequest(Object requestObject) throws IntegrationModuleException {
      GenericWebserviceRequest request = new GenericWebserviceRequest();
      request.setRequest(requestObject);
      request.setEndpoint(EndpointResolver.getEndpointUrlString("endpoint.patient.v4"));
      request.setServiceName(SERVICE_NAME);
      request.setAddLoggingHandler(true);
      request.setAddSoapFaultHandler(true);
      request.setAddMustUnderstandHandler(true);
      request.setAddInsurabilityHandler(false);
      return request;
   }
}
