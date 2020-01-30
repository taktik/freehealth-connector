package be.recipe.services.prescriber;

import be.recipe.services.core.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "getPrescriptionStatusResult",
   propOrder = {"prescriptionStatus", "executorId"}
)
@XmlRootElement(
   name = "getPrescriptionStatusResult"
)
public class GetPrescriptionStatusResult extends ResponseType {
   protected PrescriptionStatus prescriptionStatus;
   protected String executorId;

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus value) {
      this.prescriptionStatus = value;
   }

   public String getExecutorId() {
      return this.executorId;
   }

   public void setExecutorId(String value) {
      this.executorId = value;
   }
}
