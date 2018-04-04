package be.fgov.ehealth.etee.commons.core.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CheckDigitType"
)
@XmlEnum
public enum CheckDigitType {
   @XmlEnumValue("CHECK-MOD-97")
   CHECK_MOD_97("CHECK-MOD-97"),
   @XmlEnumValue("CHECK-97-MOD-97")
   CHECK_97_MOD_97("CHECK-97-MOD-97");

   private final String value;

   private CheckDigitType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CheckDigitType fromValue(String v) {
      CheckDigitType[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         CheckDigitType c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
