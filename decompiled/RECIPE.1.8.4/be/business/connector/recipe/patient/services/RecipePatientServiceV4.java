package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
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

public interface RecipePatientServiceV4 {
   GetPrescriptionResponse getPrescriptionForPatient(GetPrescriptionRequest var1) throws IntegrationModuleException;

   RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest var1) throws IntegrationModuleException;

   PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest var1) throws IntegrationModuleException;

   GetVisionResponse getVision(GetVisionRequest var1) throws IntegrationModuleException;

   PutVisionForPatientResponse putVision(PutVisionForPatientRequest var1) throws IntegrationModuleException;

   ListRelationsResponse listRelations(ListRelationsRequest var1) throws IntegrationModuleException;

   CreateRelationResponse createRelation(CreateRelationRequest var1) throws IntegrationModuleException;

   RevokeRelationResponse revokeRelation(RevokeRelationRequest var1) throws IntegrationModuleException;

   CreateReservationResponse createReservation(CreateReservationRequest var1) throws IntegrationModuleException;

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest var1) throws IntegrationModuleException;

   ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest var1) throws IntegrationModuleException;

   ListOpenRidsResponse listOpenRids(ListOpenRidsRequest var1) throws IntegrationModuleException;

   ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest var1) throws IntegrationModuleException;
}
