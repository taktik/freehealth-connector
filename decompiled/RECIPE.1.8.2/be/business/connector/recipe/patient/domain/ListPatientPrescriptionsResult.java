package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
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
   name = "ListPatientPrescriptionsResult"
)
public class ListPatientPrescriptionsResult extends ResponseType {
   @XmlElement(
      name = "prescriptions"
   )
   private List<ListPatientPrescriptionItem> prescriptions = new ArrayList();

   public ListPatientPrescriptionsResult(List<ListPatientPrescriptionItem> prescriptions) {
      this.prescriptions = prescriptions;
   }

   public ListPatientPrescriptionsResult() {
   }

   public List<ListPatientPrescriptionItem> getPrescriptions() {
      return this.prescriptions;
   }

   public void setPrescriptions(List<ListPatientPrescriptionItem> prescriptions) {
      this.prescriptions = prescriptions;
   }

   public boolean add(ListPatientPrescriptionItem e) {
      return this.prescriptions.add(e);
   }
}
