/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.utils;

import java.util.concurrent.Semaphore;

import org.jvnet.jaxb2_commons.lang.Validate;
import org.perf4j.aop.Profiled;

import be.business.connector.core.ehealth.services.KgssServiceImpl;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.technical.connector.utils.Crypto;
import be.business.connector.core.utils.I18nHelper;
import be.business.connector.core.utils.IOUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.recipe.services.executor.ListReservationsResultItem;

/**
 * The Class ListReservationsResultItem.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public class ReservationDecryptorThread extends Thread {

	/** The prescription. */
	private ListReservationsResultItem item;

	/** The available. */
	private Semaphore semaphore;

	/** The pharmacy etk. */
	private byte[] pharmacyEtk;

	/** The kgss etk. */
	private byte[] kgssEtk;

	/** The error. */
	private IntegrationModuleException error;

	/**
	 * Instantiates a new {@link ReservationDecryptorThread}.
	 *
	 * @param available
	 *            the available
	 * @param item
	 *            the prescription
	 * @param userEtk
	 *            the user etk
	 * @param kgssEtk
	 *            the kgss etk
	 */
	public ReservationDecryptorThread(final Semaphore available, final ListReservationsResultItem item, final byte[] userEtk,
			final byte[] kgssEtk) {
		Validate.notNull(available);
		Validate.notNull(item);
		Validate.notNull(userEtk);
		Validate.notNull(kgssEtk);
		this.semaphore = available;
		this.item = item;
		this.pharmacyEtk = userEtk;
		this.kgssEtk = kgssEtk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.ReservationDecryptorThread#decryption", logger = "org.perf4j.TimingLogger_Common")
	public void run() {
		try {
			final KeyResult keyResult = KgssServiceImpl.getInstance().retrieveKeyFromKgss(item.getPrescription().getEncryptionKeyId().getBytes(),
					pharmacyEtk,
					kgssEtk);
			final byte[] unsealedPrescription = Crypto.unsealForUnknown(keyResult, item.getPrescription().getPrescription());
			this.item.getPrescription().setPrescription(IOUtils.decompress(unsealedPrescription));
		} catch (final Exception e) {
			this.error = new IntegrationModuleException(I18nHelper.getLabel("technical.connector.error.retrieve.key"), e);
		} finally {
			semaphore.release();
		}
	}

	/**
	 * Gets the {@link ListReservationsResultItem}.
	 *
	 * @return the prescription
	 */
	public ListReservationsResultItem getListReservationsResultItem() {
		return item;
	}

	/**
	 * Clean.
	 */
	public void clean() {
		this.semaphore = null;
		this.item = null;
		this.pharmacyEtk = null;
		this.kgssEtk = null;
		this.error = null;
	}
}