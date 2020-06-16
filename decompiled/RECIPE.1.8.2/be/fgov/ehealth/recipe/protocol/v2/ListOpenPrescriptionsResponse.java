package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListOpenPrescriptionsResponseType",
   propOrder = {"securedListOpenPrescriptionsResponse"}
)
@XmlRootElement(
   name = "ListOpenPrescriptionsResponse"
)
public class ListOpenPrescriptionsResponse extends ResponseType {
   @XmlElement(
      name = "SecuredListOpenPrescriptionsResponse"
   )
   protected SecuredContentType securedListOpenPrescriptionsResponse;

   public SecuredContentType getSecuredListOpenPrescriptionsResponse() {
      return this.securedListOpenPrescriptionsResponse;
   }

   public void setSecuredListOpenPrescriptionsResponse(SecuredContentType value) {
      this.securedListOpenPrescriptionsResponse = value;
   }
}
