package be.recipe.services.executor;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class RevokePrescriptionParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;
   @Size(
      max = 256
   )
   private String reason;

   public RevokePrescriptionParam() {
   }

   public RevokePrescriptionParam(String rid, String reason) {
      this.rid = rid;
      this.reason = reason;
   }

   public String toString() {
      return "rid=" + this.rid + "," + "reason=" + this.reason;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String reason) {
      this.reason = reason;
   }
}
