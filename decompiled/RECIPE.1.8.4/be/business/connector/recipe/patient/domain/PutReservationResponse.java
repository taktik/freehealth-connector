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
   name = "PutReservationResponse"
)
public class PutReservationResponse {
   @XmlElement(
      name = "PutReservationResultSealed"
   )
   protected byte[] putReservationResultSealed;

   public byte[] getPutReservationResultSealed() {
      return this.putReservationResultSealed;
   }

   public void setPutReservationResultSealed(byte[] putReservationResultSealed) {
      this.putReservationResultSealed = putReservationResultSealed;
   }
}
