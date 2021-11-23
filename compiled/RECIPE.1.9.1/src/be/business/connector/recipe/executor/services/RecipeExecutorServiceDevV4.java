package be.business.connector.recipe.executor.services;

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

/**
 * The Interface RecipeExecutorServiceDevV4.
 */
public interface RecipeExecutorServiceDevV4 {

	/**
	 * Mark as un delivered.
	 *
	 * @param request
	 *            the request
	 * @return the response type @ the integration module exception
	 */
	MarkAsUnDeliveredResponse markAsUnDelivered(MarkAsUnDelivered request);

	/**
	 * Gets the prescription for executor.
	 *
	 * @param request
	 *            the request
	 * @return the prescription for executor @ the integration module exception
	 */
	GetPrescriptionForExecutorResponse getPrescriptionForExecutor(GetPrescriptionForExecutor request);

	/**
	 * Mark as archived.
	 *
	 * @param request
	 *            the request
	 * @return the response type @ the integration module exception
	 */
	MarkAsArchivedResponse markAsArchived(MarkAsArchived request);

	/**
	 * Mark as delivered.
	 *
	 * @param request
	 *            the request
	 * @return the response type @ the integration module exception
	 */
	MarkAsDeliveredResponse markAsDelivered(MarkAsDelivered request);

	/**
	 * Revoke prescription for executor.
	 *
	 * @param request
	 *            the request
	 * @return the response type @ the integration module exception
	 */
	RevokePrescriptionResponse revokePrescriptionForExecutor(RevokePrescription request);

	/**
	 * List notifications.
	 *
	 * @param request
	 *            the request
	 * @return the list notifications response @ the integration module exception
	 */
	ListNotificationsResponse listNotifications(ListNotifications request);

	/**
	 * Creates the feedback.
	 *
	 * @param request
	 *            the request
	 * @return the creates the feedback response @ the integration module exception
	 */
	CreateFeedbackResponse createFeedback(CreateFeedback request);

	/**
	 * Gets the open prescription list.
	 *
	 * @param request
	 *            the request
	 * @return the open prescription list @ the integration module exception
	 */
	ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptions request);

	/**
	 * Gets the prescription status.
	 *
	 * @param request
	 *            the request
	 * @return the prescription status @ the integration module exception
	 */
	GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request);

	/**
	 * List prescription history.
	 *
	 * @param request
	 *            the request
	 * @return the list rids history response @ the integration module exception
	 */
	ListRidsHistoryResponse listRidsHistory(ListRidsHistory request);

	/**
	 * Gets the all reservations.
	 *
	 * @param request
	 *            the request
	 * @return the all reservations @ the integration module exception
	 */
	ListReservationsResponse listReservations(ListReservations request);

	/**
	 * Lists all the rids in process.
	 *
	 * @param request
	 *            the request
	 * @return the all rids in process @ the integration module exception
	 */
	ListRidsInProcessResponse listRidsInProcess(ListRidsInProcess request);

	/**
	 * Put all rids in process.
	 *
	 * @param request
	 *            the request
	 * @return the put rids in process response @ the integration module exception
	 */
	PutRidsInProcessResponse putRidsInProcess(PutRidsInProcess request);

	ListRelationsResponse listRelations(ListRelations request);

}
