package be.business.connector.recipe.patient;

import be.recipe.services.executor.ListOpenPrescriptionsParam;
import be.recipe.services.executor.ListOpenPrescriptionsResult;
import be.recipe.services.patient.CreateRelationParam;
import be.recipe.services.patient.CreateRelationResult;
import be.recipe.services.patient.CreateReservationParam;
import be.recipe.services.patient.CreateReservationResult;
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

/**
 * The Interface PatientIntegrationModuleDevV4.
 * 
 * @author <a href="mailto:bruno.casneuf@healthconnect.be">Bruno Casneuf</a>
 */
public interface PatientIntegrationModuleDevV4 extends PatientIntegrationModuleV4 {

	/**
	 * Gets the vision.
	 *
	 * @param request
	 *            the {@link GetVisionParam}
	 * @return the {@link GetVisionResult} @ the integration module exception
	 */
	@Override
	GetVisionResult getData(GetVisionParam request);

	/**
	 * Put the vision .
	 *
	 * @param request
	 *            the {@link PutVisionParam}
	 * @return the {@link PutVisionResult} @ the integration module exception
	 */
	@Override
	PutVisionResult putData(PutVisionParam request);

	/**
	 * Put the reservation data.
	 *
	 * @param data
	 *            the {@link CreateReservationParam}
	 * @return the {@link CreateReservationResult} @ the integration module exception
	 */
	@Override
	CreateReservationResult putData(CreateReservationParam data);

	/**
	 * Update feedback flag.
	 *
	 * @param rid
	 *            the rid
	 * @param feedbackAllowed
	 *            the feedback allowed @ the integration module exception
	 */
	@Override
	void updateFeedbackFlag(String rid, boolean feedbackAllowed);

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
	 * Gets the prescription history.
	 *
	 * @param request
	 *            the {@link ListRidsHistoryParam}
	 * @return the {@link ListRidsHistoryResult} @ the integration module exception
	 */
	@Override
	ListRidsHistoryResult getData(ListRidsHistoryParam request);

	/**
	 * Gets the open prescription list.
	 *
	 * @param request
	 *            the {@link ListOpenPrescriptionsParam}
	 * @return the {@link ListOpenPrescriptionsResult} @ the integration module exception
	 */
	@Override
	ListOpenRidsResult getData(ListOpenRidsParam request);

	/**
	 * Put patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @ the integration module exception
	 */
	@Override
	CreateRelationResult putData(CreateRelationParam patientRelationParam);

	/**
	 * Gets the patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @ the integration module exception
	 */
	@Override
	ListRelationsResult getData(ListRelationsParam patientRelationParam);

	/**
	 * Revokes the patient relation data.
	 *
	 * @param patientRelationParam
	 *            the {@link PatientRelationParam}
	 * @return the {@link PatientRelationResult} @
	 */
	@Override
	RevokeRelationResult revokeData(RevokeRelationParam patientRelationParam);

	@Override
	ListPatientPrescriptionsResult listOpenPrescriptions(ListPatientPrescriptionsParam listPatientPrescriptionsParam);
}
