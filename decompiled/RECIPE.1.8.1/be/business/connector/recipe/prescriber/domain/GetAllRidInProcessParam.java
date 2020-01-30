package be.business.connector.recipe.prescriber.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllRidInProcessParam"
)
public class GetAllRidInProcessParam {
   private byte[] symmKey;
   private String executorId;

   public GetAllRidInProcessParam() {
   }

   public GetAllRidInProcessParam(String executorId) {
      this.executorId = executorId;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String executorId) {
      this.executorId = executorId;
   }
}
