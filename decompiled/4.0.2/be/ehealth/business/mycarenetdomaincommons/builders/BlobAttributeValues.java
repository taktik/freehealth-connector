package be.ehealth.business.mycarenetdomaincommons.builders;

public class BlobAttributeValues {
   String id;
   String encodingType;
   String contentType;
   String contentEncryption;
   String issuer;
   String messageVersion;
   String messageName;

   BlobAttributeValues(String id, String encodingType, String contentType, String contentEncryption, String issuer, String messageVersion, String messageName) {
      this.id = id;
      this.encodingType = encodingType;
      this.contentType = contentType;
      this.contentEncryption = contentEncryption;
      this.issuer = issuer;
      this.messageVersion = messageVersion;
      this.messageName = messageName;
   }

   public static BlobAttributeValuesBuilder builder() {
      return new BlobAttributeValuesBuilder();
   }

   public String getId() {
      return this.id;
   }

   public String getEncodingType() {
      return this.encodingType;
   }

   public String getContentType() {
      return this.contentType;
   }

   public String getContentEncryption() {
      return this.contentEncryption;
   }

   public String getIssuer() {
      return this.issuer;
   }

   public String getMessageVersion() {
      return this.messageVersion;
   }

   public String getMessageName() {
      return this.messageName;
   }

   public static class BlobAttributeValuesBuilder {
      private String id;
      private String encodingType;
      private String contentType;
      private String contentEncryption;
      private String issuer;
      private String messageVersion;
      private String messageName;

      BlobAttributeValuesBuilder() {
      }

      public BlobAttributeValuesBuilder id(String id) {
         this.id = id;
         return this;
      }

      public BlobAttributeValuesBuilder encodingType(String encodingType) {
         this.encodingType = encodingType;
         return this;
      }

      public BlobAttributeValuesBuilder contentType(String contentType) {
         this.contentType = contentType;
         return this;
      }

      public BlobAttributeValuesBuilder contentEncryption(String contentEncryption) {
         this.contentEncryption = contentEncryption;
         return this;
      }

      public BlobAttributeValuesBuilder issuer(String issuer) {
         this.issuer = issuer;
         return this;
      }

      public BlobAttributeValuesBuilder messageVersion(String messageVersion) {
         this.messageVersion = messageVersion;
         return this;
      }

      public BlobAttributeValuesBuilder messageName(String messageName) {
         this.messageName = messageName;
         return this;
      }

      public BlobAttributeValues build() {
         return new BlobAttributeValues(this.id, this.encodingType, this.contentType, this.contentEncryption, this.issuer, this.messageVersion, this.messageName);
      }

      public String toString() {
         return "BlobAttributeValues.BlobAttributeValuesBuilder(id=" + this.id + ", encodingType=" + this.encodingType + ", contentType=" + this.contentType + ", contentEncryption=" + this.contentEncryption + ", issuer=" + this.issuer + ", messageVersion=" + this.messageVersion + ", messageName=" + this.messageName + ")";
      }
   }
}
