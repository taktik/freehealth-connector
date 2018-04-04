package org.taktik.connector.business.chapterIV.common;

public enum ConversationType {
   ADMISSION("admission"),
   CONSULT("consultation");

   private String propertyString;

   private ConversationType(String propertyString) {
      this.propertyString = propertyString;
   }

   public String getPropertyString() {
      return this.propertyString;
   }
}
