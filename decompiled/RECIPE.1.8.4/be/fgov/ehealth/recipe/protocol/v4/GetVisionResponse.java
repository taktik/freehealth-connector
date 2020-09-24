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
   name = "GetVisionResponseType",
   propOrder = {"securedGetVisionResponse"}
)
@XmlRootElement(
   name = "GetVisionResponse"
)
public class GetVisionResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredGetVisionResponse"
   )
   protected SecuredContentType securedGetVisionResponse;

   public SecuredContentType getSecuredGetVisionResponse() {
      return this.securedGetVisionResponse;
   }

   public void setSecuredGetVisionResponse(SecuredContentType value) {
      this.securedGetVisionResponse = value;
   }
}
