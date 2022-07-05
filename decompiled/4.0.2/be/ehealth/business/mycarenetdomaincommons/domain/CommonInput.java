package be.ehealth.business.mycarenetdomaincommons.domain;

import java.util.List;

public class CommonInput {
   private Boolean isTest;
   private Origin origin;
   private String inputReference;
   private List<Reference> references;
   private List<Attribute> attributes;

   public CommonInput(Boolean isTest, Origin origin, String inputReference) {
      this.isTest = isTest;
      this.origin = origin;
      this.inputReference = inputReference;
   }

   public CommonInput(Boolean isTest, Origin origin, String inputReference, List<Reference> references, List<Attribute> attributes) {
      this.isTest = isTest;
      this.origin = origin;
      this.inputReference = inputReference;
      this.references = references;
      this.attributes = attributes;
   }

   public Boolean isTest() {
      return this.isTest;
   }

   public void setIsTest(Boolean isTest) {
      this.isTest = isTest;
   }

   public Origin getOrigin() {
      return this.origin;
   }

   public String getInputReference() {
      return this.inputReference;
   }

   public List<Reference> getReferences() {
      return this.references;
   }

   public List<Attribute> getAttributes() {
      return this.attributes;
   }
}
