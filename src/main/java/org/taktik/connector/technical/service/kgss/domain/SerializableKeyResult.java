package org.taktik.connector.technical.service.kgss.domain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;

public class SerializableKeyResult implements Serializable {
   private byte[] secretKey;
   private String keyId;

   public SerializableKeyResult(byte[] secretKey, String keyId) {
      this.secretKey = secretKey;
      this.keyId = keyId;
   }

   public byte[] getSecretKey() {
      return this.secretKey;
   }

   public void setSecretKey(byte[] secretKey) {
      this.secretKey = secretKey;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String keyId) {
      this.keyId = keyId;
   }

   public KeyResult toKeyResult() {
      return new KeyResult(new SecretKeySpec(this.secretKey, "AES"), this.keyId);
   }
}
