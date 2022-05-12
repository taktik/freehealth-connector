package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-QUANTITYPREFIXvalues"
)
@XmlEnum
public enum CDQUANTITYPREFIXvalues {
   @XmlEnumValue("ana")
   ANA("ana"),
   @XmlEnumValue("anaad")
   ANAAD("anaad"),
   @XmlEnumValue("ad")
   AD("ad"),
   @XmlEnumValue("qs")
   QS("qs");

   private final String value;

   private CDQUANTITYPREFIXvalues(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDQUANTITYPREFIXvalues fromValue(String v) {
      CDQUANTITYPREFIXvalues[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDQUANTITYPREFIXvalues c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
