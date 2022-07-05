package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-INNCLUSTERschemes"
)
@XmlEnum
public enum CDINNCLUSTERschemes {
   @XmlEnumValue("CD-INNCLUSTER")
   CD_INNCLUSTER("CD-INNCLUSTER"),
   @XmlEnumValue("CD-VMPGROUP")
   CD_VMPGROUP("CD-VMPGROUP");

   private final String value;

   private CDINNCLUSTERschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDINNCLUSTERschemes fromValue(String v) {
      CDINNCLUSTERschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDINNCLUSTERschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
