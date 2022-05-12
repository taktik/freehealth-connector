package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import be.fedict.commons.eid.jca.BeIDProvider;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedKeyProvider extends Provider {
   private static final String SIGNATURE = "Signature";
   private static final long serialVersionUID = 1L;
   public static final String NAME = "DistributedKeyProvider";
   private static final Logger LOG = LoggerFactory.getLogger(BeIDProvider.class);

   public DistributedKeyProvider() {
      super("DistributedKeyProvider", 1.0, "DistributedKey Provider");
      this.putService(new DistributedKeyService(this, "KeyStore", "DistributedKeyProvider", DistributedKeyStore.class.getName()));
      Map<String, String> signatureServiceAttributes = new HashMap();
      signatureServiceAttributes.put("SupportedKeyClasses", DistributedPrivateKey.class.getName());
      this.putService(new DistributedKeyService(this, "Signature", "SHA1withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA224withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA256withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA384withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA512withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "NONEwithRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "RIPEMD128withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "RIPEMD160withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "RIPEMD256withRSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA1withRSAandMGF1", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA256withRSAandMGF1", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA256withECDSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA384withECDSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "SHA512withECDSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
      this.putService(new DistributedKeyService(this, "Signature", "NONEwithECDSA", DistributedKeySignature.class.getName(), signatureServiceAttributes));
   }

   private static final class DistributedKeyService extends Provider.Service {
      public DistributedKeyService(Provider provider, String type, String algorithm, String className) {
         super(provider, type, algorithm, className, (List)null, (Map)null);
      }

      public DistributedKeyService(Provider provider, String type, String algorithm, String className, Map<String, String> attributes) {
         super(provider, type, algorithm, className, (List)null, attributes);
      }

      public Object newInstance(Object constructorParameter) throws NoSuchAlgorithmException {
         DistributedKeyProvider.LOG.debug("newInstance: " + super.getType());
         return super.getType().equals("Signature") ? new DistributedKeySignature(this.getAlgorithm()) : super.newInstance(constructorParameter);
      }

      public boolean supportsParameter(Object parameter) {
         DistributedKeyProvider.LOG.debug("supportedParameter: " + parameter);
         return super.supportsParameter(parameter);
      }
   }
}
