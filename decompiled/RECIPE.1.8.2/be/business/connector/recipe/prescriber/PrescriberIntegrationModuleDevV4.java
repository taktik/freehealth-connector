package be.business.connector.recipe.prescriber;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.recipe.services.prescriber.GetPrescriptionStatusParam;
import be.recipe.services.prescriber.GetPrescriptionStatusResult;
import be.recipe.services.prescriber.ListOpenRidsParam;
import be.recipe.services.prescriber.ListOpenRidsResult;
import be.recipe.services.prescriber.ListRidsHistoryParam;
import be.recipe.services.prescriber.ListRidsHistoryResult;
import be.recipe.services.prescriber.PutVisionParam;
import be.recipe.services.prescriber.PutVisionResult;
import be.recipe.services.prescriber.ValidationPropertiesParam;
import be.recipe.services.prescriber.ValidationPropertiesResult;

public interface PrescriberIntegrationModuleDevV4 extends PrescriberIntegrationModuleV4 {
   String createPrescription(boolean var1, String var2, byte[] var3, String var4, String var5, String var6) throws IntegrationModuleException;

   GetPrescriptionStatusResult getData(GetPrescriptionStatusParam var1) throws IntegrationModuleException;

   ListRidsHistoryResult getData(ListRidsHistoryParam var1) throws IntegrationModuleException;

   PutVisionResult putData(PutVisionParam var1) throws IntegrationModuleException;

   ListOpenRidsResult getData(ListOpenRidsParam var1) throws IntegrationModuleException;

   ValidationPropertiesResult getData(ValidationPropertiesParam var1) throws IntegrationModuleException;
}
