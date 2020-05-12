package be.business.connector.recipe.executor.domain;

import be.business.connector.recipe.domain.PrescriptionStatus;
import be.recipe.services.core.ResponseType;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
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

   public void setPrescriptionStatus(PrescriptionStatus status) {
      this.prescriptionStatus = status;
   }

   public GetPrescriptionStatusResult(PrescriptionStatus status) {
      this.prescriptionStatus = status;
   }
}
