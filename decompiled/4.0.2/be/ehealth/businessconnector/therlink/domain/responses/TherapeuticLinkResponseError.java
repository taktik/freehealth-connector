package be.ehealth.businessconnector.therlink.domain.responses;

public class TherapeuticLinkResponseError {
   private String errorCode;
   private String errorDescription;

   public TherapeuticLinkResponseError() {
   }

   public String getErrorCode() {
      return this.errorCode;
   }

   public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
   }

   public String getErrorDescription() {
      return this.errorDescription;
   }

   public void setErrorDescription(String errorDescription) {
      this.errorDescription = errorDescription;
   }

   public static class Builder {
      private TherapeuticLinkResponseError error = new TherapeuticLinkResponseError();

      public Builder() {
      }

      public Builder withErrorCode(String code) {
         this.error.setErrorCode(code);
         return this;
      }

      public Builder withErrorDescription(String desc) {
         this.error.setErrorDescription(desc);
         return this;
      }

      public TherapeuticLinkResponseError build() {
         return this.error;
      }
   }
}
