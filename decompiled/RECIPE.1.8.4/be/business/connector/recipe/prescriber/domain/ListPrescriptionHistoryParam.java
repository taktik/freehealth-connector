package be.business.connector.recipe.prescriber.domain;

import be.business.connector.recipe.domain.Page;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPrescriptionHistoryParam"
)
public class ListPrescriptionHistoryParam {
   private byte[] symmKey;
   private String patientId;
   @NotNull
   @Valid
   private Page page;

   public byte[] getSymmKey() {
      return this.symmKey;
   }

   public void setSymmKey(byte[] symmKey) {
      this.symmKey = symmKey;
   }

   public String getPatientId() {
      return this.patientId;
   }

   public void setPatientId(String patientId) {
      this.patientId = patientId;
   }

   public Page getPage() {
      return this.page;
   }

   public void setPage(Page page) {
      this.page = page;
   }
}
