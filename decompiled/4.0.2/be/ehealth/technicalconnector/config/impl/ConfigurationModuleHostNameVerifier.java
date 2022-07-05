package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleHostNameVerifier implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleHostNameVerifier.class);
   private HostnameVerifier oldHostNameVerifier;

   public ConfigurationModuleHostNameVerifier() {
   }

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      LOG.warn("Activating bypass: Hostname verifcation. DO NOT USE THIS IN PRODUCTION.");
      this.oldHostNameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
      HttpsURLConnection.setDefaultHostnameVerifier(new BypassHostnameVerifier());
   }

   public void unload() {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      HttpsURLConnection.setDefaultHostnameVerifier(this.oldHostNameVerifier);
   }

   private static class BypassHostnameVerifier implements HostnameVerifier {
      private BypassHostnameVerifier() {
      }

      public boolean verify(String hostname, SSLSession ssl) {
         ConfigurationModuleHostNameVerifier.LOG.warn("Using BypassHostnameVerifier to verify hostnames. Ignoring hostname checks for: {}", hostname);
         return true;
      }
   }
}
