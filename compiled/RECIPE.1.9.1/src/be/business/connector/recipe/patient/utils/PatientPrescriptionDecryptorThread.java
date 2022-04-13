/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.patient.utils;

import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.recipe.services.patient.GetOpenPrescriptionForPatient;
import org.jvnet.jaxb2_commons.lang.Validate;
import org.perf4j.aop.Profiled;

import java.util.concurrent.Semaphore;

public class PatientPrescriptionDecryptorThread extends Thread {

	private GetOpenPrescriptionForPatient prescription;

	private Semaphore semaphore;

	private byte[] patientEtk;

	private byte[] kgssEtk;

	private IntegrationModuleException error;

	public PatientPrescriptionDecryptorThread(final Semaphore available, final GetOpenPrescriptionForPatient prescription, final byte[] userEtk,
			final byte[] kgssEtk) {
		Validate.notNull(available);
		Validate.notNull(prescription);
		Validate.notNull(prescription.getPrescription());
		Validate.notNull(userEtk);
		Validate.notNull(kgssEtk);
		this.semaphore = available;
		this.prescription = prescription;
		this.patientEtk = userEtk;
		this.kgssEtk = kgssEtk;
	}

	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriptionDecryptorThread#decryption", logger = "org.perf4j.TimingLogger_Common")
	public void run() {
		try {
			final KeyResult keyResult = KgssServiceImpl.getInstance().retrieveKeyFromKgss(prescription.getEncryptionKeyId().getBytes(), patientEtk,
					kgssEtk);
			final byte[] unsealedPrescription = Crypto.unsealForUnknown(keyResult, prescription.getPrescription());
			this.prescription.setPrescription(IOUtils.decompress(unsealedPrescription));
		} catch (final Exception e) {
			this.error = new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.key"), e);
		} finally {
			semaphore.release();
		}
	}

	public GetOpenPrescriptionForPatient getPrescription() {
		return prescription;
	}

	public void clean() {
		this.semaphore = null;
		this.prescription = null;
		this.patientEtk = null;
		this.kgssEtk = null;
		this.error = null;
	}
}