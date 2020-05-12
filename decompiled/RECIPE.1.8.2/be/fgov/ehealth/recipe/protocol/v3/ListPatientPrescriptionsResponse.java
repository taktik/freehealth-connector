package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.ResponseType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPatientPrescriptionsResponseType",
   propOrder = {"securedListPatientPrescriptionsResponse"}
)
@XmlRootElement(
   name = "ListPatientPrescriptionsResponse"
)
public class ListPatientPrescriptionsResponse extends ResponseType {
   @XmlElement(
      name = "SecuredListPatientPrescriptionsResponse"
   )
   protected SecuredContentType securedListPatientPrescriptionsResponse;

   public SecuredContentType getSecuredListPatientPrescriptionsResponse() {
      return this.securedListPatientPrescriptionsResponse;
   }

   public void setSecuredListPatientPrescriptionsResponse(SecuredContentType value) {
      this.securedListPatientPrescriptionsResponse = value;
   }
}
