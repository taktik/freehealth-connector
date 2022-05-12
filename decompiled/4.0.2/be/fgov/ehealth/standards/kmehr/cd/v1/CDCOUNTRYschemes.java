package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-COUNTRYschemes"
)
@XmlEnum
public enum CDCOUNTRYschemes {
   @XmlEnumValue("CD-COUNTRY")
   CD_COUNTRY("CD-COUNTRY"),
   @XmlEnumValue("CD-FED-COUNTRY")
   CD_FED_COUNTRY("CD-FED-COUNTRY");

   private final String value;

   private CDCOUNTRYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCOUNTRYschemes fromValue(String v) {
      CDCOUNTRYschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDCOUNTRYschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
