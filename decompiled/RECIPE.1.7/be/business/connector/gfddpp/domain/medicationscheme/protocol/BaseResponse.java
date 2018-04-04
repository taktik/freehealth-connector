package be.business.connector.gfddpp.domain.medicationscheme.protocol;

import be.business.connector.gfddpp.domain.medicationscheme.Status;

public abstract class BaseResponse extends Base {
   protected String serverMessageID;
   protected Status status;

   public String getServerMessageID() {
      return this.serverMessageID;
   }

   public void setServerMessageID(String serverMessageID) {
      this.serverMessageID = serverMessageID;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status status) {
      this.status = status;
   }
}
