package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePatientPrescriptionRequestType",
   propOrder = {"securedRevokePatientPrescriptionRequest"}
)
@XmlRootElement(
   name = "RevokePatientPrescriptionRequest"
)
public class RevokePatientPrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredRevokePatientPrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedRevokePatientPrescriptionRequest;

   public SecuredContentType getSecuredRevokePatientPrescriptionRequest() {
      return this.securedRevokePatientPrescriptionRequest;
   }

   public void setSecuredRevokePatientPrescriptionRequest(SecuredContentType value) {
      this.securedRevokePatientPrescriptionRequest = value;
   }
}
