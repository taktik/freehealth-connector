package org.taktik.connector.technical.config.impl;

import org.taktik.connector.technical.config.Configuration;
import org.taktik.connector.technical.config.ConfigurationModule;
import org.taktik.connector.technical.exception.TechnicalConnectorException;
import org.taktik.connector.technical.exception.TechnicalConnectorExceptionValues;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationModuleSSL implements ConfigurationModule {
   private static final String JAVAX_NET_DEBUG = "javax.net.debug";
   private static final String CONNECTOR_CONFIGURATIONMODULE_SSL_DEBUG = "connector.configurationmodule.ssl.debug";
   private static final String JAVAX_NET_SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
   private static final String JAVAX_NET_SSL_TRUST_STORE = "javax.net.ssl.trustStore";
   private static final String TRUSTSTORE_PASSWORD = "truststore_password";
   private static final String KEYSTORE_DIR = "KEYSTORE_DIR";
   private static final String TRUSTSTORE_LOCATION = "truststore_location";
   private static final String TRUSTSTORE_LOCATION_ORIGINAL = "truststore_location_original";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSSL.class);
   private Map<String, String> oldValues = new HashMap<>();

   public void init(Configuration config) throws TechnicalConnectorException {
      if (this.isValid(config)) {
         this.oldValues.put("javax.net.ssl.trustStore", System.getProperty("javax.net.ssl.trustStore"));
         this.oldValues.put("javax.net.ssl.trustStorePassword", System.getProperty("javax.net.ssl.trustStorePassword"));
         System.setProperty("javax.net.ssl.trustStore", config.getProperty("truststore_location"));
         System.setProperty("javax.net.ssl.trustStorePassword", config.getProperty("truststore_password"));
      }

      String debugssl = config.getProperty("connector.configurationmodule.ssl.debug", "false");
      if ("true".equals(debugssl)) {
         this.oldValues.put("javax.net.debug", System.getProperty("javax.net.debug"));
         System.setProperty("javax.net.debug", "all");
      }

      this.verifyTrustStore();
   }

   private void verifyTrustStore() throws TechnicalConnectorException {
      String trustStoreFilePath = System.getProperty("javax.net.ssl.trustStore");
      String location = this.getTrustStoreLocation(trustStoreFilePath);
      if (!StringUtils.isEmpty(location)) {
         InputStream is = null;

         try {
            KeyStore truststore = KeyStore.getInstance("JKS");
            char[] passwordCharArray = new char[0];
            String password = System.getProperty("javax.net.ssl.trustStorePassword");
            if (password != null) {
               passwordCharArray = password.toCharArray();
            }

            is = ConnectorIOUtils.getResourceAsStream(location);
            truststore.load(is, passwordCharArray);
            List<String> aliases = Collections.list(truststore.aliases());
            LOG.debug("Content of truststore at location: " + location);
            Iterator i$ = aliases.iterator();

            while(i$.hasNext()) {
               String alias = (String)i$.next();
               Certificate cert = truststore.getCertificate(alias);
               X509Certificate x509Cert = (X509Certificate)cert;
               String dn = x509Cert.getSubjectX500Principal().getName("RFC2253");
               LOG.debug("\t." + alias + " :" + dn);
            }
         } catch (Exception var16) {
            LOG.warn(var16.getClass().getSimpleName() + ":" + var16.getMessage());
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, var16, var16.getMessage());
         } finally {
            ConnectorIOUtils.closeQuietly(is);
         }

      }
   }

   private boolean isValid(Configuration config) throws TechnicalConnectorException {
      boolean valid = false;
      String keyStoreDir = config.getProperty("KEYSTORE_DIR");
      String trustStoreName = config.getProperty("truststore_location_original", "${truststore_location}");
      config.setProperty("truststore_location_original", trustStoreName);
      if (keyStoreDir == null) {
         LOG.warn("Missing property : KEYSTORE_DIR");
      }

      if (trustStoreName == null) {
         LOG.warn("Missing property : truststore_location");
      }

      if (config.getProperty("truststore_password") == null) {
         LOG.warn("Missing property : truststore_password");
      }

      if (keyStoreDir != null && trustStoreName != null) {
         String trustStoreLocation = trustStoreName;
         trustStoreLocation = this.getTrustStoreLocation(trustStoreLocation);
         if (trustStoreLocation != null) {
            valid = true;
            config.setProperty("truststore_location", trustStoreLocation);
            if (!trustStoreLocation.equals(config.getProperty("truststore_location"))) {
               throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, "could not update configuration parameter truststore_location : config still returns old location ]" + config.getProperty("truststore_location") + "[ after update to location ]" + trustStoreLocation + "[");
            }
         }
      }

      return valid;
   }

   private String getTrustStoreLocation(String trustStoreLocation) {
      InputStream is = null;
      FileOutputStream fos = null;

      String var5;
      try {
         is = ConnectorIOUtils.getResourceAsStream(trustStoreLocation);
         LOG.debug("ConfigurationModuleSSL.getTrustStoreLocation: loading file on location [" + trustStoreLocation + "]");
         if (is == null) {
            throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_CONFIG, "trustStore file doesn't exist or is not a file on location [" + trustStoreLocation + "] and resourceFilePath [" + trustStoreLocation + "]");
         }

         LOG.debug("Creating new temp trustStore");
         File tempTrust = File.createTempFile("javax.net.ssl.trustStore", ".jks");
         tempTrust.deleteOnExit();
         LOG.debug("Creating new temp trustStore on path [" + tempTrust.getPath() + "] with filename [" + tempTrust.getName() + "]");
         fos = new FileOutputStream(tempTrust);
         IOUtils.copy(is, fos);
         var5 = tempTrust.getPath();
         return var5;
      } catch (IOException var10) {
         LOG.error(var10.getMessage());
         var5 = null;
         return var5;
      } catch (TechnicalConnectorException var11) {
         LOG.error(var11.getMessage());
         var5 = null;
      } finally {
         ConnectorIOUtils.closeQuietly(is, fos);
      }

      return var5;
   }

   public void unload() {
      LOG.debug("Unloading ConfigurationModule " + this.getClass().getName());
      ConfigurationModuleLoader.unloadSystemProperties(this.oldValues);
   }
}
