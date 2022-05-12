package be.fgov.ehealth.technicalconnector.ra.domain;

import java.io.Serializable;

public class Certificate implements Serializable {
   private static final long serialVersionUID = 1L;
   private byte[] publicKeyIdentifier;
   private Boolean automaticallyValidated;
   private byte[] replacesCertificate;

   public Certificate() {
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public void setPublicKeyIdentifier(byte[] publicKeyIdentifier) {
      this.publicKeyIdentifier = publicKeyIdentifier;
   }

   public Boolean getAutomaticallyValidated() {
      return this.automaticallyValidated;
   }

   public void setAutomaticallyValidated(Boolean automaticallyValidated) {
      this.automaticallyValidated = automaticallyValidated;
   }

   public byte[] getReplacesCertificate() {
      return this.replacesCertificate;
   }

   public void setReplacesCertificate(byte[] replacesCertificate) {
      this.replacesCertificate = replacesCertificate;
   }
}
