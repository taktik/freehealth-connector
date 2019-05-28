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
      CDBVTCONSERVATIONDELAYvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDBVTCONSERVATIONDELAYvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
