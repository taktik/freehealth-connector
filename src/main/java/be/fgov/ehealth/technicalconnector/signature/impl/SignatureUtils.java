package be.fgov.ehealth.technicalconnector.signature.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import org.apache.xml.security.algorithms.JCEMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SignatureUtils {
   private static final Logger LOG = LoggerFactory.getLogger(SignatureUtils.class);

   private SignatureUtils() {
      throw new UnsupportedOperationException();
   }

   public static <T> T getOption(String key, Map<String, Object> optionMap, T defaultValue) {
      T result = defaultValue;
      if (optionMap == null) {
         return defaultValue;
      } else {
         if (optionMap.containsKey(key) && optionMap.get(key) != null) {
            result = (T) optionMap.get(key);
         }

         LOG.debug("Using the following " + key + " : [" + result + "]");
         return result;
      }
   }

   public static MessageDigest getDigestInstance(String algorithmURI) throws NoSuchAlgorithmException {
      String algorithmID = JCEMapper.translateURItoJCEID(algorithmURI);
      if (algorithmID == null) {
         throw new NoSuchAlgorithmException("Could not translate algorithmURI [" + algorithmURI + "]");
      } else {
         String provider = JCEMapper.getProviderId();

         try {
            MessageDigest md;
            if (provider == null) {
               md = MessageDigest.getInstance(algorithmID);
            } else {
               md = MessageDigest.getInstance(algorithmID, provider);
            }

            return md;
         } catch (NoSuchProviderException var5) {
            throw new NoSuchAlgorithmException("Could not find provider for [" + algorithmID + "]", var5);
         }
      }
   }
}
