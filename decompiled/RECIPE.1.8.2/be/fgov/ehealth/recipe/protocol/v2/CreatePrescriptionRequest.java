package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CreatePrescriptionRequestType",
   propOrder = {"securedCreatePrescriptionRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "CreatePrescriptionRequest"
)
public class CreatePrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredCreatePrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedCreatePrescriptionRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected CreatePrescriptionAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredCreatePrescriptionRequest() {
      return this.securedCreatePrescriptionRequest;
   }

   public void setSecuredCreatePrescriptionRequest(SecuredContentType value) {
      this.securedCreatePrescriptionRequest = value;
   }

   public CreatePrescriptionAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(CreatePrescriptionAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
