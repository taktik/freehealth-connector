package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-UNITschemes"
)
@XmlEnum
public enum CDUNITschemes {
   @XmlEnumValue("CD-UNIT")
   CD_UNIT("CD-UNIT"),
   @XmlEnumValue("CD-CURRENCY")
   CD_CURRENCY("CD-CURRENCY"),
   UCUM("UCUM"),
   @XmlEnumValue("CD-TIMEUNIT")
   CD_TIMEUNIT("CD-TIMEUNIT");

   private final String value;

   private CDUNITschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDUNITschemes fromValue(String v) {
      CDUNITschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDUNITschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
