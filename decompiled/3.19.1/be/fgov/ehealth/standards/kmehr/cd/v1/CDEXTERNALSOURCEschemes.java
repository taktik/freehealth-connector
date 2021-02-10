package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-EXTERNALSOURCEschemes"
)
@XmlEnum
public enum CDEXTERNALSOURCEschemes {
   @XmlEnumValue("CD-EXTERNALSOURCE")
   CD_EXTERNALSOURCE("CD-EXTERNALSOURCE"),
   LOCAL("LOCAL");

   private final String value;

   private CDEXTERNALSOURCEschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDEXTERNALSOURCEschemes fromValue(String v) {
      CDEXTERNALSOURCEschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDEXTERNALSOURCEschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
