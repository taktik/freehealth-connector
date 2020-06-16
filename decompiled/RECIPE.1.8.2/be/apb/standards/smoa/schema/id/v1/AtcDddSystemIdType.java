package be.apb.standards.smoa.schema.id.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AtcDddSystemIdType",
   propOrder = {"atc", "ddd"}
)
public class AtcDddSystemIdType extends AbstractMedicinalProductIdType {
   @XmlElement(
      required = true
   )
   protected String atc;
   @XmlElement(
      required = true
   )
   protected String ddd;

   public String getAtc() {
      return this.atc;
   }

   public void setAtc(String value) {
      this.atc = value;
   }

   public String getDdd() {
      return this.ddd;
   }

   public void setDdd(String value) {
      this.ddd = value;
   }
}
