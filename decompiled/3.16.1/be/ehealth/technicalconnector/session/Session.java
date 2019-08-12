package be.ehealth.technicalconnector.session;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.impl.SessionManagerImpl;

public final class Session {
   public static final String IDENTIFICATION_KEYSTORE = "identification";
   public static final String HOLDEROFKEY_KEYSTORE = "holderofkey";
   public static final String ENCRYPTION_KEYSTORE = "encryption";
   private static volatile SessionManager manager = SessionManagerImpl.getInstance();

   public static SessionManager getInstance() {
      return manager;
   }

   public static void setSessionManager(SessionManager sessionManager) {
      if (sessionManager == null) {
         manager = SessionManagerImpl.getInstance();
      } else {
         manager = sessionManager;
      }

   }

   public static SAMLToken getSAMLToken() {
      return manager.getSession().getSAMLToken();
   }

   public static void validateSession() throws TechnicalConnectorException {
      if (!manager.hasValidSession()) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.NO_VALID_SESSION, new Object[0]);
      }
   }
}
