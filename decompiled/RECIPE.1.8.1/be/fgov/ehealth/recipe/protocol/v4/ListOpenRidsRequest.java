package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListOpenRidsRequestType",
   propOrder = {"securedListOpenRidsRequest"}
)
@XmlRootElement(
   name = "ListOpenRidsRequest"
)
public class ListOpenRidsRequest extends RequestType {
   @XmlElement(
      name = "SecuredListOpenRidsRequest",
      required = true
   )
   protected SecuredContentType securedListOpenRidsRequest;

   public SecuredContentType getSecuredListOpenRidsRequest() {
      return this.securedListOpenRidsRequest;
   }

   public void setSecuredListOpenRidsRequest(SecuredContentType value) {
      this.securedListOpenRidsRequest = value;
   }
}
