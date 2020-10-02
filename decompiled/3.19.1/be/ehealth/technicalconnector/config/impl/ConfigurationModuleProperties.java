package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleProperties implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleProperties.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      if (LOG.isDebugEnabled()) {
         LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
         if (config instanceof ConfigurationImpl) {
            Properties properties = ((ConfigurationImpl)config).getProperties();
            LOG.debug("Current properties are : ");
            Iterator i$ = properties.entrySet().iterator();

            while(i$.hasNext()) {
               Entry<Object, Object> entry = (Entry)i$.next();
               LOG.debug(" .{} = [{}]", entry.getKey(), entry.getValue());
            }
         }
      }

   }

   public void unload() {
   }
}
