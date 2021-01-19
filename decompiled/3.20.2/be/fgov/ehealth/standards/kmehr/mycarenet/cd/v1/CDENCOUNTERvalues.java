package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCOUNTERvalues"
)
@XmlEnum
public enum CDENCOUNTERvalues {
   @XmlEnumValue("homevisit")
   HOMEVISIT("homevisit"),
   @XmlEnumValue("consultation")
   CONSULTATION("consultation"),
   @XmlEnumValue("telephonicconsultation")
   TELEPHONICCONSULTATION("telephonicconsultation"),
   @XmlEnumValue("emergency")
   EMERGENCY("emergency"),
   @XmlEnumValue("hospital")
   HOSPITAL("hospital"),
   @XmlEnumValue("oneday")
   ONEDAY("oneday"),
   @XmlEnumValue("technical")
   TECHNICAL("technical"),
   @XmlEnumValue("resthomevisit")
   RESTHOMEVISIT("resthomevisit"),
   @XmlEnumValue("consult")
   CONSULT("consult"),
   @XmlEnumValue("multidisciplinaryconsult")
   MULTIDISCIPLINARYCONSULT("multidisciplinaryconsult");

   private final String value;

   private CDENCOUNTERvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCOUNTERvalues fromValue(String v) {
      CDENCOUNTERvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDENCOUNTERvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
