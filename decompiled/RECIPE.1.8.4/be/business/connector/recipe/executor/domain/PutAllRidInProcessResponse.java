package be.business.connector.recipe.executor.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutAllRidInProcessResponse"
)
public class PutAllRidInProcessResponse {
   @XmlElement(
      name = "PutAllRidInProcessResultSealed"
   )
   protected byte[] putAllRidInProcessResultSealed;

   public byte[] getPutAllRidInProcessResultSealed() {
      return this.putAllRidInProcessResultSealed;
   }

   public void setPutAllRidInProcessResultSealed(byte[] putAllRidInProcessResultSealed) {
      this.putAllRidInProcessResultSealed = putAllRidInProcessResultSealed;
   }
}
