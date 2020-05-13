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
   name = "GetPrescriptionStatusResponseType",
   propOrder = {"securedGetPrescriptionStatusResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionStatusResponse"
)
public class GetPrescriptionStatusResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetPrescriptionStatusResponse"
   )
   protected SecuredContentType securedGetPrescriptionStatusResponse;

   public SecuredContentType getSecuredGetPrescriptionStatusResponse() {
      return this.securedGetPrescriptionStatusResponse;
   }

   public void setSecuredGetPrescriptionStatusResponse(SecuredContentType value) {
      this.securedGetPrescriptionStatusResponse = value;
   }
}
