package be.business.connector.recipe.prescriber;

import java.util.List;

import be.business.connector.recipe.prescriber.domain.ListPrescriptionHistoryParam;
import be.business.connector.recipe.prescriber.domain.ListPrescriptionHistoryResult;
import be.business.connector.recipe.prescriber.dto.CreatePrescriptionDTO;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResult;

/**
 * The Interface PrescriberIntegrationModuleV4.
 */
public interface PrescriberIntegrationModuleV4 {

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
	String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType, String visibility,
			String expirationDate);

	/**
	 * Gets the prescription status.
	 *
	 * @param data
	 *            the {@link GetPrescriptionStatusParam}
	 * @return the {@link GetPrescriptionStatusResult} @ the integration module exception
	 */
	GetPrescriptionStatusResult getData(GetPrescriptionStatusParam data);

	/**
	 * Lists the prescription history.
	 *
	 * @param request
	 *            the {@link ListPrescriptionHistoryParam}
	 * @return the {@link ListPrescriptionHistoryResult} @ the integration module exception
	 */
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Put the vision.
	 *
	 * @param request
	 *            the {@link PutVisionForPrescriberParam}
	 * @return the {@link PutVisionForPrescriberResult} @ the integration module exception
	 */
	PutVisionResult putData(PutVisionParam request);

	/**
	 * Gets the open prescription list.
	 *
	 * @param data
	 *            the {@link ListOpenRidsParam}
	 * @return the {@link ListOpenRidsResult} @ the integration module exception
	 */
	ListOpenRidsResult getData(ListOpenRidsParam data);

	/**
	 * Gets the validation properties.
	 *
	 * @param data
	 *            the {@link ValidationPropertiesParam}
	 * @return the {@link ValidationPropertiesResult} @ the integration module exception
	 */
	ValidationPropertiesResult getData(ValidationPropertiesParam data);

	/**
	 * @param feedbackRequested
	 * @param patientId
	 * @param prescription
	 * @param prescriptionType
	 * @return
	 */
	String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType);

	/**
	 * @param patientId
	 * @param prescriptionType
	 */
	void prepareCreatePrescription(String patientId, String prescriptionType);

	/**
	 * @param rid
	 * @return
	 */
	GetPrescriptionForPrescriberResult getPrescription(String rid);

	/**
	 * @param rid
	 * @param reason
	 */
	void revokePrescription(String rid, String reason);

	/**
	 * @param notificationText
	 * @param patientId
	 * @param executorId
	 */
	void sendNotification(byte[] notificationText, String patientId, String executorId);

	/**
	 * @param rid
	 * @param feedbackAllowed
	 */
	void updateFeedbackFlag(String rid, boolean feedbackAllowed);

	/**
	 * @param readFlag
	 * @return
	 */
	List<ListFeedbackItem> listFeedback(boolean readFlag);

	/**
	 * @param createPrescriptionDTOs
	 * @return
	 */
	List<CreatePrescriptionDTO> createPrescriptions(List<CreatePrescriptionDTO> createPrescriptionDTOs);

}