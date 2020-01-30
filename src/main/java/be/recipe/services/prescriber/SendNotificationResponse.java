package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "sendNotificationResponse",
   propOrder = {"sendNotificationResultSealed"}
)
@XmlRootElement(
   name = "sendNotificationResponse"
)
public class SendNotificationResponse {
   @XmlElement(
      name = "SendNotificationResultSealed"
   )
   protected byte[] sendNotificationResultSealed;

   public byte[] getSendNotificationResultSealed() {
      return this.sendNotificationResultSealed;
   }

   public void setSendNotificationResultSealed(byte[] value) {
      this.sendNotificationResultSealed = value;
   }

}
