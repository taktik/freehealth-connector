package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import java.util.UUID;

abstract class Base {
   protected String clientMessageID;

   public String getClientMessageID() {
      if (this.clientMessageID == null || this.clientMessageID.equals("")) {
         this.clientMessageID = UUID.randomUUID().toString();
      }

      return this.clientMessageID;
   }

   public void setClientMessageID(String clientMessageID) {
      this.clientMessageID = clientMessageID;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      if (this.clientMessageID != null) {
         sb.append("\n\t clientMessageID: ");
         sb.append(this.clientMessageID);
      }

      return sb.toString();
   }
}
