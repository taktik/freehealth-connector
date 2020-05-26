package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVision"
)
public class PutVision {
   @XmlElement(
      name = "PutVisionParamSealed"
   )
   protected byte[] putVisionParamSealed;

   public byte[] getPutVisionParamSealed() {
      return this.putVisionParamSealed;
   }

   public void setPutVisionParamSealed(byte[] putVisionParamSealed) {
      this.putVisionParamSealed = putVisionParamSealed;
   }
}
