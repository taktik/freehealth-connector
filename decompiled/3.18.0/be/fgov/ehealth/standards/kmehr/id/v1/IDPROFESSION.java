package be.fgov.ehealth.standards.kmehr.id.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "ID-PROFESSION",
   propOrder = {"value"}
)
public class IDPROFESSION implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "S",
      required = true
   )
   protected IDPROFESSIONschemes s;
   @XmlAttribute(
      name = "SV",
      required = true
   )
   protected String sv;
   @XmlAttribute(
      name = "SL"
   )
   protected String sl;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public IDPROFESSIONschemes getS() {
      return this.s;
   }

   public void setS(IDPROFESSIONschemes value) {
      this.s = value;
   }

   public String getSV() {
      return this.sv;
   }

   public void setSV(String value) {
      this.sv = value;
   }

   public String getSL() {
      return this.sl;
   }

   public void setSL(String value) {
      this.sl = value;
   }
}
