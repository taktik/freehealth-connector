package be.business.connector.gfddpp.tipsystem;

import org.taktik.connector.business.recipeprojects.core.exceptions.IntegrationModuleException;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.StoreDataEntriesResponse;

public interface TIPSystemIntegrationModule {
   String registerData(byte[] var1, String var2, boolean var3, ThreeStateBoolean var4) throws IntegrationModuleException;

   StoreDataEntriesResponse registerMedicationSchemeEntries(StoreDataEntriesRequest var1) throws IntegrationModuleException;

   void revokeData(byte[] var1, String var2, boolean var3, ThreeStateBoolean var4) throws IntegrationModuleException;

   String updateData(byte[] var1, String var2, boolean var3, ThreeStateBoolean var4) throws IntegrationModuleException;

   String getStatusMessages(String var1, String var2) throws IntegrationModuleException;

   void shutdownGracefully() throws IntegrationModuleException;

   String archivePrescriptionComment(String var1, String var2) throws IntegrationModuleException;

   String archivePrescription(String var1) throws IntegrationModuleException;
}
