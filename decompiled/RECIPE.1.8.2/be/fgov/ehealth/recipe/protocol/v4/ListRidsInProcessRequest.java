package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListRidsInProcessRequestType",
   propOrder = {"securedListRidsInProcessRequest"}
)
@XmlRootElement(
   name = "ListRidsInProcessRequest"
)
public class ListRidsInProcessRequest extends RequestType {
   @XmlElement(
      name = "SecuredListRidsInProcessRequest",
      required = true
   )
   protected SecuredContentType securedListRidsInProcessRequest;

   public SecuredContentType getSecuredListRidsInProcessRequest() {
      return this.securedListRidsInProcessRequest;
   }

   public void setSecuredListRidsInProcessRequest(SecuredContentType value) {
      this.securedListRidsInProcessRequest = value;
   }
}
