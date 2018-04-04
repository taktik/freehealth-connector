package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDSEX;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "SexCodeType",
   propOrder = {"code"}
)
public class SexCodeType extends AbstractSexCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDSEX code;

   public CDSEX getCode() {
      return this.code;
   }

   public void setCode(CDSEX value) {
      this.code = value;
   }
}
