package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EBIRTH-DELIVERYWAYvalues"
)
@XmlEnum
public enum CDEBIRTHDELIVERYWAYvalues {
   @XmlEnumValue("spontaneous")
   SPONTANEOUS("spontaneous"),
   @XmlEnumValue("vacuum-extraction")
   VACUUM_EXTRACTION("vacuum-extraction"),
   @XmlEnumValue("forceps")
   FORCEPS("forceps"),
   @XmlEnumValue("primary-caesarean")
   PRIMARY_CAESAREAN("primary-caesarean"),
   @XmlEnumValue("secondary-caesarean")
   SECONDARY_CAESAREAN("secondary-caesarean"),
   @XmlEnumValue("vaginal breech")
   VAGINAL_BREECH("vaginal breech");

   private final String value;

   private CDEBIRTHDELIVERYWAYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEBIRTHDELIVERYWAYvalues fromValue(String v) {
      CDEBIRTHDELIVERYWAYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEBIRTHDELIVERYWAYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
