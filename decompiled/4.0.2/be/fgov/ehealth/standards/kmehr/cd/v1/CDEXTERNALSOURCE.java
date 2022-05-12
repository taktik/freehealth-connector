package be.fgov.ehealth.standards.kmehr.cd.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "CD-EXTERNALSOURCE",
   propOrder = {"value"}
)
public class CDEXTERNALSOURCE implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "S",
      required = true
   )
   protected CDEXTERNALSOURCEschemes s;
   @XmlAttribute(
      name = "SV",
      required = true
   )
   protected String sv;
   @XmlAttribute(
      name = "SL"
   )
   protected String sl;
   @XmlAttribute(
      name = "DN"
   )
   protected String dn;
   @XmlAttribute(
      name = "L"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String l;

   public CDEXTERNALSOURCE() {
   }

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public CDEXTERNALSOURCEschemes getS() {
      return this.s;
   }

   public void setS(CDEXTERNALSOURCEschemes value) {
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

   public String getDN() {
      return this.dn;
   }

   public void setDN(String value) {
      this.dn = value;
   }

   public String getL() {
      return this.l == null ? "en" : this.l;
   }

   public void setL(String value) {
      this.l = value;
   }
}
