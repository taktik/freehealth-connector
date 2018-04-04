package org.taktik.connector.technical.session;

public class SessionServiceFactoryWithCache implements SessionServiceWithCache {
   public SessionServiceFactoryWithCache() {
      Session.getInstance().registerSessionService(this);
   }

   public void flushCache() {
      AbstractSessionServiceFactory.flushCache();
   }
}
