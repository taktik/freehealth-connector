package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllReservations"
)
@XmlRootElement(
   namespace = "http://services.recipe.be"
)
public class GetAllReservations {
   @XmlElement(
      name = "GetAllReservationsParamSealed"
   )
   protected byte[] getAllReservationsParamSealed;

   public byte[] getGetAllReservationsParamSealed() {
      return this.getAllReservationsParamSealed;
   }

   public void setGetAllReservationsParamSealed(byte[] getAllReservationsParamSealed) {
      this.getAllReservationsParamSealed = getAllReservationsParamSealed;
   }
}
