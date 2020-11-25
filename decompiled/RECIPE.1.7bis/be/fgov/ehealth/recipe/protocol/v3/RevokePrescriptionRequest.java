package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.PrescriberServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import java.io.Serializable;
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
public class RevokePrescriptionRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
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
