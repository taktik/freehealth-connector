package org.taktik.connector.technical.session;

import org.taktik.connector.technical.exception.ConnectorException;
import org.taktik.connector.technical.validator.EhealthReplyValidator;
import org.taktik.connector.technical.validator.SessionValidator;
import org.taktik.connector.technical.validator.impl.EhealthReplyValidatorImpl;
import org.taktik.connector.technical.validator.impl.SAMLSessionValidator;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractSessionServiceFactory {
   private static volatile EhealthReplyValidator replyValidator = new EhealthReplyValidatorImpl();
   private static volatile SessionValidator sessionValidator = new SAMLSessionValidator();
   private static final Logger LOG = LoggerFactory.getLogger(AbstractSessionServiceFactory.class);
   private static Map<ServiceCacheKey, Object> serviceCache = new HashMap();

   protected AbstractSessionServiceFactory() {
      throw new UnsupportedOperationException();
   }

   protected static <T> T getService(Class<T> clazz, ImplementationClassFactory implementationClassFactory, String... additionalParameters) throws ConnectorException {
      ServiceCacheKey key = new ServiceCacheKey(clazz, additionalParameters);
      if (!serviceCache.containsKey(key)) {
         LOG.debug("Creating new Service for :" + clazz.getCanonicalName());
         Object implementationInstance = implementationClassFactory.createImplementationClass(clazz, sessionValidator, replyValidator, additionalParameters);
         serviceCache.put(new ServiceCacheKey(clazz, additionalParameters), implementationInstance);
      }

      return (T) serviceCache.get(key);
   }

   public static void flushCache() {
      LOG.debug("Flushing cache, clearing all services.");
      serviceCache.clear();
   }

   static {
      new SessionServiceFactoryWithCache();
   }
}
