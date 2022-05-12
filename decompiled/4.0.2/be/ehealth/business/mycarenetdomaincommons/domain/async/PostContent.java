package be.ehealth.business.mycarenetdomaincommons.domain.async;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;

public class PostContent {
   private Blob blob;
   private String messageName;
   private String commonInputReference;
   private byte[] xades;
   private boolean isTest;

   private PostContent(Builder builder) {
      this.blob = builder.blob;
      this.messageName = builder.messageName;
      this.commonInputReference = builder.commonInputReference;
      this.xades = builder.xades;
      this.isTest = builder.isTest;
   }

   public static Builder Builder() {
      return new Builder();
   }

   public Blob getBlob() {
      return this.blob;
   }

   public String getMessageName() {
      return this.messageName;
   }

   public String getCommonInputReference() {
      return this.commonInputReference;
   }

   public byte[] getXades() {
      return this.xades;
   }

   public boolean isTest() {
      return this.isTest;
   }

   public static final class Builder {
      private Blob blob;
      private String messageName;
      private String commonInputReference;
      private byte[] xades;
      private boolean isTest;

      private Builder() {
      }

      public Builder blob(Blob blob) {
         this.blob = blob;
         return this;
      }

      public Builder messageName(String messageName) {
         this.messageName = messageName;
         return this;
      }

      public Builder commonInputReference(String commonInputReference) {
         this.commonInputReference = commonInputReference;
         return this;
      }

      public Builder xades(byte[] xades) {
         this.xades = xades;
         return this;
      }

      public Builder isTest(boolean isTest) {
         this.isTest = isTest;
         return this;
      }

      public PostContent build() {
         return new PostContent(this);
      }
   }
}
