package be.fgov.ehealth.standards.kmehr.cd.v1;

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
      CDEBIRTHFOETALMONITORINGvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEBIRTHFOETALMONITORINGvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
