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
   name = "getListOpenPrescriptionResult",
   propOrder = {"prescriptions", "hasMoreResults", "session"}
)
@XmlRootElement(
   name = "getListOpenPrescriptionResult"
)
public class GetListOpenPrescriptionResult extends ResponseType {
   protected List<String> prescriptions;
   protected Boolean hasMoreResults;
   protected byte[] session;

   public List<String> getPrescriptions() {
      if (this.prescriptions == null) {
         this.prescriptions = new ArrayList();
      }

      return this.prescriptions;
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
