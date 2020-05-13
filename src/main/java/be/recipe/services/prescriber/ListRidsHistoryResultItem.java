package be.recipe.services.prescriber;

import be.recipe.services.core.PrescriptionStatus;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistoryResultItem",
   propOrder = {"rid", "prescriptionStatus"}
)
public class ListRidsHistoryResultItem {
   protected String rid;
   protected PrescriptionStatus prescriptionStatus;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus value) {
      this.prescriptionStatus = value;
   }
}
