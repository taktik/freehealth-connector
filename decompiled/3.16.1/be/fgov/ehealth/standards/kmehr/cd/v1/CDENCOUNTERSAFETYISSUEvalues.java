package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCOUNTERSAFETYISSUEvalues"
)
@XmlEnum
public enum CDENCOUNTERSAFETYISSUEvalues {
   @XmlEnumValue("verbal")
   VERBAL("verbal"),
   @XmlEnumValue("fysical")
   FYSICAL("fysical"),
   @XmlEnumValue("material")
   MATERIAL("material"),
   @XmlEnumValue("notificationtopolice")
   NOTIFICATIONTOPOLICE("notificationtopolice");

   private final String value;

   private CDENCOUNTERSAFETYISSUEvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCOUNTERSAFETYISSUEvalues fromValue(String v) {
      CDENCOUNTERSAFETYISSUEvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDENCOUNTERSAFETYISSUEvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
