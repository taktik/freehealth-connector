/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.utils;

import java.security.Key;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.PropertyHandler;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListReservationsResult;
import be.recipe.services.executor.ListReservationsResultItem;

/**
 * The Class DecryptionUtils.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public class ExecutorDecryptionUtils {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ExecutorDecryptionUtils.class);

	/** The etk helper. */
	private final ETKHelper etkHelper;

	/** The key cache. */
	private final Map<String, KeyResult> keyCache = new HashMap<>();

	private final PropertyHandler propertyHandler;

	private Key symmKey;

	/**
	 * Instantiates a new {@link ExecutorDecryptionUtils}.
	 *
	 * @param propertyHandler
	 *            the property handler
	 * @param encryptionUtils
	 *            the encryption utils
	 * @param keyCache2
	 */
	public ExecutorDecryptionUtils(final PropertyHandler propertyHandler, final EncryptionUtils encryptionUtils) {
		this.propertyHandler = propertyHandler;
		this.etkHelper = new ETKHelper(propertyHandler, encryptionUtils);
		this.symmKey = EncryptionUtils.getInstance().generateSecretKey();
	}

	/**
	 * Decrypt a {@link ListOpenPrescriptionsResult}.
	 *
	 * @param listOpenPrescriptionsResult
	 *            the encrypted {@link ListOpenPrescriptionsResult}
	 * @return the decrypted {@link ListOpenPrescriptionsResult}
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.DecompressionUtil#decryptPrescriptions")
	public ListOpenPrescriptionsResult decryptPrescriptions(final ListOpenPrescriptionsResult listOpenPrescriptionsResult)
			throws IntegrationModuleException {
		if (listOpenPrescriptionsResult == null
				|| listOpenPrescriptionsResult != null && CollectionUtils.isEmpty(listOpenPrescriptionsResult.getPrescriptions())) {
			return listOpenPrescriptionsResult;
		}
		return doDecryptions(listOpenPrescriptionsResult);
	}

	/**
	 * Decrypt a {@link ListReservationsResult}.
	 *
	 * @param listReservationsResult
	 *            the encrypted {@link ListReservationsResult}
	 * @return the decrypted {@link ListReservationsResult}
	 * @throws IntegrationModuleException
	 *             the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.DecompressionUtil#decryptReservations")
	public ListReservationsResult decryptReservations(final ListReservationsResult listReservationsResult)
			throws IntegrationModuleException {
		if (listReservationsResult == null || listReservationsResult != null && CollectionUtils.isEmpty(listReservationsResult.getItems())) {
			return listReservationsResult;
		}
		return doDecryptions(listReservationsResult);
	}

	/**
	 * Decrypt a {@link ListOpenPrescriptionsResult}.
	 *
	 * @param prescriptionList
	 *            the encrypted {@link ListOpenPrescriptionsResult}
	 * @return the decrypted {@link ListOpenPrescriptionsResult}
	 */
	private ListOpenPrescriptionsResult doDecryptions(final ListOpenPrescriptionsResult prescriptionList) {
		final int threadLimit = PropertyHandler.getInstance().getIntegerProperty("decryption.thread.number", "50");
		final Semaphore semaphore = new Semaphore(threadLimit, true);

		final Map<GetOpenPrescriptionForExecutor, PrescriptionDecryptorThread> dataMap = new HashMap<>();
		final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
		final byte[] pharmacyEtk = etkHelper.getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded();
		final byte[] kgssEtk = etkHelper.getKGSS_ETK().get(0).getEncoded();
		for (final GetOpenPrescriptionForExecutor getOpenPrescription : prescriptionList.getPrescriptions()) {
			if (getOpenPrescription.getPrescription() != null) {
				final PrescriptionDecryptorThread decryptorThread = new PrescriptionDecryptorThread(semaphore, getOpenPrescription, pharmacyEtk,
						kgssEtk);
				dataMap.put(getOpenPrescription, decryptorThread);
			}
		}

		for (final PrescriptionDecryptorThread decryptorThread : dataMap.values()) {
			semaphore.acquireUninterruptibly();
			decryptorThread.start();
		}
		try {
			semaphore.acquireUninterruptibly(threadLimit);
		} catch (final IllegalArgumentException e) {
			LOG.debug("Incorrect Thread configuration : " + e);
		}
		final ListOpenPrescriptionsResult finalResult = new ListOpenPrescriptionsResult();
		for (final PrescriptionDecryptorThread decryptorThread : dataMap.values()) {
			finalResult.getPrescriptions().add(decryptorThread.getPrescription());
		}
		finalResult.setHasMoreResults(prescriptionList.isHasMoreResults());
		finalResult.setSession(prescriptionList.getSession());

		Collections.sort(finalResult.getPrescriptions(), new Comparator<GetOpenPrescriptionForExecutor>() {
			@Override
			public int compare(final GetOpenPrescriptionForExecutor f1, final GetOpenPrescriptionForExecutor f2) {
				return f2.getCreationDate().compareTo(f1.getCreationDate());
			}
		});

		return finalResult;
	}

	/**
	 * Decrypt a {@link ListReservationsResult}.
	 *
	 * @param listReservationsResult
	 *            the encrypted {@link ListReservationsResult}
	 * @return the decrypted {@link ListReservationsResult}
	 */
	private ListReservationsResult doDecryptions(final ListReservationsResult listReservationsResult) {
		final int threadLimit = PropertyHandler.getInstance().getIntegerProperty("decryption.thread.number", "50");
		final Semaphore semaphore = new Semaphore(threadLimit, true);

		final Map<ListReservationsResultItem, ReservationDecryptorThread> dataMap = new HashMap<>();
		final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
		final byte[] pharmacyEtk = etkHelper.getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded();
		final byte[] kgssEtk = etkHelper.getKGSS_ETK().get(0).getEncoded();
		for (final ListReservationsResultItem listReservationResultItem : listReservationsResult.getItems()) {
			if (listReservationResultItem.getPrescription() != null && listReservationResultItem.getPrescription().getPrescription() != null) {
				final ReservationDecryptorThread decryptorThread = new ReservationDecryptorThread(semaphore, listReservationResultItem, pharmacyEtk,
						kgssEtk);
				dataMap.put(listReservationResultItem, decryptorThread);
			}
		}

		for (final ReservationDecryptorThread decryptorThread : dataMap.values()) {
			semaphore.acquireUninterruptibly();
			decryptorThread.start();
		}
		try {
			semaphore.acquireUninterruptibly(threadLimit);
		} catch (final IllegalArgumentException e) {
			LOG.debug("Incorrect Thread configuration : " + e);
		}
		final ListReservationsResult finalResult = new ListReservationsResult();
		for (final ReservationDecryptorThread decryptorThread : dataMap.values()) {
			finalResult.getItems().add(decryptorThread.getListReservationsResultItem());
		}
		finalResult.setHasMoreResults(listReservationsResult.isHasMoreResults());
		finalResult.setSession(listReservationsResult.getSession());

		Collections.sort(finalResult.getItems(), new Comparator<ListReservationsResultItem>() {
			@Override
			public int compare(final ListReservationsResultItem f1, final ListReservationsResultItem f2) {
				return f2.getCreationDate().compareTo(f1.getCreationDate());
			}
		});

		return finalResult;
	}
}