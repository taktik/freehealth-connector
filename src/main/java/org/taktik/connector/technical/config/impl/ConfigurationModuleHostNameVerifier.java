package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleHostNameVerifier implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleHostNameVerifier.class);
   private HostnameVerifier oldHostNameVerifier;

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      LOG.warn("Activating bypass: Hostname verifcation. DO NOT USE THIS IN PRODUCTION.");
      this.oldHostNameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
      HttpsURLConnection.setDefaultHostnameVerifier(new ConfigurationModuleHostNameVerifier.BypassHostnameVerifier());
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      HttpsURLConnection.setDefaultHostnameVerifier(this.oldHostNameVerifier);
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private static class BypassHostnameVerifier implements HostnameVerifier {
      private BypassHostnameVerifier() {
      }

      public boolean verify(String hostname, SSLSession ssl) {
         ConfigurationModuleHostNameVerifier.LOG.warn("Using BypassHostnameVerifier to verify hostnames. Ignoring hostname checks for: " + hostname);
         return true;
      }

      // $FF: synthetic method
      BypassHostnameVerifier(ConfigurationModuleHostNameVerifier.SyntheticClass_1 x0) {
         this();
      }
   }
}
