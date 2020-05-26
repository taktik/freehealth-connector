package be.fgov.ehealth.insurability.core.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "SexType"
)
@XmlEnum
public enum SexType {
   @XmlEnumValue("male")
   MALE("male"),
   @XmlEnumValue("female")
   FEMALE("female");

   private final String value;

   private SexType(String v) {
      this.value = v;
   }

   public String value() {
      return this.value;
   }

   public static SexType fromValue(String v) {
      SexType[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         SexType c = var1[var3];
         if (c.value.equals(v)) {
            return c;
         }
      }

      throw new IllegalArgumentException(v);
   }
}
