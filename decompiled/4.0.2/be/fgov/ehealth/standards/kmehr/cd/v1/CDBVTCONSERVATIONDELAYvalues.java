package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-BVT-CONSERVATIONDELAYvalues"
)
@XmlEnum
public enum CDBVTCONSERVATIONDELAYvalues {
   @XmlEnumValue("lessthan30min")
   LESSTHAN_30_MIN("lessthan30min"),
   @XmlEnumValue("morethan30min")
   MORETHAN_30_MIN("morethan30min"),
   @XmlEnumValue("unknown")
   UNKNOWN("unknown");

   private final String value;

   private CDBVTCONSERVATIONDELAYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDBVTCONSERVATIONDELAYvalues fromValue(String v) {
      CDBVTCONSERVATIONDELAYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDBVTCONSERVATIONDELAYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
