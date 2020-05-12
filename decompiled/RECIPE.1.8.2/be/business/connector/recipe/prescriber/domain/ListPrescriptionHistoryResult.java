package be.business.connector.recipe.prescriber.domain;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
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
   name = "ListPrescriptionHistoryResult"
)
public class ListPrescriptionHistoryResult extends ResponseType {
   @XmlElement
   private List<ListPrescriptionHistoryItem> items;

   public ListPrescriptionHistoryResult() {
   }

   public ListPrescriptionHistoryResult(List<ListPrescriptionHistoryItem> items) {
      this.items = items;
   }

   public List<ListPrescriptionHistoryItem> getItems() {
      return this.items;
   }

   public void setItem(List<ListPrescriptionHistoryItem> items) {
      this.items = items;
   }

   public void add(ListPrescriptionHistoryItem item) {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      this.items.add(item);
   }
}
