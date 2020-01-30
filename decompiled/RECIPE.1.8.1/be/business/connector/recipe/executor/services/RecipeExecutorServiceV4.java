package be.business.connector.recipe.executor.services;

import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateFeedbackResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionForExecutorResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListNotificationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListReservationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsArchivedResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredRequest;
import be.fgov.ehealth.recipe.protocol.v4.MarkAsUnDeliveredResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutRidsInProcessResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;

public interface RecipeExecutorServiceV4 {
   RevokePrescriptionResponse revokePrescriptionForExecutor(RevokePrescriptionRequest var1);

   CreateFeedbackResponse createFeedback(CreateFeedbackRequest var1);

   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutorRequest var1);

   MarkAsArchivedResponse markAsArchived(MarkAsArchivedRequest var1);

   MarkAsDeliveredResponse markAsDelivered(MarkAsDeliveredRequest var1);

   MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDeliveredRequest var1);

   ListNotificationsResponse listNotifications(ListNotificationsRequest var1);

   ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest var1);

   ListRidsInProcessResponse listRidsInProcess(ListRidsInProcessRequest var1);

   ListReservationsResponse listReservations(ListReservationsRequest var1);

   ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest var1);

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest var1);

   PutRidsInProcessResponse putRidsInProcess(PutRidsInProcessRequest var1);

   ListRelationsResponse listRelations(ListRelationsRequest var1);
}
