package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.ResponseType;
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
   name = "ListOpenPrescriptionsResult"
)
public class ListOpenPrescriptionsResult extends ResponseType {
   @XmlElement
   private List<String> items;

   public ListOpenPrescriptionsResult() {
   }

   public ListOpenPrescriptionsResult(List<String> items) {
      this.items = items;
   }

   public List<String> getItems() {
      return this.items;
   }

   public void setItems(List<String> items) {
      this.items = items;
   }
}
