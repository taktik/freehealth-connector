package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SEVERITYschemes"
)
@XmlEnum
public enum CDSEVERITYschemes {
   @XmlEnumValue("CD-SEVERITY")
   CD_SEVERITY("CD-SEVERITY"),
   @XmlEnumValue("CD-NYHA")
   CD_NYHA("CD-NYHA");

   private final String value;

   private CDSEVERITYschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSEVERITYschemes fromValue(String v) {
      CDSEVERITYschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDSEVERITYschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
