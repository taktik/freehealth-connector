package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentificationBvacDocument",
   propOrder = {"id", "barCode"}
)
public class IdentificationBvacDocument {
   @XmlElement(
      required = true
   )
   protected String id;
   @XmlElement(
      required = true
   )
   protected BarCode barCode;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public BarCode getBarCode() {
      return this.barCode;
   }

   public void setBarCode(BarCode value) {
      this.barCode = value;
   }
}
