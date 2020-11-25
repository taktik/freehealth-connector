package be.fgov.ehealth.recipe.protocol.v1;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v1.PrescriberServiceAdministrativeInformationType;
import be.fgov.ehealth.recipe.core.v1.SecuredContentType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "UpdateFeedbackFlagRequestType",
   propOrder = {"securedUpdateFeedbackFlagRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "UpdateFeedbackFlagRequest"
)
public class UpdateFeedbackFlagRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredUpdateFeedbackFlagRequest",
      required = true
   )
   protected SecuredContentType securedUpdateFeedbackFlagRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected PrescriberServiceAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredUpdateFeedbackFlagRequest() {
      return this.securedUpdateFeedbackFlagRequest;
   }

   public void setSecuredUpdateFeedbackFlagRequest(SecuredContentType value) {
      this.securedUpdateFeedbackFlagRequest = value;
   }

   public PrescriberServiceAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(PrescriberServiceAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
