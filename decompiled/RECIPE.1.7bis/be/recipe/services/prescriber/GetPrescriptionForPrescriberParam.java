package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class GetPrescriptionForPrescriberParam extends PartyIdentification {
   @NotNull
   @Size(
      min = 12,
      max = 12
   )
   private String rid;
   @NotNull
   private byte[] symmKey;

   public GetPrescriptionForPrescriberParam() {
   }

   @NotNull
   public String getPrescriberId() {
      return super.getPrescriberId();
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public GetPrescriptionForPrescriberParam(String rid) {
      this.rid = rid;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }
}
