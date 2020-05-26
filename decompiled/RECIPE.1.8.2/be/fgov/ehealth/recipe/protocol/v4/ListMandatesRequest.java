package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListMandatesRequestType",
   propOrder = {"securedListMandatesRequest"}
)
@XmlRootElement(
   name = "ListMandatesRequest"
)
public class ListMandatesRequest extends RequestType {
   @XmlElement(
      name = "SecuredListMandatesRequest",
      required = true
   )
   protected SecuredContentType securedListMandatesRequest;

   public SecuredContentType getSecuredListMandatesRequest() {
      return this.securedListMandatesRequest;
   }

   public void setSecuredListMandatesRequest(SecuredContentType value) {
      this.securedListMandatesRequest = value;
   }
}
