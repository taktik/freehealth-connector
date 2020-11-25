package be.business.connector.recipe.patient;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.ListPatientPrescriptionsResult;

public interface PatientIntegrationModule {
   GetPrescriptionForPatientResult getPrescription(String var1) throws IntegrationModuleException;

   void revokePrescription(String var1, String var2) throws IntegrationModuleException;

   ListPatientPrescriptionsResult listOpenPrescriptions() throws IntegrationModuleException;

   void updateFeedbackFlag(String var1, boolean var2) throws IntegrationModuleException;
}
