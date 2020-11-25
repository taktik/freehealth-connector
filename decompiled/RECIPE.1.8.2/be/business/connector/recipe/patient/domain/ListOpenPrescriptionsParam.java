package be.business.connector.recipe.patient.domain;

import be.business.connector.recipe.domain.Page;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListOpenPrescriptionsParam"
)
public class ListOpenPrescriptionsParam {
   private String patientId;
   private String prescriberId;
   @NotNull
   @Valid
   private Page page;
   private byte[] symmKey;

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public String getPrescriberId() {
      return this.prescriberId;
   }

   public void setPrescriberId(String prescriberId) {
      this.prescriberId = prescriberId;
   }

   public Page getPage() {
      return this.page;
   }

   public void setPage(Page page) {
      this.page = page;
   }

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }
}
