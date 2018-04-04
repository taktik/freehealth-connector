package org.taktik.connector.technical.enumeration;

public enum MimeType {
   plaintext("plain/text"),
   octectstream("application/octet-stream");

   private String value;

   private MimeType(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
