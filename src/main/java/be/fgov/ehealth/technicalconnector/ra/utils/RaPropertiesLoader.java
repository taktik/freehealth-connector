package be.fgov.ehealth.technicalconnector.ra.utils;

import org.taktik.connector.technical.exception.ConfigurationException;
import org.taktik.connector.technical.utils.ConnectorIOUtils;
import java.util.Properties;

public class RaPropertiesLoader {
   public static final String SIGNATURE_ALGORITHM = "csr.signature.algorithm";
   public static final String AUTH_KEY_SIZE = "authentication.key.size";
   public static final String AUTH_KEY_ALGO = "authentication.key.algorithm";
   public static final String AUTH_KEY_ALGO_OID = "authentication.key.algorithm.oid";
   public static final String DUMMYCERT_SUBJECT = "dummycert.subject";
   public static final String DUMMYCERT_SIGNATURE_ALGORITHM = "dummycert.signature.algorithm";
   public static final String ETK_CHALLENGE_DIGEST = "etk.challenge.digest";
   public static final String ETK_CHALLENGE_CIPHER = "etk.challenge.cipher";
   private static Properties props = new Properties();

   public static String getProperty(String key) {
      return props.getProperty(key);
   }

   public static String getProperty(String key, String defaultValue) {
      return props.getProperty(key, defaultValue);
   }

   static {
      try {
         props.load(ConnectorIOUtils.getResourceAsStream("/ra.properties"));
      } catch (Exception var1) {
         throw new ConfigurationException(var1);
      }
   }
}
