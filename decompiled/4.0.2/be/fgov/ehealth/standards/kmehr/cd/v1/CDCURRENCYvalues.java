package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CURRENCYvalues"
)
@XmlEnum
public enum CDCURRENCYvalues {
   @XmlEnumValue("eur")
   EUR("eur");

   private final String value;

   private CDCURRENCYvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCURRENCYvalues fromValue(String v) {
      CDCURRENCYvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDCURRENCYvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
