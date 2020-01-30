package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionStatus"
)
public class GetPrescriptionStatus {
   @XmlElement(
      name = "GetPrescriptionStatusParamSealed"
   )
   protected byte[] getPrescriptionStatusParamSealed;

   public byte[] getGetPrescriptionStatusParamSealed() {
      return this.getPrescriptionStatusParamSealed;
   }

   public void setGetPrescriptionStatusParamSealed(byte[] getPrescriptionStatusParamSealed) {
      this.getPrescriptionStatusParamSealed = getPrescriptionStatusParamSealed;
   }
}
