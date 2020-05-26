package be.recipe.services.executor;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/executor"
)
public class CreateFeedbackParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;
   @NotNull
   private byte[] content;

   @NotNull
   public String getExecutorId() {
      return super.getExecutorId();
   }

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public CreateFeedbackParam() {
   }

   public CreateFeedbackParam(String rid, String prescriberId, byte[] content) {
      this.rid = rid;
      super.setPrescriberId(prescriberId);
      this.content = content;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public byte[] getContent() {
      return this.content;
   }

   public void setContent(byte[] content) {
      this.content = content;
   }
}
