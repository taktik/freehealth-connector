package be.business.connector.recipe.prescriber.services;

import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreatePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetValidationPropertiesResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListFeedbacksResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPrescriberResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationRequest;
import be.fgov.ehealth.recipe.protocol.v4.SendNotificationResponse;

/**
 * The Interface RecipePrescriberServiceV4.
 */
public interface RecipePrescriberServiceV4 {

	/**
	 * Creates the prescription.
	 *
	 * @param request
	 *            the request
	 * @return the creates the prescription response @ the integration module exception
	 */
	CreatePrescriptionResponse createPrescription(CreatePrescriptionRequest request);

	/**
	 * Revoke prescription.
	 *
	 * @param revokePrescriptionRequest
	 *            the revoke prescription request
	 * @return the revoke prescription response @ the integration module exception
	 */
	RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest revokePrescriptionRequest);

	/**
	 * Gets the prescription for.
	 *
	 * @param getPrescriptionForPrescriberRequest
	 *            the get prescription for prescriber request
	 * @return the prescription for prescriber @ the integration module exception
	 */
	GetPrescriptionResponse getPrescriptionForPrescriber(GetPrescriptionRequest getPrescriptionForPrescriberRequest);

	/**
	 * List open prescriptions.
	 *
	 * @param listOpenPrescriptionsRequest
	 *            the list open prescriptions request
	 * @return the list open prescription response @ the integration module exception
	 */
	ListOpenRidsResponse listOpenRids(ListOpenRidsRequest listOpenPrescriptionsRequest);

	/**
	 * Send notification.
	 *
	 * @param sendNotificationRequest
	 *            the send notification request
	 * @return the send notification response @ the integration module exception
	 */
	SendNotificationResponse sendNotification(SendNotificationRequest sendNotificationRequest);

	/**
	 * Update feedback flag.
	 *
	 * @param updateFeedbackFlagRequest
	 *            the update feedback flag request
	 * @return the update feedback flag response @ the integration module exception
	 */
	PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest updateFeedbackFlagRequest);

	/**
	 * List feedbacks.
	 *
	 * @param listFeedbacksRequest
	 *            the list feedbacks request
	 * @return the list feedbacks response @ the integration module exception
	 */
	ListFeedbacksResponse listFeedbacks(ListFeedbacksRequest listFeedbacksRequest);

	/**
	 * Gets the validation properties.
	 *
	 * @param request
	 *            the request
	 * @return the validation properties @ the integration module exception
	 */
	GetValidationPropertiesResponse getValidationProperties(GetValidationPropertiesRequest request);

	/**
	 * Gets the prescription status.
	 *
	 * @param request
	 *            the request
	 * @return the prescription status @ the integration module exception
	 */
	GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest request);

	/**
	 * Put vision.
	 *
	 * @param request
	 *            the request
	 * @return the put vision response @ the integration module exception
	 */
	PutVisionForPrescriberResponse putVisionForPrescriber(PutVisionForPrescriberRequest request);

	/**
	 * List prescription history.
	 *
	 * @param request
	 *            the request
	 * @return the list rid history response @ the integration module exception
	 */
	ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest request);

}
