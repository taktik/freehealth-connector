package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacDoctor",
   propOrder = {"inamiNumber"}
)
public class IdentificationBvacDoctor {
   @XmlElement(
      required = true
   )
   protected String inamiNumber;

   public String getInamiNumber() {
      return this.inamiNumber;
   }

   public void setInamiNumber(String value) {
      this.inamiNumber = value;
   }
}
