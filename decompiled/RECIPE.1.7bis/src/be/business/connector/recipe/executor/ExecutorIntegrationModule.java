package org.taktik.connector.business.recipe.executor;

import java.util.List;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import org.taktik.connector.business.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.ListNotificationsItem;


public interface ExecutorIntegrationModule{
	GetPrescriptionForExecutorResult getPrescription(String rid) throws IntegrationModuleException;
	
	void markAsDelivered(String rid) throws IntegrationModuleException;
	
	void markAsArchived(String rid) throws IntegrationModuleException;
	
	void markAsUndelivered(String rid) throws IntegrationModuleException;
	
	void revokePrescription(String rid, String reason) throws IntegrationModuleException;
	
	List<ListNotificationsItem> listNotifications(boolean readFlag) throws IntegrationModuleException;
	
	void createFeedback(String prescriberId, String rid, byte[] feedbackText) throws IntegrationModuleException;
}
