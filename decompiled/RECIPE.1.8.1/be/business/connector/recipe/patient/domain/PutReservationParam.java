package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutReservationParam"
)
public class PutReservationParam {
   private byte[] symmKey;
   private String rid;
   private String executorId;

   public String getRid() {
      return this.rid;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public PutReservationParam(String rid, String executorId) {
      this.rid = rid;
      this.executorId = executorId;
   }

   public PutReservationParam() {
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }
}
