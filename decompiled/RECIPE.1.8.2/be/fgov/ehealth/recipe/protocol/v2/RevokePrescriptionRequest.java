package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.PrescriberServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "RevokePrescriptionRequestType",
   propOrder = {"securedRevokePrescriptionRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "RevokePrescriptionRequest"
)
public class RevokePrescriptionRequest extends RequestType {
   @XmlElement(
      name = "SecuredRevokePrescriptionRequest",
      required = true
   )
   protected SecuredContentType securedRevokePrescriptionRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected PrescriberServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredRevokePrescriptionRequest() {
      return this.securedRevokePrescriptionRequest;
   }

   public void setSecuredRevokePrescriptionRequest(SecuredContentType value) {
      this.securedRevokePrescriptionRequest = value;
   }

   public PrescriberServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(PrescriberServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
