package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

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
      CDEBIRTHSPECIALVALUESvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHSPECIALVALUESvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
