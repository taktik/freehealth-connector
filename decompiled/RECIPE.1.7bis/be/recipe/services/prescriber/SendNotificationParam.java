package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class SendNotificationParam extends PartyIdentification {
   @NotNull
   private byte[] content;

   @NotNull
   public String getExecutorId() {
      return super.getExecutorId();
   }

   @NotNull
   public String getPatientId() {
      return super.getPatientId();
   }

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }
}
