package be.business.connector.gfddpp.pcdh;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.business.connector.gfddpp.domain.ThreeStateBoolean;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.FetchDataEntriesResponse;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsRequest;
import be.business.connector.gfddpp.domain.medicationscheme.protocol.RetrieveTimestampsResponse;

public interface PCDHIntegrationModule {
   String getPharmacyDetails(String var1, String var2, String var3, String var4, String var5, boolean var6, ThreeStateBoolean var7) throws IntegrationModuleException;

   String getData(String var1, String var2, String var3, String var4, boolean var5, byte[] var6, boolean var7, ThreeStateBoolean var8) throws IntegrationModuleException;

   String getDataTypes(String var1, String var2, boolean var3, ThreeStateBoolean var4) throws IntegrationModuleException;

   RetrieveTimestampsResponse getMedicationSchemeTimestamps(RetrieveTimestampsRequest var1) throws IntegrationModuleException;

   FetchDataEntriesResponse getMedicationSchemeEntries(FetchDataEntriesRequest var1) throws IntegrationModuleException;
}
