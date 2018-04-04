package org.taktik.connector.business.recipe.prescriber.services;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.session.SessionItem;
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
	AliveCheckResponse aliveCheck(AliveCheckRequest paramAliveCheckRequest) throws IntegrationModuleException;

	CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest paramCreatePrescriptionRequest) throws IntegrationModuleException;

	RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest paramRevokePrescriptionRequest) throws IntegrationModuleException;

	GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriberRequest paramGetPrescriptionForPrescriberRequest) throws IntegrationModuleException;

	ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest paramListOpenPrescriptionsRequest) throws IntegrationModuleException;

	SendNotificationResponse sendNotification(SendNotificationRequest paramSendNotificationRequest) throws IntegrationModuleException;

	UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlagRequest paramUpdateFeedbackFlagRequest) throws IntegrationModuleException;

	ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest paramListFeedbacksRequest) throws IntegrationModuleException;
}
