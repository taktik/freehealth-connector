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
   name = "CreateRelationResponseType",
   propOrder = {"securedCreateRelationResponse"}
)
@XmlRootElement(
   name = "CreateRelationResponse"
)
public class CreateRelationResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredCreateRelationResponse"
   )
   protected SecuredContentType securedCreateRelationResponse;

   public SecuredContentType getSecuredCreateRelationResponse() {
      return this.securedCreateRelationResponse;
   }

   public void setSecuredCreateRelationResponse(SecuredContentType value) {
      this.securedCreateRelationResponse = value;
   }
}
