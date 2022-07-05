package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-CHILDPOSITIONvalues"
)
@XmlEnum
public enum CDEBIRTHCHILDPOSITIONvalues {
   @XmlEnumValue("head-down")
   HEAD_DOWN("head-down"),
   @XmlEnumValue("other-head")
   OTHER_HEAD("other-head"),
   @XmlEnumValue("breech")
   BREECH("breech"),
   @XmlEnumValue("transverse")
   TRANSVERSE("transverse");

   private final String value;

   private CDEBIRTHCHILDPOSITIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHCHILDPOSITIONvalues fromValue(String v) {
      CDEBIRTHCHILDPOSITIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHCHILDPOSITIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
