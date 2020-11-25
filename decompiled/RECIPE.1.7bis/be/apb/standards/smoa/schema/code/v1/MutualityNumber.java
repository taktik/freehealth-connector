package be.apb.standards.smoa.schema.code.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MutualityNumber",
   propOrder = {"number"}
)
public class MutualityNumber extends AbstractMutualityNumberCodeType {
   @XmlElement(
      required = true
   )
   protected String number;

   public String getNumber() {
      return this.number;
   }

   public void setNumber(String value) {
      this.number = value;
   }
}
