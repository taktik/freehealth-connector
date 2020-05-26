package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreateRelationRequestType",
   propOrder = {"securedCreateRelationRequest"}
)
@XmlRootElement(
   name = "CreateRelationRequest"
)
public class CreateRelationRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreateRelationRequest",
      required = true
   )
   protected SecuredContentType securedCreateRelationRequest;

   public SecuredContentType getSecuredCreateRelationRequest() {
      return this.securedCreateRelationRequest;
   }

   public void setSecuredCreateRelationRequest(SecuredContentType value) {
      this.securedCreateRelationRequest = value;
   }
}
