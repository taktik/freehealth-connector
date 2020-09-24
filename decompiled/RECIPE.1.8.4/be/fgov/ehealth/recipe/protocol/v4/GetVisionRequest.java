package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetVisionRequestType",
   propOrder = {"securedGetVisionRequest"}
)
@XmlRootElement(
   name = "GetVisionRequest"
)
public class GetVisionRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetVisionRequest",
      required = true
   )
   protected SecuredContentType securedGetVisionRequest;

   public SecuredContentType getSecuredGetVisionRequest() {
      return this.securedGetVisionRequest;
   }

   public void setSecuredGetVisionRequest(SecuredContentType value) {
      this.securedGetVisionRequest = value;
   }
}
