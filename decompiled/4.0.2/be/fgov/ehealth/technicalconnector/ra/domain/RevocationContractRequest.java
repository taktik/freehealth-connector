package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.exception.TechnicalConnectorException;
import be.fgov.ehealth.certra.core.v2.RevocationReasonType;
import java.io.Serializable;

public final class RevocationContractRequest extends Request implements Serializable {
   private static final long serialVersionUID = 1L;
   private byte[] publicKeyIdentifier;
   private Actor signer;
   private RevocationReasonType revocationReason;

   private RevocationContractRequest(Builder builder) throws TechnicalConnectorException {
      this.publicKeyIdentifier = builder.publicKeyIdentifier;
      this.signer = builder.signer;
      this.revocationReason = builder.revocationReason;
   }

   public static Builder newBuilder() {
      return new Builder();
   }

   public byte[] getPublicKeyIdentifier() {
      return this.publicKeyIdentifier;
   }

   public Actor getSigner() {
      return this.signer;
   }

   public RevocationReasonType getRevocationReason() {
      return this.revocationReason;
   }

   public static final class Builder {
      private byte[] publicKeyIdentifier;
      private Actor signer;
      private RevocationReasonType revocationReason;

      private Builder() {
      }

      public Builder publicKeyIdentifier(byte[] publicKeyIdentifier) {
         this.publicKeyIdentifier = publicKeyIdentifier;
         return this;
      }

      public Builder signer(Actor signer) {
         this.signer = signer;
         return this;
      }

      public Builder revocationReason(RevocationReasonType revocationReason) {
         this.revocationReason = revocationReason;
         return this;
      }

      public RevocationContractRequest build() throws TechnicalConnectorException {
         return new RevocationContractRequest(this);
      }
   }
}
