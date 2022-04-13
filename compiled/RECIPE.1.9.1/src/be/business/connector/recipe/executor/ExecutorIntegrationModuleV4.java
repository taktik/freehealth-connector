package be.business.connector.recipe.executor;

import java.util.List;

import be.business.connector.recipe.executor.domain.GetAllReservationsParam;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListNotificationsItem;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListRelationsParam;
import be.recipe.services.executor.ListRelationsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;

/**
 * The Interface ExecutorIntegrationModuleV4.
 * 
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public interface ExecutorIntegrationModuleV4 {

	/**
	 * Gets the {@link ListOpenPrescriptionsResult}.
	 *
	 * @param request
	 *            a {@link ListOpenPrescriptionsParam}
	 * @return the {@link ListOpenPrescriptionsResult} @ the integration module exception
	 */
	ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam request);

	/**
	 * Gets the {@link GetPrescriptionStatusResult}.
	 *
	 * @param gpsp
	 *            the gpsp
	 * @return the {@link GetPrescriptionStatusResult} @ the integration module exception
	 */
	GetPrescriptionStatusResult getData(GetPrescriptionStatusParam gpsp);

	/**
	 * Gets the {@link ListRidsHistoryResult}.
	 *
	 * @param request
	 *            the {@link ListRidsHistoryParam}
	 * @return the {@link ListPrescriptionHistoryParam} @ the integration module exception
	 */
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Gets the {@link ListReservationsResult}.
	 *
	 * @param request
	 *            the {@link GetAllReservationsParam}
	 * @return the {@link ListReservationsResult} @ the integration module exception
	 */
	ListReservationsResult getData(ListReservationsParam request);

	/**
	 * Performs a getData(ListReservationsParam)- and decryptListReservationsResult-operation at once.
	 * 
	 * @param request
	 *            the {@link ListReservationsParam}
	 * @return the decrypted {@link ListReservationsResult}
	 */
	ListReservationsResult listReservationsDecrypted(ListReservationsParam request);

	/**
	 * Gets the {@link ListRidsInProcessResult}.
	 *
	 * @param request
	 *            the {@link ListRidsInProcessParam}
	 * @return the data
	 * @returnthe {@link ListRidsInProcessResult} @ the integration module exception
	 */
	ListRidsInProcessResult getData(ListRidsInProcessParam request);

	/**
	 * Puts the {@link PutRidsInProcessResult}.
	 *
	 * @param request
	 *            the {@link PutRidsInProcessParam}
	 * @return the {@link PutRidsInProcessResult} @ the integration module exception
	 */
	PutRidsInProcessResult putData(PutRidsInProcessParam request);

	/**
	 * Gets the {@link be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult} and marks it as delivered.
	 *
	 * @param rid
	 *            the rid
	 * @return the {@link be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult} @ the integration module exception
	 */
	GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid);

	/**
	 * Gets the {@link ListRelationsResult}.
	 *
	 * @param patientRelationParam
	 *            the patient relation param
	 * @return the {@link ListRelationsResult} @ the integration module exception
	 */
	ListRelationsResult getData(ListRelationsParam patientRelationParam);

	/**
	 * Decrypts a {@link GetOpenPrescriptionForExecutor}.
	 *
	 * @param getOpenPrescriptionForExecutor
	 *            a the {@link GetOpenPrescriptionListParam}
	 * @return a {@link GetOpenPrescriptionForExecutor} @ the integration module exception
	 */
	GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(final GetOpenPrescriptionForExecutor getOpenPrescriptionForExecutor);

	/**
	 * Gets a prescription.
	 *
	 * @param rid
	 *            the rid
	 * @return the prescription
	 */
	GetPrescriptionForExecutorResult getPrescription(String rid);

	/**
	 * Marks a rid as archived.
	 *
	 * @param rid
	 *            the rid
	 */
	void markAsArchived(String rid);

	/**
	 * Marks a rid as delivered.
	 *
	 * @param rid
	 *            the rid
	 */
	void markAsDelivered(String rid);

	/**
	 * Marks a rid as undelivered.
	 *
	 * @param rid
	 *            the rid
	 */
	void markAsUndelivered(String rid);

	/**
	 * Revokes a rid.
	 *
	 * @param rid
	 *            the rid
	 * @param reason
	 *            the reason
	 */
	void revokePrescription(String rid, String reason);

	/**
	 * Returns a list of {@link ListNotificationsItem}.
	 *
	 * @param readFlag
	 *            the read flag
	 * @return the list
	 */
	List<ListNotificationsItem> listNotifications(boolean readFlag);

	/**
	 * Creates a feedback.
	 *
	 * @param prescriberId
	 *            the prescriber id
	 * @param rid
	 *            the rid
	 * @param feedbackText
	 *            the feedback text
	 */
	void createFeedback(String prescriberId, String rid, byte[] feedbackText);

	/**
	 * Decrypts a {@link ListOpenPrescriptionsResult}.
	 *
	 * @param listOpenPrescriptionsResult
	 *            the encrypted {@link ListOpenPrescriptionsResult}
	 * @return the decrypted {@link ListOpenPrescriptionsResult}
	 */
	ListOpenPrescriptionsResult decryptListOpenPrescriptionsResult(ListOpenPrescriptionsResult listOpenPrescriptionsResult);

	/**
	 * Performs a ListOpenPrescription- & decryptListOpenPrescriptionsResult-operation at once.
	 *
	 * @param listOpenPrescriptionsResult
	 *            the encrypted {@link ListOpenPrescriptionsResult}
	 * @return the decrypted {@link ListOpenPrescriptionsResult}
	 */
	ListOpenPrescriptionsResult listOpenPrescriptionsDecrypted(ListOpenPrescriptionsParam request);

	/**
	 * Decrypts a {@link ListReservationsResult}.
	 *
	 * @param listReservationsResult
	 *            the encrypted {@link ListReservationsResult}
	 * @return the decrypted {@link ListReservationsResult}
	 */
	ListReservationsResult decryptListReservationsResult(ListReservationsResult listReservationsResult);

	/**
	 * Clears the session-map.
	 */
	void clearSession();

}