package be.business.connector.recipe.executor.domain;

import java.io.Serializable;
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
   name = "GetAllRidInProcessResponse"
)
public class GetAllRidInProcessResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "GetAllRidInProcessResultSealed"
   )
   protected byte[] getAllRidInProcessResultSealed;

   public byte[] getGetAllRidInProcessResultSealed() {
      return this.getAllRidInProcessResultSealed;
   }

   public void setGetAllRidInProcessResultSealed(byte[] getAllRidInProcessResultSealed) {
      this.getAllRidInProcessResultSealed = getAllRidInProcessResultSealed;
   }
}
