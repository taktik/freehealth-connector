package be.cin.mycarenet._1_0.carenet.types;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "PeriodLengthTypeType"
)
@XmlEnum
public enum PeriodLengthTypeType {
   @XmlEnumValue("weeks")
   WEEKS("weeks"),
   @XmlEnumValue("months")
   MONTHS("months");

   private final String value;

   private PeriodLengthTypeType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static PeriodLengthTypeType fromValue(String v) {
      PeriodLengthTypeType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         PeriodLengthTypeType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
