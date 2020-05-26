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
   name = "ListOpenRidsResponseType",
   propOrder = {"securedListOpenRidsResponse"}
)
@XmlRootElement(
   name = "ListOpenRidsResponse"
)
public class ListOpenRidsResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListOpenRidsResponse"
   )
   protected SecuredContentType securedListOpenRidsResponse;

   public SecuredContentType getSecuredListOpenRidsResponse() {
      return this.securedListOpenRidsResponse;
   }

   public void setSecuredListOpenRidsResponse(SecuredContentType value) {
      this.securedListOpenRidsResponse = value;
   }
}
