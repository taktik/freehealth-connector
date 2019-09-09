package org.taktik.connector.technical.service;

import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.service.keydepot.KeyDepotService;
import org.taktik.connector.technical.service.keydepot.impl.KeyDepotServiceImpl;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceFactory {
   private static final Logger LOG = LoggerFactory.getLogger(ServiceFactory.class);
   private static Map<Class<?>, Object> serviceCache = new HashMap<>();

   private ServiceFactory() {
      throw new UnsupportedOperationException();
   }

   public static KeyDepotService getKeyDepotService() throws TechnicalConnectorException {
      return getService(KeyDepotService.class);
   }
   private static <T> T getService(Class<T> clazz) throws TechnicalConnectorException {
      if (!serviceCache.containsKey(clazz)) {
         createService(clazz);
      }

      return (T) serviceCache.get(clazz);
   }

   private static <T> void createService(Class<T> clazz) throws TechnicalConnectorException {
      LOG.debug("Creating new Service for :" + clazz.getCanonicalName());
      if (clazz.equals(KeyDepotService.class)) {
         serviceCache.put(clazz, create(clazz, KeyDepotServiceImpl.class));
      }

   }

   private static <T> T create(Class<T> clazz, Class<?> impl) throws TechnicalConnectorException {
      return (T) (new ConfigurableFactoryHelper(clazz.getName(), impl.getName())).getImplementation();
   }

   public static void flushCache() {
      serviceCache.clear();
   }
}
