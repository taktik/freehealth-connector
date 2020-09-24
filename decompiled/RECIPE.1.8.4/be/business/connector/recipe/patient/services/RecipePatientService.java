package be.business.connector.recipe.patient.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v1.GetPrescriptionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v1.ListPatientPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v1.RevokePatientPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v1.UpdatePatientFeedbackFlagResponse;

public interface RecipePatientService {
   GetPrescriptionForPatientResponse getPrescriptionForPatient(GetPrescriptionForPatientRequest var1) throws IntegrationModuleException;

   RevokePatientPrescriptionResponse revokePrescription(RevokePatientPrescriptionRequest var1) throws IntegrationModuleException;

   ListPatientPrescriptionsResponse listOpenPrescriptions(ListPatientPrescriptionsRequest var1) throws IntegrationModuleException;

   UpdatePatientFeedbackFlagResponse updateFeedbackFlag(UpdatePatientFeedbackFlagRequest var1) throws IntegrationModuleException;
}
