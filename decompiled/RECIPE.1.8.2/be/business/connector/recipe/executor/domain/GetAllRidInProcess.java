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
   name = "GetAllRidInProcess"
)
public class GetAllRidInProcess {
   @XmlElement(
      name = "GetAllRidInProcessParamSealed"
   )
   protected byte[] getAllRidInProcessParamSealed;

   public byte[] getGetAllRidInProcessParamSealed() {
      return this.getAllRidInProcessParamSealed;
   }

   public void setGetAllRidInProcessParam(byte[] getAllRidInProcessParamSealed) {
      this.getAllRidInProcessParamSealed = getAllRidInProcessParamSealed;
   }
}
