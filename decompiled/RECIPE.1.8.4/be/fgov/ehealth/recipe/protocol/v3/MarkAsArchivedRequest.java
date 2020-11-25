package be.fgov.ehealth.recipe.protocol.v3;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v3.ExecutorServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v3.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsArchivedRequestType",
   propOrder = {"securedMarkAsArchivedRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "MarkAsArchivedRequest"
)
public class MarkAsArchivedRequest extends RequestType {
   @XmlElement(
      name = "SecuredMarkAsArchivedRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsArchivedRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected ExecutorServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredMarkAsArchivedRequest() {
      return this.securedMarkAsArchivedRequest;
   }

   public void setSecuredMarkAsArchivedRequest(SecuredContentType value) {
      this.securedMarkAsArchivedRequest = value;
   }

   public ExecutorServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(ExecutorServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
