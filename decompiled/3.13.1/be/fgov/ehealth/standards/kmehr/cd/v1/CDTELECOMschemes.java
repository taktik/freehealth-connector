package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TELECOMschemes"
)
@XmlEnum
public enum CDTELECOMschemes {
   @XmlEnumValue("CD-ADDRESS")
   CD_ADDRESS("CD-ADDRESS"),
   @XmlEnumValue("CD-TELECOM")
   CD_TELECOM("CD-TELECOM");

   private final String value;

   private CDTELECOMschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTELECOMschemes fromValue(String v) {
      CDTELECOMschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDTELECOMschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
