package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListReservationsRequestType",
   propOrder = {"securedListReservationsRequest"}
)
@XmlRootElement(
   name = "ListReservationsRequest"
)
public class ListReservationsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListReservationsRequest",
      required = true
   )
   protected SecuredContentType securedListReservationsRequest;

   public SecuredContentType getSecuredListReservationsRequest() {
      return this.securedListReservationsRequest;
   }

   public void setSecuredListReservationsRequest(SecuredContentType value) {
      this.securedListReservationsRequest = value;
   }
}
