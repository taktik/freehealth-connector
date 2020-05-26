package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.services.GenericWebserviceCaller;
import be.business.connector.core.services.GenericWebserviceRequest;
import be.business.connector.projects.common.utils.EndpointResolver;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagResponse;

public class RecipePatientServiceImpl implements RecipePatientService {
   private static final String ENDPOINT_NAME = "endpoint.patient";
   private static final String SERVICE_NAME = RecipePatientServiceImpl.class.getName();
   private static RecipePatientService recipePatientService;

   private RecipePatientServiceImpl() {
   }

   public static RecipePatientService getInstance() {
      if (recipePatientService == null) {
         recipePatientService = new RecipePatientServiceImpl();
      }

      return recipePatientService;
   }

   public GetPrescriptionForPatientResponse getPrescriptionForPatient(GetPrescriptionForPatientRequest getPrescriptionForPrescriberRequest) throws IntegrationModuleException {
      return (GetPrescriptionForPatientResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(getPrescriptionForPrescriberRequest), GetPrescriptionForPatientResponse.class);
   }

   public RevokePatientPrescriptionResponse revokePrescription(RevokePatientPrescriptionRequest revokePrescriptionRequest) throws IntegrationModuleException {
      return (RevokePatientPrescriptionResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(revokePrescriptionRequest), RevokePatientPrescriptionResponse.class);
   }

   public ListPatientPrescriptionsResponse listOpenPrescriptions(ListPatientPrescriptionsRequest listOpenPrescriptionsRequest) throws IntegrationModuleException {
      return (ListPatientPrescriptionsResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(listOpenPrescriptionsRequest), ListPatientPrescriptionsResponse.class);
   }

   public UpdatePatientFeedbackFlagResponse updateFeedbackFlag(UpdatePatientFeedbackFlagRequest updateFeedbackFlagRequest) throws IntegrationModuleException {
      return (UpdatePatientFeedbackFlagResponse)GenericWebserviceCaller.callGenericWebservice(this.createDefaultGenericWebserviceRequest(updateFeedbackFlagRequest), UpdatePatientFeedbackFlagResponse.class);
   }

   private GenericWebserviceRequest createDefaultGenericWebserviceRequest(Object requestObject) throws IntegrationModuleException {
      GenericWebserviceRequest request = new GenericWebserviceRequest();
      request.setRequest(requestObject);
      request.setEndpoint(EndpointResolver.getEndpointUrlString("endpoint.patient"));
      request.setServiceName(SERVICE_NAME);
      request.setAddLoggingHandler(true);
      request.setAddSoapFaultHandler(true);
      request.setAddMustUnderstandHandler(true);
      request.setAddInsurabilityHandler(false);
      return request;
   }
}
