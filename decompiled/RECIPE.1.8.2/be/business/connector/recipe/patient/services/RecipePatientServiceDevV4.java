package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
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

public interface RecipePatientServiceDevV4 {
   GetPrescriptionForPatientResponse getPrescriptionForPatient(GetPrescriptionForPatient var1) throws IntegrationModuleException;

   RevokePrescriptionResponse revokePrescription(RevokePrescription var1) throws IntegrationModuleException;

   UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlag var1) throws IntegrationModuleException;

   GetVisionResponse getVision(GetVision var1) throws IntegrationModuleException;

   PutVisionResponse putVision(PutVision var1) throws IntegrationModuleException;

   ListRelationsResponse listRelations(ListRelations var1) throws IntegrationModuleException;

   CreateRelationResponse createRelation(CreateRelation var1) throws IntegrationModuleException;

   RevokeRelationResponse revokeRelation(RevokeRelation var1) throws IntegrationModuleException;

   CreateReservationResponse createReservation(CreateReservation var1) throws IntegrationModuleException;

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus var1) throws IntegrationModuleException;

   ListRidsHistoryResponse listRidsHistory(ListRidsHistory var1) throws IntegrationModuleException;

   ListOpenRidsResponse listOpenRids(ListOpenRids var1) throws IntegrationModuleException;

   ListPatientPrescriptionResponse listOpenPrescriptions(ListPatientPrescription var1) throws IntegrationModuleException;
}
