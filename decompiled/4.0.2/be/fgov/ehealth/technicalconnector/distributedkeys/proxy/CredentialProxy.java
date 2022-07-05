package be.fgov.ehealth.technicalconnector.distributedkeys.proxy;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.ehealth.technicalconnector.service.sts.security.Credential;
import be.fgov.ehealth.technicalconnector.distributedkeys.DistributedSignerProxy;
import java.io.ByteArrayOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CredentialProxy implements DistributedSignerProxy {
   private static final byte[] SHA1_DIGEST_INFO_PREFIX = new byte[]{48, 33, 48, 9, 6, 5, 43, 14, 3, 2, 26, 5, 0, 4, 20};
   private static final byte[] SHA256_DIGEST_INFO_PREFIX = new byte[]{48, 49, 48, 13, 6, 9, 96, -122, 72, 1, 101, 3, 4, 2, 1, 5, 0, 4, 32};
   private static final byte[] SHA512_DIGEST_INFO_PREFIX = new byte[]{48, 81, 48, 13, 6, 9, 96, -122, 72, 1, 101, 3, 4, 2, 3, 5, 0, 4, 64};
   private static Map<String, byte[]> digestInfoPrefixes = new HashMap();
   private Credential credential;

   public CredentialProxy(Credential cred) {
      this.credential = cred;
   }

   public byte[] sign(byte[] digestValue, String digestAlgo, String alias) throws SignatureException {
      try {
         if ("RSA".equals(this.credential.getPrivateKey().getAlgorithm())) {
            Signature signature = Signature.getInstance("NONEwithRSA");
            signature.initSign(this.credential.getPrivateKey());
            ByteArrayOutputStream digestInfo = new ByteArrayOutputStream();
            if (!digestInfoPrefixes.containsKey(digestAlgo)) {
               throw new NoSuchAlgorithmException(digestAlgo);
            } else {
               byte[] digestInfoPrefix = (byte[])digestInfoPrefixes.get(digestAlgo);
               digestInfo.write(digestInfoPrefix);
               digestInfo.write(digestValue);
               signature.update(digestInfo.toByteArray());
               return signature.sign();
            }
         } else {
            throw new SignatureException("Unsupported key");
         }
      } catch (Exception var7) {
         throw new SignatureException(var7);
      }
   }

   public Set<String> getAliases() {
      try {
         return new HashSet(Collections.list(this.credential.getKeyStore().aliases()));
      } catch (Exception var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   public List<X509Certificate> getCertificateChain(String alias) {
      try {
         List<X509Certificate> result = new ArrayList();
         Certificate[] var3 = this.credential.getCertificateChain();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            Certificate cert = var3[var5];
            result.add((X509Certificate)cert);
         }

         return result;
      } catch (Exception var7) {
         throw new IllegalArgumentException(var7);
      }
   }

   public String getAlgorithm(String alias) {
      try {
         return this.credential.getPrivateKey().getAlgorithm();
      } catch (TechnicalConnectorException var3) {
         throw new IllegalArgumentException(var3);
      }
   }

   static {
      digestInfoPrefixes.put("SHA-1", SHA1_DIGEST_INFO_PREFIX);
      digestInfoPrefixes.put("SHA-256", SHA256_DIGEST_INFO_PREFIX);
      digestInfoPrefixes.put("SHA-512", SHA512_DIGEST_INFO_PREFIX);
   }
}
