package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ENCRYPTION-METHODschemes"
)
@XmlEnum
public enum CDENCRYPTIONMETHODschemes {
   @XmlEnumValue("CD-ENCRYPTION-METHOD")
   CD_ENCRYPTION_METHOD("CD-ENCRYPTION-METHOD");

   private final String value;

   private CDENCRYPTIONMETHODschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDENCRYPTIONMETHODschemes fromValue(String v) {
      CDENCRYPTIONMETHODschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDENCRYPTIONMETHODschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
