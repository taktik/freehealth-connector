package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationModuleSSLVerifier implements ConfigurationModule {
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSSLVerifier.class);
   private SSLSocketFactory oldSSLSocketFactory;

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());
      LOG.warn("Activating bypass: SSL verifcation. DO NOT USE THIS IN PRODUCTION.");
      TrustManager[] trustAllCerts = new TrustManager[]{new ConfigurationModuleSSLVerifier.ConnectorTrustManager()};

      try {
         SSLContext sc = SSLContext.getInstance("SSL");
         sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
         this.oldSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
         HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      } catch (Exception var4) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var4, new Object[]{var4.getMessage()});
      }
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      HttpsURLConnection.setDefaultSSLSocketFactory(this.oldSSLSocketFactory);
   }

   private static class ConnectorTrustManager implements X509TrustManager {
      private ConnectorTrustManager() {
      }

      public X509Certificate[] getAcceptedIssuers() {
         ConfigurationModuleSSLVerifier.LOG.warn("SSL verifcation disabled! DO NOT USE THIS IN PRODUCTION.");
         ConfigurationModuleSSLVerifier.LOG.debug("getAcceptedIssuers() : empty list");
         return new X509Certificate[0];
      }

      public void checkClientTrusted(X509Certificate[] certs, String authType) {
         ConfigurationModuleSSLVerifier.LOG.debug("checkClientTrusted() : authType={}", authType);
      }

      public void checkServerTrusted(X509Certificate[] certs, String authType) {
         ConfigurationModuleSSLVerifier.LOG.debug("checkServerTrusted() : authType={}", authType);

         for(int i = 0; i < certs.length; ++i) {
            ConfigurationModuleSSLVerifier.LOG.debug("Server Certificate to be checked {} : {} with issuer: {}", new Object[]{i, certs[i].getSubjectX500Principal().getName("RFC1779"), certs[i].getIssuerX500Principal().getName("RFC1779")});
         }

      }

      // $FF: synthetic method
      ConnectorTrustManager(Object x0) {
         this();
      }
   }
}
