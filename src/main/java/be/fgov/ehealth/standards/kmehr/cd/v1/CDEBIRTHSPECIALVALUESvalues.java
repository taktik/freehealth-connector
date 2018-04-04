package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-SPECIALVALUESvalues"
)
@XmlEnum
public enum CDEBIRTHSPECIALVALUESvalues {
   @XmlEnumValue("noanswer")
   NOANSWER("noanswer"),
   @XmlEnumValue("unknown")
   UNKNOWN("unknown"),
   @XmlEnumValue("nottested")
   NOTTESTED("nottested");

   private final String value;

   private CDEBIRTHSPECIALVALUESvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHSPECIALVALUESvalues fromValue(String v) {
      CDEBIRTHSPECIALVALUESvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEBIRTHSPECIALVALUESvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
