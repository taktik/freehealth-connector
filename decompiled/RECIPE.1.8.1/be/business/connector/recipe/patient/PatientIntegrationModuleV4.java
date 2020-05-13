package be.business.connector.recipe.patient;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResult;

public interface PatientIntegrationModuleV4 extends PatientIntegrationModule {
   GetVisionResult getData(GetVisionParam var1) throws IntegrationModuleException;

   PutVisionResult putData(PutVisionParam var1) throws IntegrationModuleException;

   CreateReservationResult putData(CreateReservationParam var1) throws IntegrationModuleException;

   void updateFeedbackFlag(String var1, boolean var2) throws IntegrationModuleException;

   GetPrescriptionStatusResult getData(GetPrescriptionStatusParam var1) throws IntegrationModuleException;

   ListRidsHistoryResult getData(ListRidsHistoryParam var1) throws IntegrationModuleException;

   ListOpenRidsResult getData(ListOpenRidsParam var1) throws IntegrationModuleException;

   CreateRelationResult putData(CreateRelationParam var1) throws IntegrationModuleException;

   ListRelationsResult getData(ListRelationsParam var1) throws IntegrationModuleException;

   RevokeRelationResult revokeData(RevokeRelationParam var1) throws IntegrationModuleException;
}
