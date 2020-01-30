package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.CreatePrescriptionAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
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
public class CreatePrescriptionRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
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
