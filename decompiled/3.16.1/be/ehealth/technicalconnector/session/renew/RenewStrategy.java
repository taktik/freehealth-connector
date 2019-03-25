package be.ehealth.technicalconnector.session.renew;

import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;

public interface RenewStrategy extends SessionServiceWithCache {
   String PROP_AUTO_RENEW = "sessionmanager.activate.autorenew";
   String PROP_VALIDITY_TOKEN = "sessionmanager.validity.token";
   int DEFAULT_VALIDITY_TOKEN = 24;

   void renew(SessionItem var1) throws SessionManagementException;

   void register(SessionServiceWithCache var1);
}
