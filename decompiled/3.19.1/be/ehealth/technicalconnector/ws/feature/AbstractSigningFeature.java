package be.ehealth.technicalconnector.ws.feature;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSigningFeature extends GenericFeature {
   public static final String ID = "be.ehealth.technicalconnector.ws.feature.signing";
   public static final String SIGNATURE_METHOD_ALGORITHM = "signature.method.algorithm";
   public static final String DIGEST_METHOD_ALGORITHM = "digest.method.algorithm";

   public AbstractSigningFeature() {
      super(true);
   }

   public String getID() {
      return "be.ehealth.technicalconnector.ws.feature.signing";
   }

   abstract String getSignatureMethodAlgorithm();

   abstract String getDigestMethodAlgorithm();

   public Map<String, Object> requestParamOptions() {
      HashMap<String, Object> options = new HashMap();
      options.put("signature.method.algorithm", this.getSignatureMethodAlgorithm());
      options.put("digest.method.algorithm", this.getDigestMethodAlgorithm());
      return options;
   }
}
