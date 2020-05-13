package be.recipe.services.prescriber;

import be.recipe.services.PartyIdentification;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class GetListOpenPrescriptionParam extends PartyIdentification {
   @NotNull
   private byte[] symmKey;

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
}
