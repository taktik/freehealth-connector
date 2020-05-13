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
   name = "PutVisionParam"
)
public class PutVisionParam {
   private byte[] symmKey;
   private String rid;
   private String vision;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getVision() {
      return this.vision;
   }

   public void setVision(String vision) {
      this.vision = vision;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }
}
