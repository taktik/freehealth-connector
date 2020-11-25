package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "updateFeedbackFlagResponse",
   propOrder = {"updateFeedbackFlagResultSealed"}
)
@XmlRootElement(
   name = "updateFeedbackFlagResponse"
)
public class UpdateFeedbackFlagResponse {
   @XmlElement(
      name = "UpdateFeedbackFlagResultSealed"
   )
   protected byte[] updateFeedbackFlagResultSealed;

   public byte[] getUpdateFeedbackFlagResultSealed() {
      return this.updateFeedbackFlagResultSealed;
   }

   public void setUpdateFeedbackFlagResultSealed(byte[] value) {
      this.updateFeedbackFlagResultSealed = value;
   }

}
