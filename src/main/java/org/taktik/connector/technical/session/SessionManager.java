package org.taktik.connector.technical.session;

import org.taktik.connector.technical.exception.SessionManagementException;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.sts.security.SAMLToken;
import java.security.KeyStore;
import java.util.Map;

public interface SessionManager {
   void loadSession(SAMLToken var1, String var2) throws TechnicalConnectorException, SessionManagementException;

   void loadSession(SAMLToken var1, String var2, String var3) throws TechnicalConnectorException, SessionManagementException;

   SessionItem createSessionEidOnly() throws SessionManagementException, TechnicalConnectorException;

   SessionItem createSession(String var1) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createSession(String var1, String var2) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1, String var2) throws SessionManagementException, TechnicalConnectorException;

   SessionItem createFallbackSession(String var1, String var2, String var3) throws SessionManagementException, TechnicalConnectorException;

   SessionItem getSession();

   void unloadSession();

   boolean hasValidSession() throws SessionManagementException;

   void loadEncryptionKeys(String var1) throws TechnicalConnectorException, SessionManagementException;

   void registerSessionService(SessionServiceWithCache var1);

   void setKeyStore(Map<String, KeyStore> var1);
}
