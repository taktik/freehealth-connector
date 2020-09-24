package be.business.connector.recipe.executor.domain;

import be.recipe.services.core.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http://services.recipe.be/executor"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutAllRidInProcessResult"
)
public class PutAllRidInProcessResult extends ResponseType {
   private String statusMessage;

   public PutAllRidInProcessResult() {
   }

   public PutAllRidInProcessResult(String statusMessage) {
      this.statusMessage = statusMessage;
   }

   public String getStatusMesage() {
      return this.statusMessage;
   }

   public void setStatusMesage(String statusMessage) {
      this.statusMessage = statusMessage;
   }
}
