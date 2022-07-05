package be.ehealth.businessconnector.mycarenet.attestv3.builders;

import be.ehealth.business.mycarenetdomaincommons.domain.Attribute;
import be.ehealth.business.mycarenetdomaincommons.domain.InputReference;
import be.ehealth.business.mycarenetdomaincommons.domain.Ssin;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class SendAttestationRequestInput {
   private boolean isTest;
   private InputReference inputReference;
   private Ssin patientSsin;
   private LocalDateTime referenceDate;
   private byte[] kmehrmessage;
   private List<Attribute> commonInputAttributes;
   private String issuer;
   private String messageVersion;

   SendAttestationRequestInput(boolean isTest, InputReference inputReference, Ssin patientSsin, LocalDateTime referenceDate, byte[] kmehrmessage, List<Attribute> commonInputAttributes, String issuer, String messageVersion) {
      this.isTest = isTest;
      this.inputReference = inputReference;
      this.patientSsin = patientSsin;
      this.referenceDate = referenceDate;
      this.kmehrmessage = kmehrmessage;
      this.commonInputAttributes = commonInputAttributes;
      this.issuer = issuer;
      this.messageVersion = messageVersion;
   }

   public static SendAttestationRequestInputBuilder builder() {
      return new SendAttestationRequestInputBuilder();
   }

   public boolean isTest() {
      return this.isTest;
   }

   public InputReference getInputReference() {
      return this.inputReference;
   }

   public Ssin getPatientSsin() {
      return this.patientSsin;
   }

   public LocalDateTime getReferenceDate() {
      return this.referenceDate;
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

   public static class SendAttestationRequestInputBuilder {
      private boolean isTest;
      private InputReference inputReference;
      private Ssin patientSsin;
      private LocalDateTime referenceDate;
      private byte[] kmehrmessage;
      private List<Attribute> commonInputAttributes;
      private String issuer;
      private String messageVersion;

      SendAttestationRequestInputBuilder() {
      }

      public SendAttestationRequestInputBuilder isTest(boolean isTest) {
         this.isTest = isTest;
         return this;
      }

      public SendAttestationRequestInputBuilder inputReference(InputReference inputReference) {
         this.inputReference = inputReference;
         return this;
      }

      public SendAttestationRequestInputBuilder patientSsin(Ssin patientSsin) {
         this.patientSsin = patientSsin;
         return this;
      }

      public SendAttestationRequestInputBuilder referenceDate(LocalDateTime referenceDate) {
         this.referenceDate = referenceDate;
         return this;
      }

      public SendAttestationRequestInputBuilder kmehrmessage(byte[] kmehrmessage) {
         this.kmehrmessage = kmehrmessage;
         return this;
      }

      public SendAttestationRequestInputBuilder commonInputAttributes(List<Attribute> commonInputAttributes) {
         this.commonInputAttributes = commonInputAttributes;
         return this;
      }

      public SendAttestationRequestInputBuilder issuer(String issuer) {
         this.issuer = issuer;
         return this;
      }

      public SendAttestationRequestInputBuilder messageVersion(String messageVersion) {
         this.messageVersion = messageVersion;
         return this;
      }

      public SendAttestationRequestInput build() {
         return new SendAttestationRequestInput(this.isTest, this.inputReference, this.patientSsin, this.referenceDate, this.kmehrmessage, this.commonInputAttributes, this.issuer, this.messageVersion);
      }

      public String toString() {
         return "SendAttestationRequestInput.SendAttestationRequestInputBuilder(isTest=" + this.isTest + ", inputReference=" + this.inputReference + ", patientSsin=" + this.patientSsin + ", referenceDate=" + this.referenceDate + ", kmehrmessage=" + Arrays.toString(this.kmehrmessage) + ", commonInputAttributes=" + this.commonInputAttributes + ", issuer=" + this.issuer + ", messageVersion=" + this.messageVersion + ")";
      }
   }
}
