/*
 * (C) 2021 Recip-e. All rights reserved.
 */
package be.business.connector.recipe.utils;

import static be.business.connector.core.utils.RecipeConstants.PROGRAM_IDENTIFICATION;
import static be.business.connector.projects.common.utils.ValidationUtils.validateExpirationDate;

import java.security.Key;
import java.util.List;
import java.util.concurrent.Semaphore;

import org.joda.time.DateTime;
import org.jvnet.jaxb2_commons.lang.Validate;
import org.perf4j.aop.Profiled;

import be.business.connector.core.utils.ETKHelper;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.core.utils.PropertyHandler;
import be.business.connector.projects.common.utils.ValidationUtils;
import be.business.connector.recipe.prescriber.AbstractPrescriberIntegrationModule;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.business.connector.recipe.prescriber.services.RecipePrescriberServiceV4Impl;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse;
import be.recipe.services.prescriber.CreatePrescriptionParam;
import be.recipe.services.prescriber.CreatePrescriptionResult;

/**
 * The Class PrescriptionDecryptorThread.
 *
 * @author <a href="mailto:bruno.casneuf@recip-e.be">Bruno Casneuf</a>
 */
public class PrescriptionEncryptorThread extends Thread {

	/** The prescription. */
	private CreatePrescriptionDTO createPrescriptionDTO;

	/** The available. */
	private Semaphore semaphore;

	private KeyResult key;

	private Key symmKey;

	private ETKHelper etkHelper;

	private AbstractPrescriberIntegrationModule prescriptionIntegrationModule;

	/**
	 * Instantiates a new {@link PrescriptionEncryptorThread}.
	 *
	 * @param available
	 *            the available
	 * @param prescription
	 *            the prescription
	 * @param userEtk
	 *            the user etk
	 * @param kgssEtk
	 *            the kgss etk
	 */
	public PrescriptionEncryptorThread(final Semaphore available, final CreatePrescriptionDTO createPrescriptionDTO, final KeyResult key,
			final Key symmKey, final ETKHelper etkHelper, final AbstractPrescriberIntegrationModule prescriptionIntegrationModule) {
		this.semaphore = available;
		this.createPrescriptionDTO = createPrescriptionDTO;
		this.key = key;
		this.symmKey = symmKey;
		this.etkHelper = etkHelper;
		this.prescriptionIntegrationModule = prescriptionIntegrationModule;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Profiled(logFailuresSeparately = true, tag = "0.PrescriptionEncryptorThread#encryption", logger = "org.perf4j.TimingLogger_Common")
	public void run() {
		try {
			ValidationUtils.validatePatientId(createPrescriptionDTO.getPatientId());
			ValidationUtils.validateVisi(createPrescriptionDTO.getVisi(), false);
			Validate.notNull(createPrescriptionDTO);
			final PropertyHandler propertyHandler = PropertyHandler.getInstance();
			final String expirationDate = prescriptionIntegrationModule.extractExpirationDateFromKmehr(createPrescriptionDTO.getPrescription());
			validateExpirationDate(expirationDate);
			prescriptionIntegrationModule.validateKmehr(createPrescriptionDTO.getPrescription(), createPrescriptionDTO.getPrescriptionType(),
					expirationDate);

			final List<EncryptionToken> etkRecipes = etkHelper.getRecipe_ETK();

			final byte[] message = IOUtils.compress(createPrescriptionDTO.getPrescription());
			final byte[] sealedMessage = prescriptionIntegrationModule.sealPrescriptionForUnknown(key, message);

			// create sealed content
			final CreatePrescriptionParam params = new CreatePrescriptionParam();
			params.setPrescription(sealedMessage);
			params.setPrescriptionType(createPrescriptionDTO.getPrescriptionType());
			params.setFeedbackRequested(createPrescriptionDTO.isFeedbackRequested());
			params.setKeyId(key.getKeyId());
			params.setSymmKey(symmKey.getEncoded());
			params.setPatientId(createPrescriptionDTO.getPatientId());

			// New params for CreatePrescriptionParam in V4: begin
			params.setExpirationDate(expirationDate);
			params.setVision(createPrescriptionDTO.getVisi());
			// New params for CreatePrescriptionParam in V4: end

			// init helper
			final MarshallerHelper<CreatePrescriptionResult, CreatePrescriptionParam> helper = new MarshallerHelper<>(CreatePrescriptionResult.class,
					CreatePrescriptionParam.class);

			// create request
			final CreatePrescriptionRequest request = new CreatePrescriptionRequest();
			request.setSecuredCreatePrescriptionRequest(
					prescriptionIntegrationModule
							.createSecuredContentType(prescriptionIntegrationModule.sealRequest(etkRecipes.get(0), helper.toXMLByteArray(params))));
			request.setProgramId(propertyHandler.getProperty(PROGRAM_IDENTIFICATION));
			request.setId(prescriptionIntegrationModule.getId());
			request.setIssueInstant(new DateTime());

			final CreatePrescriptionAdministrativeInformationType adminValue = new CreatePrescriptionAdministrativeInformationType();
			adminValue.setKeyIdentifier(key.getKeyId().getBytes());
			adminValue.setPrescriptionVersion(PropertyHandler.getInstance().getProperty("prescription.version"));
			adminValue.setReferenceSourceVersion(
					prescriptionIntegrationModule.extractReferenceSourceVersionFromKmehr(createPrescriptionDTO.getPrescription()));
			adminValue.setPrescriptionType(createPrescriptionDTO.getPrescriptionType());
			request.setAdministrativeInformation(adminValue);

			// WS call
			final CreatePrescriptionResponse response = RecipePrescriberServiceV4Impl.getInstance().createPrescription(request);

			final CreatePrescriptionResult result = helper.unsealWithSymmKey(response.getSecuredCreatePrescriptionResponse().getSecuredContent(),
					symmKey);
			prescriptionIntegrationModule.checkStatus(result);

			createPrescriptionDTO.setRid(result.getRid());
			createPrescriptionDTO.setErrorOccured(false);

		} catch (final Exception exception) {
			createPrescriptionDTO.setException(exception);
			createPrescriptionDTO.setErrorOccured(true);
		} finally {
			semaphore.release();
		}
	}

	/**
	 * Clean.
	 */
	public void clean() {
		this.semaphore = null;
	}

	/**
	 * @return
	 */
	public CreatePrescriptionDTO getCreatePrescriptionDTO() {
		return this.createPrescriptionDTO;
	}

}