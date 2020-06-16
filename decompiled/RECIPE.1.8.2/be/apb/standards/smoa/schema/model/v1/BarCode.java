package be.apb.standards.smoa.schema.model.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "BarCode",
   propOrder = {"insurer", "key1"}
)
public class BarCode {
   @XmlElement(
      required = true
   )
   protected String insurer;
   @XmlElement(
      required = true
   )
   protected String key1;

   public String getInsurer() {
      return this.insurer;
   }

   public void setInsurer(String value) {
      this.insurer = value;
   }

   public String getKey1() {
      return this.key1;
   }

   public void setKey1(String value) {
      this.key1 = value;
   }
}
