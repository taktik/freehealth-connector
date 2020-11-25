package be.fgov.ehealth.recipe.protocol.v2;

import be.fgov.ehealth.commons.protocol.v1.RequestType;
import be.fgov.ehealth.recipe.core.v2.SecuredContentType;
import be.fgov.ehealth.recipe.core.v2.SendNotificationAdministrativeInformationType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendNotificationRequestType",
   propOrder = {"securedSendNotificationRequest", "administrativeInformation"}
)
@XmlRootElement(
   name = "SendNotificationRequest"
)
public class SendNotificationRequest extends RequestType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "SecuredSendNotificationRequest",
      required = true
   )
   protected SecuredContentType securedSendNotificationRequest;
   @XmlElement(
      name = "AdministrativeInformation",
      required = true
   )
   protected SendNotificationAdministrativeInformationType administrativeInformation;

   public SecuredContentType getSecuredSendNotificationRequest() {
      return this.securedSendNotificationRequest;
   }

   public void setSecuredSendNotificationRequest(SecuredContentType value) {
      this.securedSendNotificationRequest = value;
   }

   public SendNotificationAdministrativeInformationType getAdministrativeInformation() {
      return this.administrativeInformation;
   }

   public void setAdministrativeInformation(SendNotificationAdministrativeInformationType value) {
      this.administrativeInformation = value;
   }
}
