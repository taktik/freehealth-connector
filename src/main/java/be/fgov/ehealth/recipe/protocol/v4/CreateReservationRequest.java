package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateReservationRequestType",
   propOrder = {"securedCreateReservationRequest"}
)
@XmlRootElement(
   name = "CreateReservationRequest"
)
public class CreateReservationRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateReservationRequest",
      required = true
   )
   protected SecuredContentType securedCreateReservationRequest;

   public SecuredContentType getSecuredCreateReservationRequest() {
      return this.securedCreateReservationRequest;
   }

   public void setSecuredCreateReservationRequest(SecuredContentType value) {
      this.securedCreateReservationRequest = value;
   }
}
