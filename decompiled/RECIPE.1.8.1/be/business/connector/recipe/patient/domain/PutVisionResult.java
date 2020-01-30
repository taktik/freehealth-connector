package be.business.connector.recipe.patient.domain;

import be.recipe.services.core.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(
   namespace = "http:/services.recipe.be/patient"
)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PutVisionResult"
)
public class PutVisionResult extends ResponseType {
   private String responseStatus;

   public PutVisionResult() {
   }

   public PutVisionResult(String responseStatus) {
      this.responseStatus = responseStatus;
   }

   public String getResponseStatus() {
      return this.responseStatus;
   }

   public void setResponseStatus(String responseStatus) {
      this.responseStatus = responseStatus;
   }
}
