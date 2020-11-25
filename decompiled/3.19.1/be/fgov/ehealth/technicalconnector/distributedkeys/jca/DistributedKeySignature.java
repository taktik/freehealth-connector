package be.fgov.ehealth.technicalconnector.distributedkeys.jca;

import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.SignatureSpi;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DistributedKeySignature extends SignatureSpi {
   private static final Logger LOG = LoggerFactory.getLogger(DistributedKeySignature.class);
   private static Map<String, String> digestAlgos = new HashMap();
   private final MessageDigest messageDigest;
   private DistributedPrivateKey privateKey;
   private Signature verifySignature;
   private final String signatureAlgorithm;
   private final ByteArrayOutputStream precomputedDigestOutputStream;

   DistributedKeySignature(String signatureAlgorithm) throws NoSuchAlgorithmException {
      LOG.debug("constructor: " + signatureAlgorithm);
      this.signatureAlgorithm = signatureAlgorithm;
      if (!digestAlgos.containsKey(signatureAlgorithm)) {
         LOG.error("no such algo: " + signatureAlgorithm);
         throw new NoSuchAlgorithmException(signatureAlgorithm);
      } else {
         String digestAlgo = (String)digestAlgos.get(signatureAlgorithm);
         if (null != digestAlgo) {
            this.messageDigest = MessageDigest.getInstance(digestAlgo);
            this.precomputedDigestOutputStream = null;
         } else {
            LOG.debug("NONE message digest");
            this.messageDigest = null;
            this.precomputedDigestOutputStream = new ByteArrayOutputStream();
         }

      }
   }

   protected void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
      LOG.debug("engineInitSign");
      if (!(privateKey instanceof DistributedPrivateKey)) {
         throw new InvalidKeyException();
      } else {
         this.privateKey = (DistributedPrivateKey)privateKey;
         if (null != this.messageDigest) {
            this.messageDigest.reset();
         }

      }
   }

   protected void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
      LOG.debug("engineInitVerify");
      if (null == this.verifySignature) {
         try {
            this.verifySignature = Signature.getInstance(this.signatureAlgorithm);
         } catch (NoSuchAlgorithmException var3) {
            throw new InvalidKeyException("no such algo: " + var3.getMessage(), var3);
         }
      }

      this.verifySignature.initVerify(publicKey);
   }

   protected void engineUpdate(byte b) throws SignatureException {
      this.messageDigest.update(b);
      if (null != this.verifySignature) {
         this.verifySignature.update(b);
      }

   }

   protected void engineUpdate(byte[] b, int off, int len) throws SignatureException {
      if (null != this.messageDigest) {
         this.messageDigest.update(b, off, len);
      }

      if (null != this.precomputedDigestOutputStream) {
         this.precomputedDigestOutputStream.write(b, off, len);
      }

      if (null != this.verifySignature) {
         this.verifySignature.update(b, off, len);
      }

   }

   protected byte[] engineSign() throws SignatureException {
      LOG.debug("engineSign");
      byte[] digestValue;
      String digestAlgo;
      if (null != this.messageDigest) {
         digestValue = this.messageDigest.digest();
         digestAlgo = this.messageDigest.getAlgorithm();
         if (this.signatureAlgorithm.endsWith("andMGF1")) {
            digestAlgo = digestAlgo + "-PSS";
         }
      } else {
         if (null == this.precomputedDigestOutputStream) {
            throw new SignatureException();
         }

         digestValue = this.precomputedDigestOutputStream.toByteArray();
         digestAlgo = "NONE";
      }

      return this.privateKey.sign(digestValue, digestAlgo);
   }

   protected boolean engineVerify(byte[] sigBytes) throws SignatureException {
      LOG.debug("engineVerify");
      if (null == this.verifySignature) {
         throw new SignatureException("initVerify required");
      } else {
         return this.verifySignature.verify(sigBytes);
      }
   }

   /** @deprecated */
   @Deprecated
   protected void engineSetParameter(String param, Object value) throws InvalidParameterException {
   }

   /** @deprecated */
   @Deprecated
   protected Object engineGetParameter(String param) throws InvalidParameterException {
      return null;
   }

   static {
      digestAlgos.put("SHA1withRSA", "SHA-1");
      digestAlgos.put("SHA224withRSA", "SHA-224");
      digestAlgos.put("SHA256withRSA", "SHA-256");
      digestAlgos.put("SHA384withRSA", "SHA-384");
      digestAlgos.put("SHA512withRSA", "SHA-512");
      digestAlgos.put("NONEwithRSA", (Object)null);
      digestAlgos.put("RIPEMD128withRSA", "RIPEMD128");
      digestAlgos.put("RIPEMD160withRSA", "RIPEMD160");
      digestAlgos.put("RIPEMD256withRSA", "RIPEMD256");
      digestAlgos.put("SHA1withRSAandMGF1", "SHA-1");
      digestAlgos.put("SHA256withRSAandMGF1", "SHA-256");
   }
}
