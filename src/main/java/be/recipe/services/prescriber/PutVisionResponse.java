package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "putVisionResponse",
   propOrder = {"putVisionResultSealed"}
)
@XmlRootElement(
   name = "putVisionResponse"
)
public class PutVisionResponse {
   @XmlElement(
      name = "PutVisionResultSealed"
   )
   protected byte[] putVisionResultSealed;

   public byte[] getPutVisionResultSealed() {
      return this.putVisionResultSealed;
   }

   public void setPutVisionResultSealed(byte[] value) {
      this.putVisionResultSealed = value;
   }

}
