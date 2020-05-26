package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.ResponseType;
import be.recipe.services.executor.PrescriptionWithSecurityToken;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetOpenPrescriptionListResult"
)
public class GetOpenPrescriptionListResult extends ResponseType {
   private static final long serialVersionUID = 1L;
   private List<PrescriptionWithSecurityToken> prescriptions;

   public List<PrescriptionWithSecurityToken> getPrescriptions() {
      return this.prescriptions;
   }

   public void setPrescriptions(List<PrescriptionWithSecurityToken> prescriptions) {
      this.prescriptions = prescriptions;
   }
}
