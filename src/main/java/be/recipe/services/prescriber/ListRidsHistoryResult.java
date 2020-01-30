package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "listRidsHistoryResult",
   propOrder = {"items", "hasMoreResults", "session"}
)
@XmlRootElement(
   name = "listRidsHistoryResult"
)
public class ListRidsHistoryResult extends ResponseType {
   protected List<ListRidsHistoryResultItem> items;
   protected Boolean hasMoreResults;
   protected byte[] session;

   public List<ListRidsHistoryResultItem> getItems() {
      if (this.items == null) {
         this.items = new ArrayList();
      }

      return this.items;
   }

   public Boolean isHasMoreResults() {
      return this.hasMoreResults;
   }

   public void setHasMoreResults(Boolean value) {
      this.hasMoreResults = value;
   }

   public byte[] getSession() {
      return this.session;
   }

   public void setSession(byte[] value) {
      this.session = value;
   }

   public Boolean getHasMoreResults() {
      return this.hasMoreResults;
   }
}
