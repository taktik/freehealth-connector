package be.business.connector.recipe.prescriber.domain;

import be.recipe.services.core.ResponseType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/prescriber"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "GetAllRidInProcessResult"
)
public class GetAllRidInProcessResult extends ResponseType {
   private List<String> rids;

   public void addRid(String rid) {
      if (this.rids == null) {
         this.rids = new ArrayList();
      }

      this.rids.add(rid);
   }

   public List<String> getRids() {
      return this.rids;
   }

   public void setRids(List<String> rids) {
      this.rids = rids;
   }
}
