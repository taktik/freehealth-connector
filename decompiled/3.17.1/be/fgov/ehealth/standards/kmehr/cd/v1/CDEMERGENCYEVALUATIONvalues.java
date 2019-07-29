package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EMERGENCYEVALUATIONvalues"
)
@XmlEnum
public enum CDEMERGENCYEVALUATIONvalues {
   @XmlEnumValue("couldwaitafterwe")
   COULDWAITAFTERWE("couldwaitafterwe"),
   @XmlEnumValue("noturgent")
   NOTURGENT("noturgent"),
   @XmlEnumValue("urgent")
   URGENT("urgent"),
   @XmlEnumValue("lifethreathning")
   LIFETHREATHNING("lifethreathning");

   private final String value;

   private CDEMERGENCYEVALUATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEMERGENCYEVALUATIONvalues fromValue(String v) {
      CDEMERGENCYEVALUATIONvalues[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEMERGENCYEVALUATIONvalues c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
