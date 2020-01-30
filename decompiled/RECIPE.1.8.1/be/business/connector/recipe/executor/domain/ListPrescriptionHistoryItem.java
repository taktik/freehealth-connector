package be.business.connector.recipe.executor.domain;

import be.business.connector.recipe.domain.PrescriptionStatus;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPrescriptionHistoryItem"
)
public class ListPrescriptionHistoryItem {
   private String rid;
   private PrescriptionStatus prescriptionStatus;

   public ListPrescriptionHistoryItem() {
   }

   public ListPrescriptionHistoryItem(String rid, PrescriptionStatus prescriptionStatus) {
      this.rid = rid;
      this.prescriptionStatus = prescriptionStatus;
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
      this.prescriptionStatus = prescriptionStatus;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }
}
