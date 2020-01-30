package be.fgov.ehealth.recipe.core.v2;

import be.fgov.ehealth.commons.core.v1.IdentifierType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SendNotificationAdministrativeInformationType",
   propOrder = {"executorIdentifier"}
)
public class SendNotificationAdministrativeInformationType extends PrescriberServiceAdministrativeInformationType {
   @XmlElement(
      name = "ExecutorIdentifier",
      required = true
   )
   protected IdentifierType executorIdentifier;

   public IdentifierType getExecutorIdentifier() {
      return this.executorIdentifier;
   }

   public void setExecutorIdentifier(IdentifierType value) {
      this.executorIdentifier = value;
   }
}
