package be.business.connector.recipe.prescriber.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListFeedbacksResponse;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListOpenRidsResponse;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.RevokePrescriptionResponse;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.UpdateFeedbackFlagResponse;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesResponse;

public interface RecipePrescriberServiceDevV4 {
   CreatePrescriptionResponse createPrescription(CreatePrescription var1) throws IntegrationModuleException;

   RevokePrescriptionResponse revokePrescription(RevokePrescription var1) throws IntegrationModuleException;

   GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriber var1) throws IntegrationModuleException;

   ListOpenRidsResponse listOpenRids(ListOpenRids var1) throws IntegrationModuleException;

   SendNotificationResponse sendNotification(SendNotification var1) throws IntegrationModuleException;

   UpdateFeedbackFlagResponse putFeedbackFlag(UpdateFeedbackFlag var1) throws IntegrationModuleException;

   ListFeedbacksResponse listFeedbacks(ListFeedbacks var1) throws IntegrationModuleException;

   ValidationPropertiesResponse getValidationProperties(ValidationProperties var1) throws IntegrationModuleException;

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus var1) throws IntegrationModuleException;

   PutVisionResponse putVisionForPrescriber(PutVision var1) throws IntegrationModuleException;

   ListRidsHistoryResponse listRidsHistory(ListRidsHistory var1) throws IntegrationModuleException;
}
