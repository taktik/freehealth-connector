package be.recipe.services.prescriber;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
public class CreatePrescriptionResult {
   private String rid;

   public CreatePrescriptionResult() {
   }

   public CreatePrescriptionResult(String rid) {
      this.rid = rid;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }
}
