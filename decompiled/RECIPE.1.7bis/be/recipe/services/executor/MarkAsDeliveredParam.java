package be.recipe.services.executor;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class MarkAsDeliveredParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid = null;

   public MarkAsDeliveredParam(String rid) {
      this.rid = rid;
   }

   public MarkAsDeliveredParam() {
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }
}
