package be.business.connector.recipe.patient.services;

import be.recipe.services.patient.CreateRelation;
import be.recipe.services.patient.CreateRelationResponse;
import be.recipe.services.patient.CreateReservation;
import be.recipe.services.patient.CreateReservationResponse;
import be.recipe.services.patient.GetPrescriptionForPatient;
import be.recipe.services.patient.GetPrescriptionForPatientResponse;
import be.recipe.services.patient.GetPrescriptionStatus;
import be.recipe.services.patient.GetPrescriptionStatusResponse;
import be.recipe.services.patient.GetVision;
import be.recipe.services.patient.GetVisionResponse;
import be.recipe.services.patient.ListOpenRids;
import be.recipe.services.patient.ListOpenRidsResponse;
import be.recipe.services.patient.ListPatientPrescription;
import be.recipe.services.patient.ListPatientPrescriptionResponse;
import be.recipe.services.patient.ListRelations;
import be.recipe.services.patient.ListRelationsResponse;
import be.recipe.services.patient.ListRidsHistory;
import be.recipe.services.patient.ListRidsHistoryResponse;
import be.recipe.services.patient.PutVision;
import be.recipe.services.patient.PutVisionResponse;
import be.recipe.services.patient.RevokePrescription;
import be.recipe.services.patient.RevokePrescriptionResponse;
import be.recipe.services.patient.RevokeRelation;
import be.recipe.services.patient.RevokeRelationResponse;
import be.recipe.services.patient.UpdateFeedbackFlag;
import be.recipe.services.patient.UpdateFeedbackFlagResponse;

/**
 * The Interface RecipePatientServiceV4.
 */
public interface RecipePatientServiceDevV4 {

	GetPrescriptionForPatientResponse getPrescriptionForPatient(GetPrescriptionForPatient getPrescriptionForPrescriberRequest);

	RevokePrescriptionResponse revokePrescription(RevokePrescription revokePrescriptionRequest);

	UpdateFeedbackFlagResponse updateFeedbackFlag(UpdateFeedbackFlag updateFeedbackFlagRequest);

	GetVisionResponse getVision(GetVision request);

	PutVisionResponse putVision(PutVision request);

	ListRelationsResponse listRelations(ListRelations request);

	CreateRelationResponse createRelation(CreateRelation request);

	RevokeRelationResponse revokeRelation(RevokeRelation request);

	CreateReservationResponse createReservation(CreateReservation request);

	GetPrescriptionStatusResponse getPrescriptionStatus(GetPrescriptionStatus request);

	ListRidsHistoryResponse listRidsHistory(ListRidsHistory request);

	ListOpenRidsResponse listOpenRids(ListOpenRids listOpenPrescriptionsRequest);

	ListPatientPrescriptionResponse listOpenPrescriptions(ListPatientPrescription request);

}