package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listFeedbacksResponse",
   propOrder = {"listFeedbacksResultSealed"}
)
@XmlRootElement(
   name = "listFeedbacksResponse"
)
public class ListFeedbacksResponse {
   @XmlElement(
      name = "ListFeedbacksResultSealed"
   )
   protected byte[] listFeedbacksResultSealed;

   public byte[] getListFeedbacksResultSealed() {
      return this.listFeedbacksResultSealed;
   }

   public void setListFeedbacksResultSealed(byte[] value) {
      this.listFeedbacksResultSealed = value;
   }
}
