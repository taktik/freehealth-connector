package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionStatusResponse"
)
public class GetPrescriptionStatusResponse {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetPrescriptionStatusResultSealed"
   )
   protected byte[] getPrescriptionStatusResultSealed;

   public byte[] getGetPrescriptionStatusResultSealed() {
      return this.getPrescriptionStatusResultSealed;
   }

   public void setGetPrescriptionStatusResultSealed(byte[] getPrescriptionStatusResultSealed) {
      this.getPrescriptionStatusResultSealed = getPrescriptionStatusResultSealed;
   }
}
