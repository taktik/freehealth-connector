package be.business.connector.recipe.patient;

import java.io.IOException;

import be.business.connector.core.utils.Exceptionutils;
import be.business.connector.recipe.patient.utils.PatientDecryptionUtils;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import org.apache.log4j.Logger;

import be.business.connector.common.StandaloneRequestorProvider;
import be.business.connector.common.module.AbstractIntegrationModule;
import be.business.connector.core.domain.KgssIdentifierType;
import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.core.utils.IOUtils;
import be.business.connector.core.utils.MarshallerHelper;
import be.business.connector.recipe.patient.domain.GetVisionParam;
import be.business.connector.recipe.patient.domain.PutReservationParam;
import be.business.connector.recipe.patient.domain.PutVisionParam;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import be.fgov.ehealth.commons.core.v1.LocalisedString;
import be.fgov.ehealth.commons.core.v1.StatusType;
import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.etee.crypto.encrypt.EncryptionToken;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import be.recipe.services.patient.GetPrescriptionForPatientParam;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionForPatientResultSealed;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.RevokePrescriptionParam;
import org.perf4j.aop.Profiled;

/**
 * The Class AbstractPatientIntegrationModule.
 */
public abstract class AbstractPatientIntegrationModule extends AbstractIntegrationModule implements PatientIntegrationModuleV4{

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(AbstractPatientIntegrationModule.class);

	private final PatientDecryptionUtils utils;

	/**
	 * Instantiates a new abstract patient integration module.
	 *
	 * @ the integration module exception
	 */
	public AbstractPatientIntegrationModule() {
		super();
		utils = new PatientDecryptionUtils(getPropertyHandler(), getEncryptionUtils());
	}

	/**
	 * Unseal prescription.
	 *
	 * @param result
	 *            the result
	 * @return the gets the prescription for patient result @ the integration module exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected GetPrescriptionForPatientResult unsealPrescription(final byte[] result) throws IOException {
		final MarshallerHelper<GetPrescriptionForPatientResult, Object> marshaller = new MarshallerHelper<>(GetPrescriptionForPatientResult.class,
				Object.class);
		final GetPrescriptionForPatientResult unsealedResult = marshaller.unsealWithSymmKey(result, getSymmKey());
		checkStatus(unsealedResult);
		unsealPrescriptionBytes(unsealedResult);
		return unsealedResult;
	}

	/**
	 * Unseal prescription bytes.
	 *
	 * @param result
	 *            the result @ the integration module exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void unsealPrescriptionBytes(final GetPrescriptionForPatientResult result) throws IOException {
		final KeyResult key = getKeyFromKgss(result.getEncryptionKeyId(), getEtkHelper().getSystemETK().get(0).getEncoded());
		final byte[] unsealedPrescription = IOUtils.decompress(unsealPrescriptionForUnknown(key, result.getPrescription()));
		result.setPrescription(unsealedPrescription);
	}

	/**
	 * Creates the get prescription for patient result.
	 *
	 * @param sealedExecutorResponse
	 *            the sealed executor response
	 * @return the gets the prescription for patient result @ the integration module exception
	 */
	protected GetPrescriptionForPatientResult createGetPrescriptionForPatientResult(final byte[] sealedExecutorResponse) {

		final MarshallerHelper<GetPrescriptionForPatientResultSealed, Object> marshaller = new MarshallerHelper<>(
				GetPrescriptionForPatientResultSealed.class, Object.class);

		final String requestorIdInformation = StandaloneRequestorProvider.getRequestorIdInformation();
		final String requestorTypeInformation = StandaloneRequestorProvider.getRequestorTypeInformation();

		final GetPrescriptionForPatientResultSealed sealedResult = marshaller.unsealWithSymmKey(sealedExecutorResponse, getSymmKey());
		final KeyResult key = getKeyFromKgss(sealedResult.getEncryptionKeyId(),
				getEtkHelper().getEtks(KgssIdentifierType.NIHII_PHARMACY, requestorIdInformation).get(0).getEncoded());
		final byte[] unsealedPrescription = unsealWithSymKey(sealedResult, key, requestorIdInformation, requestorTypeInformation);

		final GetPrescriptionForPatientResult finalResult = new GetPrescriptionForPatientResult();
		finalResult.setPrescription(unsealedPrescription);
		return finalResult;
	}

	/**
	 * Gets the sealed get prescription for patient param.
	 *
	 * @param rid
	 *            the rid
	 * @return the sealed get prescription for patient param @ the integration module exception
	 */
	public byte[] getSealedGetPrescriptionForPatientParam(final String rid) {
		final GetPrescriptionForPatientParam param = new GetPrescriptionForPatientParam();
		param.setRid(rid);
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, GetPrescriptionForPatientParam.class);
	}

	/**
	 * Seal for recipe.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 * @return the byte[] @ the integration module exception
	 */
	protected <T> byte[] sealForRecipe(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		final EncryptionToken etkRecipe = getEtkHelper().getRecipe_ETK().get(0);
		return sealRequest(etkRecipe, helper.toXMLByteArray(data));
	}

	/**
	 * Marshall.
	 *
	 * @param <T>
	 *            the generic type
	 * @param data
	 *            the data
	 * @param type
	 *            the type
	 * @return the byte[] @ the integration module exception
	 */
	protected <T> byte[] marshall(final T data, final Class<T> type) {
		final MarshallerHelper<Object, T> helper = new MarshallerHelper<>(Object.class, type);
		return helper.toXMLByteArray(data);
	}

	/**
	 * Creates the secured content type.
	 *
	 * @param content
	 *            the content
	 * @return the secured content type
	 */
	protected SecuredContentType createSecuredContentType(final byte[] content) {
		final SecuredContentType secured = new SecuredContentType();
		secured.setSecuredContent(content);
		return secured;
	}

	/**
	 * Check status.
	 *
	 * @param response
	 *            the response @ the integration module exception
	 */
	protected void checkStatus(final ResponseType response) {
		if (!EHEALTH_SUCCESS_CODE_100.equals(response.getStatus().getCode()) && !EHEALTH_SUCCESS_CODE_200.equals(response.getStatus().getCode())) {
			LOG.error("Error Status received : " + response.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(response.getStatus()));
		}
	}

	/**
	 * Check status.
	 *
	 * @param response
	 *            the response @ the integration module exception
	 */
	protected void checkStatus(final be.recipe.services.core.ResponseType response) {
		if (response != null && response.getStatus() != null && !EHEALTH_SUCCESS_CODE_100.equals(response.getStatus().getCode())
				&& !EHEALTH_SUCCESS_CODE_200.equals(response.getStatus().getCode())) {
			LOG.error("Error Status received : " + response.getStatus().getCode());
			throw new IntegrationModuleException(getLocalisedMsg(response.getStatus()), response);
		}
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (status.getMessages().size() > 0) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Gets the localised msg.
	 *
	 * @param status
	 *            the status
	 * @return the localised msg
	 */
	private String getLocalisedMsg(final be.recipe.services.core.StatusType status) {
		final String locale = IntegrationModuleException.getUserLocale();
		for (final be.recipe.services.core.LocalisedString msg : status.getMessages()) {
			if (msg.getLang() != null && locale.equalsIgnoreCase(msg.getLang().value())) {
				return msg.getValue();
			}
		}
		if (status.getMessages().size() > 0) {
			return status.getMessages().get(0).getValue();
		}
		return status.getCode();
	}

	/**
	 * Gets the sealed revoke prescription param.
	 *
	 * @param rid
	 *            the rid
	 * @param reason
	 *            the reason
	 * @return the sealed revoke prescription param @ the integration module exception
	 */
	protected byte[] getSealedRevokePrescriptionParam(final String rid, final String reason) {
		final RevokePrescriptionParam param = new RevokePrescriptionParam();
		param.setRid(rid);
		param.setReason(reason);
		param.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(param, RevokePrescriptionParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param getVisionParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final GetVisionParam getVisionParam) {
		return sealForRecipe(getVisionParam, GetVisionParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param putReservationParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final PutReservationParam putReservationParam) {
		return sealForRecipe(putReservationParam, PutReservationParam.class);
	}

	/**
	 * Gets the sealed data.
	 *
	 * @param putVisionParam
	 *            the request
	 * @return the sealed data @ the integration module exception
	 */
	protected byte[] getSealedData(final PutVisionParam putVisionParam) {
		return sealForRecipe(putVisionParam, PutVisionParam.class);
	}

	protected byte[] getSealedData(final ListPatientPrescriptionsParam request) {
		request.setSymmKey(getSymmKey().getEncoded());
		return sealForRecipe(request, ListPatientPrescriptionsParam.class);
	}

	@Profiled(logFailuresSeparately = true, tag = "0.ExecutorIntegrationModuleV4#decryptListOpenPrescriptionsResult")
	@Override
	public ListPatientPrescriptionsResult decryptListPatientPrescriptionsResult(final ListPatientPrescriptionsResult listPatientPrescriptionsResult) {
		try {
			return utils.decryptPrescriptions(listPatientPrescriptionsResult);
		} catch (final Exception ex) {
			Exceptionutils.errorHandler(ex, "error.data.uncompression");
		}
		return null;
	}


}