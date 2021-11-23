package be.business.connector.recipe.prescriber;

import be.business.connector.recipe.prescriber.domain.ListPrescriptionHistoryParam;
import be.business.connector.recipe.prescriber.domain.ListPrescriptionHistoryResult;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResult;

/**
 * The Interface PrescriberIntegrationModuleDevV4.
 */
public interface PrescriberIntegrationModuleDevV4 extends PrescriberIntegrationModuleV4 {

	/**
	 * Creates the prescription.
	 *
	 * @param feedbackRequested
	 *            the feedback requested
	 * @param patientId
	 *            the patient id
	 * @param prescription
	 *            the prescription
	 * @param prescriptionType
	 *            the prescription type
	 * @param visibility
	 *            the visibility
	 * @param expirationDate
	 *            the expiration date
	 * @return the rid @ the integration module exception
	 */
	@Override
	String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, String visibility,
			String expirationDate);

	/**
	 * Gets the prescription status.
	 *
	 * @param data
	 *            the {@link GetPrescriptionStatusParam}
	 * @return the {@link GetPrescriptionStatusResult} @ the integration module exception
	 */
	@Override
	GetPrescriptionStatusResult getData(GetPrescriptionStatusParam data);

	/**
	 * Lists the prescription history.
	 *
	 * @param request
	 *            the {@link ListPrescriptionHistoryParam}
	 * @return the {@link ListPrescriptionHistoryResult} @ the integration module exception
	 */
	@Override
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Put the vision.
	 *
	 * @param request
	 *            the {@link PutVisionForPrescriberParam}
	 * @return the {@link PutVisionForPrescriberResult} @ the integration module exception
	 */
	@Override
	PutVisionResult putData(PutVisionParam request);

	/**
	 * Gets the open prescription list.
	 *
	 * @param data
	 *            the {@link ListOpenRidsParam}
	 * @return the {@link ListOpenRidsResult} @ the integration module exception
	 */
	@Override
	ListOpenRidsResult getData(ListOpenRidsParam data);

	/**
	 * Gets the validation properties.
	 *
	 * @param data
	 *            the {@link ValidationPropertiesParam}
	 * @return the {@link ValidationPropertiesResult} @ the integration module exception
	 */
	@Override
	ValidationPropertiesResult getData(ValidationPropertiesParam data);

}