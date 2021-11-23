/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.utils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;
import org.perf4j.aop.Profiled;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.EncryptionUtils;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule;
import be.business.connector.recipe.prescriber.PrescriberIntegrationModuleV4Impl;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.recipe.services.executor.ListOpenPrescriptionsResult;

/**
 * The Class DecryptionUtils.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public class PrescriberEncryptionUtils {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(PrescriberEncryptionUtils.class);

	/** The etk helper. */
	private final ETKHelper etkHelper;

	/** The key cache. */
	private final Map<String, KeyResult> keyCache;

	private final PropertyHandler propertyHandler;

	private final Key symmKey;

	/**
	 * Instantiates a new {@link PrescriberEncryptionUtils}.
	 *
	 * @param propertyHandler
	 *            the property handler
	 * @param encryptionUtils
	 *            the encryption utils
	 * @param keyCache2
	 */
	public PrescriberEncryptionUtils(final PropertyHandler propertyHandler, final EncryptionUtils encryptionUtils,
			final Map<String, KeyResult> keyCache) {
		this.propertyHandler = propertyHandler;
		this.etkHelper = new ETKHelper(propertyHandler, encryptionUtils);
		this.symmKey = EncryptionUtils.getInstance().generateSecretKey();
		this.keyCache = keyCache;
	}

	/**
	 * Encrypt a list of {@link CreatePrescriptionDTO}s.
	 *
	 * @param createPrescriptionDTOs
	 *            the list of {@link CreatePrescriptionDTO}s
	 * @param prescriberIntegrationModuleV4Impl
	 *            the {@link PrescriberIntegrationModuleV4Impl}
	 * @return the encrypted {@link ListOpenPrescriptionsResult}
	 */
	public List<CreatePrescriptionDTO> doEncryptions(final List<CreatePrescriptionDTO> createPrescriptionDTOs,
			final AbstractPrescriberIntegrationModule prescriberIntegrationModuleV4) {
		final int threadLimit = PropertyHandler.getInstance().getIntegerProperty("encryption.thread.number", "50");
		final Semaphore semaphore = new Semaphore(threadLimit, true);
		final List<PrescriptionEncryptorThread> dataList = new ArrayList<>();
		for (final CreatePrescriptionDTO createPrescriptionDTO : createPrescriptionDTOs) {
			if (createPrescriptionDTO.getPrescription() != null) {
				final KeyResult key = getNewKey(createPrescriptionDTO.getPatientId(), createPrescriptionDTO.getPrescriptionType());
				final PrescriptionEncryptorThread encryptorThread = new PrescriptionEncryptorThread(semaphore, createPrescriptionDTO, key, symmKey,
						etkHelper, prescriberIntegrationModuleV4);
				dataList.add(encryptorThread);
			}
		}

		for (final PrescriptionEncryptorThread encryptorThread : dataList) {
			semaphore.acquireUninterruptibly();
			encryptorThread.start();
		}
		try {
			semaphore.acquireUninterruptibly(threadLimit);
		} catch (final IllegalArgumentException e) {
			LOG.debug("Incorrect Thread configuration : " + e);
			throw new IntegrationModuleException(e.getMessage(), e);
		}
		final List<CreatePrescriptionDTO> finalResult = new ArrayList<>();
		for (final PrescriptionEncryptorThread decryptorThread : dataList) {
			finalResult.add(decryptorThread.getCreatePrescriptionDTO());
		}

		for (final CreatePrescriptionDTO createPrescriptionDTO : createPrescriptionDTOs) {
			for (final CreatePrescriptionDTO finalDTO : finalResult) {
				if (createPrescriptionDTO.getSequenceNumber() == finalDTO.getSequenceNumber()) {
					createPrescriptionDTO.setRid(finalDTO.getRid());
					LOG.info(String.format("Error occured during doEncryptions-operations[%s]:[%s] ", finalDTO.getSequenceNumber(), finalDTO.getException()));
					createPrescriptionDTO.setErrorOccured(finalDTO.isErrorOccured());
					createPrescriptionDTO.setException(finalDTO.getException());
				}
			}
		}

		return createPrescriptionDTOs;
	}

	/**
	 * Gets the new key.
	 *
	 * @param patientId
	 *            the patient id
	 * @param prescriptionType
	 *            the prescription type
	 * @return the new key @ the integration module exception
	 */
	public KeyResult getNewKey(final String patientId, final String prescriptionType) {
		KeyResult key = null;

		if (keyCache.containsKey(patientId)) {
			key = keyCache.get(patientId);
		} else {
			key = getNewKeyFromKgss(prescriptionType, StandaloneRequestorProvider.getRequestorIdInformation(), null, patientId,
					etkHelper.getSystemETK().get(0).getEncoded());
		}
		return key;
	}

	/**
	 * Gets the new key from kgss.
	 *
	 * @param prescriptionType
	 *            the prescription type
	 * @param prescriberId
	 *            the prescriber id
	 * @param executorId
	 *            the executor id
	 * @param patientId
	 *            the patient id
	 * @param myEtk
	 *            the my etk
	 * @return the new key from kgss @ the integration module exception
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriberIntegrationModule#getNewKeyFromKgss")
	protected KeyResult getNewKeyFromKgss(final String prescriptionType, final String prescriberId, final String executorId, final String patientId,
			final byte[] myEtk) {

		final EncryptionToken etkKgss = etkHelper.getKGSS_ETK().get(0);
		final List<String> credentialTypes = propertyHandler.getMatchingProperties("kgss.createPrescription.ACL." + prescriptionType);

		try {
			return KgssServiceImpl.getInstance().retrieveNewKey(etkKgss.getEncoded(), credentialTypes, prescriberId, executorId, patientId, myEtk);
		} catch (final Exception t) {
			Exceptionutils.errorHandler(t);
		}
		return null;
	}
}