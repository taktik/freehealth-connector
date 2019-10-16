package be.fgov.ehealth.consultrn._1_0.core;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
   name = "GenderEnumType"
)
@XmlEnum
public enum GenderEnumType {
   MALE,
   FEMALE,
   UNKNOWN;

   public String value() {
      return this.name();
   }

   public static GenderEnumType fromValue(String v) {
      return valueOf(v);
   }
}
