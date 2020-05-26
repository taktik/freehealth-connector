package be.business.connector.core.ehealth.services;

import be.business.connector.core.exceptions.IntegrationModuleException;
import be.ehealth.technicalconnector.service.kgss.domain.KeyResult;
import java.util.List;

public interface KgssService {
   KeyResult retrieveKeyFromKgss(byte[] var1, byte[] var2, byte[] var3) throws IntegrationModuleException;

   KeyResult retrieveNewKey(byte[] var1, List<String> var2, String var3, String var4, String var5, byte[] var6) throws IntegrationModuleException;
}
