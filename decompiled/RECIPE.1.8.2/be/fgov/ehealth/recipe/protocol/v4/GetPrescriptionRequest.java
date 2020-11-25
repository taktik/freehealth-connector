package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionRequestType",
   propOrder = {"securedGetPrescriptionRequest"}
)
@XmlRootElement(
   name = "GetPrescriptionRequest"
)
public class GetPrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetPrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedGetPrescriptionRequest;

   public SecuredContentType getSecuredGetPrescriptionRequest() {
      return this.securedGetPrescriptionRequest;
   }

   public void setSecuredGetPrescriptionRequest(SecuredContentType value) {
      this.securedGetPrescriptionRequest = value;
   }
}
