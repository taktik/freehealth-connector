package be.ehealth.business.mycarenetdomaincommons.domain.async;

import be.ehealth.business.mycarenetdomaincommons.domain.Blob;

public class PostContent {
   private Blob blob;
   private String messageName;
   private String commonInputReference;
   private byte[] xades;
   private boolean isTest;

   private PostContent(PostContent.Builder builder) {
      this.blob = builder.blob;
      this.messageName = builder.messageName;
      this.commonInputReference = builder.commonInputReference;
      this.xades = builder.xades;
      this.isTest = builder.isTest;
   }

   public static PostContent.Builder Builder() {
      return new PostContent.Builder();
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

   // $FF: synthetic method
   PostContent(PostContent.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private Blob blob;
      private String messageName;
      private String commonInputReference;
      private byte[] xades;
      private boolean isTest;

      private Builder() {
      }

      public PostContent.Builder blob(Blob blob) {
         this.blob = blob;
         return this;
      }

      public PostContent.Builder messageName(String messageName) {
         this.messageName = messageName;
         return this;
      }

      public PostContent.Builder commonInputReference(String commonInputReference) {
         this.commonInputReference = commonInputReference;
         return this;
      }

      public PostContent.Builder xades(byte[] xades) {
         this.xades = xades;
         return this;
      }

      public PostContent.Builder isTest(boolean isTest) {
         this.isTest = isTest;
         return this;
      }

      public PostContent build() {
         return new PostContent(this);
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
