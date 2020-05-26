package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SubstanceProductId",
   propOrder = {"inn"}
)
public class SubstanceProductId extends AbstractSubstanceProductIdType {
   @XmlElement(
      required = true
   )
   protected String inn;

   public String getInn() {
      return this.inn;
   }

   public void setInn(String value) {
      this.inn = value;
   }
}
