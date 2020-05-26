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
   name = "GetPrescriptionResponseType",
   propOrder = {"securedGetPrescriptionResponse"}
)
@XmlRootElement(
   name = "GetPrescriptionResponse"
)
public class GetPrescriptionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetPrescriptionResponse"
   )
   protected SecuredContentType securedGetPrescriptionResponse;

   public SecuredContentType getSecuredGetPrescriptionResponse() {
      return this.securedGetPrescriptionResponse;
   }

   public void setSecuredGetPrescriptionResponse(SecuredContentType value) {
      this.securedGetPrescriptionResponse = value;
   }
}
