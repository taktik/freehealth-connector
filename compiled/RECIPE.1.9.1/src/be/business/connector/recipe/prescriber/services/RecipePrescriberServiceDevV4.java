package be.business.connector.recipe.prescriber.services;

import be.recipe.services.prescriber.CreatePrescription;
import be.recipe.services.prescriber.CreatePrescriptionResponse;
import be.recipe.services.prescriber.GetPrescriptionForPrescriber;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResponse;
import be.recipe.services.prescriber.GetPrescriptionStatus;
import be.recipe.services.prescriber.GetPrescriptionStatusResponse;
import be.recipe.services.prescriber.ListFeedbacks;
import be.recipe.services.prescriber.ListFeedbacksResponse;
import be.recipe.services.prescriber.ListOpenRids;
import be.recipe.services.prescriber.ListRidsHistory;
import be.recipe.services.prescriber.ListRidsHistoryResponse;
import be.recipe.services.prescriber.PutVision;
import be.recipe.services.prescriber.PutVisionResponse;
import be.recipe.services.prescriber.RevokePrescription;
import be.recipe.services.prescriber.RevokePrescriptionResponse;
import be.recipe.services.prescriber.SendNotification;
import be.recipe.services.prescriber.SendNotificationResponse;
import be.recipe.services.prescriber.UpdateFeedbackFlag;
import be.recipe.services.prescriber.UpdateFeedbackFlagResponse;
import be.recipe.services.prescriber.ValidationProperties;
import be.recipe.services.prescriber.ValidationPropertiesResponse;

/**
 * The Interface RecipePrescriberServiceV4.
 */
public interface RecipePrescriberServiceDevV4 {

	/**
	 * Creates the prescription.
	 *
	 * @param request
	 *            the request
	 * @return the creates the prescription response @ the integration module exception
	 */
	CreatePrescriptionResponse createPrescription(CreatePrescription request);

	/**
	 * Revoke prescription.
	 *
	 * @param revokePrescriptionRequest
	 *            the revoke prescription request
	 * @return the revoke prescription response @ the integration module exception
	 */
	RevokePrescriptionResponse revokePrescription(RevokePrescription revokePrescriptionRequest);

	/**
	 * Gets the prescription for.
	 *
	 * @param getPrescriptionForPrescriberRequest
	 *            the get prescription for prescriber request
	 * @return the prescription for prescriber @ the integration module exception
	 */
	GetPrescriptionForPrescriberResponse getPrescriptionForPrescriber(GetPrescriptionForPrescriber getPrescriptionForPrescriberRequest);

	/**
	 * List open prescriptions.
	 *
	 * @param listOpenPrescriptionsRequest
	 *            the list open prescriptions request
	 * @return the list open prescription response @ the integration module exception
	 */
	be.recipe.services.prescriber.ListOpenRidsResponse listOpenRids(ListOpenRids listOpenPrescriptionsRequest);

	/**
	 * Send notification.
	 *
	 * @param sendNotificationRequest
	 *            the send notification request
	 * @return the send notification response @ the integration module exception
	 */
	SendNotificationResponse sendNotification(SendNotification sendNotificationRequest);

	/**
	 * Update feedback flag.
	 *
	 * @param updateFeedbackFlagRequest
	 *            the update feedback flag request
	 * @return the update feedback flag response @ the integration module exception
	 */
	UpdateFeedbackFlagResponse putFeedbackFlag(UpdateFeedbackFlag updateFeedbackFlagRequest);

	/**
	 * List feedbacks.
	 *
	 * @param listFeedbacksRequest
	 *            the list feedbacks request
	 * @return the list feedbacks response @ the integration module exception
	 */
	ListFeedbacksResponse listFeedbacks(ListFeedbacks listFeedbacksRequest);

	/**
	 * Gets the validation properties.
	 *
	 * @param request
	 *            the request
	 * @return the validation properties @ the integration module exception
	 */
	ValidationPropertiesResponse getValidationProperties(ValidationProperties request);

	/**
	 * Gets the prescription status.
	 *
	 * @param request
	 *            the request
	 * @return the prescription status @ the integration module exception
	 */
	GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request);

	/**
	 * Put vision.
	 *
	 * @param request
	 *            the request
	 * @return the put vision response @ the integration module exception
	 */
	PutVisionResponse putVisionForPrescriber(PutVision request);

	/**
	 * List prescription history.
	 *
	 * @param request
	 *            the request
	 * @return the list rid history response @ the integration module exception
	 */
	ListRidsHistoryResponse listRidsHistory(ListRidsHistory request);

}
