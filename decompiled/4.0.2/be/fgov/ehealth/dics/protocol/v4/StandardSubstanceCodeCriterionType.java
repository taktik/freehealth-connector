package be.fgov.ehealth.dics.protocol.v4;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "StandardSubstanceCodeCriterionType",
   propOrder = {"value"}
)
public class StandardSubstanceCodeCriterionType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "Standard"
   )
   protected String standard;

   public StandardSubstanceCodeCriterionType() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public String getStandard() {
      return this.standard;
   }

   public void setStandard(String value) {
      this.standard = value;
   }
}
