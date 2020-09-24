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
   name = "RevokeRelationResponseType",
   propOrder = {"securedRevokeRelationResponse"}
)
@XmlRootElement(
   name = "RevokeRelationResponse"
)
public class RevokeRelationResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredRevokeRelationResponse"
   )
   protected SecuredContentType securedRevokeRelationResponse;

   public SecuredContentType getSecuredRevokeRelationResponse() {
      return this.securedRevokeRelationResponse;
   }

   public void setSecuredRevokeRelationResponse(SecuredContentType value) {
      this.securedRevokeRelationResponse = value;
   }
}
