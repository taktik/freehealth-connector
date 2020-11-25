package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.config.ConfigurationModule;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.exception.TechnicalConnectorExceptionValues;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConfigurationModuleSSL implements ConfigurationModule {
   private static final String JAVAX_NET_DEBUG = "javax.net.debug";
   private static final String CONNECTOR_CONFIGURATIONMODULE_SSL_DEBUG = "connector.configurationmodule.ssl.debug";
   public static final String CONNECTOR_CONFIGURATIONMODULE_SSL_VERIFICATIONFLAVOUR = "connector.configurationmodule.ssl.verifcation.flavour";
   private static final Logger LOG = LoggerFactory.getLogger(ConfigurationModuleSSL.class);
   public static final String SSL_VERIFICATION_FLAVOUR_PASSTHROUGH = "passthrough";
   public static final String SSL_VERIFICATION_FLAVOUR_TRUSTSTORE = "truststore";
   private SSLSocketFactory oldDefaultSSLSocketFactory;

   public void init(Configuration config) throws TechnicalConnectorException {
      LOG.debug("Initializing ConfigurationModule {}", this.getClass().getName());

      try {
         this.oldDefaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
         SSLContext sc = SSLContext.getInstance("SSL");
         String sslVerifationFlavour = config.getProperty("connector.configurationmodule.ssl.verifcation.flavour", "truststore");
         TrustManager[] trustManagers;
         if ("passthrough".equals(sslVerifationFlavour)) {
            trustManagers = new TrustManager[]{TrustManagerFactory.passThroughTrustManager()};
         } else {
            if (!"truststore".equals(sslVerifationFlavour)) {
               throw new ConfigurationException("Unsupported SSL verifcation flavour " + sslVerifationFlavour);
            }

            trustManagers = new TrustManager[]{TrustManagerFactory.keystoreTrustManager(config)};
         }

         sc.init((KeyManager[])null, trustManagers, (SecureRandom)null);
         HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      } catch (Exception var5) {
         throw new TechnicalConnectorException(TechnicalConnectorExceptionValues.ERROR_GENERAL, var5, new Object[]{var5.getMessage()});
      }
   }

   public void unload() throws TechnicalConnectorException {
      LOG.debug("Unloading ConfigurationModule {}", this.getClass().getName());
      HttpsURLConnection.setDefaultSSLSocketFactory(this.oldDefaultSSLSocketFactory);
   }
}
