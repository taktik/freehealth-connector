package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TIMEUNITschemes"
)
@XmlEnum
public enum CDTIMEUNITschemes {
   @XmlEnumValue("CD-TIMEUNIT")
   CD_TIMEUNIT("CD-TIMEUNIT"),
   LOCAL("LOCAL");

   private final String value;

   private CDTIMEUNITschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTIMEUNITschemes fromValue(String v) {
      CDTIMEUNITschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTIMEUNITschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
