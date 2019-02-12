package be.apb.gfddpp.common.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SSLUtil {
   private static final Logger LOG = LogManager.getLogger(SSLUtil.class);

   public static void trustAllCertificates() {
      try {
         LOG.debug("Trusting All Server Certificates");
         HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession sslSession) {
               SSLUtil.LOG.debug("Https HostnameVerifier invoked for '" + hostname + "'");
               return true;
            }
         });
         TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
               SSLUtil.LOG.debug("getAcceptedIssuers() : empty list");
               return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
               SSLUtil.LOG.debug("checkClientTrusted() : authType=" + authType);
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
               if (SSLUtil.LOG.isDebugEnabled()) {
                  SSLUtil.LOG.debug("checkServerTrusted() : authType=" + authType);

                  for(int i = 0; i < certs.length; ++i) {
                     SSLUtil.LOG.debug("Server Certificate to be checked " + i + " : " + certs[i].getIssuerDN());
                  }
               }

            }
         }};
         SSLContext sc = SSLContext.getInstance("SSL");
         sc.init((KeyManager[])null, trustAllCerts, new SecureRandom());
         HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
      } catch (NoSuchAlgorithmException var2) {
         throw new IllegalArgumentException(var2);
      } catch (KeyManagementException var3) {
         throw new IllegalArgumentException(var3);
      }
   }
}
