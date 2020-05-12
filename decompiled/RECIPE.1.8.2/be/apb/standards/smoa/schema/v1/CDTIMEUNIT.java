package be.apb.standards.smoa.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "CD-TIMEUNIT"
)
@XmlEnum
public enum CDTIMEUNIT {
   @XmlEnumValue("a")
   A("a"),
   @XmlEnumValue("mo")
   MO("mo"),
   @XmlEnumValue("wk")
   WK("wk"),
   @XmlEnumValue("d")
   D("d"),
   @XmlEnumValue("hr")
   HR("hr"),
   @XmlEnumValue("min")
   MIN("min"),
   @XmlEnumValue("s")
   S("s"),
   @XmlEnumValue("ms")
   MS("ms"),
   @XmlEnumValue("us")
   US("us"),
   @XmlEnumValue("ns")
   NS("ns");

   private final String value;

   private CDTIMEUNIT(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static CDTIMEUNIT fromValue(String v) {
      CDTIMEUNIT[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CDTIMEUNIT c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
