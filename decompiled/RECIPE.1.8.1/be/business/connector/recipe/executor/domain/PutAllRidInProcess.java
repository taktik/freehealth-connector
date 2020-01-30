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
   name = "PutAllRidInProcess"
)
public class PutAllRidInProcess {
   @XmlElement(
      name = "PutAllRidInProcessParamSealed"
   )
   protected byte[] putAllRidInProcessParamSealed;

   public byte[] getPutAllRidInProcessParamSealed() {
      return this.putAllRidInProcessParamSealed;
   }

   public void setPutAllRidInProcessParamsealed(byte[] putAllRidInProcessParamSealed) {
      this.putAllRidInProcessParamSealed = putAllRidInProcessParamSealed;
   }
}
