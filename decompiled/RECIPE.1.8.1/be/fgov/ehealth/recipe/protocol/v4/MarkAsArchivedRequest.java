package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsArchivedRequestType",
   propOrder = {"securedMarkAsArchivedRequest"}
)
@XmlRootElement(
   name = "MarkAsArchivedRequest"
)
public class MarkAsArchivedRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsArchivedRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsArchivedRequest;

   public SecuredContentType getSecuredMarkAsArchivedRequest() {
      return this.securedMarkAsArchivedRequest;
   }

   public void setSecuredMarkAsArchivedRequest(SecuredContentType value) {
      this.securedMarkAsArchivedRequest = value;
   }
}
