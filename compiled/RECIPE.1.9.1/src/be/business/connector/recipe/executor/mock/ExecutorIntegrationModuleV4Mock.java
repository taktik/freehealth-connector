package be.business.connector.recipe.executor.mock;

import java.util.Calendar;
import java.util.Random;

import be.business.connector.recipe.executor.ExecutorIntegrationModuleDevV4Impl;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.GetPrescriptionStatusParam;
import be.recipe.services.executor.GetPrescriptionStatusResult;
import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListReservationsParam;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListReservationsResultItem;
import be.recipe.services.executor.ListRidsHistoryParam;
import be.recipe.services.executor.ListRidsHistoryResult;
import be.recipe.services.executor.ListRidsHistoryResultItem;
import be.recipe.services.executor.ListRidsInProcessParam;
import be.recipe.services.executor.ListRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessParam;
import be.recipe.services.executor.PutRidsInProcessResult;
import be.recipe.services.executor.PutRidsInProcessResultItem;

/**
 * The Class ExecutorIntegrationModuleV4Mock.
 */
public class ExecutorIntegrationModuleV4Mock extends ExecutorIntegrationModuleDevV4Impl {

	/**
	 * Instantiates a new executor integration module V 4 mock.
	 *
	 * @ the integration module exception
	 */
	public ExecutorIntegrationModuleV4Mock() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListOpenPrescriptionsResult getData(ListOpenPrescriptionsParam request) {
		ListOpenPrescriptionsResult result = new ListOpenPrescriptionsResult();
		int random = new Random().nextInt(10);
		for (int i = 1; i < random; i++) {
			GetOpenPrescriptionForExecutor goplr = new GetOpenPrescriptionForExecutor();
			goplr.setRid("BEP0" + i);
			goplr.setCreationDate(Calendar.getInstance());
			goplr.setPatientId("patient" + i);
			result.getPrescriptions().add(goplr);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionStatusResult getData(GetPrescriptionStatusParam request) {
		GetPrescriptionStatusResult result = new GetPrescriptionStatusResult();
		int pick = new Random().nextInt(PrescriptionStatus.values().length);
		result.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsHistoryResult getData(ListRidsHistoryParam request) {
		ListRidsHistoryResult result = new ListRidsHistoryResult();
		int random = new Random().nextInt(10);
		for (int i = 1; i < random; i++) {
			ListRidsHistoryResultItem item1 = new ListRidsHistoryResultItem();
			item1.setRid("BEP0JNT9191" + i);
			int pick = new Random().nextInt(PrescriptionStatus.values().length);
			item1.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
			result.getItems().add(item1);
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListReservationsResult getData(ListReservationsParam request) {
		ListReservationsResult result = new ListReservationsResult();
		int random = new Random().nextInt(10);
		for (int i = 1; i < random; i++) {
			ListReservationsResultItem item = new ListReservationsResultItem();
			item.setExecutorId("123456789");
			item.setRid("BEP0JNT9191" + i);
			result.getItems().add(item);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListRidsInProcessResult getData(ListRidsInProcessParam request) {
		ListRidsInProcessResult result = new ListRidsInProcessResult();
		int random = new Random().nextInt(10);
		for (int i = 1; i < random; i++) {
			result.getRids().add("BEP0JNT9191");
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PutRidsInProcessResult putData(PutRidsInProcessParam request) {
		PutRidsInProcessResult result = new PutRidsInProcessResult();
		int random = new Random().nextInt(10);
		for (int i = 1; i < random; i++) {
			PutRidsInProcessResultItem item = new PutRidsInProcessResultItem();
			item.setRid("123456789");
			int pick = new Random().nextInt(PrescriptionStatus.values().length);
			item.setPrescriptionStatus(PrescriptionStatus.values()[pick]);
			item.setStatusUpdater("987654321");
			result.getItems().add(item);
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public GetPrescriptionForExecutorResult getAndMarkAsDelivered(String rid) {
		throw new UnsupportedOperationException();
	}
}
