package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-SITEschemes"
)
@XmlEnum
public enum CDSITEschemes {
   @XmlEnumValue("CD-SITE")
   CD_SITE("CD-SITE"),
   @XmlEnumValue("CD-DEFIB-SITE")
   CD_DEFIB_SITE("CD-DEFIB-SITE");

   private final String value;

   private CDSITEschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDSITEschemes fromValue(String v) {
      CDSITEschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDSITEschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
