package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetValidationPropertiesRequestType",
   propOrder = {"securedGetValidationPropertiesRequest"}
)
@XmlRootElement(
   name = "GetValidationPropertiesRequest"
)
public class GetValidationPropertiesRequest extends RequestType {
   @XmlElement(
      name = "SecuredGetValidationPropertiesRequest",
      required = true
   )
   protected SecuredContentType securedGetValidationPropertiesRequest;

   public SecuredContentType getSecuredGetValidationPropertiesRequest() {
      return this.securedGetValidationPropertiesRequest;
   }

   public void setSecuredGetValidationPropertiesRequest(SecuredContentType value) {
      this.securedGetValidationPropertiesRequest = value;
   }
}
