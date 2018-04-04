package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.Validate;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleBootstrap implements ConfigurationModule {
   private static List<ConfigurationModuleBootstrap.ModuleBootstrapHook> registry = new ArrayList();
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleBootstrap.class);
   public static final String DEFAULT_MODULEBOOTSTRAP_LOCATION = "META-INF/connector.bootstrap";
   public static final String PROP_MODULE_BOOTSTRAP = "org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.module";

   public static void register(ConfigurationModuleBootstrap.ModuleBootstrapHook hook) {
      registry.add(hook);
   }

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      Set<String> configuredModules = new HashSet();
      if (config != null) {
         configuredModules.addAll(config.getMatchingProperties("org.taktik.connector.technical.config.impl.ConfigurationModuleBootstrap.module"));
      }

      configuredModules.addAll(loadAllProperties("META-INF/connector.bootstrap"));
      Iterator i$ = configuredModules.iterator();

      while(i$.hasNext()) {
         String module = (String)i$.next();

         try {
            Class<ConfigurationModuleBootstrap.ModuleBootstrapHook> provider = (Class<ModuleBootstrapHook>) Class.forName(module);
            registry.add(provider.newInstance());
         } catch (Exception var6) {
            LOG.warn("Unable to load module [" + module + "]", var6);
         }
      }

      i$ = registry.iterator();

      while(i$.hasNext()) {
         ConfigurationModuleBootstrap.ModuleBootstrapHook module = (ConfigurationModuleBootstrap.ModuleBootstrapHook)i$.next();
         DateTime start = new DateTime();
         module.bootstrap();
         LOG.debug("ConfigurationModule [" + module.getClass() + "] loaded in " + new Duration(start, new DateTime()));
      }

   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
   }

   private static Set<String> loadAllProperties(String resourceName) throws TechnicalConnectorException {
      Set<String> result = new HashSet();
      Validate.notNull(resourceName, "Resource name must not be null");
      ClassLoader classLoaderToUse = getDefaultClassLoader();

      try {
         Enumeration urls = classLoaderToUse != null ? classLoaderToUse.getResources(resourceName) : ClassLoader.getSystemResources(resourceName);

         while(urls.hasMoreElements()) {
            processUrl(result, (URL)urls.nextElement());
         }
      } catch (Exception var4) {
         LOG.warn("Unable to obtain classloader", var4);
      }

      return result;
   }

   private static void processUrl(Set<String> result, URL url) {
      InputStream is = null;
      BufferedReader br = null;

      try {
         is = ConnectorIOUtils.getResourceAsStream(url.toExternalForm());
         br = new BufferedReader(new InputStreamReader(is));

         String strLine;
         while((strLine = br.readLine()) != null) {
            result.add(strLine);
         }
      } catch (Exception var8) {
         LOG.warn("Unable to load read file [" + url + "]", var8);
      } finally {
         ConnectorIOUtils.closeQuietly(br, is);
      }

   }

   private static ClassLoader getDefaultClassLoader() {
      ClassLoader cl = null;

      try {
         cl = Thread.currentThread().getContextClassLoader();
      } catch (Exception var3) {
         LOG.debug("Cannot access thread context ClassLoader - falling back...", var3);
      }

      if (cl == null) {
         LOG.debug("No thread context class loader -> use class loader of this class.");
         cl = ConfigurationModuleBootstrap.class.getClassLoader();
         if (cl == null) {
            LOG.debug("getClassLoader() returning null indicates the bootstrap ClassLoader");

            try {
               cl = ClassLoader.getSystemClassLoader();
            } catch (Exception var2) {
               LOG.debug("Cannot access system ClassLoader - oh well, maybe the caller can live with null...", var2);
            }
         }
      }

      return cl;
   }

   public interface ModuleBootstrapHook {
      void bootstrap();
   }
}
