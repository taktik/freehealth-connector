package org.taktik.connector.business.recipe.prescriber;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.recipe.services.prescriber.GetPrescriptionForPrescriberResult;
import be.recipe.services.prescriber.ListFeedbackItem;
import java.util.List;

public interface PrescriberIntegrationModule {
   String createPrescription(boolean var1, String var2, byte[] var3, String var4) throws IntegrationModuleException;

   void revokePrescription(String var1, String var2) throws IntegrationModuleException;

   GetPrescriptionForPrescriberResult getPrescription(String var1) throws IntegrationModuleException;

   List<String> listOpenPrescription(String var1) throws IntegrationModuleException;

   List<String> listOpenPrescription() throws IntegrationModuleException;

   void sendNotification(byte[] var1, String var2, String var3) throws IntegrationModuleException;

   void updateFeedbackFlag(String var1, boolean var2) throws IntegrationModuleException;

   List<ListFeedbackItem> listFeedback(boolean var1) throws IntegrationModuleException;

   void ping() throws IntegrationModuleException;

   void setPersonalPassword(String var1) throws IntegrationModuleException;

   void prepareCreatePrescription(String var1, String var2) throws IntegrationModuleException;
}
