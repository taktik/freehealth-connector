package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class UpdateFeedbackFlagParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;
   @NotNull
   private Boolean allowFeedback = true;

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public UpdateFeedbackFlagParam() {
   }

   public UpdateFeedbackFlagParam(String rid, boolean allowFeedback) {
      this.rid = rid;
      this.allowFeedback = allowFeedback;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public boolean isAllowFeedback() {
      return this.allowFeedback.booleanValue();
   }

   public void setAllowFeedback(boolean allowFeedback) {
      this.allowFeedback = allowFeedback;
   }
}
