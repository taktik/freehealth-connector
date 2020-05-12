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
   name = "CreateReservationResponseType",
   propOrder = {"securedCreateReservationResponse"}
)
@XmlRootElement(
   name = "CreateReservationResponse"
)
public class CreateReservationResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredCreateReservationResponse"
   )
   protected SecuredContentType securedCreateReservationResponse;

   public SecuredContentType getSecuredCreateReservationResponse() {
      return this.securedCreateReservationResponse;
   }

   public void setSecuredCreateReservationResponse(SecuredContentType value) {
      this.securedCreateReservationResponse = value;
   }
}
