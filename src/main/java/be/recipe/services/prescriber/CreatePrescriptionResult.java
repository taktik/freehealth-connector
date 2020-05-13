package be.recipe.services.prescriber;

import be.recipe.services.core.ResponseType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "createPrescriptionResult",
   propOrder = {"rid"}
)
@XmlRootElement(
   name = "createPrescriptionResult"
)
public class CreatePrescriptionResult extends ResponseType {
   @XmlElement(
      required = true
   )
   protected String rid;

   public String getRid() {
      return this.rid;
   }

   public void setRid(String value) {
      this.rid = value;
   }
}
