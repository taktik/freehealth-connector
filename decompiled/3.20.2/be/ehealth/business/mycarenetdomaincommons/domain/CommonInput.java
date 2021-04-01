package be.ehealth.business.mycarenetdomaincommons.domain;

public class CommonInput {
   private Boolean isTest;
   private Origin origin;
   private String inputReference;

   public CommonInput(Boolean isTest, Origin origin, String inputReference) {
      this.isTest = isTest;
      this.origin = origin;
      this.inputReference = inputReference;
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
}
