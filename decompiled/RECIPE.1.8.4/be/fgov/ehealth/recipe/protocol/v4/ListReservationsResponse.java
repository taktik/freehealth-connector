package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.commons.protocol.v2.StatusResponseType;
import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListReservationsResponseType",
   propOrder = {"securedListReservationsResponse"}
)
@XmlRootElement(
   name = "ListReservationsResponse"
)
public class ListReservationsResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListReservationsResponse"
   )
   protected SecuredContentType securedListReservationsResponse;

   public SecuredContentType getSecuredListReservationsResponse() {
      return this.securedListReservationsResponse;
   }

   public void setSecuredListReservationsResponse(SecuredContentType value) {
      this.securedListReservationsResponse = value;
   }
}
