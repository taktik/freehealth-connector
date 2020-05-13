package be.business.connector.recipe.executor.services;

import be.fgov.ehealth.recipe.protocol.v3.AliveCheckRequest;
import be.fgov.ehealth.recipe.protocol.v3.AliveCheckResponse;
import be.recipe.services.executor.CreateFeedback;
import be.recipe.services.executor.CreateFeedbackResponse;
import be.recipe.services.executor.GetPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionForExecutorResponse;
import be.recipe.services.executor.GetPrescriptionStatus;
import be.recipe.services.executor.GetPrescriptionStatusResponse;
import be.recipe.services.executor.ListNotifications;
import be.recipe.services.executor.ListNotificationsResponse;
import be.recipe.services.executor.ListOpenPrescriptions;
import be.recipe.services.executor.ListOpenPrescriptionsResponse;
import be.recipe.services.executor.ListRelations;
import be.recipe.services.executor.ListRelationsResponse;
import be.recipe.services.executor.ListReservations;
import be.recipe.services.executor.ListReservationsResponse;
import be.recipe.services.executor.ListRidsHistory;
import be.recipe.services.executor.ListRidsHistoryResponse;
import be.recipe.services.executor.ListRidsInProcess;
import be.recipe.services.executor.ListRidsInProcessResponse;
import be.recipe.services.executor.MarkAsArchived;
import be.recipe.services.executor.MarkAsArchivedResponse;
import be.recipe.services.executor.MarkAsDelivered;
import be.recipe.services.executor.MarkAsDeliveredResponse;
import be.recipe.services.executor.MarkAsUnDelivered;
import be.recipe.services.executor.MarkAsUnDeliveredResponse;
import be.recipe.services.executor.PutRidsInProcess;
import be.recipe.services.executor.PutRidsInProcessResponse;
import be.recipe.services.executor.RevokePrescription;
import be.recipe.services.executor.RevokePrescriptionResponse;

public interface RecipeExecutorServiceDevV4 {
   MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDelivered var1);

   GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutor var1);

   MarkAsArchivedResponse markAsArchived(MarkAsArchived var1);

   MarkAsDeliveredResponse markAsDelivered(MarkAsDelivered var1);

   RevokePrescriptionResponse revokePrescriptionForExecutor(RevokePrescription var1);

   ListNotificationsResponse listNotifications(ListNotifications var1);

   CreateFeedbackResponse createFeedback(CreateFeedback var1);

   AliveCheckResponse aliveCheck(AliveCheckRequest var1);

   ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptions var1);

   GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus var1);

   ListRidsHistoryResponse listRidsHistory(ListRidsHistory var1);

   ListReservationsResponse listReservations(ListReservations var1);

   ListRidsInProcessResponse listRidsInProcess(ListRidsInProcess var1);

   PutRidsInProcessResponse putRidsInProcess(PutRidsInProcess var1);

   ListRelationsResponse listRelations(ListRelations var1);
}
