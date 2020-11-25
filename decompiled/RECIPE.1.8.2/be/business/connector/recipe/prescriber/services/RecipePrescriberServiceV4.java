package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationResponse;

public interface RecipePrescriberServiceV4 {
   CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest var1) throws IntegrationModuleException;

   RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest var1) throws IntegrationModuleException;

   GetPrescriptionResponse getPrescriptionForPrescriber(GetPrescriptionRequest var1) throws IntegrationModuleException;

   ListOpenRidsResponse listOpenRids(ListOpenRidsRequest var1) throws IntegrationModuleException;

   SendNotificationResponse sendNotification(SendNotificationRequest var1) throws IntegrationModuleException;

   PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest var1) throws IntegrationModuleException;

   ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest var1) throws IntegrationModuleException;

   GetValidationPropertiesResponse getValidationProperties(GetValidationPropertiesRequest var1) throws IntegrationModuleException;

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest var1) throws IntegrationModuleException;

   PutVisionForPrescriberResponse putVisionForPrescriber(PutVisionForPrescriberRequest var1) throws IntegrationModuleException;

   ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest var1) throws IntegrationModuleException;
}
