package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.beid.BeIDFactory;
import be.ehealth.technicalconnector.beid.BeIDInstantiator;
import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleBeidNet implements ConfigurationModule {
   private static final Logger log = LoggerFactory.getLogger(ConfigurationModuleBeidNet.class);
   public static String PROP_BEID_NET_ACTIVATE = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleBeidNet.activate";
   public static String PROP_BEID_NET_INSTANTIATOR = "be.ehealth.technicalconnector.config.impl.ConfigurationModuleBeidNet.instantiator";
   public static String PROP_BEID_NET_INSTANTIATOR_DEFAULT = "be.fgov.ehealth.technicalconnector.beid.BeIdNetInstantiator";
   private static boolean initialized;

   public ConfigurationModuleBeidNet() {
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      log.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      if (ConfigUtils.isNet() && config.getBooleanProperty(PROP_BEID_NET_ACTIVATE, true) && !initialized) {
         String className = config.getProperty(PROP_BEID_NET_INSTANTIATOR, PROP_BEID_NET_INSTANTIATOR_DEFAULT);

         try {
            Class<BeIDInstantiator> clazz = Class.forName(className);
            BeIDInstantiator instantiator = (BeIDInstantiator)clazz.newInstance();
            BeIDFactory.setInstantiator(instantiator);
            initialized = true;
         } catch (Exception var5) {
            log.debug("Unable to register BeIDInstantiator [{}]", className, var5);
         }
      }

   }

   public void unload() throws TechnicalConnectorException {
      log.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      if (initialized) {
         BeIDFactory.setInstantiator((BeIDInstantiator)null);
      }

   }
}
