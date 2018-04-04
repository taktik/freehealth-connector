package be.ehealth.technicalconnector.session;

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
}
