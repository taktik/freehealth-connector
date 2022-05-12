package be.ehealth.businessconnector.mycarenet.attestv3.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import java.util.Arrays;
import java.util.List;

public class CancelAttestationRequestInput {
   private boolean isTest;
   private InputReference inputReference;
   private byte[] kmehrmessage;
   private List<Attribute> commonInputAttributes;
   private String issuer;
   private String messageVersion;

   CancelAttestationRequestInput(boolean isTest, InputReference inputReference, byte[] kmehrmessage, List<Attribute> commonInputAttributes, String issuer, String messageVersion) {
      this.isTest = isTest;
      this.inputReference = inputReference;
      this.kmehrmessage = kmehrmessage;
      this.commonInputAttributes = commonInputAttributes;
      this.issuer = issuer;
      this.messageVersion = messageVersion;
   }

   public static CancelAttestationRequestInputBuilder builder() {
      return new CancelAttestationRequestInputBuilder();
   }

   public boolean isTest() {
      return this.isTest;
   }

   public InputReference getInputReference() {
      return this.inputReference;
   }

   public byte[] getKmehrmessage() {
      return this.kmehrmessage;
   }

   public List<Attribute> getCommonInputAttributes() {
      return this.commonInputAttributes;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public String getMessageVersion() {
      return this.messageVersion;
   }

   public static class CancelAttestationRequestInputBuilder {
      private boolean isTest;
      private InputReference inputReference;
      private byte[] kmehrmessage;
      private List<Attribute> commonInputAttributes;
      private String issuer;
      private String messageVersion;

      CancelAttestationRequestInputBuilder() {
      }

      public CancelAttestationRequestInputBuilder isTest(boolean isTest) {
         this.isTest = isTest;
         return this;
      }

      public CancelAttestationRequestInputBuilder inputReference(InputReference inputReference) {
         this.inputReference = inputReference;
         return this;
      }

      public CancelAttestationRequestInputBuilder kmehrmessage(byte[] kmehrmessage) {
         this.kmehrmessage = kmehrmessage;
         return this;
      }

      public CancelAttestationRequestInputBuilder commonInputAttributes(List<Attribute> commonInputAttributes) {
         this.commonInputAttributes = commonInputAttributes;
         return this;
      }

      public CancelAttestationRequestInputBuilder issuer(String issuer) {
         this.issuer = issuer;
         return this;
      }

      public CancelAttestationRequestInputBuilder messageVersion(String messageVersion) {
         this.messageVersion = messageVersion;
         return this;
      }

      public CancelAttestationRequestInput build() {
         return new CancelAttestationRequestInput(this.isTest, this.inputReference, this.kmehrmessage, this.commonInputAttributes, this.issuer, this.messageVersion);
      }

      public String toString() {
         return "CancelAttestationRequestInput.CancelAttestationRequestInputBuilder(isTest=" + this.isTest + ", inputReference=" + this.inputReference + ", kmehrmessage=" + Arrays.toString(this.kmehrmessage) + ", commonInputAttributes=" + this.commonInputAttributes + ", issuer=" + this.issuer + ", messageVersion=" + this.messageVersion + ")";
      }
   }
}
