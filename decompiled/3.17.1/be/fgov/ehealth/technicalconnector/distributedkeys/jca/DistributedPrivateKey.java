package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import be.fgov.ehealth.technicalconnector.distributedkeys.DistributedSignerProxy;
import java.security.PrivateKey;
import java.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedPrivateKey implements PrivateKey {
   private static final long serialVersionUID = 1L;
   private static final Logger LOG = LoggerFactory.getLogger(DistributedPrivateKey.class);
   private DistributedSignerProxy proxy;
   private String alias;

   DistributedPrivateKey(DistributedSignerProxy proxy, String alias) {
      this.proxy = proxy;
      this.alias = alias;
   }

   public String getAlgorithm() {
      return "RSA";
   }

   public String getFormat() {
      return null;
   }

   public byte[] getEncoded() {
      return null;
   }

   byte[] sign(byte[] digestValue, String digestAlgo) throws SignatureException {
      LOG.debug("Invoking DistributedSigner");
      return this.proxy.sign(digestValue, digestAlgo, this.alias);
   }
}
