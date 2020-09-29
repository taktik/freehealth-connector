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
   name = "MarkAsArchivedResponseType",
   propOrder = {"securedMarkAsArchivedResponse"}
)
@XmlRootElement(
   name = "MarkAsArchivedResponse"
)
public class MarkAsArchivedResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredMarkAsArchivedResponse"
   )
   protected SecuredContentType securedMarkAsArchivedResponse;

   public SecuredContentType getSecuredMarkAsArchivedResponse() {
      return this.securedMarkAsArchivedResponse;
   }

   public void setSecuredMarkAsArchivedResponse(SecuredContentType value) {
      this.securedMarkAsArchivedResponse = value;
   }
}
