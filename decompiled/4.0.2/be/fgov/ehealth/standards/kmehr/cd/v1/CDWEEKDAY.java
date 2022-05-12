package be.fgov.ehealth.standards.kmehr.cd.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CD-WEEKDAY",
   propOrder = {"value"}
)
public class CDWEEKDAY implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected CDWEEKDAYvalues value;
   @XmlAttribute(
      name = "S",
      required = true
   )
   protected String s;
   @XmlAttribute(
      name = "SV",
      required = true
   )
   protected String sv;

   public CDWEEKDAY() {
   }

   public CDWEEKDAYvalues getValue() {
      return this.value;
   }

   public void setValue(CDWEEKDAYvalues value) {
      this.value = value;
   }

   public String getS() {
      return this.s == null ? "CD-WEEKDAY" : this.s;
   }

   public void setS(String value) {
      this.s = value;
   }

   public String getSV() {
      return this.sv;
   }

   public void setSV(String value) {
      this.sv = value;
   }
}
