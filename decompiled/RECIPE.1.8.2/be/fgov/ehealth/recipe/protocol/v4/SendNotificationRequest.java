package be.fgov.ehealth.recipe.protocol.v4;

import be.fgov.ehealth.recipe.core.v4.SecuredContentType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendNotificationRequestType",
   propOrder = {"securedSendNotificationRequest"}
)
@XmlRootElement(
   name = "SendNotificationRequest"
)
public class SendNotificationRequest extends RequestType {
   @XmlElement(
      name = "SecuredSendNotificationRequest",
      required = true
   )
   protected SecuredContentType securedSendNotificationRequest;

   public SecuredContentType getSecuredSendNotificationRequest() {
      return this.securedSendNotificationRequest;
   }

   public void setSecuredSendNotificationRequest(SecuredContentType value) {
      this.securedSendNotificationRequest = value;
   }
}
