package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionStatusResponse",
   propOrder = {"getPrescriptionStatusResultSealed"}
)
@XmlRootElement(
   name = "getPrescriptionStatusResponse"
)
public class GetPrescriptionStatusResponse {
   @XmlElement(
      name = "GetPrescriptionStatusResultSealed"
   )
   protected byte[] getPrescriptionStatusResultSealed;

   public byte[] getGetPrescriptionStatusResultSealed() {
      return this.getPrescriptionStatusResultSealed;
   }

   public void setGetPrescriptionStatusResultSealed(byte[] value) {
      this.getPrescriptionStatusResultSealed = value;
   }

}
