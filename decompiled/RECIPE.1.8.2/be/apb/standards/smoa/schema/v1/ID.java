package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ID",
   propOrder = {"value"}
)
public class ID {
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Type"
   )
   protected String type;
   @XmlAttribute(
      name = "IdType"
   )
   protected String idType;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String value) {
      this.type = value;
   }

   public String getIdType() {
      return this.idType;
   }

   public void setIdType(String value) {
      this.idType = value;
   }
}
