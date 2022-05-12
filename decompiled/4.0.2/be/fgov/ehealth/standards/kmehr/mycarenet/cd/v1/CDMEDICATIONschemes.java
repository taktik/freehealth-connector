package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-MEDICATIONschemes"
)
@XmlEnum
public enum CDMEDICATIONschemes {
   @XmlEnumValue("CD-DRUG-CNK")
   CD_DRUG_CNK("CD-DRUG-CNK"),
   @XmlEnumValue("CD-VACCINE")
   CD_VACCINE("CD-VACCINE"),
   LOCAL("LOCAL");

   private final String value;

   private CDMEDICATIONschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDMEDICATIONschemes fromValue(String v) {
      CDMEDICATIONschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDMEDICATIONschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
