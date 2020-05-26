package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokeRelationRequestType",
   propOrder = {"securedRevokeRelationRequest"}
)
@XmlRootElement(
   name = "RevokeRelationRequest"
)
public class RevokeRelationRequest extends RequestType {
   @XmlElement(
      name = "SecuredRevokeRelationRequest",
      required = true
   )
   protected SecuredContentType securedRevokeRelationRequest;

   public SecuredContentType getSecuredRevokeRelationRequest() {
      return this.securedRevokeRelationRequest;
   }

   public void setSecuredRevokeRelationRequest(SecuredContentType value) {
      this.securedRevokeRelationRequest = value;
   }
}
