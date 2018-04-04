package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "QuantityPrefix"
)
@XmlEnum
public enum QuantityPrefix {
   @XmlEnumValue("ana")
   ANA("ana"),
   @XmlEnumValue("ana ad")
   ANA_AD("ana ad"),
   @XmlEnumValue("ad")
   AD("ad"),
   @XmlEnumValue("qs")
   QS("qs");

   private final String value;

   private QuantityPrefix(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static QuantityPrefix fromValue(String v) {
      QuantityPrefix[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         QuantityPrefix c = arr$[i$];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
