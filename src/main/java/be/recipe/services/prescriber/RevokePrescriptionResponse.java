package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "revokePrescriptionResponse",
   propOrder = {"revokePrescriptionResultSealed"}
)
@XmlRootElement(
   name = "revokePrescriptionResponse"
)
public class RevokePrescriptionResponse {
   @XmlElement(
      name = "RevokePrescriptionResultSealed"
   )
   protected byte[] revokePrescriptionResultSealed;

   public byte[] getRevokePrescriptionResultSealed() {
      return this.revokePrescriptionResultSealed;
   }

   public void setRevokePrescriptionResultSealed(byte[] value) {
      this.revokePrescriptionResultSealed = value;
   }

}
