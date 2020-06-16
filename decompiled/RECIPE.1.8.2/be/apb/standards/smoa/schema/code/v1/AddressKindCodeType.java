package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDADDRESS;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "AddressKindCodeType",
   propOrder = {"usage"}
)
public class AddressKindCodeType extends AbstractAddressKindCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDADDRESS usage;

   public CDADDRESS getUsage() {
      return this.usage;
   }

   public void setUsage(CDADDRESS value) {
      this.usage = value;
   }
}
