package be.business.connector.recipe.prescriber.domain;

import be.business.connector.recipe.domain.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetPrescriptionStatusResult"
)
public class GetPrescriptionStatusResult extends ResponseType implements Serializable {
   private static final long serialVersionUID = 1L;
   private PrescriptionStatus prescriptionStatus;

   public GetPrescriptionStatusResult() {
   }

   public PrescriptionStatus getPrescriptionStatus() {
      return this.prescriptionStatus;
   }

   public void setPrescriptionStatus(PrescriptionStatus prescriptionStatus) {
      this.prescriptionStatus = prescriptionStatus;
   }

   public GetPrescriptionStatusResult(PrescriptionStatus prescriptionStatus) {
      this.prescriptionStatus = prescriptionStatus;
   }
}
