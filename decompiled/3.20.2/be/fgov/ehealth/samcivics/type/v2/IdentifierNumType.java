package be.fgov.ehealth.samcivics.type.v2;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "IdentifierNumType",
   propOrder = {"value"}
)
public class IdentifierNumType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "exclusionType",
      required = true
   )
   protected String exclusionType;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getExclusionType() {
      return this.exclusionType;
   }

   public void setExclusionType(String value) {
      this.exclusionType = value;
   }
}
