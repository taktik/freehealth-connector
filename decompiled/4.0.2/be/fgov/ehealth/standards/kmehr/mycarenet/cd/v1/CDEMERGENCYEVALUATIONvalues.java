package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

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
      CDEMERGENCYEVALUATIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEMERGENCYEVALUATIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
