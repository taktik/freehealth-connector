package be.apb.standards.smoa.schema.code.v1;

import be.apb.standards.smoa.schema.v1.CDHCPARTY;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "PrescriberCategoryCodeType",
   propOrder = {"value"}
)
public class PrescriberCategoryCodeType extends AbstractPrescriberCategoryCodeType {
   @XmlElement(
      required = true
   )
   @XmlSchemaType(
      name = "token"
   )
   protected CDHCPARTY value;

   public CDHCPARTY getValue() {
      return this.value;
   }

   public void setValue(CDHCPARTY value) {
      this.value = value;
   }
}
