package be.ehealth.technicalconnector.session;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import java.security.KeyStore;
import java.util.Map;

public interface SessionManager {
   void loadSession(SAMLToken var1, String var2) throws TechnicalConnectorException;

   void loadSession(SAMLToken var1, String var2, String var3) throws TechnicalConnectorException;

   SessionItem createSessionEidOnly() throws TechnicalConnectorException;

   SessionItem createSession(String var1) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createSession(String var1, String var2) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1, String var2) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1, String var2, String var3) throws SessionManagementException, TechnicalConnectorException;

   SessionItem getSession();

   void unloadSession();

   boolean hasValidSession() throws SessionManagementException;

   void loadEncryptionKeys(String var1) throws TechnicalConnectorException;

   void registerSessionService(SessionServiceWithCache var1);

   void setKeyStore(Map<String, KeyStore> var1);
}
