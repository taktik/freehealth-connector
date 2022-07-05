package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-WEEKDAYvalues"
)
@XmlEnum
public enum CDWEEKDAYvalues {
   @XmlEnumValue("sunday")
   SUNDAY("sunday"),
   @XmlEnumValue("monday")
   MONDAY("monday"),
   @XmlEnumValue("tuesday")
   TUESDAY("tuesday"),
   @XmlEnumValue("wednesday")
   WEDNESDAY("wednesday"),
   @XmlEnumValue("thursday")
   THURSDAY("thursday"),
   @XmlEnumValue("friday")
   FRIDAY("friday"),
   @XmlEnumValue("saturday")
   SATURDAY("saturday");

   private final String value;

   private CDWEEKDAYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDWEEKDAYvalues fromValue(String v) {
      CDWEEKDAYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDWEEKDAYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
