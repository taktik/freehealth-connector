/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.executor;

import java.io.IOException;

import org.perf4j.aop.Profiled;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.recipe.utils.ExecutorDecryptionUtils;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.recipe.services.executor.GetOpenPrescriptionForExecutor;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.executor.ListReservationsResult;

/**
 * The Class AbstractExecutorIntegrationModuleV4.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public abstract class AbstractExecutorIntegrationModuleV4 extends AbstractExecutorIntegrationModule implements ExecutorIntegrationModuleV4 {

	/** The utils. */
	private final ExecutorDecryptionUtils utils;

	/**
	 * Instantiates a new abstract executor integration module V 4.
	 */
	public AbstractExecutorIntegrationModuleV4() {
		utils = new ExecutorDecryptionUtils(getPropertyHandler(), getEncryptionUtils());
	}
	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#decryptGetOpenPrescriptionForExecutor")
	@Override
	public GetOpenPrescriptionForExecutor decryptGetOpenPrescriptionForExecutor(final GetOpenPrescriptionForExecutor gopfe) {
		try {
			final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
			final KeyResult key = getKeyFromKgss(gopfe.getEncryptionKeyId(),
					getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
			final byte[] unsealedPrescription = unsealPrescriptionForUnknown(key, gopfe.getPrescription());
			final GetOpenPrescriptionForExecutor finalResult = new GetOpenPrescriptionForExecutor();
			finalResult.setCreationDate(gopfe.getCreationDate());
			finalResult.setEncryptionKeyId(gopfe.getEncryptionKeyId());
			finalResult.setFeedbackAllowed(gopfe.isFeedbackAllowed());
			finalResult.setId(gopfe.getId());
			finalResult.setPatientId(gopfe.getPatientId());
			finalResult.setPrescriberId(gopfe.getPrescriberId());
			finalResult.setPrescription(IOUtils.decompress(unsealedPrescription));
			finalResult.setPrescriptionType(gopfe.getPrescriptionType());
			finalResult.setRid(gopfe.getRid());
			finalResult.setStatus(gopfe.getStatus());
			finalResult.setExpirationDate(gopfe.getExpirationDate());
			return finalResult;
		} catch (final IOException ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#decryptListOpenPrescriptionsResult")
	@Override
	public ListOpenPrescriptionsResult decryptListOpenPrescriptionsResult(final ListOpenPrescriptionsResult listOpenPrescriptionsResult) {
		try {
			return utils.decryptPrescriptions(listOpenPrescriptionsResult);
		} catch (final Exception ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#decryptListReservationsResult")
	@Override
	public ListReservationsResult decryptListReservationsResult(final ListReservationsResult listReservationsResult) {
		try {
			return utils.decryptReservations(listReservationsResult);
		} catch (final Exception ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}
}