package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.recipe.services.patient.CreateRelation;
import be.recipe.services.patient.CreateRelationResponse;
import be.recipe.services.patient.CreateReservation;
import be.recipe.services.patient.CreateReservationResponse;
import be.recipe.services.patient.GetPrescriptionForPatient;
import be.recipe.services.patient.GetPrescriptionForPatientResponse;
import be.recipe.services.patient.GetPrescriptionStatus;
import be.recipe.services.patient.GetPrescriptionStatusResponse;
import be.recipe.services.patient.GetVision;
import be.recipe.services.patient.GetVisionResponse;
import be.recipe.services.patient.ListOpenRids;
import be.recipe.services.patient.ListOpenRidsResponse;
import be.recipe.services.patient.ListPatientPrescription;
import be.recipe.services.patient.ListPatientPrescriptionResponse;
import be.recipe.services.patient.ListRelations;
import be.recipe.services.patient.ListRelationsResponse;
import be.recipe.services.patient.ListRidsHistory;
import be.recipe.services.patient.ListRidsHistoryResponse;
import be.recipe.services.patient.PutVision;
import be.recipe.services.patient.PutVisionResponse;
import be.recipe.services.patient.RevokePrescription;
import be.recipe.services.patient.RevokePrescriptionResponse;
import be.recipe.services.patient.RevokeRelation;
import be.recipe.services.patient.RevokeRelationResponse;
import be.recipe.services.patient.UpdateFeedbackFlag;
import be.recipe.services.patient.UpdateFeedbackFlagResponse;

public class RecipePatientServiceDevV4Impl implements RecipePatientServiceDevV4 {
   private static final String ENDPOINT_NAME = "endpoint.patient.v4";
   private static final String SERVICE_NAME = RecipePatientServiceDevV4Impl.class.getName();
   private static RecipePatientServiceDevV4 recipePatientService;

   private RecipePatientServiceDevV4Impl() {
   }

   public static RecipePatientServiceDevV4 getInstance() {
      if (recipePatientService == null) {
         recipePatientService = new RecipePatientServiceDevV4Impl();
      }

      return recipePatientService;
   }

   public GetPrescriptionForPatientResponse getPrescriptionForPatient(GetPrescriptionForPatient getPrescriptionForPrescriberRequest) throws IntegrationModuleException {
      return (GetPrescriptionForPatientResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest), GetPrescriptionForPatientResponse.class);
   }

   public RevokePrescriptionResponse revokePrescription(RevokePrescription revokePrescriptionRequest) throws IntegrationModuleException {
      return (RevokePrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(revokePrescriptionRequest), RevokePrescriptionResponse.class);
   }

   public ListPatientPrescriptionResponse listOpenPrescriptions(ListPatientPrescription listOpenPrescriptionsRequest) throws IntegrationModuleException {
      return (ListPatientPrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listOpenPrescriptionsRequest), ListPatientPrescriptionResponse.class);
   }

   public UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlag updateFeedbackFlagRequest) throws IntegrationModuleException {
      return (UpdateFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest), UpdateFeedbackFlagResponse.class);
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

   public GetVisionResponse getVision(GetVision request) throws IntegrationModuleException {
      return (GetVisionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), GetVisionResponse.class);
   }

   public PutVisionResponse putVision(PutVision request) throws IntegrationModuleException {
      return (PutVisionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), PutVisionResponse.class);
   }

   public ListRelationsResponse listRelations(ListRelations request) throws IntegrationModuleException {
      return (ListRelationsResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), ListRelationsResponse.class);
   }

   public CreateRelationResponse createRelation(CreateRelation request) throws IntegrationModuleException {
      return (CreateRelationResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), CreateRelationResponse.class);
   }

   public RevokeRelationResponse revokeRelation(RevokeRelation request) throws IntegrationModuleException {
      return (RevokeRelationResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), RevokeRelationResponse.class);
   }

   public CreateReservationResponse createReservation(CreateReservation request) throws IntegrationModuleException {
      return (CreateReservationResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), CreateReservationResponse.class);
   }

   public GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request) throws IntegrationModuleException {
      return (GetPrescriptionStatusResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), GetPrescriptionStatusResponse.class);
   }

   public ListRidsHistoryResponse listRidsHistory(ListRidsHistory request) throws IntegrationModuleException {
      return (ListRidsHistoryResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(request), ListRidsHistoryResponse.class);
   }

   public ListOpenRidsResponse listOpenRids(ListOpenRids listOpenRids) throws IntegrationModuleException {
      return (ListOpenRidsResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listOpenRids), ListOpenRidsResponse.class);
   }
}
