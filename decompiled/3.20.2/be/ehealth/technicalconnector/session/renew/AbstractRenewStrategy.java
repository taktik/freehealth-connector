package be.ehealth.technicalconnector.session.renew;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.config.ConfigValidator;
import be.ehealth.technicalconnector.config.domain.Duration;
import be.ehealth.technicalconnector.exception.SessionManagementException;
import be.ehealth.technicalconnector.exception.SessionManagementExceptionValues;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.SAMLTokenFactory;
import be.ehealth.technicalconnector.service.sts.STSService;
import be.ehealth.technicalconnector.service.sts.STSServiceFactory;
import be.ehealth.technicalconnector.service.sts.security.SAMLToken;
import be.ehealth.technicalconnector.session.SessionItem;
import be.ehealth.technicalconnector.session.SessionServiceWithCache;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

public abstract class AbstractRenewStrategy implements RenewStrategy {
   private static final Logger LOG = LoggerFactory.getLogger(AbstractRenewStrategy.class);
   private static final Object mutex = new Object();
   private final List<SessionServiceWithCache> cacheServices = new ArrayList();

   protected static void executeReload(SessionItem session, List<SessionServiceWithCache> cacheServices) throws SessionManagementException {
      ConfigValidator config = ConfigFactory.getConfigValidator();

      try {
         if (session.getHeaderCredential() != null && config.getBooleanProperty("sessionmanager.activate.autorenew", false)) {
            synchronized(mutex) {
               LOG.debug("Trying to renew existing session.");
               Duration validity = config.getDurationProperty("sessionmanager.validity.token", config.getLongProperty("sessionmanager.validity.token", 24L), TimeUnit.HOURS);
               STSService sts = STSServiceFactory.getInstance();
               Element assertion = sts.renewToken(session.getHeaderCredential(), session.getHolderOfKeyCredential(), session.getSAMLToken().getAssertion(), validity);
               SAMLToken token = SAMLTokenFactory.getInstance().createSamlToken(assertion, session.getHolderOfKeyCredential());
               session.setSAMLToken(token);
               Iterator i$ = cacheServices.iterator();

               while(i$.hasNext()) {
                  SessionServiceWithCache serviceWithCache = (SessionServiceWithCache)i$.next();
                  serviceWithCache.flushCache();
               }
            }
         }

      } catch (TechnicalConnectorException var12) {
         throw new SessionManagementException(SessionManagementExceptionValues.ERROR_GENERAL, var12, new Object[]{var12.getMessage()});
      }
   }

   public void register(SessionServiceWithCache serviceWithCache) {
      this.cacheServices.add(serviceWithCache);
   }

   public void flushCache() {
      Iterator i$ = this.cacheServices.iterator();

      while(i$.hasNext()) {
         SessionServiceWithCache service = (SessionServiceWithCache)i$.next();
         service.flushCache();
      }

   }

   public List<SessionServiceWithCache> getCacheServices() {
      return this.cacheServices;
   }
}
