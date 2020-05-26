package org.taktik.connector.business.recipe.executor.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
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
	RevokePrescriptionForExecutorResponse revokePrescriptionForExecutor(RevokePrescriptionForExecutorRequest paramRevokePrescriptionForExecutorRequest) throws IntegrationModuleException;

	AliveCheckResponse aliveCheck(AliveCheckRequest paramAliveCheckRequest) throws IntegrationModuleException;

	CreateFeedbackResponse createFeedback(CreateFeedbackRequest paramCreateFeedbackRequest) throws IntegrationModuleException;

	GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest paramGetPrescriptionForExecutorRequest) throws IntegrationModuleException;

	MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest paramMarkAsArchivedRequest) throws IntegrationModuleException;

	MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest paramMarkAsDeliveredRequest) throws IntegrationModuleException;

	MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest paramMarkAsUnDeliveredRequest) throws IntegrationModuleException;

	ListNotificationsResponse listNotifications(ListNotificationsRequest paramListNotificationsRequest) throws IntegrationModuleException;
}
