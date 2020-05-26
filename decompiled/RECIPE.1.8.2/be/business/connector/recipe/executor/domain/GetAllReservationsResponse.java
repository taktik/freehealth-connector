package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllReservationsResponse"
)
@XmlRootElement(
   namespace = "http://services.recipe.be"
)
public class GetAllReservationsResponse {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetAllReservationsResultSealed"
   )
   protected byte[] getAllReservationsResultSealed;

   public byte[] getGetAllReservationsResultSealed() {
      return this.getAllReservationsResultSealed;
   }

   public void setGetAllReservationsResultSealed(byte[] getAllReservationsResultSealed) {
      this.getAllReservationsResultSealed = getAllReservationsResultSealed;
   }
}
