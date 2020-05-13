package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class ListFeedbacksParam extends PartyIdentification {
   @NotNull
   private Boolean readFlag;
   @NotNull
   private byte[] symmKey;

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public ListFeedbacksParam() {
   }

   public ListFeedbacksParam(String prescriberId, boolean readFlag) {
      this.readFlag = readFlag;
   }

   public ListFeedbacksParam(String prescriberId, boolean onlyUnreadfeedback, byte[] symmKey) {
      this.readFlag = onlyUnreadfeedback;
      this.symmKey = symmKey;
   }

   public Boolean getReadFlag() {
      return this.readFlag;
   }

   public void setReadFlag(Boolean readFlag) {
      this.readFlag = readFlag;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }
}
