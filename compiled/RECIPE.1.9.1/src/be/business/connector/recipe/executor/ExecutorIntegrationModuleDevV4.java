package be.business.connector.recipe.executor;

import be.business.connector.recipe.executor.domain.GetAllReservationsParam;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
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
 * The Interface ExecutorIntegrationModuleDevV4.
 */
public interface ExecutorIntegrationModuleDevV4 extends ExecutorIntegrationModuleV4 {

	/**
	 * Gets the {@link ListOpenPrescriptionsResult}.
	 *
	 * @param request
	 *            a {@link ListOpenPrescriptionsParam}
	 * @return the {@link ListOpenPrescriptionsResult} @ the integration module exception
	 */
	@Override
	ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam request);

	/**
	 * Decrypt a {@link GetOpenPrescriptionForExecutor}.
	 *
	 * @param getOpenPrescriptionForExecutor
	 *            a the {@link GetOpenPrescriptionListParam}
	 * @return a {@link GetOpenPrescriptionForExecutor} @ the integration module exception
	 */
	@Override
	GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(final GetOpenPrescriptionForExecutor getOpenPrescriptionForExecutor);

	/**
	 * Gets the {@link GetPrescriptionStatusResult}.
	 *
	 * @param request
	 *            the {@link GetPrescriptionStatusParam}
	 * @return the {@link GetPrescriptionStatusResult} @ the integration module exception
	 */
	@Override
	GetPrescriptionStatusResult getData(GetPrescriptionStatusParam request);

	/**
	 * Gets the {@link ListRidsHistoryResult}.
	 *
	 * @param request
	 *            the {@link ListRidsHistoryParam}
	 * @return the {@link ListPrescriptionHistoryParam} @ the integration module exception
	 */
	@Override
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Gets the {@link ListReservationsResult}.
	 *
	 * @param request
	 *            the {@link GetAllReservationsParam}
	 * @return the {@link ListReservationsResult} @ the integration module exception
	 */
	@Override
	ListReservationsResult getData(ListReservationsParam request);

	/**
	 * Gets the {@link ListRidsInProcessResult}.
	 *
	 * @param request
	 *            the {@link ListRidsInProcessParam}
	 * @returnthe {@link ListRidsInProcessResult} @ the integration module exception
	 */
	@Override
	ListRidsInProcessResult getData(ListRidsInProcessParam request);

	/**
	 * Puts the {@link PutRidsInProcessResult}.
	 *
	 * @param request
	 *            the {@link PutRidsInProcessParam}
	 * @returnthe {@link PutRidsInProcessResult} @ the integration module exception
	 */
	@Override
	PutRidsInProcessResult putData(PutRidsInProcessParam request);

	/**
	 * Gets the {@link be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult} and marks it as delivered.
	 *
	 * @param rid
	 *            the rid
	 * @return the {@link be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult} @ the integration module exception
	 */
	@Override
	be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid);

	@Override
	ListRelationsResult getData(ListRelationsParam patientRelationParam);

}