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
      CDITEMBVTvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDITEMBVTvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
