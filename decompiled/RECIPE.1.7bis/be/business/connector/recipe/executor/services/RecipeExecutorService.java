package be.business.connector.recipe.executor.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v2.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v2.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v2.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v2.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v2.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v2.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v2.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v2.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v2.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v2.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v2.RevokePrescriptionForExecutorResponse;

public interface RecipeExecutorService {
   RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest var1) throws IntegrationModuleException;

   AliveCheckResponse aliveCheck(AliveCheckRequest var1) throws IntegrationModuleException;

   CreateFeedbackResponse createFeedback(CreateFeedbackRequest var1) throws IntegrationModuleException;

   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest var1) throws IntegrationModuleException;

   MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest var1) throws IntegrationModuleException;

   MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest var1) throws IntegrationModuleException;

   MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest var1) throws IntegrationModuleException;

   ListNotificationsResponse listNotifications(ListNotificationsRequest var1) throws IntegrationModuleException;
}
