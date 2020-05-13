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
      QuantityPrefix[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         QuantityPrefix c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
