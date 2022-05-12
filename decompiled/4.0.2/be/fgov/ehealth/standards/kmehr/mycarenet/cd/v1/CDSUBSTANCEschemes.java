package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SUBSTANCEschemes"
)
@XmlEnum
public enum CDSUBSTANCEschemes {
   @XmlEnumValue("CD-INNCLUSTER")
   CD_INNCLUSTER("CD-INNCLUSTER"),
   @XmlEnumValue("CD-ATC")
   CD_ATC("CD-ATC"),
   @XmlEnumValue("CD-EAN")
   CD_EAN("CD-EAN"),
   @XmlEnumValue("CD-SUBSTANCE-CNK")
   CD_SUBSTANCE_CNK("CD-SUBSTANCE-CNK");

   private final String value;

   private CDSUBSTANCEschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSUBSTANCEschemes fromValue(String v) {
      CDSUBSTANCEschemes[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDSUBSTANCEschemes c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
