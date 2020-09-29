package be.business.connector.recipe.patient.domain;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ListPrescriptionHistoryResponse"
)
public class ListPrescriptionHistoryResponse implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "ListPrescriptionHistoryResultSealed"
   )
   protected byte[] listPrescriptionHistoryResultSealed;

   public ListPrescriptionHistoryResponse() {
   }

   public ListPrescriptionHistoryResponse(byte[] listPrescriptionHistoryResultSealed) {
      this.listPrescriptionHistoryResultSealed = listPrescriptionHistoryResultSealed;
   }

   public byte[] getListPrescriptionHistoryResultSealed() {
      return this.listPrescriptionHistoryResultSealed;
   }

   public void setListPrescriptionHistoryResultSealed(byte[] listPrescriptionHistoryResultSealed) {
      this.listPrescriptionHistoryResultSealed = listPrescriptionHistoryResultSealed;
   }
}
