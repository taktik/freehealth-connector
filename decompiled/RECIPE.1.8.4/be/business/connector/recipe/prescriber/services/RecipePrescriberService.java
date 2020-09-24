package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
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

public interface RecipePrescriberService {
   AliveCheckResponse aliveCheck(AliveCheckRequest var1) throws IntegrationModuleException;

   CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest var1) throws IntegrationModuleException;

   RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest var1) throws IntegrationModuleException;

   GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriberRequest var1) throws IntegrationModuleException;

   ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest var1) throws IntegrationModuleException;

   SendNotificationResponse sendNotification(SendNotificationRequest var1) throws IntegrationModuleException;

   UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlagRequest var1) throws IntegrationModuleException;

   ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest var1) throws IntegrationModuleException;
}
