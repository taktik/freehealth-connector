package be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-ACCESSRIGHTschemes"
)
@XmlEnum
public enum CDACCESSRIGHTschemes {
   @XmlEnumValue("CD-ACCESSRIGHT")
   CD_ACCESSRIGHT("CD-ACCESSRIGHT"),
   LOCAL("LOCAL");

   private final String value;

   private CDACCESSRIGHTschemes(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDACCESSRIGHTschemes fromValue(String v) {
      CDACCESSRIGHTschemes[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CDACCESSRIGHTschemes c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
