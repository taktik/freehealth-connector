package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ITEM-BVTvalues"
)
@XmlEnum
public enum CDITEMBVTvalues {
   @XmlEnumValue("referenceid")
   REFERENCEID("referenceid"),
   @XmlEnumValue("patientopposition")
   PATIENTOPPOSITION("patientopposition"),
   @XmlEnumValue("sample")
   SAMPLE("sample"),
   @XmlEnumValue("biopsynumber")
   BIOPSYNUMBER("biopsynumber"),
   @XmlEnumValue("technicalremarks")
   TECHNICALREMARKS("technicalremarks"),
   @XmlEnumValue("lab")
   LAB("lab"),
   @XmlEnumValue("error")
   ERROR("error"),
   @XmlEnumValue("status")
   STATUS("status");

   private final String value;

   private CDITEMBVTvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDITEMBVTvalues fromValue(String v) {
      CDITEMBVTvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDITEMBVTvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
