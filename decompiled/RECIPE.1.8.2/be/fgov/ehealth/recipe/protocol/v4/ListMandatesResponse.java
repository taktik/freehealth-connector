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
   name = "ListMandatesResponseType",
   propOrder = {"securedListMandatesResponse"}
)
@XmlRootElement(
   name = "ListMandatesResponse"
)
public class ListMandatesResponse extends StatusResponseType {
   @XmlElement(
      name = "SecuredListMandatesResponse"
   )
   protected SecuredContentType securedListMandatesResponse;

   public SecuredContentType getSecuredListMandatesResponse() {
      return this.securedListMandatesResponse;
   }

   public void setSecuredListMandatesResponse(SecuredContentType value) {
      this.securedListMandatesResponse = value;
   }
}
