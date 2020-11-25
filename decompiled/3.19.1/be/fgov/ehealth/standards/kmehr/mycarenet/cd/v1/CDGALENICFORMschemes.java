package be.fgov.ehealth.standards.kmehr.mycarenet.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-GALENICFORMschemes"
)
@XmlEnum
public enum CDGALENICFORMschemes {
   @XmlEnumValue("CD-DRUG-PRESENTATION")
   CD_DRUG_PRESENTATION("CD-DRUG-PRESENTATION"),
   @XmlEnumValue("CD-MAGISTRALFORM")
   CD_MAGISTRALFORM("CD-MAGISTRALFORM");

   private final String value;

   private CDGALENICFORMschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDGALENICFORMschemes fromValue(String v) {
      CDGALENICFORMschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDGALENICFORMschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
