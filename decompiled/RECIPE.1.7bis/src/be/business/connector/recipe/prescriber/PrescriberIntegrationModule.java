package org.taktik.connector.business.recipe.prescriber;

import java.util.List;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;

public interface PrescriberIntegrationModule {
	String createPrescription(boolean feedbackRequested, String patientId, byte[] prescription, String prescriptionType) throws IntegrationModuleException;
	
	void revokePrescription(String rid, String reason) throws IntegrationModuleException;
	
	GetPrescriptionForPrescriberResult getPrescription(String rid) throws IntegrationModuleException;
	
	List<String> listOpenPrescription(String patientId) throws IntegrationModuleException;

        List<String> listOpenPrescription() throws IntegrationModuleException;
	
	void sendNotification(byte[] notificationText, String patientId, String executorId) throws IntegrationModuleException;
	
	void updateFeedbackFlag(String rid, boolean feedbackAllowed) throws IntegrationModuleException;
	
	List<ListFeedbackItem> listFeedback(boolean readFlag) throws IntegrationModuleException;
	
	void ping() throws IntegrationModuleException;
	
	void setPersonalPassword(String personalPassword) throws IntegrationModuleException;
	
	void prepareCreatePrescription(String patientId, String prescriptionType) throws IntegrationModuleException;
}
