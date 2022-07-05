package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EMPLOYMENTSITUATIONvalues"
)
@XmlEnum
public enum CDEMPLOYMENTSITUATIONvalues {
   @XmlEnumValue("selfemployed")
   SELFEMPLOYED("selfemployed"),
   @XmlEnumValue("employed")
   EMPLOYED("employed"),
   @XmlEnumValue("civilservant")
   CIVILSERVANT("civilservant");

   private final String value;

   private CDEMPLOYMENTSITUATIONvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEMPLOYMENTSITUATIONvalues fromValue(String v) {
      CDEMPLOYMENTSITUATIONvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDEMPLOYMENTSITUATIONvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
