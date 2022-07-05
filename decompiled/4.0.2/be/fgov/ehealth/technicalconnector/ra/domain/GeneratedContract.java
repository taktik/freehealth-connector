package be.fgov.ehealth.technicalconnector.ra.domain;

import be.ehealth.technicalconnector.utils.IdentifierType;
import java.io.Serializable;

public final class GeneratedContract implements Serializable {
   private static final long serialVersionUID = 1L;
   private String dn;
   private Actor signer;
   private LocalizedText text;
   private ContactData contactData;
   private IdentifierType identifierType;
   private boolean contractViewed;

   public GeneratedContract() {
   }

   public String getDN() {
      return this.dn;
   }

   public void setDN(String dn) {
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

   public ContactData getContactData() {
      return this.contactData;
   }

   public void setContactData(ContactData contactData) {
      this.contactData = contactData;
   }

   public IdentifierType getIdentifierType() {
      return this.identifierType;
   }

   public void setIdentifierType(IdentifierType identifierType) {
      this.identifierType = identifierType;
   }

   public boolean isContractViewed() {
      return this.contractViewed;
   }

   public void setContractViewed(boolean contractViewed) {
      this.contractViewed = contractViewed;
   }
}
