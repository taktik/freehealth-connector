package be.apb.standards.smoa.schema.code.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SpecialPrescriptionCodeType",
   propOrder = {"type"}
)
public class SpecialPrescriptionCodeType extends AbstractSpecialPrescriptionCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected SPECIALPRESCRIPTIONTYPE type;

   public SPECIALPRESCRIPTIONTYPE getType() {
      return this.type;
   }

   public void setType(SPECIALPRESCRIPTIONTYPE value) {
      this.type = value;
   }
}
