package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationModuleProxy implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleProxy.class);
   private static final String HTTP_PROXY_HOST = "http.proxyHost";
   private static final String HTTP_PROXY_PORT = "http.proxyPort";
   private static final String HTTP_PROXY_USER = "http.proxyUser";
   private static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
   private static final String HTTPS_PROXY_HOST = "https.proxyHost";
   private static final String HTTPS_PROXY_PORT = "https.proxyPort";
   private static final String HTTPS_PROXY_USER = "https.proxyUser";
   private static final String HTTPS_NON_PROXY_HOSTS = "http.nonProxyHosts";
   private static final String HTTPS_PROXY_PASSWORD = "https.proxyPassword";
   private static final String SOCKS_PROXY_PORT = "socksProxyPort";
   private static final String SOCKS_PROXY_HOST = "socksProxyHost";
   private Map<String, String> oldValues = new HashMap();

   private void loadProxyProperty(Configuration config, String key) {
      if (config.containsKey(key)) {
         String value = config.getProperty(key);
         if (value != null && !"".equals(value.trim())) {
            LOG.debug("Setting system property: " + key + " with value [" + value + "]");
            this.oldValues.put("proxySet", System.getProperty("proxySet"));
            this.oldValues.put(key, System.getProperty(key));
            System.getProperties().put("proxySet", "true");
            System.getProperties().put(key, value);
         } else {
            LOG.debug("Ignoring key[" + key + "]");
         }
      } else {
         LOG.debug("Config doesn't contain key [" + key + "]");
      }

   }

   public void init(Configuration config) {
      LOG.debug("Initializing ConfigurationModule " + this.getClass().getName());
      LOG.info("Initialise connector with proxy settings");
      this.oldValues.put("java.net.useSystemProxies", System.getProperty("java.net.useSystemProxies"));
      System.getProperties().put("java.net.useSystemProxies", "true");
      this.loadProxyProperty(config, "http.proxyHost");
      this.loadProxyProperty(config, "http.proxyPort");
      this.loadProxyProperty(config, "http.proxyUser");
      this.loadProxyProperty(config, "http.proxyPassword");
      this.loadProxyProperty(config, "https.proxyHost");
      this.loadProxyProperty(config, "https.proxyPort");
      this.loadProxyProperty(config, "https.proxyUser");
      this.loadProxyProperty(config, "https.proxyPassword");
      this.loadProxyProperty(config, "socksProxyHost");
      this.loadProxyProperty(config, "socksProxyPort");
      this.loadProxyProperty(config, "http.nonProxyHosts");
   }

   public void unload() {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      ConfigurationModuleLoader.unloadSystemProperties(this.oldValues);
   }
}
