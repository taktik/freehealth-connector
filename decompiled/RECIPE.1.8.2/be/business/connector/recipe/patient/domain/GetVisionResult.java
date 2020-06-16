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
   name = "GetVisionResult"
)
public class GetVisionResult extends ResponseType {
   private String vision;

   public String getVision() {
      return this.vision;
   }

   public void setVision(String vision) {
      this.vision = vision;
   }
}
