package be.ehealth.technicalconnector.handler.wss4j;

import be.ehealth.technicalconnector.config.ConfigFactory;
import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;

public class ECAlgorithmHelper implements AlgorithmHelper {
   public static final String PROP_DEFAULT_DIGEST_ALGO = "default.ec.digest.method.algorithm";
   public static final String PROP_DEFAULT_SIGNATURE_ALGO = "default.ec.signature.method.algorithm";

   public ECAlgorithmHelper() {
   }

   public boolean canHandle(Credential cred) {
      try {
         return "EC".equals(cred.getPrivateKey().getAlgorithm());
      } catch (TechnicalConnectorException var3) {
         return false;
      }
   }

   public String determineDigestAlgo(Credential cred) {
      return ConfigFactory.getConfigValidator().getProperty("default.ec.digest.method.algorithm", "http://www.w3.org/2001/04/xmlenc#sha256");
   }

   public String determineSignatureAlgorithm(Credential cred) {
      return ConfigFactory.getConfigValidator().getProperty("default.ec.signature.method.algorithm", "http://www.w3.org/2001/04/xmldsig-more#ecdsa-sha256");
   }
}
