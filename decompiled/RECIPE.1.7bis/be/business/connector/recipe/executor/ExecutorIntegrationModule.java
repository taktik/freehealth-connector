package be.business.connector.recipe.executor;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.recipe.executor.domain.GetPrescriptionForExecutorResult;
import be.recipe.services.executor.ListNotificationsItem;
import java.util.List;

public interface ExecutorIntegrationModule {
   GetPrescriptionForExecutorResult getPrescription(String var1) throws IntegrationModuleException;

   void markAsDelivered(String var1) throws IntegrationModuleException;

   void markAsArchived(String var1) throws IntegrationModuleException;

   void markAsUndelivered(String var1) throws IntegrationModuleException;

   void revokePrescription(String var1, String var2) throws IntegrationModuleException;

   List<ListNotificationsItem> listNotifications(boolean var1) throws IntegrationModuleException;

   void createFeedback(String var1, String var2, byte[] var3) throws IntegrationModuleException;
}
