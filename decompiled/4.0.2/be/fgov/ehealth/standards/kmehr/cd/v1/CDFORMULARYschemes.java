package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-FORMULARYschemes"
)
@XmlEnum
public enum CDFORMULARYschemes {
   @XmlEnumValue("CD-FORMULARY")
   CD_FORMULARY("CD-FORMULARY"),
   @XmlEnumValue("CD-FORMULARYREFERENCE")
   CD_FORMULARYREFERENCE("CD-FORMULARYREFERENCE");

   private final String value;

   private CDFORMULARYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDFORMULARYschemes fromValue(String v) {
      CDFORMULARYschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDFORMULARYschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
