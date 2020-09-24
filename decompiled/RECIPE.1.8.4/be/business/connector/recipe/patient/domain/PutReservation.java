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
   name = "PutReservation"
)
public class PutReservation {
   @XmlElement(
      name = "PutReservationParamSealed"
   )
   protected byte[] putReservationParamSealed;

   public byte[] getPutReservationParamSealed() {
      return this.putReservationParamSealed;
   }

   public void setPutReservationParamSealed(byte[] putReservationParamSealed) {
      this.putReservationParamSealed = putReservationParamSealed;
   }
}
