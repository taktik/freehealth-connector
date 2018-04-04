package org.taktik.connector.technical.service.sts.domain;

public class SAMLNameIdentifier {
   private String assertionIssuer;
   private String format;
   private String nameQualifier;
   private String value;

   public SAMLNameIdentifier(String assertionIssuer, String format, String nameQualifier, String value) {
      this.assertionIssuer = assertionIssuer;
      this.format = format;
      this.nameQualifier = nameQualifier;
      this.value = value;
   }

   public String getFormat() {
      return this.format;
   }

   public String getNameQualifier() {
      return this.nameQualifier;
   }

   public String getValue() {
      return this.value;
   }

   public String getAssertionIssuer() {
      return this.assertionIssuer;
   }
}
