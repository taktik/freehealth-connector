package be.recipe.services.prescriber;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetListOpenPrescriptionResult"
)
public class GetListOpenPrescriptionResult {
   @XmlElement(
      name = "prescriptions"
   )
   private List<String> listOfPrescriptions = null;

   public GetListOpenPrescriptionResult() {
   }

   public GetListOpenPrescriptionResult(List<String> listOfPrescriptions) {
      this.setPrescriptions(listOfPrescriptions);
   }

   public List<String> getPrescriptions() {
      return this.listOfPrescriptions;
   }

   public void setPrescriptions(List<String> listOfPrescriptions) {
      this.listOfPrescriptions = listOfPrescriptions;
   }
}
