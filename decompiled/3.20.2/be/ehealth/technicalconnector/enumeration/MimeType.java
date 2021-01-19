package be.ehealth.technicalconnector.enumeration;

public enum MimeType {
   /** @deprecated */
   @Deprecated
   plaintext("plain/text"),
   PLAIN_TEXT("plain/text"),
   /** @deprecated */
   @Deprecated
   octectstream("application/octet-stream"),
   OCTECT_STREAM("application/octet-stream");

   private String value;

   private MimeType(String value) {
      this.value = value;
   }

   public String getValue() {
      return this.value;
   }
}
