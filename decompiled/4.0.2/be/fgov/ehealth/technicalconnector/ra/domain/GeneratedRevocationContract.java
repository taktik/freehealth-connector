package be.fgov.ehealth.technicalconnector.ra.domain;

import be.fgov.ehealth.certra.core.v2.RevocationReasonType;
import java.io.Serializable;

public final class GeneratedRevocationContract implements Serializable {
   private static final long serialVersionUID = 1L;
   private String dn;
   private Actor signer;
   private LocalizedText text;
   private RevocationReasonType revocationReason;
   private boolean contractViewed;

   public GeneratedRevocationContract() {
   }

   public String getDn() {
      return this.dn;
   }

   public void setDn(String dn) {
      this.dn = dn;
   }

   public Actor getSigner() {
      return this.signer;
   }

   public void setSigner(Actor signer) {
      this.signer = signer;
   }

   public LocalizedText getText() {
      return this.text;
   }

   public void setText(LocalizedText text) {
      this.text = text;
   }

   public RevocationReasonType getRevocationReason() {
      return this.revocationReason;
   }

   public void setRevocationReason(RevocationReasonType revocationReason) {
      this.revocationReason = revocationReason;
   }

   public boolean isContractViewed() {
      return this.contractViewed;
   }

   public void setContractViewed(boolean contractViewed) {
      this.contractViewed = contractViewed;
   }
}
