package be.ehealth.technicalconnector.session;

public class SessionServiceFactoryWithCache implements SessionServiceWithCache {
   public SessionServiceFactoryWithCache() {
      Session.getInstance().registerSessionService(this);
   }

   public void flushCache() {
      AbstractSessionServiceFactory.flushCache();
   }
}
