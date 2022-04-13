package be.business.connector.recipe.patient.services;

import be.fgov.ehealth.recipe.protocol.v4.CreateRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateRelationResponse;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationRequest;
import be.fgov.ehealth.recipe.protocol.v4.CreateReservationResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetPrescriptionStatusResponse;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionRequest;
import be.fgov.ehealth.recipe.protocol.v4.GetVisionResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenPrescriptionsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListOpenRidsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRelationsResponse;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryRequest;
import be.fgov.ehealth.recipe.protocol.v4.ListRidsHistoryResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutFeedbackFlagResponse;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientRequest;
import be.fgov.ehealth.recipe.protocol.v4.PutVisionForPatientResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokePrescriptionResponse;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationRequest;
import be.fgov.ehealth.recipe.protocol.v4.RevokeRelationResponse;

/**
 * The Interface RecipePatientServiceV4.
 */
public interface RecipePatientServiceV4 {

	/**
	 * Gets the prescription for the patient.
	 *
	 * @param getPrescriptionForPrescriberRequest
	 *            the get prescription for prescriber request
	 * @return the prescription for patient @ the integration module exception
	 */
	GetPrescriptionResponse getPrescriptionForPatient(GetPrescriptionRequest getPrescriptionForPrescriberRequest);

	/**
	 * Revokes a prescription.
	 *
	 * @param revokePrescriptionRequest
	 *            the revoke prescription request
	 * @return the revoke prescription response @ the integration module exception
	 */
	RevokePrescriptionResponse revokePrescription(RevokePrescriptionRequest revokePrescriptionRequest);

	/**
	 * Puts a feedback flag.
	 *
	 * @param updateFeedbackFlagRequest
	 *            the update feedback flag request
	 * @return the put feedback flag response @ the integration module exception
	 */
	PutFeedbackFlagResponse putFeedbackFlag(PutFeedbackFlagRequest updateFeedbackFlagRequest);

	/**
	 * Gets the vision.
	 *
	 * @param request
	 *            the request
	 * @return the vision @ the integration module exception
	 */
	GetVisionResponse getVision(GetVisionRequest request);

	/**
	 * Puts a vision.
	 *
	 * @param request
	 *            the request
	 * @return the put vision for patient response @ the integration module exception
	 */
	PutVisionForPatientResponse putVision(PutVisionForPatientRequest request);

	/**
	 * List relations.
	 *
	 * @param request
	 *            the request
	 * @return the list relations response @ the integration module exception
	 */
	ListRelationsResponse listRelations(ListRelationsRequest request);

	/**
	 * Creates the relation.
	 *
	 * @param request
	 *            the request
	 * @return the creates the relation response @ the integration module exception
	 */
	CreateRelationResponse createRelation(CreateRelationRequest request);

	/**
	 * Revoke relation.
	 *
	 * @param request
	 *            the request
	 * @return the revoke relation response @ the integration module exception
	 */
	RevokeRelationResponse revokeRelation(RevokeRelationRequest request);

	/**
	 * Creates the reservation.
	 *
	 * @param request
	 *            the request
	 * @return the creates the reservation response @ the integration module exception
	 */
	CreateReservationResponse createReservation(CreateReservationRequest request);

	/**
	 * Gets the prescription status.
	 *
	 * @param request
	 *            the request
	 * @return the prescription status @ the integration module exception
	 */
	GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatusRequest request);

	/**
	 * List rids history.
	 *
	 * @param request
	 *            the request
	 * @return the list rids history response @ the integration module exception
	 */
	ListRidsHistoryResponse listRidsHistory(ListRidsHistoryRequest request);

	/**
	 * List open rids.
	 *
	 * @param listOpenPrescriptionsRequest
	 *            the list open prescriptions request
	 * @return the list open rids response @ the integration module exception
	 */
	ListOpenRidsResponse listOpenRids(ListOpenRidsRequest listOpenPrescriptionsRequest);

	/**
	 * List open prescriptions.
	 *
	 * @param request
	 *            the request
	 * @return the list open prescriptions response @ the integration module exception
	 */
	ListOpenPrescriptionsResponse listOpenPrescriptions(ListOpenPrescriptionsRequest request);

}