package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

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
   name = "CD-ITEM",
   propOrder = {"value"}
)
public class CDITEM implements Serializable {
   private static final long serialVersionUID = 1L;
   @XmlValue
   protected String value;
   @XmlAttribute(
      name = "S",
      required = true
   )
   protected CDITEMschemes s;
   @XmlAttribute(
      name = "SV",
      required = true
   )
   protected String sv;
   @XmlAttribute(
      name = "DN"
   )
   protected String dn;
   @XmlAttribute(
      name = "SL"
   )
   protected String sl;
   @XmlAttribute(
      name = "L"
   )
   @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
   @XmlSchemaType(
      name = "language"
   )
   protected String l;
   @XmlAttribute(
      name = "nullFlavor"
   )
   protected String nullFlavor;

   public String getValue() {
      return this.value;
   }

   public void setValue(String value) {
      this.value = value;
   }

   public CDITEMschemes getS() {
      return this.s;
   }

   public void setS(CDITEMschemes value) {
      this.s = value;
   }

   public String getSV() {
      return this.sv;
   }

   public void setSV(String value) {
      this.sv = value;
   }

   public String getDN() {
      return this.dn;
   }

   public void setDN(String value) {
      this.dn = value;
   }

   public String getSL() {
      return this.sl;
   }

   public void setSL(String value) {
      this.sl = value;
   }

   public String getL() {
      return this.l == null ? "en" : this.l;
   }

   public void setL(String value) {
      this.l = value;
   }

   public String getNullFlavor() {
      return this.nullFlavor;
   }

   public void setNullFlavor(String value) {
      this.nullFlavor = value;
   }
}
