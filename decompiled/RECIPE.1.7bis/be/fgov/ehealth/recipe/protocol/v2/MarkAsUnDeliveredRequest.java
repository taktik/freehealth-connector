package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.ExecutorServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MarkAsUnDeliveredRequestType",
   propOrder = {"securedMarkAsUnDeliveredRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "MarkAsUnDeliveredRequest"
)
public class MarkAsUnDeliveredRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredMarkAsUnDeliveredRequest",
      required = true
   )
   protected SecuredContentType securedMarkAsUnDeliveredRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected ExecutorServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredMarkAsUnDeliveredRequest() {
      return this.securedMarkAsUnDeliveredRequest;
   }

   public void setSecuredMarkAsUnDeliveredRequest(SecuredContentType value) {
      this.securedMarkAsUnDeliveredRequest = value;
   }

   public ExecutorServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(ExecutorServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
