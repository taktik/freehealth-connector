package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.ExecutorServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionForExecutorRequestType",
   propOrder = {"securedGetPrescriptionForExecutorRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "GetPrescriptionForExecutorRequest"
)
public class GetPrescriptionForExecutorRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredGetPrescriptionForExecutorRequest",
      required = true
   )
   protected SecuredContentType securedGetPrescriptionForExecutorRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected ExecutorServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredGetPrescriptionForExecutorRequest() {
      return this.securedGetPrescriptionForExecutorRequest;
   }

   public void setSecuredGetPrescriptionForExecutorRequest(SecuredContentType value) {
      this.securedGetPrescriptionForExecutorRequest = value;
   }

   public ExecutorServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(ExecutorServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
