/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.patient.utils;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.PropertyHandler;
import be.recipe.services.patient.GetOpenPrescriptionForPatient;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class PatientDecryptionUtils {

	private static final Logger LOG = Logger.getLogger(PatientDecryptionUtils.class);

	private final ETKHelper etkHelper;

	public PatientDecryptionUtils(final PropertyHandler propertyHandler, final EncryptionUtils encryptionUtils) {
		etkHelper = new ETKHelper(propertyHandler, encryptionUtils);
	}

	@Profiled(logFailuresSeparately = true, tag = "0.DecompressionUtil#decryptPrescriptions")
	public ListPatientPrescriptionsResult decryptPrescriptions(ListPatientPrescriptionsResult listPatientPrescriptionsResult)
			throws IntegrationModuleException {
		if (listPatientPrescriptionsResult == null
				|| listPatientPrescriptionsResult != null && CollectionUtils.isEmpty(listPatientPrescriptionsResult.getPrescriptions())) {
			return listPatientPrescriptionsResult;
		}
		return doDecryptions(listPatientPrescriptionsResult);
	}

	private ListPatientPrescriptionsResult doDecryptions(final ListPatientPrescriptionsResult prescriptionList) {
		final int threadLimit = PropertyHandler.getInstance().getIntegerProperty("decryption.thread.number", "50");
		final Semaphore semaphore = new Semaphore(threadLimit, true);

		final Map<GetOpenPrescriptionForPatient, PatientPrescriptionDecryptorThread> dataMap = new HashMap<>();
		final byte[] patientEtk = etkHelper.getSystemETK().get(0).getEncoded();
		final byte[] kgssEtk = etkHelper.getKGSS_ETK().get(0).getEncoded();
		for (final GetOpenPrescriptionForPatient getOpenPrescription : prescriptionList.getPrescriptions()) {
			if (getOpenPrescription.getPrescription() != null) {
				final PatientPrescriptionDecryptorThread decryptorThread = new PatientPrescriptionDecryptorThread(semaphore, getOpenPrescription,
						patientEtk,
						kgssEtk);
				dataMap.put(getOpenPrescription, decryptorThread);
			}
		}

		for (final PatientPrescriptionDecryptorThread decryptorThread : dataMap.values()) {
			semaphore.acquireUninterruptibly();
			decryptorThread.start();
		}
		try {
			semaphore.acquireUninterruptibly(threadLimit);
		} catch (final IllegalArgumentException e) {
			LOG.debug("Incorrect Thread configuration : " + e);
		}
		final ListPatientPrescriptionsResult finalResult = new ListPatientPrescriptionsResult();
		for (final PatientPrescriptionDecryptorThread decryptorThread : dataMap.values()) {
			finalResult.getPrescriptions().add(decryptorThread.getPrescription());
		}
		finalResult.setHasMoreResults(prescriptionList.isHasMoreResults());

		Collections.sort(finalResult.getPrescriptions(), new Comparator<GetOpenPrescriptionForPatient>() {
			@Override
			public int compare(final GetOpenPrescriptionForPatient f1, final GetOpenPrescriptionForPatient f2) {
				return f2.getCreationDate().compareTo(f1.getCreationDate());
			}
		});

		return finalResult;
	}

}