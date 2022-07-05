package be.fgov.ehealth.consultrn.commons.core.v3;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "MiddleNameType",
   propOrder = {"value"}
)
public class MiddleNameType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Order",
      required = true
   )
   @XmlSchemaType(
      name = "anySimpleType"
   )
   protected String order;

   public MiddleNameType() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getOrder() {
      return this.order;
   }

   public void setOrder(String value) {
      this.order = value;
   }
}
