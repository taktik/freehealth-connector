package be.business.connector.recipe.patient.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
public class GetPrescriptionStatusParam {
   private byte[] symmKey;
   private String rid;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getRid() {
      return this.rid;
   }
}
