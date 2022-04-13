package be.business.connector.recipe.patient;

import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResult;
import be.recipe.services.patient.GetPrescriptionForPatientResult;
import be.recipe.services.patient.GetPrescriptionStatusParam;
import be.recipe.services.patient.GetPrescriptionStatusResult;
import be.recipe.services.patient.GetVisionParam;
import be.recipe.services.patient.GetVisionResult;
import be.recipe.services.patient.ListOpenRidsParam;
import be.recipe.services.patient.ListOpenRidsResult;
import be.recipe.services.patient.ListPatientPrescriptionsParam;
import be.recipe.services.patient.ListPatientPrescriptionsResult;
import be.recipe.services.patient.ListRelationsParam;
import be.recipe.services.patient.ListRelationsResult;
import be.recipe.services.patient.ListRidsHistoryParam;
import be.recipe.services.patient.ListRidsHistoryResult;
import be.recipe.services.patient.PutVisionParam;
import be.recipe.services.patient.PutVisionResult;
import be.recipe.services.patient.RevokeRelationParam;
import be.recipe.services.patient.RevokeRelationResult;
import org.perf4j.aop.Profiled;

/**
 * The Interface PatientIntegrationModuleV4.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public interface PatientIntegrationModuleV4 {

	/**
	 * Gets the vision.
	 *
	 * @param request
	 *            the {@link GetVisionParam}
	 * @return the {@link GetVisionResult} @ the integration module exception
	 */
	GetVisionResult getData(GetVisionParam request);

	/**
	 * Put the vision .
	 *
	 * @param request
	 *            the {@link PutVisionParam}
	 * @return the {@link PutVisionResult} @ the integration module exception
	 */
	PutVisionResult putData(PutVisionParam request);

	/**
	 * Put the reservation data.
	 *
	 * @param data
	 *            the {@link CreateReservationParam}
	 * @return the {@link CreateReservationResult} @ the integration module exception
	 */
	CreateReservationResult putData(CreateReservationParam data);

	/**
	 * Update feedback flag.
	 *
	 * @param rid
	 *            the rid
	 * @param feedbackAllowed
	 *            the feedback allowed @ the integration module exception
	 */
	void updateFeedbackFlag(String rid, boolean feedbackAllowed);

	/**
	 * Gets the prescription status.
	 *
	 * @param data
	 *            the {@link GetPrescriptionStatusParam}
	 * @return the {@link GetPrescriptionStatusResult} @ the integration module exception
	 */
	GetPrescriptionStatusResult getData(GetPrescriptionStatusParam data);

	/**
	 * Gets the prescription history.
	 *
	 * @param request
	 *            the {@link ListRidsHistoryParam}
	 * @return the {@link ListRidsHistoryResult} @ the integration module exception
	 */
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Gets the open prescription list.
	 *
	 * @param request
	 *            the {@link ListOpenPrescriptionsParam}
	 * @return the {@link ListOpenPrescriptionsResult} @ the integration module exception
	 */
	ListOpenRidsResult getData(ListOpenRidsParam request);

	/**
	 * Put patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @ the integration module exception
	 */
	CreateRelationResult putData(CreateRelationParam patientRelationParam);

	/**
	 * Gets the patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @ the integration module exception
	 */
	ListRelationsResult getData(ListRelationsParam patientRelationParam);

	/**
	 * Revokes the patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @
	 */
	RevokeRelationResult revokeData(RevokeRelationParam patientRelationParam);

	/**
	 * @return
	 */
	ListPatientPrescriptionsResult listOpenPrescriptions(ListPatientPrescriptionsParam listPatientPrescriptionsParam);

	ListPatientPrescriptionsResult decryptListPatientPrescriptionsResult(final ListPatientPrescriptionsResult listOpenPrescriptionsResult);

	/**
	 * @param rid
	 * @return
	 */
	GetPrescriptionForPatientResult getPrescription(String rid);

	/**
	 * @param rid
	 * @param reason
	 */
	void revokePrescription(String rid, String reason);
}
