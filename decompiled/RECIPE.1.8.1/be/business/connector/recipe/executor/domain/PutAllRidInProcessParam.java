package be.business.connector.recipe.executor.domain;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutAllRidInProcessParam"
)
public class PutAllRidInProcessParam {
   private List<String> rids;
   private byte[] symmKey;

   public PutAllRidInProcessParam() {
   }

   public PutAllRidInProcessParam(List<String> rids) {
      this.rids = rids;
   }

   public List<String> getRids() {
      return this.rids;
   }

   public void setRids(List<String> rids) {
      this.rids = rids;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }
}
