package be.ehealth.technicalconnector.config.impl;

import be.ehealth.technicalconnector.config.Configuration;
import be.ehealth.technicalconnector.exception.ConfigurationException;
import be.ehealth.technicalconnector.utils.ConnectorIOUtils;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrustManagerFactory {
   private static final String TRUSTSTORE_PASSWORD = "connector.configurationmodule.ssl.trustore.password";
   private static final String TRUSTSTORE_LOCATION = "connector.configurationmodule.ssl.trustore.location";
   private static final Logger LOG = LoggerFactory.getLogger(TrustManagerFactory.class);
   private static final String DEFAULT_ALGORITHM = "PKIX";

   public TrustManagerFactory() {
   }

   public static TrustManager passThroughTrustManager() {
      return new PassThroughTrustManager();
   }

   public static TrustManager keystoreTrustManager(Configuration config) {
      try {
         String trustStoreLocation = config.getProperty("connector.configurationmodule.ssl.trustore.location", "${KEYSTORE_DIR}${truststore_location}");
         String pwdString = config.getProperty("connector.configurationmodule.ssl.trustore.password", "${truststore_password}");
         char[] passwordCharArray = null;
         if (pwdString != null) {
            passwordCharArray = pwdString.toCharArray();
         }

         KeyStore trustStore = getStore(trustStoreLocation, passwordCharArray);
         dumpContext(trustStore, trustStoreLocation);
         javax.net.ssl.TrustManagerFactory factory = javax.net.ssl.TrustManagerFactory.getInstance("PKIX");
         factory.init(trustStore);
         return factory.getTrustManagers()[0];
      } catch (Exception var6) {
         throw new ConfigurationException(var6);
      }
   }

   private static KeyStore getStore(String storeLocation, char[] passwordCharArray) {
      InputStream is = null;

      KeyStore var4;
      try {
         KeyStore truststore = KeyStore.getInstance("JKS");
         is = ConnectorIOUtils.getResourceAsStream(storeLocation);
         truststore.load(is, passwordCharArray);
         var4 = truststore;
      } catch (Exception var8) {
         throw new ConfigurationException(var8);
      } finally {
         ConnectorIOUtils.closeQuietly((Object)is);
      }

      return var4;
   }

   private static void dumpContext(KeyStore store, String location) {
      try {
         LOG.debug("Content of KeyStore [{}]", location);
         List<String> aliases = Collections.list(store.aliases());
         Iterator var3 = aliases.iterator();

         while(var3.hasNext()) {
            String alias = (String)var3.next();
            Certificate cert = store.getCertificate(alias);
            X509Certificate x509Cert = (X509Certificate)cert;
            String dn = x509Cert.getSubjectX500Principal().getName("RFC2253");
            LOG.debug("\t.{}: {}", alias, dn);
         }

      } catch (Exception var8) {
         throw new ConfigurationException(var8);
      }
   }

   private static class PassThroughTrustManager implements X509TrustManager {
      private static final Logger LOG = LoggerFactory.getLogger(PassThroughTrustManager.class);

      private PassThroughTrustManager() {
      }

      public X509Certificate[] getAcceptedIssuers() {
         LOG.warn("SSL verifcation disabled! DO NOT USE THIS IN PRODUCTION.");
         LOG.debug("getAcceptedIssuers() : empty list");
         return new X509Certificate[0];
      }

      public void checkClientTrusted(X509Certificate[] certs, String authType) {
         LOG.debug("checkClientTrusted() : authType={}", authType);
      }

      public void checkServerTrusted(X509Certificate[] certs, String authType) {
         LOG.debug("checkServerTrusted() : authType={}", authType);

         for(int i = 0; i < certs.length; ++i) {
            LOG.debug("Server Certificate to be checked {} : {} with issuer: {}", new Object[]{i, certs[i].getSubjectX500Principal().getName("RFC1779"), certs[i].getIssuerX500Principal().getName("RFC1779")});
         }

      }
   }
}
