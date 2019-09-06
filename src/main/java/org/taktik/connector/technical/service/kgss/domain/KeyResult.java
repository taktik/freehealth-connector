package org.taktik.connector.technical.service.kgss.domain;

import javax.crypto.SecretKey;

public class KeyResult {
   private SecretKey secretKey;
   private String keyId;

   public KeyResult(SecretKey secretKey, String keyId) {
      this.secretKey = secretKey;
      this.keyId = keyId;
   }

   public SecretKey getSecretKey() {
      return this.secretKey;
   }

   public void setSecretKey(SecretKey secretKey) {
      this.secretKey = secretKey;
   }

   public String getKeyId() {
      return this.keyId;
   }

   public void setKeyId(String keyId) {
      this.keyId = keyId;
   }

   public SerializableKeyResult toSerializableKeyResult() {
      return new SerializableKeyResult(this.secretKey.getEncoded(), this.keyId);
   }
}
