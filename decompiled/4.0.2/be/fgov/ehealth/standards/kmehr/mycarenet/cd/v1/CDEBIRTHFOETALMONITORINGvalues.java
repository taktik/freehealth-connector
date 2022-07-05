package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-FOETALMONITORINGvalues"
)
@XmlEnum
public enum CDEBIRTHFOETALMONITORINGvalues {
   CTG("CTG"),
   STAN("STAN"),
   MBE("MBE"),
   @XmlEnumValue("intermittent-auscultation")
   INTERMITTENT_AUSCULTATION("intermittent-auscultation");

   private final String value;

   private CDEBIRTHFOETALMONITORINGvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHFOETALMONITORINGvalues fromValue(String v) {
      CDEBIRTHFOETALMONITORINGvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHFOETALMONITORINGvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
