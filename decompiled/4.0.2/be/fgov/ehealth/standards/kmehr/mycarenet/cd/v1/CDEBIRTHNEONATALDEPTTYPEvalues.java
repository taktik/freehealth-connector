package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-NEONATALDEPTTYPEvalues"
)
@XmlEnum
public enum CDEBIRTHNEONATALDEPTTYPEvalues {
   @XmlEnumValue("nstar")
   NSTAR("nstar"),
   @XmlEnumValue("nic")
   NIC("nic");

   private final String value;

   private CDEBIRTHNEONATALDEPTTYPEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHNEONATALDEPTTYPEvalues fromValue(String v) {
      CDEBIRTHNEONATALDEPTTYPEvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHNEONATALDEPTTYPEvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
