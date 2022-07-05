package be.ehealth.businessconnector.ehbox.api.utils;

import javax.activation.DataHandler;

public class SigningValue {
   private String signingDownloadFileName;
   private String textSigningContent;
   private DataHandler binarySigningContent;
   private String signingType;

   public SigningValue() {
   }

   public String getSigningDownloadFileName() {
      return this.signingDownloadFileName;
   }

   public void setSigningDownloadFileName(String signingDownloadFileName) {
      this.signingDownloadFileName = signingDownloadFileName;
   }

   public String getTextSigningContent() {
      return this.textSigningContent;
   }

   public void setTextSigningContent(String textSigningContent) {
      this.textSigningContent = textSigningContent;
   }

   public DataHandler getBinarySigningContent() {
      return this.binarySigningContent;
   }

   public void setBinarySigningContent(DataHandler binarySigningContent) {
      this.binarySigningContent = binarySigningContent;
   }

   public String getSigningType() {
      return this.signingType;
   }

   public void setSigningType(String signingType) {
      this.signingType = signingType;
   }
}
