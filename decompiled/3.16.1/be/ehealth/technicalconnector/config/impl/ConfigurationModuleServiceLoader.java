package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import java.lang.reflect.Method;
import java.security.CodeSource;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleServiceLoader implements ConfigurationModule {
   public static final String SERVICELOADER_ROOTKEY = "configurationmodule.serviceloader";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleServiceLoader.class);

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      if (LOG.isDebugEnabled()) {
         List<String> serviceLoaders = config.getMatchingProperties("configurationmodule.serviceloader");
         Iterator i$ = serviceLoaders.iterator();

         while(i$.hasNext()) {
            String serviceLoader = (String)i$.next();
            String[] splittedServiceLoader = serviceLoader.split(":", 2);

            try {
               Class provider = Class.forName(splittedServiceLoader[0]);
               Object result = null;
               Method method;
               if (splittedServiceLoader.length == 1) {
                  method = provider.getMethod("newInstance");
                  result = method.invoke(provider);
               } else {
                  if (splittedServiceLoader.length != 2) {
                     LOG.debug("Unsupported serviceLoader value [" + serviceLoader + "].");
                     break;
                  }

                  method = provider.getMethod("newInstance", String.class);
                  result = method.invoke(provider, splittedServiceLoader[1]);
               }

               CodeSource source = result.getClass().getProtectionDomain().getCodeSource();
               LOG.debug(MessageFormat.format("{0} implementation: {1} loaded from: {2}", splittedServiceLoader[0], result.getClass().getName(), source == null ? "Java Runtime" : source.getLocation()));
            } catch (Exception var9) {
               LOG.debug(var9.getClass().getSimpleName() + ": " + var9.getMessage(), var9);
            }
         }

      }
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
   }
}
