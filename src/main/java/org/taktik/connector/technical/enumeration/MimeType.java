package org.taktik.connector.technical.enumeration;

public enum MimeType {
   @Deprecated
   plaintext("plain/text"),
   PLAIN_TEXT("plain/text"),
   @Deprecated
   octetstream("application/octet-stream"),
   OCTET_STREAM("application/octet-stream");

   private String value;

   private MimeType(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
