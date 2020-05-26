package be.business.connector.recipe.patient.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListAddressedPrescriptionItem"
)
public class ListPatientPrescriptionItem {
   private String rid = null;
   private String prescriberLabel = null;
   private Date creationDate = null;
   private String prescriberId = null;

   public ListPatientPrescriptionItem() {
   }

   public ListPatientPrescriptionItem(String rid, String prescriberLabel, Date creationDate, String prescriberId) {
      this.rid = rid;
      this.prescriberLabel = prescriberLabel;
      this.creationDate = new Date(creationDate.getTime());
      this.prescriberId = prescriberId;
   }

   public String getRid() {
      return this.rid;
   }

   public void setRid(String rid) {
      this.rid = rid;
   }

   public String getPrescriberLabel() {
      return this.prescriberLabel;
   }

   public void setPrescriberLabel(String prescriberLabel) {
      this.prescriberLabel = prescriberLabel;
   }

   public Date getCreationDate() {
      return new Date(this.creationDate.getTime());
   }

   public void setCreationDate(Date creationDate) {
      this.creationDate = new Date(creationDate.getTime());
   }

   public void setPrescriberId(String prescriberId) {
      this.prescriberId = prescriberId;
   }

   public String getPrescriberId() {
      return this.prescriberId;
   }
}
