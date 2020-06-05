package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ORTHO-APPROACHvalues"
)
@XmlEnum
public enum CDORTHOAPPROACHvalues {
   @XmlEnumValue("lateral")
   LATERAL("lateral"),
   @XmlEnumValue("posterior")
   POSTERIOR("posterior"),
   @XmlEnumValue("anterior")
   ANTERIOR("anterior"),
   @XmlEnumValue("bytrochanterotomy")
   BYTROCHANTEROTOMY("bytrochanterotomy"),
   @XmlEnumValue("withfemoralosteotomy")
   WITHFEMORALOSTEOTOMY("withfemoralosteotomy"),
   @XmlEnumValue("other")
   OTHER("other"),
   @XmlEnumValue("subvastus")
   SUBVASTUS("subvastus"),
   @XmlEnumValue("midvastus")
   MIDVASTUS("midvastus"),
   @XmlEnumValue("parapatellarlateral")
   PARAPATELLARLATERAL("parapatellarlateral"),
   @XmlEnumValue("parapatellarmedial")
   PARAPATELLARMEDIAL("parapatellarmedial"),
   @XmlEnumValue("tuberositasosteotomie")
   TUBEROSITASOSTEOTOMIE("tuberositasosteotomie"),
   @XmlEnumValue("antelateral")
   ANTELATERAL("antelateral"),
   @XmlEnumValue("postlateral")
   POSTLATERAL("postlateral");

   private final String value;

   private CDORTHOAPPROACHvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDORTHOAPPROACHvalues fromValue(String v) {
      CDORTHOAPPROACHvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDORTHOAPPROACHvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
