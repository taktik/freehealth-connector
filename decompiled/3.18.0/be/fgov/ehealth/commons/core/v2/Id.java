package be.fgov.ehealth.commons.core.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdType",
   propOrder = {"value"}
)
@XmlRootElement(
   name = "Id"
)
public class Id implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Type",
      required = true
   )
   @XmlSchemaType(
      name = "anyURI"
   )
   protected String type;

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
}
