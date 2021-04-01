package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-HEADINGschemes"
)
@XmlEnum
public enum CDHEADINGschemes {
   @XmlEnumValue("CD-HEADING")
   CD_HEADING("CD-HEADING"),
   @XmlEnumValue("CD-HEADING-LAB")
   CD_HEADING_LAB("CD-HEADING-LAB"),
   @XmlEnumValue("CD-SITE")
   CD_SITE("CD-SITE"),
   LOCAL("LOCAL"),
   @XmlEnumValue("CD-HEADING-REG")
   CD_HEADING_REG("CD-HEADING-REG");

   private final String value;

   private CDHEADINGschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDHEADINGschemes fromValue(String v) {
      CDHEADINGschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDHEADINGschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
