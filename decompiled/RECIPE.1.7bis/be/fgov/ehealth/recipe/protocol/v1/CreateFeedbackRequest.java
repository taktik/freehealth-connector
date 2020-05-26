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
   name = "CreateFeedbackRequestType",
   propOrder = {"securedCreateFeedbackRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "CreateFeedbackRequest"
)
public class CreateFeedbackRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredCreateFeedbackRequest",
      required = true
   )
   protected SecuredContentType securedCreateFeedbackRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected ExecutorServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredCreateFeedbackRequest() {
      return this.securedCreateFeedbackRequest;
   }

   public void setSecuredCreateFeedbackRequest(SecuredContentType value) {
      this.securedCreateFeedbackRequest = value;
   }

   public ExecutorServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(ExecutorServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
