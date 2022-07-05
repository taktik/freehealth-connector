package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-LATERALITYvalues"
)
@XmlEnum
public enum CDBVTLATERALITYvalues {
   @XmlEnumValue("left")
   LEFT("left"),
   @XmlEnumValue("right")
   RIGHT("right"),
   @XmlEnumValue("odd")
   ODD("odd"),
   @XmlEnumValue("unknown")
   UNKNOWN("unknown");

   private final String value;

   private CDBVTLATERALITYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTLATERALITYvalues fromValue(String v) {
      CDBVTLATERALITYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDBVTLATERALITYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
