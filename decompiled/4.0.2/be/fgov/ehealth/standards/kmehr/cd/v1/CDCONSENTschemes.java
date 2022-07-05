package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-CONSENTschemes"
)
@XmlEnum
public enum CDCONSENTschemes {
   @XmlEnumValue("CD-CONSENTTYPE")
   CD_CONSENTTYPE("CD-CONSENTTYPE"),
   LOCAL("LOCAL");

   private final String value;

   private CDCONSENTschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDCONSENTschemes fromValue(String v) {
      CDCONSENTschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDCONSENTschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
