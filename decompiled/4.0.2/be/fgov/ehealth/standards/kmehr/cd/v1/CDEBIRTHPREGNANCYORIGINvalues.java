package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-PREGNANCYORIGINvalues"
)
@XmlEnum
public enum CDEBIRTHPREGNANCYORIGINvalues {
   @XmlEnumValue("spontaneous")
   SPONTANEOUS("spontaneous"),
   @XmlEnumValue("hormonal")
   HORMONAL("hormonal"),
   IVF("IVF"),
   ICSI("ICSI");

   private final String value;

   private CDEBIRTHPREGNANCYORIGINvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHPREGNANCYORIGINvalues fromValue(String v) {
      CDEBIRTHPREGNANCYORIGINvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHPREGNANCYORIGINvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
