package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConfigurableFactoryHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class ConfigurationModuleLoader {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleLoader.class);
   private static final String CONNECTOR_CONFIGMODULES = "connector.configmodule";
   private static List<ConfigurationModule> modulesRegistry = new ArrayList();

   private ConfigurationModuleLoader() {
      throw new UnsupportedOperationException();
   }

   static void load(Configuration instance) throws TechnicalConnectorException {
      LOG.debug("Loading ConfigurationModule");
      Validate.notNull(instance);
      ConfigurableFactoryHelper<ConfigurationModule> helper = new ConfigurableFactoryHelper("connector.configmodule", (String)null);
      List<ConfigurationModule> modulesToLoad = helper.getImplementations(true, true);
      filter(modulesToLoad);
      List<ConfigurationModule> modules = new ArrayList();
      modules.add(new ConfigurationModuleClassloader());
      modules.add(new ConfigurationModuleVersion());
      modules.add(new ConfigurationModuleProperties());
      modules.addAll(modulesToLoad);
      modules.add(new ConfigurationModuleSecurityProvider());
      modules.add(new ConfigurationModuleTrustStore());
      modules.add(new ConfigurationModuleEndpointDistributor());
      modules.add(new ConfigurationModuleOCSP());
      modules.add(new ConfigurationModuleEhealthTime());
      modulesRegistry.clear();

      for (ConfigurationModule module : modules) {
         if (Boolean.parseBoolean(System.getProperty("org.taktik.connector.technical.config.impl.Configuration.use." + module.getClass().getSimpleName(), "true"))) {
            modulesRegistry.add(module);
            module.init(instance);
         }
      }

   }

   static void filter(List<ConfigurationModule> modulesToLoad) {
      CollectionUtils.filter(modulesToLoad, new ConfigurationModuleLoader.ConfigurationModulePredicate(ConfigurationModuleClassloader.class, ConfigurationModuleVersion.class, ConfigurationModuleProperties.class, ConfigurationModuleSecurityProvider.class, ConfigurationModuleTrustStore.class));
   }

   static void unload() {
      Iterator i$ = modulesRegistry.iterator();

      while(i$.hasNext()) {
         ConfigurationModule module = (ConfigurationModule)i$.next();

         try {
            module.unload();
         } catch (TechnicalConnectorException var3) {
            LOG.error(var3.getClass().getSimpleName() + ":" + var3.getMessage(), var3);
         }
      }

   }

   static void unloadSystemProperties(Map<String, String> oldValues) {
      Iterator i$ = oldValues.entrySet().iterator();

      while(i$.hasNext()) {
         Entry<String, String> entry = (Entry)i$.next();
         LOG.info("Resetting key [" + (String)entry.getKey() + "] for value[" + (String)entry.getValue() + "]");
         String oldValue = (String)oldValues.get(entry.getKey());
         if (oldValue == null) {
            System.getProperties().remove(entry.getKey());
         } else {
            System.setProperty((String)entry.getKey(), (String)entry.getValue());
         }
      }

   }

   private static class ConfigurationModulePredicate implements Predicate {
      private Class[] clazz;

      public ConfigurationModulePredicate(Class... clazz) {
         this.clazz = clazz;
      }

      public boolean evaluate(Object object) {
         return !ArrayUtils.contains(this.clazz, object.getClass());
      }
   }
}
