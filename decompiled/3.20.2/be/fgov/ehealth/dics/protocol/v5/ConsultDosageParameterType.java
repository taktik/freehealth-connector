package be.fgov.ehealth.dics.protocol.v5;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ConsultDosageParameterType",
   propOrder = {"definition", "standardUnit", "snomedCT"}
)
public class ConsultDosageParameterType implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlElement(
      name = "Definition",
      required = true
   )
   protected String definition;
   @XmlElement(
      name = "StandardUnit",
      required = true
   )
   protected String standardUnit;
   @XmlElement(
      name = "SnomedCT",
      required = true
   )
   protected String snomedCT;
   @XmlAttribute(
      name = "name",
      required = true
   )
   protected String name;

   public String getDefinition() {
      return this.definition;
   }

   public void setDefinition(String value) {
      this.definition = value;
   }

   public String getStandardUnit() {
      return this.standardUnit;
   }

   public void setStandardUnit(String value) {
      this.standardUnit = value;
   }

   public String getSnomedCT() {
      return this.snomedCT;
   }

   public void setSnomedCT(String value) {
      this.snomedCT = value;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }
}
