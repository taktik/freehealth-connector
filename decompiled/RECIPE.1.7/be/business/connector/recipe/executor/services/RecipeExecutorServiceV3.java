package org.taktik.connector.business.recipe.executor.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v3.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v3.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v3.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v3.RevokePrescriptionForExecutorResponse;

public interface RecipeExecutorServiceV3 {
   RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest var1) throws IntegrationModuleException;

   AliveCheckResponse aliveCheck(AliveCheckRequest var1) throws IntegrationModuleException;

   CreateFeedbackResponse createFeedback(CreateFeedbackRequest var1) throws IntegrationModuleException;

   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest var1) throws IntegrationModuleException;

   MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest var1) throws IntegrationModuleException;

   MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest var1) throws IntegrationModuleException;

   MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest var1) throws IntegrationModuleException;

   ListNotificationsResponse listNotifications(ListNotificationsRequest var1) throws IntegrationModuleException;
}
